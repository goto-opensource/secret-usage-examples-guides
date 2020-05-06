package com.company;

import com.azure.core.credential.TokenCredential;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;

public class Main {

    public static void main(String[] args) {
        TokenCredential a = new DefaultAzureCredentialBuilder().build();
        SecretClient client = new SecretClientBuilder()
                .vaultUrl("https://test-vault-234567876354.vault.azure.net")
                .credential(a)
                .buildClient();

        KeyVaultSecret test = client.getSecret("test");
        System.out.println("Your secret is " + test.getValue());
    }
}
