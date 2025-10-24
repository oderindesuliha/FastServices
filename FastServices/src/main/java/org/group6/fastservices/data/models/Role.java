package org.group6.fastservices.data.models;

public enum Role {
    ORGANIZATION,
    CUSTOMER,
    ADMIN
}
package org.group6.fastservices.security.jwt;

import jakarta.servlet.*;
        import jakarta.servlet.http.*;
        import lombok.RequiredArgsConstructor;
import org.group6.fastservices.data.models.Role;
import org.group6.fastservices.security.CustomServiceResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtProvider;
    private final CustomServiceResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String token = extractToken(request);

        if (token != null && jwtProvider.validateToken(token)) {
            String username = jwtProvider.extractUsername(token);
            Role role = jwtProvider.extractRole(token);

            var service = resolver.getServiceForRole(role);
            var userDetails = service.loadUserByUsername(username);

            var auth = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        return (StringUtils.hasText(header) && header.startsWith("Bearer "))
                ? header.substring(7)
                : null;
    }
}
