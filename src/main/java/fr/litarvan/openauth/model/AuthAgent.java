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
 * JSON model of an auth agent
 *
 * @version 1.0.4
 * @author Litarvan
 */
public class AuthAgent {

    /**
     * The Minecraft auth agent
     */
    public static final AuthAgent MINECRAFT = new AuthAgent("Minecraft", 1);

    /**
     * The Scroll auth agent
     */
    public static final AuthAgent SCROLLS = new AuthAgent("Scrolls", 1);

    /**
     * The agent name
     */
    private String name;

    /**
     * The agent version
     */
    private int version;

    /**
     * Agent constructor
     *
     * @param name
     *            The name of the agent
     * @param version
     *            The version of the agent (1 by default)
     */
    public AuthAgent(String name, int version) {
        this.name = name;
        this.version = version;
    }

    /**
     * Sets a new name
     *
     * @param name
     *            The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the agent
     *
     * @return The agent name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets a new version
     *
     * @param version
     *            The new version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Returns the version of the agent
     *
     * @return The agent version
     */
    public int getVersion() {
        return this.version;
    }

}
