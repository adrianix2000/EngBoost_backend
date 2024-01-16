package pl.adrianix2000.Engboost.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.adrianix2000.Engboost.Entities.UserDto;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Service
public class JWTService {

    private String privateKey = "crtWfNtHLfBtJYPYvYQ7dWingGINZ3Q6E86EwrNxh3s";

    public String generateJwtToken(UserDto user) {
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MINUTE, 15);
        Date expirationDate = calendar.getTime();

        String generatedToken = Jwts.builder()
                .claim("firstname", user.getFirstname())
                .claim("lastname", user.getLastname())
                .claim("email", user.getEmail())
                .setSubject(user.getUsername())
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, privateKey)
                .compact();

        return generatedToken;
    }

    public Authentication verifyJwtToken(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(privateKey)
                .parseClaimsJws(token)
                .getBody();

        UserDto user = UserDto.builder()
                .username(claims.getSubject())
                .email((String) claims.get("email"))
                .firstname((String) claims.get("firstname"))
                .lastname((String) claims.get("lastname"))
                .build();

        return new UsernamePasswordAuthenticationToken(user,
                null, Collections.emptyList());

    }
}
