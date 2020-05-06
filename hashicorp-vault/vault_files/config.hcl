ui = true

storage "file" {
    path = ".\\data"
  }

listener "tcp" {
  address     = "127.0.0.1:8200"
  tls_disable = false
  tls_min_version = "tls12"
  tls_key_file = ".\\vault.key"
  tls_cert_file = ".\\vault.cert"
}
