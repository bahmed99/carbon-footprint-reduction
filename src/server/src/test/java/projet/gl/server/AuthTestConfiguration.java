package projet.gl.server;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import projet.gl.server.auth.JwtService;
import projet.gl.server.token.TokenRepository;

import org.mockito.Mockito;

@TestConfiguration
public class AuthTestConfiguration {
    @Bean
    @Primary
    public JwtService jwtService() {
        return Mockito.mock(JwtService.class);
    }

    @Bean
    @Primary
    public TokenRepository tokenRepository() {
        return Mockito.mock(TokenRepository.class);
    }
}
