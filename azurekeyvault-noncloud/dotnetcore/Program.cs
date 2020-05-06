using System;
using System.Threading.Tasks;
using Microsoft.Azure.KeyVault;
using Microsoft.Azure.Services.AppAuthentication;

namespace asp
{
    public class Program
    {
        public static async Task Main(string[] args)
        {
            try
            {
                AzureServiceTokenProvider azureServiceTokenProvider = new AzureServiceTokenProvider();
                KeyVaultClient keyVaultClient = new KeyVaultClient(new KeyVaultClient.AuthenticationCallback(azureServiceTokenProvider.KeyVaultTokenCallback));
                var secret = await keyVaultClient.GetSecretAsync("https://test-vault-234567876354.vault.azure.net/secrets/test").ConfigureAwait(false);
                System.Console.WriteLine($"Your secret is: {secret.Value}!");

            }
            catch (Exception ex)
            {
                System.Console.WriteLine($"{ex.Message + ex.StackTrace}!");
            }
        }

    }
}
