# Using key vault Java application


## In the code, see HelloController.java#18 for an example on how to get secrets from the vault.
## Follow common steps on how to turn on System Managed Identity and create a secret&an access policy.
## Check pom.xml for the new dependency:
```xml
<dependency>
    <groupId>com.azure</groupId>
    <artifactId>azure-identity</artifactId>
    <version>1.0.4</version>
</dependency>
<dependency>
    <groupId>com.azure</groupId>
    <artifactId>azure-core</artifactId>
    <version>1.3.0</version>
</dependency>
<dependency>
    <groupId>com.azure</groupId>
    <artifactId>azure-security-keyvault-secrets</artifactId>
    <version>4.1.0</version>
</dependency>
```
