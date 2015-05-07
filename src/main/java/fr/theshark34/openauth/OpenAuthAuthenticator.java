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

import java.net.URL;

/**
 * The OpenAuth Authenticator
 *
 * <p>
 *     Use this class to auth a player using an OpenAuth server.
 * </p>
 *
 * @version 1.0.0-RELEASE
 * @author TheShark34
 */
public class OpenAuthAuthenticator {

    /**
     * The OpenAuth server URL
     */
    private URL serverURL;

    /**
     * OpenAuth Authenticator constructor
     *
     * @param serverURL
     *            The URL of your OpenAuth server
     */
    public OpenAuthAuthenticator(URL serverURL) {
        this.serverURL = serverURL;
    }

    /**
     * Sets a new OpenAuth Server URL
     *
     * @param serverURL
     *            The new OpenAuth Server URL
     */
    public void setServerURL(URL serverURL) {
        this.serverURL = serverURL;
    }

    /**
     * Returns the OpenAuth Server URL (Given with the constructor or the setter)
     *
     * @return The given OpenAuth Server URL
     */
    public URL getServerURL() {
        return this.serverURL;
    }

    /**
     * Authenticate a user
     *
     * @param username
     *            The username of the user
     * @param password
     *            The password of the user
     */
    public void authenticate(String username, String password) throws OpenAuthException {
        // TODO: Authenticate method :p
    }

}
