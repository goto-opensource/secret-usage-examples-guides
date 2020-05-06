from azure.keyvault.secrets import SecretClient
from azure.identity import DefaultAzureCredential, SharedTokenCacheCredential

try:
    KVUri = "https://test-vault-234567876354.vault.azure.net"
    credential = DefaultAzureCredential()
    client = SecretClient(vault_url=KVUri, credential=credential)
    secretName = "test"
    retrieved_secret = client.get_secret(secretName)
    print(retrieved_secret.value)
except Exception as ex:
    print(ex)