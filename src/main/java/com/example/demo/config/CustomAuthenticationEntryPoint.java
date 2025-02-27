package com.example.demo.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        // Ottieni lo stato HTTP della risposta
        int status = response.getStatus();
        Map<String, Object> errorResponse = new HashMap<>();

        switch (status) {
            case HttpServletResponse.SC_NOT_FOUND -> {
                errorResponse.put("error", "Not Found");
                errorResponse.put("message", "La risorsa richiesta non è stata trovata");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
            case HttpServletResponse.SC_BAD_REQUEST -> {
                errorResponse.put("error", "Bad Request");
                errorResponse.put("message", "Richiesta non valida. Controlla i parametri.");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            case HttpServletResponse.SC_FORBIDDEN -> {
                errorResponse.put("error", "Forbidden");
                errorResponse.put("message", "Accesso negato. Non hai i permessi necessari.");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
            case HttpServletResponse.SC_UNAUTHORIZED -> {
                errorResponse.put("error", "Unauthorized");
                errorResponse.put("message", "Accesso negato. Effettua il login.");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            default -> {
                errorResponse.put("error", "Internal Server Error");
                errorResponse.put("message", "Si è verificato un errore imprevisto.");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

        // Imposta il content type a JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Scrivi la risposta JSON
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}



