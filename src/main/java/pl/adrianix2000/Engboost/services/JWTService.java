package pl.adrianix2000.Engboost.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class JWTService {

    private String privateKey = "crtWfNtHLfBtJYPYvYQ7dWingGINZ3Q6E86EwrNxh3s";

    public String generateJwtToken() {
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MINUTE, 5);
        Date expirationDate = calendar.getTime();

        String generatedToken = Jwts.builder()
                .claim("name", "Adrian")
                .claim("email", "daudshlock")
                .setSubject("Test")
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, privateKey)
                .compact();

        return generatedToken;
    }

    public boolean verifyJwtToken(String token)  {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(privateKey)
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("Token verified. Subject: " + claims.getSubject());

            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid token signature");
            return false;
        }
    }
}
