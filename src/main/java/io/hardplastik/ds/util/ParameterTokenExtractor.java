package io.hardplastik.ds.util;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

public class ParameterTokenExtractor implements TokenExtractor {

    @Override
    public Optional<String> getToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getParameter("access_token"));
    }
    
}
