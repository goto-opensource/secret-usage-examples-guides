const aws = require('aws-sdk')
aws.config.update({ region: 'us-east-2' });
async function main() {

    const newLocal = new aws.SSM();
    console.log((await newLocal.getParameter({ Name: 'TestSecretParameter', WithDecryption: true }).promise()).Parameter.Value);
}

main()