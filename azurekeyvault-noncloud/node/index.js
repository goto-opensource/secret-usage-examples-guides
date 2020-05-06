const { DefaultAzureCredential } = require('@azure/identity');
const { SecretClient } = require('@azure/keyvault-secrets');

async function name() {
    try {
        const credential = new DefaultAzureCredential();
        const url = "https://test-vault-234567876354.vault.azure.net/";
        const client = new SecretClient(url, credential);
        const secretName = "test";
        const secret = await client.getSecret(secretName);
        console.log(`Your secret value is: ${secret.value}`);
    } catch (error) {
        console.log(error);
    }
}

name()