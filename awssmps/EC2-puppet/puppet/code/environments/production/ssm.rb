# $ENVIRONMENT/lib/puppet/functions/ssm.rb
Puppet::Functions.create_function(:ssm) do
    begin
        require 'aws-sdk-ssm'
    rescue LoadError => e
        raise Puppet::DataBinding::LookupError, "Must install gem aws-sdk-ssm to use hiera_ssm_paramstore"
    end

    dispatch :ssm do
        param 'String', ssmvar
    end

    def ssm(ssmvar)
        variable = args[0]
        ssmc = Aws::SSM::Client.new()
        return ssmc.get_parameter({
            name: variable,
            with_decryption: true
        }).parameter.value
    end
end