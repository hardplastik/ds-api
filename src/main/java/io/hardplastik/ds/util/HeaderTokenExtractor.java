package io.hardplastik.ds.util;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

public class HeaderTokenExtractor implements TokenExtractor {

    public static final String TOKEN_TYPE = "BEARER";
    
    public static final String HEADER = "Authorization";
    
    @Override
    public Optional<String> getToken(HttpServletRequest request) {
        String token = null;
        String header = request.getHeader(HEADER);
        if (header != null) {
            String[] parts = header.split(" ");
            if (parts.length == 2 && parts[0].equalsIgnoreCase(TOKEN_TYPE)) {
                token = parts[1];
            }
        }
        return Optional.ofNullable(token);
    }

}
