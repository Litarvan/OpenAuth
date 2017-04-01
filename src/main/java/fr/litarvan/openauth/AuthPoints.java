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

/**
 * The server Auth Points
 *
 * <p>
 *     Contains the pages url of a server
 * </p>
 *
 * @version 1.0.4
 * @author Litarvan
 */
public class AuthPoints {

    /**
     * The Mojang auth server auth points
     */
    public static final AuthPoints NORMAL_AUTH_POINTS = new AuthPoints("authenticate", "refresh", "validate", "signout", "invalidate");

    /**
     * The server authenticate point
     */
    private String authenticatePoint;

    /**
     * The server refresh point
     */
    private String refreshPoint;

    /**
     * The server validate point
     */
    private String validatePoint;

    /**
     * The server signout point
     */
    private String signoutPoint;

    /**
     * The server invalidate point
     */
    private String invalidatePoint;

    /**
     * AuthPoints constructor
     *
     * @param authenticatePoint
     *            Authenticate point
     * @param refreshPoint
     *            Refresh point
     * @param validatePoint
     *            Validate point
     * @param signoutPoint
     *            Signout point
     * @param invalidatePoint
     *            Invalidate point
     */
    public AuthPoints(String authenticatePoint, String refreshPoint, String validatePoint, String signoutPoint, String invalidatePoint) {
        this.authenticatePoint = authenticatePoint;
        this.refreshPoint = refreshPoint;
        this.validatePoint = validatePoint;
        this.signoutPoint = signoutPoint;
        this.invalidatePoint = invalidatePoint;
    }

    /**
     * Returns the server authenticate point
     *
     * @return The authenticate point
     */
    public String getAuthenticatePoint() {
        return this.authenticatePoint;
    }

    /**
     * Returns the server refresh point
     *
     * @return The refresh point
     */
    public String getRefreshPoint() {
        return this.refreshPoint;
    }

    /**
     * Returns the server validate point
     *
     * @return The validate point
     */
    public String getValidatePoint() {
        return this.validatePoint;
    }

    /**
     * Returns the server signout point
     *
     * @return The signout point
     */
    public String getSignoutPoint() {
        return this.signoutPoint;
    }

    /**
     * Returns the server invalidate point
     *
     * @return The invalidate point
     */
    public String getInvalidatePoint() {
        return this.invalidatePoint;
    }

}
