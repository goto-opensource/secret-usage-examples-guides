using Amazon.SimpleSystemsManagement;
using Amazon.SimpleSystemsManagement.Model;
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
            AmazonSimpleSystemsManagementClient ssmClient = new AmazonSimpleSystemsManagementClient();
            GetParameterRequest getParamReq = new GetParameterRequest()
            {
                Name = "TestSecretParameter",
                WithDecryption = true
            };
            GetParameterResponse getParameterResponse = ssmClient.GetParameter(getParamReq);
            ViewBag.Message = getParameterResponse.Parameter.Value; ;
            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }
    }
}