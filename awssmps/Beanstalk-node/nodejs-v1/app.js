const aws = require('aws-sdk')
aws.config.update({ region: 'us-east-2' });

var port = process.env.PORT || 3000,
    http = require('http'),
    fs = require('fs');

var log = function (entry) {
    fs.appendFileSync('/tmp/sample-app.log', new Date().toISOString() + ' - ' + entry + '\n');
};

var server = http.createServer(function (req, res) {
    try {
        var params = {
            Name: 'TestSecretParameter', /* required */
            WithDecryption: true
        };
        (new aws.SSM()).getParameter(params, (err, data) => {
            if (err) {
                res.writeHead(200);
                res.write(err.toString());
                res.end();
            }
            else {
                res.writeHead(200);
                res.write(data.Parameter.Value);
                res.end();
            }
        });
    } catch (error) {
        res.writeHead(200);
        res.write(error);
        res.end();
    }
});

// Listen on port 3000, IP defaults to 127.0.0.1
server.listen(port);

// Put a friendly message on the terminal
console.log('Server running at http://127.0.0.1:' + port + '/');
