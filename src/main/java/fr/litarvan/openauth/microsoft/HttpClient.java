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

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClient
{
    public static final String MIME_TYPE_JSON = "application/json";
    public static final String MIME_TYPE_URLENCODED_FORM = "application/x-www-form-urlencoded";


    private final Gson gson;

    public HttpClient()
    {
        this.gson = new Gson();
    }


    public String getText(String url, Map<String, String> params) throws MicrosoftAuthenticationException
    {
        return readResponse(createConnection(url + '?' + buildParams(params)));
    }

    public <T> T getJson(String url, String token, Class<T> responseClass) throws MicrosoftAuthenticationException
    {
        HttpURLConnection connection = createConnection(url);
        connection.addRequestProperty("Authorization", "Bearer " + token);
        connection.addRequestProperty("Accept", MIME_TYPE_JSON);

        return readJson(connection, responseClass);
    }

    public HttpURLConnection postForm(String url, Map<String, String> params) throws MicrosoftAuthenticationException
    {
        return post(url, MIME_TYPE_URLENCODED_FORM, "*/*", buildParams(params));
    }

    public <T> T postJson(String url, Object request, Class<T> responseClass) throws MicrosoftAuthenticationException
    {
        HttpURLConnection connection = post(url, MIME_TYPE_JSON, MIME_TYPE_JSON, gson.toJson(request));
        return readJson(connection, responseClass);
    }

    public <T> T postFormGetJson(String url, Map<String, String> params, Class<T> responseClass) throws MicrosoftAuthenticationException
    {
        return readJson(postForm(url, params), responseClass);
    }


    protected HttpURLConnection post(String url, String contentType, String accept, String data) throws MicrosoftAuthenticationException
    {
        HttpURLConnection connection = createConnection(url);
        connection.setDoOutput(true);
        connection.addRequestProperty("Content-Type", contentType);
        connection.addRequestProperty("Accept", accept);

        try {
            connection.setRequestMethod("POST");
            connection.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new MicrosoftAuthenticationException(e);
        }

        return connection;
    }

    protected <T> T readJson(HttpURLConnection connection, Class<T> responseType) throws MicrosoftAuthenticationException
    {
        return gson.fromJson(readResponse(connection), responseType);
    }

    protected String readResponse(HttpURLConnection connection) throws MicrosoftAuthenticationException
    {
        String redirection = connection.getHeaderField("Location");
        if (redirection != null) {
            return readResponse(createConnection(redirection));
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line).append('\n');
            }
        } catch (IOException e) {
            throw new MicrosoftAuthenticationException(e);
        }

        return response.toString();
    }

    protected HttpURLConnection followRedirects(HttpURLConnection connection) throws MicrosoftAuthenticationException
    {
        String redirection = connection.getHeaderField("Location");
        if (redirection != null) {
            connection = followRedirects(createConnection(redirection));
        }

        return connection;
    }

    protected String buildParams(Map<String, String> params)
    {
        StringBuilder query = new StringBuilder();
        params.forEach((key, value) -> {
            if (query.length() > 0) {
                query.append('&');
            }

            try {
                query.append(key).append('=').append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException ignored) {
                // Can't happen
            }
        });

        return query.toString();
    }

    protected HttpURLConnection createConnection(String url) throws MicrosoftAuthenticationException
    {
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            throw new MicrosoftAuthenticationException(e);
        }

        String userAgent = "Mozilla/5.0 (XboxReplay; XboxLiveAuth/3.0) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/71.0.3578.98 " +
                "Safari/537.36";

        connection.setRequestProperty("Accept-Language", "en-US");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("User-Agent", userAgent);

        return connection;
    }
}
