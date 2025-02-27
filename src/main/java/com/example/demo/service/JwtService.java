package com.example.demo.service;

import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    // Chiave segreta per firmare e verificare il token
    private final String SECRET_KEY = "5e0e23a7cd81828e7dfd2bae5db8cba320e687c31d135b2e722605a2f136eb77";

    // Verifica che il token sia valido controllando che il nome utente estratto dal token
    // corrisponde a quello dell'utente e se il token è scaduto
    public boolean isValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    // Metodo che controlla se il token è scaduto confrontando la data di scadenza con quella corrente
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    // Metodo che estrae la data di scadenza del token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    // Metodo che estae il nome utente dal token
    public String extractUsername(String token) {
       return extractClaim(token, Claims::getSubject);
       // getSubject perchè quando generiamo il token
       // abbiamo aggiunto lo User nel parametro Subject
    }
    // Metodo che estae un determinato claim dal token utilizzando una funzione risolutrice
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }
    // Metodo che estre tutti i claims dal token utilizzando la chiave di firma
    // I claims in JWT sono pezzi di informazione che vengono asseriti riguardo a un soggetto (l'utente)
    // Ogni claim è rappresentato come una coppia chiave/valore, dove la chiave è sempre una stringa e
    // il valore può essere qualsiasi valore JSON
    // Esistono 3 principali tipi di claims:
    // 1) CLAIMS REGISTRATI: sono standardizzati e definiti dalla specifica JWT per garantire l’interoperabilità con applicazioni di terze parti.
    // Alcuni esempi includono:
    // iss (issuer): Chi ha emesso il token.
    // sub (subject): Il soggetto del token (di solito l’utente).
    // aud (audience): Il destinatario per cui il token è inteso.
    // exp (expiration time): Il tempo dopo il quale il token scade.
    // iat (issued at): Il tempo in cui il token è stato emesso.
    // jti (JWT ID): Identificatore univoco del token12.
    // CLAIMS PUBBLICI: possono essere definiti da chiunque e devono essere unici per evitare collisioni.
    // Ad esempio, il nome e l’email di un utente.
    // CLAIMS PRIVATI: sono definiti per uso specifico all’interno di un’applicazione e non sono destinati all’interoperabilità con altre applicazioni1.
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    // Metodo che genera un nuovo token JWT per un utente specifico,
    // impostando il nome utente come soggetto, la data di emissione e la data di scadenza
    // (24 ore dopo l’emissione).
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .claim("ruolo", user.getRuolo())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000 ))
                .signWith(getSigninKey())
                .compact();
    }

    // Metodo che decodifica la chiave segreta e la converte in una chiave HMAC-SHA utilizzabile per firmare e verificare i token.
    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
