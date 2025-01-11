package io.hardplastik.ds.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

public class CompositeTokenExtractor implements TokenExtractor {
    
    private List<TokenExtractor> tokenExtractors;

    public CompositeTokenExtractor() {
        this(Arrays.asList(new HeaderTokenExtractor(), new ParameterTokenExtractor()));
    }

    public CompositeTokenExtractor(List<TokenExtractor> tokenExtractors) {
        this.tokenExtractors = tokenExtractors;
    }

    @Override
    public Optional<String> getToken(HttpServletRequest request) {
        return tokenExtractors
                .stream()
                .map(extractor -> extractor.getToken(request))
                .filter(Optional::isPresent)
                .map(Optional::get).findAny();
    }

}
