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
package fr.litarvan.openauth;

import com.google.gson.Gson;
import fr.litarvan.openauth.model.AuthAgent;
import fr.litarvan.openauth.model.request.*;
import fr.litarvan.openauth.model.response.*;
import fr.litarvan.openauth.model.AuthError;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * The Authenticator
 *
 * <p>
 *     The main class of the lib, use it to authenticate a user !
 * </p>
 *
 * @version 1.0.4
 * @author Litarvan
 */
public class Authenticator {

    /**
     * The Mojang official auth server
     */
    public static final String MOJANG_AUTH_URL = "https://authserver.mojang.com/";

    /**
     * The auth server URL
     */
    private String authURL;

    /**
     * The server auth points
     */
    private AuthPoints authPoints;

    /**
     * Create an authenticator
     *
     * @param authURL
     *            The auth server URL
     *
     * @param authPoints
     *            The URIs of the multiple requests
     */
    public Authenticator(String authURL, AuthPoints authPoints) {
        this.authURL = authURL;
        this.authPoints = authPoints;
    }

    /**
     * Authenticates an user using his password.
     *
     * @param agent
     *            The auth agent (optional)
     * @param username
     *            User mojang account name
     * @param password
     *            User mojang account password
     * @param clientToken
     *            The client token (optional, like a key for the access token)
     *
     * @throws AuthenticationException If the server returned an error as a JSON
     *
     * @return The response sent by the server (parsed from a JSON)
     */
    public AuthResponse authenticate(AuthAgent agent, String username, String password, String clientToken) throws AuthenticationException {
        AuthRequest request = new AuthRequest(agent, username, password, clientToken);
        return (AuthResponse) sendRequest(request, AuthResponse.class, authPoints.getAuthenticatePoint());
    }

    /**
     * Refresh a valid access token. It can be uses to keep a user logged in between gaming sessions
     * and is preferred over storing the user's password in a file.
     *
     * @param accessToken
     *            The saved access token
     * @param clientToken
     *            The saved client token (need to be the same used when authenticated to get the acces token)
     *
     * @throws AuthenticationException If the server returned an error as a JSON
     *
     * @return The response sent by the server (parsed from a JSON)
     */
    public RefreshResponse refresh(String accessToken, String clientToken) throws AuthenticationException {
        RefreshRequest request = new RefreshRequest(accessToken, clientToken);
        return (RefreshResponse) sendRequest(request, RefreshResponse.class, authPoints.getRefreshPoint());
    }

    /**
     * Check if an access token is a valid session token with a currently-active session.
     * Note: this method will not respond successfully to all currently-logged-in sessions,
     * just the most recently-logged-in for each user. It is intended to be used by servers to validate
     * that a user should be connecting (and reject users who have logged in elsewhere since starting Minecraft),
     * NOT to auth that a particular session token is valid for authentication purposes.
     * To authenticate a user by session token, use the refresh verb and catch resulting errors.
     *
     * @param accessToken
     *            The access token to check
     *
     * @throws AuthenticationException If the server returned an error as a JSON
     */
    public void validate(String accessToken) throws AuthenticationException {
        ValidateRequest request = new ValidateRequest(accessToken);
        sendRequest(request, null, authPoints.getValidatePoint());
    }

    /**
     * Invalidates accessTokens using an account's username and password
     *
     * @param username
     *            User mojang account name
     * @param password
     *            User mojang account password
     *
     * @throws AuthenticationException If the server returned an error as a JSON
     */
    public void signout(String username, String password) throws AuthenticationException {
        SignoutRequest request = new SignoutRequest(username, password);
        sendRequest(request, null, authPoints.getSignoutPoint());
    }

    /**
     * Invalidates accessTokens using a client/access token pair
     *
     * @param accessToken
     *            Valid access token to invalidate
     * @param clientToken
     *            Client token used when authenticated to get the access token
     *
     * @throws AuthenticationException If the server returned an error as a JSON
     */
    public void invalidate(String accessToken, String clientToken) throws AuthenticationException {
        InvalidateRequest request = new InvalidateRequest(accessToken, clientToken);
        sendRequest(request, null, authPoints.getInvalidatePoint());
    }

    /**
     * Send a request to the auth server
     *
     * @param request
     *            The auth request to send
     * @param model
     *            The model of the reponse
     * @param authPoint
     *            The auth point of the request
     * @throws AuthenticationException
     *            If it returned an error or the request failed
     *
     * @throws AuthenticationException If the server returned an error as a JSON
     *
     * @return Instance of the given reponse model if it not null
     */
    private Object sendRequest(Object request, Class<?> model, String authPoint) throws AuthenticationException {
        Gson gson = new Gson();
        String response;

        try {
            response = sendPostRequest(this.authURL + authPoint, gson.toJson(request));
        } catch (IOException e) {
            throw new AuthenticationException(new AuthError("Can't send the request : " + e.getClass().getName(), e.getMessage(), "Unknown"));
        }

        if(model != null)
            return gson.fromJson(response, model);
        else
            return null;
    }

    /**
     * Sends a post request of a json
     *
     * @param url
     *            The url to send the request
     * @param json
     *            The json to send
     * @throws IOException
     *            If it failed
     *
     * @throws AuthenticationException If the request returned an error JSON or not a JSON
     *
     * @return The request response
     */
    private String sendPostRequest(String url, String json) throws AuthenticationException, IOException {
        byte[] jsonBytes = json.getBytes(StandardCharsets.UTF_8);
        URL serverURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) serverURL.openConnection();
        connection.setRequestMethod("POST");

        // Sending post request
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        connection.setRequestProperty("Content-Length", String.valueOf(jsonBytes.length));
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(jsonBytes, 0, jsonBytes.length);
        wr.flush();
        wr.close();

        connection.connect();

        int responseCode = connection.getResponseCode();

        if(responseCode == 204) {
            connection.disconnect();
            return null;
        }

        InputStream is;
        if(responseCode == 200)
            is = connection.getInputStream();
        else
            is = connection.getErrorStream();

        String response;

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        response = br.readLine();
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.disconnect();

        while (response != null && response.startsWith("\uFEFF"))
            response = response.substring(1);

        if (responseCode != 200) {
            Gson gson = new Gson();

            if (response != null && !response.startsWith("{"))
                throw new AuthenticationException(new AuthError("Internal server error", response, "Remote"));

            throw new AuthenticationException(gson.fromJson(response, AuthError.class));
        }

        return response;
    }

}
