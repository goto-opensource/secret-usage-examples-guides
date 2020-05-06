import hvac
import os

client = hvac.Client(url='https://localhost:8200', verify=False) # use false for testing only (self signed cert on dev machine)
client.token = os.environ['VAULT_TOKEN']
secret = client.secrets.kv.v2.read_secret_version(mount_point="apikeys_prod", path='keys') # https://hvac.readthedocs.io/en/stable/source/hvac_api_secrets_engines.html#hvac.api.secrets_engines.KvV2.read_secret_version
print(secret['data']['data']['foobar'])