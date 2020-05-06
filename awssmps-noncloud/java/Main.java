import java.nio.ByteBuffer;

import com.amazonaws.auth.ContainerCredentialsProvider;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClient;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;

public class Main {

    public static void main(String[] args) {
        AWSSimpleSystemsManagement client = AWSSimpleSystemsManagementClientBuilder.defaultClient();
        System.out.println(String(
                client.getParameter(new GetParameterRequest().withName("TestSecretParameter").withWithDecryption(true))
                        .getParameter().getValue()));
    }
}