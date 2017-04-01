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
 * JSON Model of an validate request
 *
 * @version 1.0.4
 * @author Litarvan
 */
public class ValidateRequest {

    /**
     * The access token that you want to validate
     */
    private String accessToken;

    /**
     * Validate Request constructor
     *
     * @param accessToken
     *            The access token that you want to validate
     */
    public ValidateRequest(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Sets a new access token
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
        return accessToken;
    }

}
