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
package fr.litarvan.openauth.model.response;

import fr.litarvan.openauth.model.AuthProfile;

/**
 * JSON Model of an authentication response
 *
 * @version 1.0.4
 * @author Litarvan
 */
public class AuthResponse {

    /**
     * The access token
     */
    private String accessToken;

    /**
     * The client token (same as the one given by the request)
     */
    private String clientToken;

    /**
     * All available profiles
     */
    private AuthProfile[] availableProfiles;

    /**
     * The current selected profile from the agent
     */
    private AuthProfile selectedProfile;

    /**
     * Auth Response constructor
     *
     * @param accessToken
     *            The access token
     * @param clientToken
     *            The client token (same as the one given by the request)
     * @param availableProfiles
     *            All available profiles
     * @param selectedProfile
     *            The current selected profile from the agent
     */
    public AuthResponse(String accessToken, String clientToken, AuthProfile[] availableProfiles, AuthProfile selectedProfile) {
        this.accessToken = accessToken;
        this.clientToken = clientToken;
        this.availableProfiles = availableProfiles;
        this.selectedProfile = selectedProfile;
    }

    /**
     * Returns the access token
     *
     * @return The access token
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * Returns the client token (same as the one given by the request)
     *
     * @return The client token
     */
    public String getClientToken() {
        return this.clientToken;
    }

    /**
     * Returns the available profiles
     *
     * @return The available profiles
     */
    public AuthProfile[] getAvailableProfiles() {
        return this.availableProfiles;
    }

    /**
     * Returns the selected profile from the agent
     *
     * @return The selected profile
     */
    public AuthProfile getSelectedProfile() {
        return this.selectedProfile;
    }

}
