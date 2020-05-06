using Amazon.KeyManagementService;
using Amazon.KeyManagementService.Model;
using MVC5App.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Mvc;

namespace MVC5App.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }

        public ActionResult About()
        {
            AmazonKeyManagementServiceClient kmsClient = new AmazonKeyManagementServiceClient();
            MemoryStream ciphertextBlob = new MemoryStream(System.Convert.FromBase64String("AQICAHhz3DTDbBFKvcH3h3G0XcAydE7z0NSuctiln97zJ5nE4wFxA5N+tzlgp802MoxbiGzFAAAAazBpBgkqhkiG9w0BBwagXDBaAgEAMFUGCSqGSIb3DQEHATAeBglghkgBZQMEAS4wEQQMKLqxiMZZV/jDc05yAgEQgCh4shBdE4wRPXLFtrp+4ImXFimaBz78P2WDWOA9TpxE1Ye57CyqTIJh"));
            DecryptRequest decryptRequest = new DecryptRequest()
            {
                CiphertextBlob = ciphertextBlob
            };
            MemoryStream plainText = kmsClient.Decrypt(decryptRequest).Plaintext;
            ViewBag.Message = Encoding.ASCII.GetString(plainText.ToArray()); ;
            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }
    }
}