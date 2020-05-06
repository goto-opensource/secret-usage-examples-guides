import java.nio.ByteBuffer;

import com.amazonaws.auth.ContainerCredentialsProvider;
import com.amazonaws.util.EC2MetadataUtils;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.util.Base64;

public class Main {

    public static void main(String[] args) {
        AWSKMS kmsClient = AWSKMSClientBuilder.defaultClient();
        System.out.println(new String(kmsClient
                .decrypt(new DecryptRequest().withCiphertextBlob(ByteBuffer.wrap(Base64.decode(
                        "AQICAHhz3DTDbBFKvcH3h3G0XcAydE7z0NSuctiln97zJ5nE4wFxA5N+tzlgp802MoxbiGzFAAAAazBpBgkqhkiG9w0BBwagXDBaAgEAMFUGCSqGSIb3DQEHATAeBglghkgBZQMEAS4wEQQMKLqxiMZZV/jDc05yAgEQgCh4shBdE4wRPXLFtrp+4ImXFimaBz78P2WDWOA9TpxE1Ye57CyqTIJh"))))
                .getPlaintext().array()));

    }
}