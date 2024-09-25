package pl.tul.smartbot.model.dto.url;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Object allows storing credentials obtained from Vault.
 */
@AllArgsConstructor
@Data
@Builder
public class CredentialsDTO {
    private final String username;
    private final char[] password;
}
