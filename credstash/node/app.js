let Credstash = require('nodecredstash');

let credstash = new Credstash({ awsOpts: { region: 'us-east-2' } });

credstash.getSecret({ name: 'foo', version: 1 })
    .then(secret => console.log(secret));