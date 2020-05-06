using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Vault;

namespace dotnet
{
    class Program
    {
        static async Task Main(string[] args)
        {
            string engine = "exttest", path = "anyad", key = "kurva", secret = "komolyan";
            var vaultClient = new VaultClient(new Uri("https://127.0.0.1:8200"), System.Environment.GetEnvironmentVariable("VAULT_TOKEN"));
            var data = new Dictionary<string, string> { { key, secret } };
            vaultClient.Secret.Write(engine + '/' + path, data).Wait();
        }
    }
}
