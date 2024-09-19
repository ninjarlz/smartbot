package pl.tul.smartbot.model.dto.security;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Object allows storing user details obtained from JWT.
 */
@AllArgsConstructor
@Data
@Builder
public class UserDetailsDTO {
    private final long userId;
    @Nullable
    private final String username;
    @Nullable
    private final String email;
}
