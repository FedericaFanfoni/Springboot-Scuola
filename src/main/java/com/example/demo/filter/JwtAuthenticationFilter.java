package com.example.demo.filter;

import com.example.demo.service.JwtService;
import com.example.demo.service.UserDetailsServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
// L'uso di questa classe serve per eseguire un filtro una volta per ogni richiesta HTTP
// Il suo scopo principale è autenticare le richieste utilizzando i token JWT.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImp userDetailsService;

    @Override
    protected void doFilterInternal(
         @NonNull HttpServletRequest request,
         @NonNull HttpServletResponse response,
         @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Recupero dell'header di autorizzazione dalla richiesta
        String authHeader = request.getHeader("Authorization");

        // Verifica dell'header
        // Se l’header è nullo o non inizia con "Bearer ",
        // la richiesta viene passata al filtro successivo senza ulteriori elaborazioni.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Viene estratto il token dall’header e il nome utente dal token.
        String token = authHeader.substring(8);
        String username = jwtService.extractUsername(token);

        // Se il nome utente è valido e non esiste già un’autenticazione nel contesto di sicurezza,
        // vengono caricati i dettagli dell’utente e viene verificata la validità del token.
        // Se il token è valido, viene creato un oggetto UsernamePasswordAuthenticationToken e impostato nel contesto di sicurezza.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtService.isValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // La richiesta viene passata al filtro successivo nella catena
        filterChain.doFilter(request, response);
    }
}
