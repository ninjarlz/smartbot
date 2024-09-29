package pl.tul.smartbot.service.devmode;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;
import pl.tul.smartbot.config.properties.devmode.DevModeProperties;
import pl.tul.smartbot.model.response.devmode.DevModeAccessTokenResponse;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang3.BooleanUtils.TRUE;
import static pl.tul.smartbot.config.properties.devmode.DevModeProperties.DEV_MODE_PREFIX;
import static pl.tul.smartbot.config.properties.devmode.DevModeProperties.MOCK_AUTH_ENABLED_PROPERTY;
import static pl.tul.smartbot.util.constant.security.Permissions.READ_CHATBOT_PERMISSION;
import static pl.tul.smartbot.util.constant.security.Permissions.WRITE_CHATBOT_PERMISSION;
import static pl.tul.smartbot.util.constant.security.TokenStructure.BEARER_TOKEN_TYPE;
import static pl.tul.smartbot.util.constant.security.TokenStructure.SCOPES_CLAIM;
import static pl.tul.smartbot.util.constant.security.TokenStructure.TOKEN_TYPE;
import static pl.tul.smartbot.util.constant.security.TokenStructure.USER_ID_CLAIM;

@ConditionalOnProperty(prefix = DEV_MODE_PREFIX, name = MOCK_AUTH_ENABLED_PROPERTY, havingValue = TRUE)
@Service
@RequiredArgsConstructor
public class DevModeAuthService {

    private static final String SIGNING_ALGORITHM = "RS256";
    private static final String RSA_KEY_ID = "FS_KEY";
    private static final String JWKS_RESPONSE_FORMAT = "{\"keys\": [%s]}";

    private final OAuth2ResourceServerProperties oAuth2ResourceServerProperties;
    private final DevModeProperties devModeProperties;

    private RSAKey rsaKey;
    private String publicJwksJson;

    @PostConstruct
    private void generateRsaKeyPair() throws JOSEException {
        rsaKey = new RSAKeyGenerator(2048)
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(new Algorithm(SIGNING_ALGORITHM))
                .keyID(RSA_KEY_ID)
                .generate();

        var rsaPublicJWK = rsaKey.toPublicJWK();
        publicJwksJson = rsaPublicJWK.toJSONString();
    }

    public DevModeAccessTokenResponse getAccessTokenResponse() throws JOSEException {
        return DevModeAccessTokenResponse.builder()
                .accessToken(generateToken())
                .tokenType(BEARER_TOKEN_TYPE)
                .expiresIn(devModeProperties.getMockAuthTokenTimeout())
                .build();
    }

    public String getJwksResponse() {
        return JWKS_RESPONSE_FORMAT.formatted(publicJwksJson);
    }

    private String generateToken() throws JOSEException {
        var expiration = Instant.now().plus(devModeProperties.getMockAuthTokenTimeout(), ChronoUnit.SECONDS);
        var signer = new RSASSASigner(rsaKey);
        var claimsBuilder = new JWTClaimsSet.Builder()
                .expirationTime(Date.from(expiration))
                .issuer(oAuth2ResourceServerProperties.getJwt().getIssuerUri());
        var claims = getClaims();
        claims.forEach(claimsBuilder::claim);

        var signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256)
                .keyID(rsaKey.getKeyID()).type(new JOSEObjectType(TOKEN_TYPE)).build(), claimsBuilder.build());
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    private Map<String, Object> getClaims() {
        return Map.of(
                USER_ID_CLAIM, devModeProperties.getMockAuthUserId(),
                SCOPES_CLAIM, Set.of(getPermission())
        );
    }

    private String getPermission() {
        return switch (devModeProperties.getMockAuthPermission()) {
            case WRITE -> WRITE_CHATBOT_PERMISSION;
            case READ -> READ_CHATBOT_PERMISSION;
        };
    }
}

