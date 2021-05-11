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

public class XboxLoginRequest<T>
{
    private final T Properties;
    private final String RelyingParty;
    private final String TokenType;

    public XboxLoginRequest(T Properties, String RelyingParty, String TokenType)
    {
        this.Properties = Properties;
        this.RelyingParty = RelyingParty;
        this.TokenType = TokenType;
    }

    public T getProperties()
    {
        return Properties;
    }

    public String getSiteName()
    {
        return RelyingParty;
    }

    public String getTokenType()
    {
        return TokenType;
    }
}
