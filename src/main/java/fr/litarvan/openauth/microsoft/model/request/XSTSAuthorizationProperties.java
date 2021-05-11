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
package fr.litarvan.openauth.microsoft.model.request;

public class XSTSAuthorizationProperties
{
    private final String SandboxId;
    private final String[] UserTokens;

    public XSTSAuthorizationProperties(String SandboxId, String[] UserTokens)
    {
        this.SandboxId = SandboxId;
        this.UserTokens = UserTokens;
    }

    public String getSandboxId()
    {
        return SandboxId;
    }

    public String[] getUserTokens()
    {
        return UserTokens;
    }
}
