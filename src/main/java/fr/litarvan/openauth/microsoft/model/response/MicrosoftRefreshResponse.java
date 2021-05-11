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

public class MicrosoftRefreshResponse
{
    private final String token_type;
    private final long expires_in;
    private final String scope;
    private final String access_token;
    private final String refresh_token;
    private final String user_id;

    public MicrosoftRefreshResponse(String token_type, long expires_in, String scope, String access_token, String refresh_token, String user_id)
    {
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.scope = scope;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.user_id = user_id;
    }

    public String getTokenType()
    {
        return token_type;
    }

    public long getExpiresIn()
    {
        return expires_in;
    }

    public String getScope()
    {
        return scope;
    }

    public String getAccessToken()
    {
        return access_token;
    }

    public String getRefreshToken()
    {
        return refresh_token;
    }

    public String getUserId()
    {
        return user_id;
    }
}
