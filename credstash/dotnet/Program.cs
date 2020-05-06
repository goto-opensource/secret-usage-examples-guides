using System;
using System.Threading.Tasks;
using Amazon;
using Narochno.Credstash;

namespace dotnet
{
    class Program
    {
        static async Task Main(string[] args)
        {
            var credstash = CredstashBuilder.WithRegion(RegionEndpoint.USEast2);

            var secret = await credstash.GetSecretAsync("foo");
            Console.WriteLine(secret.HasValue ? secret.Value : throw new ApplicationException("No such secret!"));
            return;
        }
    }
}
