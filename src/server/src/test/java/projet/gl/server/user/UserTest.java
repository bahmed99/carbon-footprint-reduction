package projet.gl.server.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import projet.gl.server.token.Token;

class UserTest {
    private User user;

    @Test
    void testEmptyContructor() {
        // Arrange
        user = new User();
        // Assert
        assertNotNull(user);
    }

    @Test
    void canGetId() {
        // Arrange
        Integer id = 1;
        user = User.builder().id(id).build();
        // Act
        Integer result = user.getId();
        // Assert
        assertEquals(id, result);
    }

    @Test
    void canSetId() {
        // Arrange
        Integer id = 1;
        user = User.builder().build();
        // Act
        user.setId(id);
        // Assert
        assertEquals(id, user.getId());
    }

    @Test
    void canGetFirstname() {
        // Arrange
        String firstname = "dupont";
        user = User.builder().firstname(firstname).build();
        // Act
        String result = user.getFirstname();
        // Assert
        assertEquals(firstname, result);
    }

    @Test
    void canSetFirstname() {
        // Arrange
        String firstname = "dupont";
        user = User.builder().build();
        // Act
        user.setFirstname(firstname);
        // Assert
        assertEquals(firstname, user.getFirstname());
    }

    @Test
    void canGetLastname() {
        // Arrange
        String lastname = "dupont";
        user = User.builder().lastname(lastname).build();
        // Act
        String result = user.getLastname();
        // Assert
        assertEquals(lastname, result);
    }

    @Test
    void canSetLastname() {
        // Arrange
        String lastname = "dupont";
        user = User.builder().build();
        // Act
        user.setLastname(lastname);
        // Assert
        assertEquals(lastname, user.getLastname());
    }

    @Test
    void canGetEmail() {
        // Arrange
        String email = "dupont@email.com";
        user = User.builder().email(email).build();
        // Act
        String result = user.getEmail();
        // Assert
        assertEquals(email, result);
    }

    @Test
    void canSetEmail() {
        // Arrange
        String email = "dupont@email.com";
        user = User.builder().build();
        // Act
        user.setEmail(email);
        // Assert
        assertEquals(email, user.getEmail());
    }

    @Test
    void canGetPassword() {
        // Arrange
        String password = "password";
        user = User.builder().password(password).build();
        // Act
        String result = user.getPassword();
        // Assert
        assertEquals(password, result);
    }

    @Test
    void canSetPassword() {
        // Arrange
        String password = "password";
        user = User.builder().build();
        // Act
        user.setPassword(password);
        // Assert
        assertEquals(password, user.getPassword());
    }

    @Test
    void canGetPhone() {
        // Arrange
        String phone = "0123456789";
        user = User.builder().phone(phone).build();
        // Act
        String result = user.getPhone();
        // Assert
        assertEquals(phone, result);
    }

    @Test
    void canSetPhone() {
        // Arrange
        String phone = "0123456789";
        user = User.builder().build();
        // Act
        user.setPhone(phone);
        // Assert
        assertEquals(phone, user.getPhone());
    }

    @Test
    void canGetAddress() {
        // Arrange
        String address = "1 rue de la paix";
        user = User.builder().address(address).build();
        // Act
        String result = user.getAddress();
        // Assert
        assertEquals(address, result);
    }

    @Test
    void canSetAddress() {
        // Arrange
        String address = "1 rue de la paix";
        user = User.builder().build();
        // Act
        user.setAddress(address);
        // Assert
        assertEquals(address, user.getAddress());
    }

    @Test
    void canGetRole() {
        // Arrange
        Role role = Role.ADMIN;
        user = User.builder().role(role).build();
        // Act
        Role result = user.getRole();
        // Assert
        assertEquals(role, result);
    }

    @Test
    void canSetRole() {
        // Arrange
        Role role = Role.ADMIN;
        user = User.builder().build();
        // Act
        user.setRole(role);
        // Assert
        assertEquals(role, user.getRole());
    }

    @Test
    void canGetTokens() {
        // Arrange
        Token token = Token.builder().build();
        user = User.builder().tokens(List.of(token)).build();
        // Act
        List<Token> result = user.getTokens();
        // Assert
        assertEquals(List.of(token), result);
    }

    @Test
    void canSetTokens() {
        // Arrange
        Token token = Token.builder().build();
        user = User.builder().build();
        // Act
        user.setTokens(List.of(token));
        // Assert
        assertEquals(List.of(token), user.getTokens());
    }

    @Test
    void canGetAuthorities() {
        // Arrange
        Role role = Role.ADMIN;
        user = User.builder().role(role).build();
        // Act
        Collection<? extends GrantedAuthority> result = user.getAuthorities();
        // Assert
        assertEquals(List.of(new SimpleGrantedAuthority(role.name())), result);
    }

    @Test
    void canGetUsername() {
        // Arrange
        String email = "email";
        user = User.builder().email(email).build();
        // Act
        String result = user.getUsername();
        // Assert
        assertEquals(email, result);
    }

    @Test
    void canIsAccountNonExpired() {
        // Arrange
        user = User.builder().build();
        // Act
        boolean result = user.isAccountNonExpired();
        // Assert
        assertEquals(true, result);
    }

    @Test
    void canIsAccountNonLocked() {
        // Arrange
        user = User.builder().build();
        // Act
        boolean result = user.isAccountNonLocked();
        // Assert
        assertEquals(true, result);
    }

    @Test
    void canIsCredentialsNonExpired() {
        // Arrange
        user = User.builder().build();
        // Act
        boolean result = user.isCredentialsNonExpired();
        // Assert
        assertEquals(true, result);
    }

    @Test
    void canIsEnabled() {
        // Arrange
        user = User.builder().build();
        // Act
        boolean result = user.isEnabled();
        // Assert
        assertEquals(true, result);
    }
}