using System;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using Amazon.KeyManagementService;
using Amazon.KeyManagementService.Model;

namespace dotnet
{
    class Program
    {
        static async Task Main(string[] args)
        {
            AmazonKeyManagementServiceClient kmsClient = new AmazonKeyManagementServiceClient();
            Console.WriteLine(Encoding.ASCII.GetString((await kmsClient.DecryptAsync(new DecryptRequest()
            {
                CiphertextBlob = new MemoryStream(System.Convert.FromBase64String("AQICAHhz3DTDbBFKvcH3h3G0XcAydE7z0NSuctiln97zJ5nE4wFxA5N+tzlgp802MoxbiGzFAAAAazBpBgkqhkiG9w0BBwagXDBaAgEAMFUGCSqGSIb3DQEHATAeBglghkgBZQMEAS4wEQQMKLqxiMZZV/jDc05yAgEQgCh4shBdE4wRPXLFtrp+4ImXFimaBz78P2WDWOA9TpxE1Ye57CyqTIJh"))
            })).Plaintext.ToArray()));

            return;
        }
    }
}
