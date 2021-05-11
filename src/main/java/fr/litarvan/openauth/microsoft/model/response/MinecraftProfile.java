/*
 * Copyright 2015-2021 Adrien 'Litarvan' Navratil
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
package fr.litarvan.openauth.microsoft.model.response;

/**
 * Minecraft player profile
 *
 * <p>
 *     Represents a Minecraft player profile data. UUID is {@link #id} and username is {@link #name}.
 * </p>
 *
 * @version 1.1.0
 * @author Litarvan
 */
public class MinecraftProfile
{
    private final String id;
    private final String name;
    private final MinecraftSkin[] skins;

    public MinecraftProfile(String id, String name, MinecraftSkin[] skins)
    {
        this.id = id;
        this.name = name;
        this.skins = skins;
    }

    /**
     * @return The player Minecraft UUID
     */
    public String getId()
    {
        return id;
    }

    /**
     * @return The player Minecraft username
     */
    public String getName()
    {
        return name;
    }

    public MinecraftSkin[] getSkins()
    {
        return skins;
    }

    public static class MinecraftSkin
    {
        private final String id;
        private final String state;
        private final String url;
        private final String variant;
        private final String alias;

        public MinecraftSkin(String id, String state, String url, String variant, String alias)
        {
            this.id = id;
            this.state = state;
            this.url = url;
            this.variant = variant;
            this.alias = alias;
        }

        public String getId()
        {
            return id;
        }

        public String getState()
        {
            return state;
        }

        public String getUrl()
        {
            return url;
        }

        public String getVariant()
        {
            return variant;
        }

        public String getAlias()
        {
            return alias;
        }
    }
}
