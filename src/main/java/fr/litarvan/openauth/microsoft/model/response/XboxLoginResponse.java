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

public class XboxLoginResponse
{
    private final String IssueInstant;
    private final String NotAfter;
    private final String Token;
    private final XboxLiveLoginResponseClaims DisplayClaims;

    public XboxLoginResponse(String IssueInstant, String NotAfter, String Token, XboxLiveLoginResponseClaims DisplayClaims)
    {
        this.IssueInstant = IssueInstant;
        this.NotAfter = NotAfter;
        this.Token = Token;
        this.DisplayClaims = DisplayClaims;
    }

    public String getIssueInstant()
    {
        return IssueInstant;
    }

    public String getNotAfter()
    {
        return NotAfter;
    }

    public String getToken()
    {
        return Token;
    }

    public XboxLiveLoginResponseClaims getDisplayClaims()
    {
        return DisplayClaims;
    }

    public static class XboxLiveLoginResponseClaims
    {
        private final XboxLiveUserInfo[] xui;

        public XboxLiveLoginResponseClaims(XboxLiveUserInfo[] xui)
        {
            this.xui = xui;
        }

        public XboxLiveUserInfo[] getUsers()
        {
            return xui;
        }
    }

    public static class XboxLiveUserInfo
    {
        private final String uhs;

        public XboxLiveUserInfo(String uhs)
        {
            this.uhs = uhs;
        }

        public String getUserHash()
        {
            return uhs;
        }
    }
}
