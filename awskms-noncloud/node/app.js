const aws = require('aws-sdk')
aws.config.update({ region: 'us-east-2' });

async function main() {
    const kmsClient = new aws.KMS();
    const r = (await kmsClient.decrypt({ CiphertextBlob: Buffer.from("AQICAHhz3DTDbBFKvcH3h3G0XcAydE7z0NSuctiln97zJ5nE4wFxA5N+tzlgp802MoxbiGzFAAAAazBpBgkqhkiG9w0BBwagXDBaAgEAMFUGCSqGSIb3DQEHATAeBglghkgBZQMEAS4wEQQMKLqxiMZZV/jDc05yAgEQgCh4shBdE4wRPXLFtrp+4ImXFimaBz78P2WDWOA9TpxE1Ye57CyqTIJh", 'base64') }).promise()).Plaintext.toString();
    console.log(r);
}
main();