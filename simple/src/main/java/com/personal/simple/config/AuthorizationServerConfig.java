package com.personal.simple.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // Variables stored on the machine that the API is hosted on and
    // the machine the client is hosted on.
    static final String CLIENT_ID = System.getenv("OAUTHCLIENTID");

    static final String CLIENT_SECRET = System.getenv("OAUTHCLIENTSECRET");

    // Users can use a username and password to login on the client.
    static final String GRANT_TYPE_PASSWORD = "password";

    // Clients can send an authorization_code to verify identity.
    static final String AUTHORIZATION_CODE = "authorization_code";

    /* These variables setup a scope for users using our client. 
    * If scope is left empty these values would be the same anyway.
    * This just makes it easier to limit later if desired.
    */
    static final String SCOPE_READ = "read";

    static final String SCOPE_WRITE = "write";

    static final String TRUST = "trust";

    // This variable limits the length of time an access token is valid. 
    // A value of negative one means the token will not expire.
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = -1;

    // Token store is managed by oauth
    @Autowired
    private TokenStore tokenStore;

    // Is a server that processes authentication requests.
    @Autowired
    private AuthenticationManager authenticationManager;

    // BCryptPasswordEncoder that is used to encrypt passwords and the client secret
    @Autowired
    private PasswordEncoder encoder;


    /** 
     * This method configures the server so it knows what our client's id and password are.
     * 
     * @param configurer Spring Boot Security runs this method with a configurer it provides.
     * 
    **/
    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        configurer.inMemory()
            .withClient(CLIENT_ID)
            .secret(encoder.encode(CLIENT_SECRET))
            .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE)
            .scopes(
                SCOPE_READ,
                SCOPE_WRITE,
                TRUST
                )
            .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
    }

    /** 
     * This method connects our authentication server and token store to our endpoints
     * 
     * @param endpoints Spring Boot Security will take the endpoints in the controllers and 
     * add them to this function as a AuthorizationServerEndpointsConfigurer.
     * 
    **/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
        // This pathMapping renames an endpoint so our client can use /login instead of /oauth/token.
        endpoints.pathMapping("/oauth/token", "/login");
    }
}