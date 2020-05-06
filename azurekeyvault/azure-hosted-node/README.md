# NodeJS App Service in Azure
> https://docs.microsoft.com/en-us/samples/azure-samples/key-vault-node-getting-started/quickstart-set-and-retrieve-a-secret-from-azure-key-vault-using-a-node-web-app/

## Follow the common steps to set up a vault+secrets and an identity for the app.
## See index.js#26 for example on how to get a secret from the vault.
## Check packages.json for the dependencies:
```json
"dependencies": {
        "@azure/keyvault-secrets": "4.0.0",
        "@azure/identity": "1.0.0"
    }
```