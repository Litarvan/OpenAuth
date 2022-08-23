![OpenAuth Logo](./banner.png)

> Minecraft authentication library which supports both Mojang and Microsoft accounts, or even custom servers

# Credits

This project would have not existed without the work from:

 - [Kronos666](https://github.com/Kronos666) for Mojang authentication
 - [Alexis Bize](https://github.com/Alexis-Bize) for Microsoft authentication
 - [Mickaël Guessant](https://github.com/mguessan) for Microsoft webview authentication

# Importing library

## Gradle
```gradle
repositories {
    maven {
        url 'https://litarvan.github.io/maven'
    }
}

dependencies {
    implementation 'fr.litarvan:openauth:1.1.3'
}
```

## Maven
```xml
<repositories>
    <repositories>
        <id>litarvan</id>
        <url>https://litarvan.github.io/maven</url>
    </repositories>
</repositories>
<dependencies>
    <dependency>
        <groupId>fr.litarvan</groupId>
        <artifactId>openauth</artifactId>
        <version>1.1.3</version>
    </dependency>
</dependencies>
```

## Other
Use the JAR file from releases, or from the GitHub Actions.

# How to use it (Microsoft)

```java
MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
MicrosoftAuthResult result = authenticator.loginWithCredentials("email", "password");
      // Or using a webview: authenticator.loginWithWebView();
  // Or using refresh token: authenticator.loginWithRefreshToken("refresh token");
   // Or using your own way: authenticator.loginWithTokens("access token", "refresh token");
        
System.out.printf("Logged in with '%s'%n", result.getProfile().getName());
```

You can save the refresh token (`result.getRefreshToken()`) to log back the user silently later.

# How to use it (Mojang)
Use it to connect to any yggdrasil server (The Mojang official auth server, or any openauth server, and maybe other servers !)

## Init it
First init the authenticator with settings from your server : 
```java
AuthPoints points = new AuthPoints("authpage", "refreshpage", "validatepage", "signoutpage", "invalidatepage");
Authenticator authenticator = new Authenticator("urlofyourserver", points);
```

So the auth points are the url (relative to the main url) of all your page, you can give only page that you wan't to use !
urlofyourserver is of course the URL of yout Yggdrasil server.

You can use pre-made auth points : AuthPoints.MOJANG_AUTH_POINTS or AuthPoints.OPENAUTH_AUTH_POINTS
You can alose user the official mojang server : Authenticator.MOJANG_AUTH_URL

## Send a request !
### Authenticate
Receive an access token by authenticating an user
```java
AuthResponse response = authenticator.authenticate(authagent, "username", "password", "clientToken");
```

So, replace auth agent with your auth agent (Minecraft, Scrolls, or other) pre-made agent are AuthAgent.MINECRAFT and AuthAgent.SCROLLS. Auth Agent are used to get the profile of the account of your server, every accounts have some profiles, one profile is one game, so to create yout auth agent object, use :
```java
AuthAgent myAgent = new AuthAgent("name", version);
```
You can manage the profiles in the server ^^
The Auth agent is optional, if you don't send it, response.getSelectedProfile() and response.getAvailableProfiles() will be null.

Replace username with the username of the user account, and password with the password of the user account you wanna auth !

The client token is like a key to protect the access token if you wan't, it is optional !

Every profiles has a username and an ID, user (profile).getName() and (profile).getId() to get them !

### Refresh
Receive a new access token by re-authenticating an user with the saved access token of the previous authentication
```java
RefreshResponse response = authenticator.refresh("accessToken", "clientToken");
```

Replace accessToken by the saved access token from the previous authentication of the user you wanna re-auth, and clientToken by the client token given when authenticating (optinal if you didn't give it)

The reponse is near the same as the auth response (selectedProfile, accessToken, etc...)

### Validate
This is to check an access token, WARNING : This method should not be used by the client. Juste do
```java
authenticator.validate("accessToken");
```

### Signout
Invalidate an access token using the username and the password of the user that had the access token
```java
authenticator.signout("username", "password");
```

### Invalidate
Invalidate an access token using the access token and its client token
```java
authenticator.invalidate("accessToken", "clientToken");
```
