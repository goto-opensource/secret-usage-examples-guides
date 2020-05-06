node 'client' {
  ssh_private_key { 'bob':
    ensure => present,
    user   => root,
    type   => rsa,
    key    => file('/keys/private.client.key') # do not store the key in git. it only needs to be present on the master and client, and nowhere else
  }
}
