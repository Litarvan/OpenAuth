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

/**
 * The OpenAuth Exception
 *
 * @version 1.0.0-RELEASE
 * @author TheShark34
 */
public class OpenAuthException extends Exception {

    /**
     * The different types of error
     */
    public enum ErrorType {
        INVALID_CREDENTIALS,
        CANT_CONNECT_TO_SERVER
    }

    /**
     * The type of the error
     */
    private ErrorType type;

    /**
     * The error message
     */
    private String message;

    /**
     * OpenAuth Exception error
     *
     * @param type
     *            The type of the error
     * @param message
     *            The error message
     */
    public OpenAuthException(ErrorType type, String message) {
        this.type = type;
        this.message = message;
    }

    /**
     * Returns the type of the error
     *
     * @return The error type
     */
    public ErrorType getType() {
        return this.type;
    }

    /**
     * Returns the error message
     *
     * @return The error message
     */
    public String getMessage() {
        return this.message;
    }

}
