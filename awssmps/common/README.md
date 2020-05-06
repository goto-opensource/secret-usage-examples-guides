# Using AWS SSM Parameter Store in various AWS services (Beanstalk, EC2, lambda) in various languages/frameworks

## To utilize encrypted parameters, first we need to create a Key that will be used for encryption.
## Create a Customer Managed Key in KMS (next-next-finish, aside the alias, leave everything on default)
![](1.png)
![](2.png)

## Navigate to the policy setting of the application's role (in our example, the application uses the role `Beanstalk2`)
![](3.png)
## Edit the policy settings for the role, by adding an inline policy
![](4.png)
![](5.png)
## Select KMS as the service and add Write/Decrypt action
![](6.png)
## Specify the ARN of the newly created key from KMS
![](7.png)
![](8.png)
## Review, name and create the policy
![](9.png)

## Now we can use this key when creating parameters.
## In Systems Manager/Parameter Store create a new parameter.
![](10.png)
## User Secure String and choose the newly created KMS key for encryption.
![](11.png)

## Finish the creation by clicking `Create parameter`
## Navigate to the app policy settings again and create a new inline policy.
## As Service, select Systems Manager and in action choose Read/GetParameter
![](12.png)
## Specify the resource
![](13.png)
## Review and create the policy.

## Your application now can use the parameter. For examples, see the projects in the directory.
