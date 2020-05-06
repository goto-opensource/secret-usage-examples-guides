import com.bettercloud.vault.SslConfig;
import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.response.LogicalResponse;

public class Program {
    public static void main(String[] args) throws Exception {


        final VaultConfig config = new VaultConfig()
                .address("https://127.0.0.1:8200")
                .token(System.getenv("VAULT_TOKEN"))
                .engineVersion(2)
                .build();
        config.sslConfig(new SslConfig().verify(false).build()); // Only for local development/self signed cert. please use good certs in production.
        final Vault vault = new Vault(config);

        LogicalResponse read = vault.logical().read("apikeys_prod/keys");
        System.out.println(read.getData().get("foobar"));

    }
}
