# Using a AWS SSM Parameter Store from Beanstalk (.NET) 

## Follow the instructions in the `common` directory on how to set up a key and a policy (and also how to generate encrypted data). 
## In the code, check MVC5App\Controllers\HomeController.cs#23 to see an example on how to use the secret at run-time.
## Check MVC5App.csproj for dependencies (AWSSDK.SimpleSystemsManagement NuGet package)!