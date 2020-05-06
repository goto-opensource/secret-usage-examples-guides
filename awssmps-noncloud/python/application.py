import boto3
import base64

ssm_client = boto3.client('ssm', region_name='us-east-2')

print(ssm_client.get_parameter(Name="TestSecretParameter", WithDecryption=True)['Parameter']['Value'])
