{
    "access_token": "0b9bb396-e7fb-43f1-a580-50ea28ff90dc",
    "token_type": "bearer",
    "refresh_token": "efdbc090-ced6-4e07-a0f9-fdbd5241ba0e",
    "expires_in": 42723,
    "scope": "webclient"
}


AuthenticationManager
AuthenticationProvider
AuthenticationSuccessHandler
AbstractSecurityInterceptor
ExceptionTranslationFilter
AuthenticationEntryPoint
AuthenticationProvider
SecurityMetadataSource


			  .map(SecurityContext::getAuthentication)
			  .filter(Authentication::isAuthenticated)
			  .map(Authentication::getPrincipal)
			  .map(User.class::cast);




AbstractSecurityInterceptor

The AccessDecisionManager is called by the AbstractSecurityInterceptor 