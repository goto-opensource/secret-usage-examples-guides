# $ENVIRONMENT/lib/puppet/functions/env.rb
Puppet::Functions.create_function(:env) do
    dispatch :env do
        param 'String', envvar
    end

    def env(envvar)
        variable = args[0]
        return ENV[variable]
    end
end
