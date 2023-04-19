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

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private boolean completed;

    public LoginFrame()
    {
        this.setTitle("Microsoft Authentication");
        this.setSize(750, 750);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
                if(!completed)
                    future.complete(null);
            }
        });

        Platform.runLater(() -> this.init(url));
        return this.future;
    }

    protected void init(String url)
    {
        WebView webView = new WebView();
        JFXPanel content = (JFXPanel) this.getContentPane();

        content.setScene(new Scene(webView, this.getWidth(), this.getHeight()));

        webView.getEngine().locationProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains("access_token")) {
                this.future.complete(newValue);
                completed = true;
                this.dispose();
            }
        });
        webView.getEngine().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
        webView.getEngine().load(url);

        this.setVisible(true);
    }
}
