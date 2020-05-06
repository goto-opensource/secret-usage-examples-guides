import base64
from wsgiref.simple_server import make_server

def application(environ, start_response):
    path    = environ['PATH_INFO']
    method  = environ['REQUEST_METHOD']
    try:
        import boto3
        kms_client = boto3.client('kms', region_name='us-east-2')
        # Decrypt a data key
        ciphertext = base64.b64decode('AQICAHhz3DTDbBFKvcH3h3G0XcAydE7z0NSuctiln97zJ5nE4wFxA5N+tzlgp802MoxbiGzFAAAAazBpBgkqhkiG9w0BBwagXDBaAgEAMFUGCSqGSIb3DQEHATAeBglghkgBZQMEAS4wEQQMKLqxiMZZV/jDc05yAgEQgCh4shBdE4wRPXLFtrp+4ImXFimaBz78P2WDWOA9TpxE1Ye57CyqTIJh')

        response = kms_client.decrypt(
            CiphertextBlob=ciphertext
        )['Plaintext']
            
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
