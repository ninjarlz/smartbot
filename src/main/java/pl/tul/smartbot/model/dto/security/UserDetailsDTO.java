package pl.tul.smartbot.model.dto.security;

import jakarta.annotation.Nullable;
import lombok.Builder;

/**
 * Object allows storing user details obtained from JWT.
 */
@Builder
public record UserDetailsDTO(long userId, @Nullable String username, @Nullable String email) {
}
