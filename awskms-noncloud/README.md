# AWS Key Management Service

```
AWS Key Management Service (KMS) makes it easy for you to create and manage cryptographic keys and control their use across a wide range of AWS services and in your applications. AWS KMS is a secure and resilient service that uses hardware security modules that have been validated under FIPS 140-2, or are in the process of being validated, to protect your keys. AWS KMS is integrated with AWS CloudTrail to provide you with logs of all key usage to help meet your regulatory and compliance needs.
```
> https://aws.amazon.com/kms/

In practice, AWS KMS is the way to securely encrypt and decrypt secrets at runtime. Everything is managed centrally, and during normal operation, the keys never leave AWS boundaries. In the code, using your identities, You can refer the keys and decrypt data with them. In these folder You will find examples on how to do this in various frameworks and languages.
