package projet.gl.server.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import projet.gl.server.user.User;

class TokenTest {
    private Token token;

    @Test
    void testEmptyContructor() {
        // Arrange
        token = new Token();
        // Assert
        assertNotNull(token);
    }

    @Test
    void canGetId() {
        // Arrange
        Integer id = 1;
        token = Token.builder().id(id).build();
        // Act
        Integer result = token.getId();
        // Assert
        assertEquals(id, result);
    }

    @Test
    void canSetId() {
        // Arrange
        Integer id = 1;
        token = Token.builder().build();
        // Act
        token.setId(id);
        // Assert
        assertEquals(id, token.getId());
    }

    @Test
    void canGetTokenData() {
        // Arrange
        String tokenData = "token";
        token = Token.builder().token(tokenData).build();
        // Act
        String result = token.getTokenData();
        // Assert
        assertEquals(tokenData, result);
    }

    @Test
    void canSetTokenData() {
        // Arrange
        String tokenData = "token";
        token = Token.builder().build();
        // Act
        token.setTokenData(tokenData);
        // Assert
        assertEquals(tokenData, token.getTokenData());
    }

    @Test
    void canGetTokenType() {
        // Arrange
        TokenType tokenType = TokenType.BEARER;
        token = Token.builder().tokenType(tokenType).build();
        // Act
        TokenType result = token.getTokenType();
        // Assert
        assertEquals(tokenType, result);
    }

    @Test
    void canSetTokenType() {
        // Arrange
        TokenType tokenType = TokenType.BEARER;
        token = Token.builder().build();
        // Act
        token.setTokenType(tokenType);
        // Assert
        assertEquals(tokenType, token.getTokenType());
    }

    @Test
    void canGetRevoked() {
        // Arrange
        boolean revoked = true;
        token = Token.builder().revoked(revoked).build();
        // Act
        boolean result = token.isRevoked();
        // Assert
        assertEquals(revoked, result);
    }

    @Test
    void canSetRevoked() {
        // Arrange
        boolean revoked = true;
        token = Token.builder().build();
        // Act
        token.setRevoked(revoked);
        // Assert
        assertEquals(revoked, token.isRevoked());
    }

    @Test
    void canGetExpired() {
        // Arrange
        boolean expired = true;
        token = Token.builder().expired(expired).build();
        // Act
        boolean result = token.isExpired();
        // Assert
        assertEquals(expired, result);
    }

    @Test
    void canSetExpired() {
        // Arrange
        boolean expired = true;
        token = Token.builder().build();
        // Act
        token.setExpired(expired);
        // Assert
        assertEquals(expired, token.isExpired());
    }

    @Test
    void canGetUser() {
        // Arrange
        User user = User.builder().build();
        token = Token.builder().user(user).build();
        // Act
        User result = token.getUser();
        // Assert
        assertEquals(user, result);
    }

    @Test
    void canSetUser() {
        // Arrange
        User user = User.builder().build();
        token = Token.builder().build();
        // Act
        token.setUser(user);
        // Assert
        assertEquals(user, token.getUser());
    }
}
