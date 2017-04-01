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
package fr.litarvan.openauth.model;

/**
 * JSON model of an error
 *
 * @version 1.0.4
 * @author Litarvan
 */
public class AuthError {

    /**
     * Short description of the error
     */
    private String error;

    /**
     * Longer description wich can be shown to the user
     */
    private String errorMessage;

    /**
     * Cause of the error (optional)
     */
    private String cause;

    /**
     * Auth Error constructor
     *
     * @param error
     *            Short description of the error
     * @param errorMessage
     *            Longer description wich can be shown to the user
     * @param cause
     *            Cause of the error
     */
    public AuthError(String error, String errorMessage, String cause) {
        this.error = error;
        this.errorMessage = errorMessage;
        this.cause = cause;
    }

    /**
     * Returns the error (Short description of the error)
     *
     * @return The error
     */
    public String getError() {
        return error;
    }

    /**
     * Returns the error message (Longer description of the error)
     *
     * @return The error message
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Returns the cause of the error
     *
     * @return The cause
     */
    public String getCause() {
        return cause;
    }

}
