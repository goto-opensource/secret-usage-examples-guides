node 'client' {
  file { '/data/api_key':
    ensure  => present,
    mode    => '0444',
    content =>  lookup('ApiKey')
  }
}
