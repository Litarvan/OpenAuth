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
package fr.litarvan.openauth.microsoft;

import java.io.IOException;

public class MicrosoftAuthenticationException extends Exception
{
    public MicrosoftAuthenticationException(String message)
    {
        super(message);
    }

    public MicrosoftAuthenticationException(IOException cause)
    {
        super("I/O exception thrown during Microsoft HTTP requests", cause);
    }

    public MicrosoftAuthenticationException(Throwable cause)
    {
        super(cause);
    }
}
