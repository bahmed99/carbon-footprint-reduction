package projet.gl.server.auth;

import lombok.RequiredArgsConstructor;
import projet.gl.server.token.Token;
import projet.gl.server.token.TokenRepository;
import projet.gl.server.token.TokenType;
import projet.gl.server.user.Role;
import projet.gl.server.user.User;
import projet.gl.server.user.UserRepository;
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .address(request.getAddress())
                .role(Role.USER)
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }


    public String getUsernameFromToken(String token) {
        String userEmail = jwtService.extractUsername(token);
        User user = repository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found")); 
    
        String userFullName = user.getFirstname() + " " + user.getLastname();
        return userFullName;
    }



    public boolean isTokenValidForUser(String token, User user) {
        List<Token> userTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        Token userToken = userTokens.stream()
                .filter(t -> t.getTokenData().equals(token))
                .findFirst()
                .orElse(null);
    
        if (userToken == null || userToken.isExpired()) {
            throw new RuntimeException("Token is invalid or expired");
        }
    
        return true;
    }

}
