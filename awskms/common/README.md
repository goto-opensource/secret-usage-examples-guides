# Using AWS KMS in various AWS services (Beanstalk, EC2, lambda) in various languages/frameworks

## Create a Customer Managed Key in KMS (next-next-finish, aside the alias, leave everything on default)
![](1.png)
![](2.png)

## Generate a secret with AWS CLI, using this newly created key
## If you CLI's role does not have access to the key by default (does not have Admin access policy), do the same as with the application's policy and add explicit access to it (follow steps below).
## You should type and see something like this:
```sh
aws.exe kms encrypt --key-id KEYID --plaintext STUFF_TO_ENCRYPT
```
![](secret.png)

## If you try to use the key in an application now to decrypt something, it will fail - we need to allow the apps to use the key explicitly.
![](appexample.png)
![](fail.png)

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

## After a few seconds, trying to use the key again will yield a success
![](success.png)
