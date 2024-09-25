package pl.tul.smartbot.service.vault;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import pl.tul.smartbot.exception.vault.VaultKeyNotFoundException;
import pl.tul.smartbot.model.dto.url.CredentialsDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaultCredentialsService {

    private static final String ROOT_PATH = "credentials";

    private final VaultTemplate vaultTemplate;

    public CredentialsDTO getCredentials(String credentialsKey) throws VaultKeyNotFoundException {
        var vaultKeyValueOperations = vaultTemplate.opsForKeyValue(ROOT_PATH, VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);
        var vaultEntry = Optional.ofNullable(vaultKeyValueOperations.get(credentialsKey, CredentialsDTO.class))
                .orElseThrow(() -> new VaultKeyNotFoundException(credentialsKey));
        return Optional.ofNullable(vaultEntry.getData())
                .orElseThrow(() -> new VaultKeyNotFoundException(credentialsKey));
    }

    public void putCredentials(String credentialsKey, CredentialsDTO credentialsDTO) {
        var vaultKeyValueOperations = vaultTemplate.opsForKeyValue(ROOT_PATH, VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);
        vaultKeyValueOperations.put(credentialsKey, credentialsDTO);
    }

}
