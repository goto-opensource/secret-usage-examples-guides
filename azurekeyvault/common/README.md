# Using Azure keyvault

## Create a new keyvault:
![](1.png)

## Name it, specify resource group, region, etc. then clink `Review + create`
![](2.png)
![](3.png)

## Create a secret
![](8.png)
![](9.png)

## On your app service/anything you want to use this secret, turn on `System managed identity`
![](5.png)

## On the Key vault, create a new `Access policy`
![](10.png)

## Specify `Get` permission for the principal of your choice
![](11.png)

## Save the new policy
![](12.png)

## Your app now can use the secret in the vault - for an example, see the `azure-hosted-dotnetcore` project.