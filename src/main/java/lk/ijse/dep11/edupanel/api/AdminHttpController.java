package lk.ijse.dep11.edupanel.api;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import lk.ijse.dep11.edupanel.entity.Lecturer;
import lk.ijse.dep11.edupanel.repository.LecturerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/lecturers")
@CrossOrigin
public class AdminHttpController {

    private final LecturerRepository lecturerRepository;
    //Instance of the secret key for signing
    private final SecretKey secretKey;

    public AdminHttpController(LecturerRepository lecturerRepository, SecretKey secretKey) {
        this.lecturerRepository = lecturerRepository; //dependency injection
        this.secretKey = secretKey; //dependency injection
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public String login(@RequestBody Map<String, String> credentials) {

        // (1) Verify the user
        String username = credentials.get("username");
        String password = credentials.get("password");

        if(!username.equals("admin") && !password.equals("admin123")){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        // (2) Generate the token
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.issuer("dep-11");
        jwtBuilder.issuedAt(new Date());
        LocalDateTime tokenExpTime = LocalDateTime.now().plusMinutes(10);
        //Convert LocalDate to util datetime
        Date expTime = Date.from(tokenExpTime.atZone(ZoneId.systemDefault()).toInstant());
        jwtBuilder.expiration(expTime);
        //subject = principle = user
        jwtBuilder.subject(username);

        jwtBuilder.signWith(secretKey);
        System.out.println(secretKey);

        // (3) Return the signed JWT (JWS)
        return jwtBuilder.compact();

    }
}
