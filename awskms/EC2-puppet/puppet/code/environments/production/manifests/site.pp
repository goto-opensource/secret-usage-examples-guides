node 'client' {
  file { '/tmp/testkeywrite':
    ensure => 'present',
    mode => '0444',
    content => lookup('mysecret'),
  }
}
