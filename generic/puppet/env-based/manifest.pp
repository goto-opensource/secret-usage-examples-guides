node 'client' {
  file { '/data/api_key':
    ensure  => present,
    user    => technical_user,
    mode    => '0444',
    content => env('SECRET') # do not store the key in git. it only needs to be present on the master and client, and nowhere else
  }
}
