import base64
from wsgiref.simple_server import make_server


def application(environ, start_response):
    path = environ['PATH_INFO']
    method = environ['REQUEST_METHOD']
    try:
        import boto3
        ssm_client = boto3.client('ssm', region_name='us-east-2')

        response = ssm_client.get_parameter(
            Name="TestSecretParameter",
            WithDecryption=True
        )['Parameter']['Value']

    except Exception as e:
        response = str(e)

    status = '200 OK'
    headers = [('Content-type', 'text/html')]

    start_response(status, headers)
    return [response]


if __name__ == '__main__':
    httpd = make_server('', 8000, application)
    print("Serving on port 8000...")
    httpd.serve_forever()
