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
package fr.litarvan.openauth;

import fr.litarvan.openauth.model.AuthError;

/**
 * Authentication exceptions
 *
 * @version 1.0.4
 * @author Litarvan
 */
public class AuthenticationException extends Exception {

    /**
     * The given JSON model instance of the error
     */
    private AuthError model;

    /**
     * Create a new Authentication Exception
     *
     * @param model
     *            The given JSON model instance of the error
     */
    public AuthenticationException(AuthError model) {
        super(model.getErrorMessage());
        this.model = model;
    }

    /**
     * Returns the given JSON model instance of the error
     *
     * @return The error model
     */
    public AuthError getErrorModel() {
        return model;
    }
}