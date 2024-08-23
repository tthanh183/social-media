package com.example.socialmedia.service;

import com.example.socialmedia.dto.request.AuthenticationRequest;
import com.example.socialmedia.dto.request.IntrospectRequest;
import com.example.socialmedia.dto.request.LogoutRequest;
import com.example.socialmedia.dto.request.RefreshRequest;
import com.example.socialmedia.dto.response.AuthenticationResponse;
import com.example.socialmedia.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthenticationService {
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
    public AuthenticationResponse authenticate(AuthenticationRequest request);
    public void logout(LogoutRequest request) throws ParseException, JOSEException ;
    public AuthenticationResponse refresh(RefreshRequest request) throws ParseException, JOSEException ;
}
