/*
 * Copyright 2015-2021 Adrien 'Litarvan' Navratil
 *
 * This file is part of OpenAuth.

 * OpenAuth is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenAuth is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OpenAuth.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.litarvan.openauth.microsoft;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import sun.net.www.protocol.https.Handler;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.CompletableFuture;

/*
 * Had to use Swing here, JavaFX is meant to have an 'Application' but only one can exist.
 * Creating one would break compatibility with JavaFX apps (which already have their own
 * class), and letting the user do so would break compatibility with Swing apps.
 *
 * This method makes the frame compatible with pretty much everything.
 */

public class LoginFrame extends JFrame
{
    private CompletableFuture<String> future;

    public LoginFrame()
    {
        this.setTitle("Connexion à Microsoft");
        this.setSize(750, 750);
        this.setLocationRelativeTo(null);

        this.setContentPane(new JFXPanel());
    }

    public CompletableFuture<String> start(String url)
    {
        if (this.future != null) {
            return this.future;
        }

        this.future = new CompletableFuture<>();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                future.completeExceptionally(new MicrosoftAuthenticationException("User closed the authentication window"));
            }
        });

        Platform.runLater(() -> this.init(url));
        return this.future;
    }

    protected void init(String url)
    {
        try {
            overrideFactory();
        } catch (Throwable ignored) {
            // If the handler was already defined, we can safely ignore this.
            // If it isn't the right one, we can't override it anyway.
        }

        WebView webView = new WebView();
        JFXPanel content = (JFXPanel) this.getContentPane();

        content.setScene(new Scene(webView, this.getWidth(), this.getHeight()));

        webView.getEngine().locationProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains("access_token")) {
                this.setVisible(false);
                this.future.complete(newValue);
            }
        });
        webView.getEngine().load(url);

        this.setVisible(true);
    }

    protected static void overrideFactory()
    {
        URL.setURLStreamHandlerFactory(protocol -> {
            if ("https".equals(protocol)) {
                return new Handler()
                {
                    @Override
                    protected URLConnection openConnection(URL url) throws IOException
                    {
                        return openConnection(url, null);
                    }

                    @Override
                    protected URLConnection openConnection(URL url, Proxy proxy) throws IOException
                    {
                        HttpURLConnection connection = (HttpURLConnection) super.openConnection(url, proxy);

                        if (("login.microsoftonline.com".equals(url.getHost()) && url.getPath().endsWith("/oauth2/authorize"))
                            || ("login.live.com".equals(url.getHost()) && "/oauth20_authorize.srf".equals(url.getPath()))
                            || ("login.live.com".equals(url.getHost()) && "/ppsecure/post.srf".equals(url.getPath()))
                            || ("login.microsoftonline.com".equals(url.getHost()) && "/login.srf".equals(url.getPath()))
                            || ("login.microsoftonline.com".equals(url.getHost()) && url.getPath().endsWith("/login"))
                            || ("login.microsoftonline.com".equals(url.getHost()) && url.getPath().endsWith("/SAS/ProcessAuth"))
                            || ("login.microsoftonline.com".equals(url.getHost()) && url.getPath().endsWith("/federation/oauth2"))
                            || ("login.microsoftonline.com".equals(url.getHost()) && url.getPath().endsWith("/oauth2/v2.0/authorize"))) {
                            return new MicrosoftPatchedHttpURLConnection(url, connection);
                        }

                        return connection;
                    }
                };
            }

            return null;
        });
    }
}
