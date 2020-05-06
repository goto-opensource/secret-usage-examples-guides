package org.example;

import com.amazonaws.auth.ContainerCredentialsProvider;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClient;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
import com.amazonaws.util.EC2MetadataUtils;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.util.Base64;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.entities.Subsegment;
import com.amazonaws.xray.handlers.TracingHandler;
import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import com.amazonaws.xray.plugins.ElasticBeanstalkPlugin;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.EnumSet;
import java.util.Scanner;

public class Application {
    private static final String BUCKET_NAME = String.format("elasticbeanstalk-samples-%s",
            Regions.getCurrentRegion().getName());
    private static final String OBJECT_KEY = "java-sample-app-v2.zip";

    private static String INDEX_HTML = "";

    // Initialize the global AWS XRay Recorder with the Elastic Beanstalk plugin to
    // trace environment metadata

    static {
        AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard().withPlugin(new ElasticBeanstalkPlugin());
        AWSXRay.setGlobalRecorder(builder.build());
    }

    // Create AWS clients instrumented with AWS XRay tracing handler

    private static final AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withRegion(Regions.getCurrentRegion().getName()).withRequestHandlers(new TracingHandler()).build();

    private static String loadIndex() {
        final String fileName = isXRayEnabled() ? "/index_xray.html" : "/index_default.html";
        try {
            final String page = new Scanner(Application.class.getResourceAsStream(fileName), "UTF-8")
                    .useDelimiter("\\A").next();
            return page;
        } catch (final Exception exception) {
            return getStackTrace(exception);
        }
    }

    private static String getStackTrace(final Throwable throwable) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter, true);
        throwable.printStackTrace(printWriter);

        return stringWriter.getBuffer().toString();
    }

    private static int getPort() {
        return Integer.parseInt(System.getenv().get("PORT"));
    }

    private static boolean isXRayEnabled() {
        return Boolean.valueOf(System.getenv("XRAY_ENABLED"));
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(getPort());
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(HomeServlet.class, "/*");
        handler.addServletWithMapping(TraceServlet.class, "/trace");
        handler.addServletWithMapping(CronServlet.class, "/crontask");
        try {
            AWSSimpleSystemsManagement client = AWSSimpleSystemsManagementClientBuilder.defaultClient();
            GetParameterRequest paramreq = new GetParameterRequest().withName("TestSecretParameter").withWithDecryption(true);
            GetParameterResult parameter = client.getParameter(paramreq);
            INDEX_HTML = new String(parameter.getParameter().getValue());
        } catch (Exception e) {
            INDEX_HTML = e.getMessage() + "\n" + e.getStackTrace() + "\n" + EC2MetadataUtils.getIAMInstanceProfileInfo().instanceProfileArn + "\n";
        }
        if (isXRayEnabled()) {
            FilterHolder filterHolder = handler.addFilterWithMapping(AWSXRayServletFilter.class, "/*",
                    EnumSet.of(DispatcherType.REQUEST));
            filterHolder.setInitParameter("dynamicNamingFallbackName", "ElasticBeanstalkSample");
            filterHolder.setInitParameter("dynamicNamingRecognizedHosts", "*");
        }
        server.setHandler(handler);
        server.start();
        server.join();
    }

    public static class HomeServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(INDEX_HTML);
        }
    }

    public static class CronServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Process Task Here.");
        }
    }

    public static class TraceServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

            if (isXRayEnabled()) {
                traceS3();
            }
        }

        private void traceS3() {
            // Add subsegment to current request to track call to S3
            Subsegment subsegment = AWSXRay.beginSubsegment("## Getting object metadata");
            try {
                // Gets metadata about this sample app object in S3
                s3Client.getObjectMetadata(BUCKET_NAME, OBJECT_KEY);
            } catch (Exception ex) {
                subsegment.addException(ex);
            } finally {
                AWSXRay.endSubsegment();
            }
        }
    }
}