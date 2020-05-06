using System;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using Amazon.SimpleSystemsManagement;
using Amazon.SimpleSystemsManagement.Model;

namespace dotnet
{
    class Program
    {
        static async Task Main(string[] args)
        {
            AmazonSimpleSystemsManagementClient ssmClient = new AmazonSimpleSystemsManagementClient();
            Console.WriteLine((await ssmClient.GetParameterAsync(new GetParameterRequest() { Name = "TestSecretParameter", WithDecryption = true })).Parameter.Value);
            return;
        }
    }
}
