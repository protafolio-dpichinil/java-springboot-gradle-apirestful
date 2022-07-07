package cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.security;

import cl.dpichinil.portafolio.javaspringbootgradleapirestful.config.properties.ApplicationProperties;
import cl.dpichinil.portafolio.javaspringbootgradleapirestful.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {
    private final ApplicationProperties applicationProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authToken = request.getHeader(this.applicationProperties.getTokenHeader());
        if(authToken!=null && authToken.startsWith("Bearer ")){
            authToken = authToken.substring(7);
        }
        String username = TokenUtil.getUsernameFromToken(authToken);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (TokenUtil.validateToken(authToken) == 0) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
                logger.debug("authenticated user " + username +", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
