package com.example.springboot;

import com.azure.core.credential.TokenCredential;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;



@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        TokenCredential a = new DefaultAzureCredentialBuilder().build();
        SecretClient client = new SecretClientBuilder().vaultUrl("https://test-vault-234567876354.vault.azure.net").credential(new DefaultAzureCredentialBuilder().build()).buildClient();

        KeyVaultSecret test = client.getSecret("test");
        return "Greetings from Spring Boot! Your secret is " + test.getValue();
    }

}
