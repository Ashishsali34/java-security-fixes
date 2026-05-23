import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtExample {

    // Secret key (keep it safe in real apps)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ✅ Generate JWT
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("my-app")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(SECRET_KEY)
                .compact();
    }

    // ✅ Validate JWT
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false; // invalid token
        }
    }

    // ✅ Extract username
    public static String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // ✅ Test
    public static void main(String[] args) {

        String token = generateToken("Aashish");
        System.out.println("Generated Token: " + token);

        boolean isValid = validateToken(token);
        System.out.println("Is Valid: " + isValid);

        String username = getUsername(token);
        System.out.println("Username: " + username);
    }
}