process.env.VAULT_SKIP_VERIFY = true; // only for self signed cert!
const Vault = require("node-vault")
const options = {
    apiVersion: 'v1',
    endpoint: 'https://127.0.0.1:8200',
    token: "s.FeIhZtfWYrZ0kLNC3mlTl499"
};

async function main() {

    var vault = Vault(options);
    console.log((await vault.read('apikeys_prod/data/keys'))['data']['data']['foobar']);
}

main()