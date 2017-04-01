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
 * JSON model of a profile
 *
 * @version 1.0.4
 * @author Litarvan
 */
public class AuthProfile {

    /**
     * The profile name
     */
    private String name;

    /**
     * The profile UUID
     */
    private String id;

    /**
     * Blank auth profile
     */
    public AuthProfile() {
        this.name = "";
        this.id = "";
    }

    /**
     * Normal auth profile
     *
     * @param name
     *            The profile name
     * @param id
     *            The profile UUID
     */
    public AuthProfile(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Returns the name of the profile
     *
     * @return The profile name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the profile UUID
     *
     * @return The profile UUID
     */
    public String getId() {
        return this.id;
    }

}
