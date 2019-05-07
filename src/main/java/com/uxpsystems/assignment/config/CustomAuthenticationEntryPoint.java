package com.uxpsystems.assignment.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint{

    public CustomAuthenticationEntryPoint() {
        this.setRealmName("Assignment");
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
        throws IOException, ServletException {
        response.setHeader("WWW-Authenticate", "FormBased");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String customErrorMsg = null;
        if(authException.getMessage() != null)
        {
        	String errorDetails = authException.getMessage();
        	if(errorDetails.contains("<message>") && errorDetails.contains("</message>"))
        	{
        		customErrorMsg = errorDetails.substring(errorDetails.lastIndexOf("<message>")+9, errorDetails.lastIndexOf("</message>"));
        	}
        }
        if(customErrorMsg != null)
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, customErrorMsg);
        else
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}