package io.hardplastik.ds.auth;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import io.hardplastik.ds.model.Account;
import io.hardplastik.ds.service.JwtService;
import io.hardplastik.ds.util.CompositeTokenExtractor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private JwtService jwtService;

    private CompositeTokenExtractor tokenExtractor = new CompositeTokenExtractor();

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        
        Optional<String> tokenWrapper = tokenExtractor.getToken(request);

        if (tokenWrapper.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = tokenWrapper.get();
            String username = jwtService.extractUsername(token);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (username != null && authentication == null
                && jwtService.isTokenValid(token)) {
                    List<String> roles = jwtService.extractRoles(token);
                    Account account = new Account();
                    account.setUsername(username);
                    account.setAuthorities(roles);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request, response);
        } catch(Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }

    }

}
