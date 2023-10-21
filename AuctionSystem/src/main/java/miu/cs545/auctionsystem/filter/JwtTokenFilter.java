package miu.cs545.auctionsystem.filter;



import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import miu.cs545.auctionsystem.util.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.split(" ")[1];
        boolean result = tokenUtil.validateToken(token);
        if(!result){
            filterChain.doFilter(request, response);
            return;
        }


        Claims claims = tokenUtil.parseClaims(token);
        String subject = claims.getSubject();
        String[] info = subject.split(",");
        String email = info[1];
        System.out.println( claims.get("roles"));
        List<String > roles  = (List<String>) claims.get("roles");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities= new ArrayList<>();
         if(roles!=null && ! roles.isEmpty()) {

             simpleGrantedAuthorities= roles.stream().map(role -> new SimpleGrantedAuthority(role))
                     .collect(Collectors.toList());

         }

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null,   simpleGrantedAuthorities);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);
    }

}
