from flask import Flask
app = Flask(__name__)

@app.route("/")
def hello():
    try:
        from azure.keyvault.secrets import SecretClient
        from azure.identity import DefaultAzureCredential
        KVUri = "https://test-vault-234567876354.vault.azure.net"
        credential = DefaultAzureCredential()
        client = SecretClient(vault_url=KVUri, credential=DefaultAzureCredential())
        secretName = "test"
        retrieved_secret = client.get_secret(secretName)
        return retrieved_secret.value
    except Exception as ex:
        return ex.message