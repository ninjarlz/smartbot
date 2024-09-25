package pl.tul.smartbot.exception.vault;

public class VaultKeyNotFoundException extends Exception {

    private static final String MESSAGE = "Vault entry with value for a key '%s' not found";

    public VaultKeyNotFoundException(String vaultKey) {
        super(MESSAGE.formatted(vaultKey));
    }
}
