package projet.gl.server.auth;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import projet.gl.server.user.User;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    
    
    @GetMapping("/getUsername")
    public String getUsernameFromToken(@RequestParam("token") String token) {
        String username = service.getUsernameFromToken(token);
        if (username != null) {
            return "Username: " + username;
        } else {
            return "Invalid token or expired token";
        }
    }

/*     @GetMapping("/checkToken")
    public ResponseEntity<String> checkTokenValidity(@RequestParam("token") String token, Principal principal) {
        if (principal != null) {
            User user = (User) principal;
    

            if (service.isTokenValidForUser(token, user)) {    ////// faire verification ici meme user ou pas ???
                //autre group by brand et model
                
                return ResponseEntity.ok("OK");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
    } */

}
