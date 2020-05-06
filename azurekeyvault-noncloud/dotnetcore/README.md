# Using a Key vault from a .NET Core app

## Follow the instructions in the `common` directory on how to set up a vault and give permissons. 
## In the code, check Program.cs to see an example on how to get the secret at run-time.
## Check asp.csproj for dependencies.
```xml
  <ItemGroup>
    <PackageReference Include="Microsoft.Azure.KeyVault" Version="3.0.5" />
    <PackageReference Include="Microsoft.Azure.KeyVault.Core" Version="3.0.5" />
    <PackageReference Include="Microsoft.Azure.Services.AppAuthentication" Version="1.4.0" />
  </ItemGroup>
```