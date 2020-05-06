# Using a key vault from a NodeJS app

## Follow the common steps to set up a vault+secrets and an identity for the app.
## See index.js#26 for example on how to get a secret from the vault.
## Check packages.json for the dependencies:
`At the time of writing only the preview version handles auth correctly`
```json
"dependencies": {
        "@azure/keyvault-secrets": "4.0.0",
        "@azure/identity": "1.1.0-preview1"
    }
```