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

public class MinecraftStoreResponse
{
    private final StoreProduct[] items;
    private final String signature;
    private final String keyId;

    public MinecraftStoreResponse(StoreProduct[] items, String signature, String keyId)
    {
        this.items = items;
        this.signature = signature;
        this.keyId = keyId;
    }

    public StoreProduct[] getItems()
    {
        return items;
    }

    public String getSignature()
    {
        return signature;
    }

    public String getKeyId()
    {
        return keyId;
    }

    public static class StoreProduct
    {
        private final String name;
        private final String signature;

        public StoreProduct(String name, String signature)
        {
            this.name = name;
            this.signature = signature;
        }

        public String getName()
        {
            return name;
        }

        public String getSignature()
        {
            return signature;
        }
    }
}
