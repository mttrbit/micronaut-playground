package hello.ciot.adapters.security;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties("micronaut.security.credentials")
public class CredentialsConfig {
    @NotBlank
    private String username = "username";

    @NotBlank
    private String password = "password";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
