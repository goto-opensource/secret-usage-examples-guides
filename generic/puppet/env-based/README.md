# Using environmental variables in puppet

The functionality needs the creation of a server-side function - present on the master.
Copy env.rb to the specified directory (line 0 in the file).
Now You can use the function in manifests, like in site.pp.
To specify env. variables available to puppet master, one of the possible ways to do this is via the systemctl file of the puppet master service.
The environmental variable needs to be present on the puppetmaster only - an example is in the service file. Note: do not push the secret in git, like i did - SSH (or alternative) onto the master, and set it up manually. If you need to store&share it somewhere, store&share it in LastPass.