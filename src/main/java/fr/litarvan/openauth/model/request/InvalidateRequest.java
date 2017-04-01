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
package fr.litarvan.openauth.model.request;

/**
 * JSON Model of an invalidate request
 *
 * @version 1.0.4
 * @author Litarvan
 */
public class InvalidateRequest {

    /**
     * The access token you want to invalidate
     */
    private String accessToken;

    /**
     * The client token associated with the access token
     */
    private String clientToken;

    /**
     * Invalidate Request constructor
     *
     * @param accessToken
     *            The access token you want to invalidate
     * @param clientToken
     *            The client token associated with the access token
     */
    public InvalidateRequest(String accessToken, String clientToken) {
        this.accessToken = accessToken;
        this.clientToken = clientToken;
    }

    /**
     * Sets a new access token (That you want to invalidate)
     *
     * @param accessToken
     *            The new access token
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Returns the access token (Given by the constructor or the setter)
     *
     * @return The given access token
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * Sets a new client token (Need to be associated with the access token)
     *
     * @param clientToken
     *            The new client token
     */
    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    /**
     * Returns the client token (Given by the constructor or the setter)
     *
     * @return The given client token
     */
    public String getClientToken() {
        return clientToken;
    }

}
