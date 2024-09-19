package pl.tul.smartbot.converter.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Component;
import pl.tul.smartbot.model.dto.security.AuthenticationTokenDTO;
import pl.tul.smartbot.model.dto.security.UserDetailsDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

/**
 * Converter allowing to extract permissions and user identifier related claims from JWT token.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationTokenConverter implements Converter<Jwt, AuthenticationTokenDTO> {

    private static final String SCOPES_CLAIM = "scopes";
    private static final String USER_ID_CLAIM = "userId";
    private static final String USERNAME_CLAIM = "username";
    private static final String EMAIL_CLAIM = "email";
    private static final String USER_ID_CLAIM_IS_EMPTY = "User id claim is empty";

    /**
     * Converts the source Jwt object to the {@link AuthenticationTokenDTO}. Permissions and user details are extracted
     * from JWT claims. Permissions are converted to SimpleGrantedAuthority objects that will be used to authorize user
     * requests by Spring Security mechanisms. User details will be converted to {@link UserDetailsDTO} object.
     *
     * @param jwt the source object to convert, object representing JWT
     * @return custom authentication token
     */
    @Override
    public AuthenticationTokenDTO convert(Jwt jwt) {
        List<SimpleGrantedAuthority> permissionAuthorities = getPermissionAuthorities(jwt);
        UserDetailsDTO userDetailsDTO = getUserDetails(jwt);
        var authenticationTokenDTO = new AuthenticationTokenDTO(permissionAuthorities, jwt, userDetailsDTO);
        authenticationTokenDTO.setAuthenticated(true);
        return authenticationTokenDTO;
    }

    private List<SimpleGrantedAuthority> getPermissionAuthorities(Jwt jwt) {
        log.debug("JWT token conversion - extracting permission claims");

        @SuppressWarnings("unchecked")
        Collection<String> permissions = (Collection<String>) jwt.getClaims().get(SCOPES_CLAIM);

        return Optional.ofNullable(permissions)
                .orElse(emptyList())
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    private UserDetailsDTO getUserDetails(Jwt jwt) {
        log.debug("JWT token conversion - extracting user claims");
        Long userId = (Long) jwt.getClaims().get(USER_ID_CLAIM);
        String username = (String) jwt.getClaims().get(USERNAME_CLAIM);
        String email = (String) jwt.getClaims().get(EMAIL_CLAIM);
        if (isNull(userId)) {
            log.error(USER_ID_CLAIM_IS_EMPTY);
            throw new InvalidBearerTokenException(USER_ID_CLAIM_IS_EMPTY);
        }
        return UserDetailsDTO.builder()
                .userId(userId)
                .email(email)
                .username(username)
                .build();
    }
}
