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
 * JSON Model of an signout request
 *
 * @version 1.0.4
 * @author Litarvan
 */
public class SignoutRequest {

    /**
     * The username of the player that you want to signout
     */
    private String username;

    /**
     * The password of the player that you want to signout
     */
    private String password;

    /**
     * Signout Request constructor
     *
     * @param username
     *            The username of the player that you want to signout
     * @param password
     *            The password of the player that you want to signout
     */
    public SignoutRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Sets a new username (Of the player that you want to signout)
     *
     * @param username
     *            The new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the username (Given by the constructor or the setter)
     *
     * @return The given username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets a new password (Of the player that you want to signout)
     *
     * @param password
     *            The new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the password (Given by the constructor or the setter)
     *
     * @return The given password
     */
    public String getPassword() {
        return password;
    }

}
