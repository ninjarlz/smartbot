package pl.tul.smartbot.util.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.tul.smartbot.exception.security.AuthenticationException;
import pl.tul.smartbot.model.dto.security.AuthenticationTokenDTO;
import pl.tul.smartbot.model.dto.security.UserDetailsDTO;

import java.util.Optional;
import java.util.Set;

import static pl.tul.smartbot.util.constant.security.TokenStructure.SCOPES_CLAIM;

/**
 * Delegate allowing to use user and token details stored in security context
 */
@Component
@Slf4j
public class AuthenticationDelegate {

    private static final String NO_AUTHENTICATION_CREDENTIALS_SET_MSG = "No authentication credentials set.";

    /**
     * Obtaining logged-in user details from security context
     *
     * @return user details
     */
    public UserDetailsDTO getUserDetails() throws AuthenticationException {
        return getAuthentication().getPrincipal();
    }

    /**
     * Obtaining userId from logged-in user details
     *
     * @return current userId
     */
    public long getUserId() throws AuthenticationException {
        return getUserDetails().getUserId();
    }

    /**
     * Obtaining permissions of logged-in user from security context
     *
     * @return scopes
     */
    public Set<String> getPermissions() throws AuthenticationException {
        return getAuthentication().getCredentials().getClaim(SCOPES_CLAIM);
    }

    /**
     * Return flag indicating whether token of logged-in user contains given permissions
     *
     * @return flag indicating whether token of logged-in user contains given permissions
     */
    public boolean doesTokenContainsPermissions(String... permissions) throws AuthenticationException {
        return getPermissions().containsAll(Set.of(permissions));
    }

    /**
     * Obtaining current JWT value from security context
     *
     * @return current JWT representation
     */
    public String getTokenValue() throws AuthenticationException {
        var jwt = getAuthentication().getCredentials();
        return jwt.getTokenValue();
    }

    private AuthenticationTokenDTO getAuthentication() throws AuthenticationException {
        return (AuthenticationTokenDTO) Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(this::buildAuthenticationException);
    }

    private AuthenticationException buildAuthenticationException() {
        return new AuthenticationException(NO_AUTHENTICATION_CREDENTIALS_SET_MSG);
    }
}
