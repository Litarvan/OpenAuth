/*
 * Copyright 2015 TheShark34
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
package fr.theshark34.openauth;

import com.google.gson.Gson;
import fr.theshark34.openauth.model.AuthAgent;
import fr.theshark34.openauth.model.AuthError;
import fr.theshark34.openauth.model.request.*;
import fr.theshark34.openauth.model.response.AuthResponse;
import fr.theshark34.openauth.model.response.RefreshResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The Authenticator
 *
 * <p>
 *     The mainc class of the lib, use it to authenticate a user !
 * </p>
 *
 * @version 1.0.0-SNAPSHOT
 * @author TheShark34
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
     * @return Instance of the given reponse model if it not null
     */
    private Object sendRequest(Object request, Class<?> model, String authPoint) throws AuthenticationException {
        Gson gson = new Gson();
        String response = null;
        try {
            response = sendPostRequest(this.authURL + authPoint, gson.toJson(request));
        } catch (IOException e) {
            AuthError errorModel = gson.fromJson(e.getMessage(), AuthError.class);

            if(errorModel == null)
                errorModel = new AuthError("", e.getMessage(), "");

            throw new AuthenticationException(errorModel);
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
     * @return The request response
     */
    private String sendPostRequest(String url, String json) throws IOException {
        URL serverURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) serverURL.openConnection();
        connection.setRequestMethod("POST");

        // Sending post request
        connection.setDoOutput(true);
        connection.addRequestProperty("Content-Type", "application/json");
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(json);
        wr.flush();
        wr.close();

        connection.connect();

        int responseCode = connection.getResponseCode();

        if(responseCode == 204) {
            connection.disconnect();
            return null;
        }

        InputStream is = null;
        if(responseCode == 200)
            is = connection.getInputStream();
        else
            is = connection.getErrorStream();

        String response = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        response = br.readLine();
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.disconnect();

        if(responseCode == 200)
            return response;

        throw new IOException(response);
    }

}
