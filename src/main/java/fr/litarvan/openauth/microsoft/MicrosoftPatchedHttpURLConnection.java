package fr.litarvan.openauth.microsoft;

/*
 * Thanks a lot to Mickaël Guessant for this trick
 *
 * https://github.com/mguessan
 * https://github.com/mguessan/davmail/blob/master/src/java/davmail/exchange/auth/O365InteractiveAuthenticatorFrame.java
 */

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * HttpURLConnection Microsoft-patched wrapped
 *
 * <p>
 *     This class serves as HttpURLConnection, but actually wraps a real one and
 *     patch its input to disable Microsoft meta integrity check, which can fail
 *     on Java >=11 on non-macOS platforms.
 * </p>
 *
 * @version 1.1.1
 * @author Litarvan
 */
public class MicrosoftPatchedHttpURLConnection extends HttpURLConnection
{
    private final HttpURLConnection inner;

    public MicrosoftPatchedHttpURLConnection(URL url, HttpURLConnection inner)
    {
        super(url);

        this.inner = inner;
    }

    @Override
    public void setRequestMethod(String method) throws ProtocolException
    {
        this.inner.setRequestMethod(method);
    }

    @Override
    public void setInstanceFollowRedirects(boolean followRedirects)
    {
        this.inner.setInstanceFollowRedirects(followRedirects);
    }

    @Override
    public boolean getInstanceFollowRedirects()
    {
        return this.inner.getInstanceFollowRedirects();
    }

    @Override
    public String getRequestMethod()
    {
        return this.inner.getRequestMethod();
    }

    @Override
    public int getResponseCode() throws IOException
    {
        return this.inner.getResponseCode();
    }

    @Override
    public String getResponseMessage() throws IOException
    {
        return this.inner.getResponseMessage();
    }

    @Override
    public Map<String, List<String>> getHeaderFields()
    {
        return this.inner.getHeaderFields();
    }

    @Override
    public String getHeaderField(String name)
    {
        return this.inner.getHeaderField(name);
    }

    @Override
    public String getHeaderField(int n)
    {
        return this.inner.getHeaderField(n);
    }

    @Override
    public void disconnect()
    {
        this.inner.disconnect();
    }

    @Override
    public void setDoOutput(boolean dooutput)
    {
        this.inner.setDoOutput(dooutput);
    }

    @Override
    public boolean usingProxy()
    {
        return this.inner.usingProxy();
    }

    @Override
    public void connect() throws IOException
    {
        this.inner.connect();
    }

    @Override
    public InputStream getInputStream() throws IOException
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (InputStream in = this.inner.getInputStream()) {
            int n;
            byte[] data = new byte[8192];

            while ((n = in.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, n);
            }
        }

        byte[] patched = buffer
                .toString("UTF-8")
                .replaceAll("integrity ?=", "integrity.disabled=")
                .replaceAll("setAttribute\\(\"integrity\"", "setAttribute(\"integrity.disabled\"")
                .getBytes(StandardCharsets.UTF_8);

        return new ByteArrayInputStream(patched);
    }

    @Override
    public OutputStream getOutputStream() throws IOException
    {
        return this.inner.getOutputStream();
    }

    @Override
    public InputStream getErrorStream()
    {
        return this.inner.getErrorStream();
    }
}
