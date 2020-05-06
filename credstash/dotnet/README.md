# Using a Credstash from .NET)

## Follow the instructions in the `common` directory.
## In the code, check Program.cs to see an example on how to use the secret at run-time. I've made a helper class to make instantiation of Credstash in code easier.
## Check dotnet.csproj for dependencies (Narochno.Credstash NuGet package)!
```xml
<ItemGroup>
    <PackageReference Include="Narochno.Credstash" Version="*"/>
  </ItemGroup>
```