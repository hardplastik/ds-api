package io.hardplastik.ds.util;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenExtractor {

    Optional<String> getToken(HttpServletRequest request);

}
