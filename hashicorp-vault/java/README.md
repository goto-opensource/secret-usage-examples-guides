# Using a Hashicorp vault from Java 

## Follow the instructions in the `common` directory on how to set up a vault, create a secret and a policy+token to read it.
## In the code, check Program.java to see an example on how to use the secret at run-time.
## Notice `-Djdk.tls.client.protocols=TLSv1.2` in the VM options. Without this You might encounter client side exceptions.
## Check pom.xml for dependencies.
```xml
<dependency>
    <groupId>com.bettercloud</groupId>
    <artifactId>vault-java-driver</artifactId>
    <version>5.1.0</version>
</dependency>
```