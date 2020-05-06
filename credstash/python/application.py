import credstash


# class Object:
#     pass
# 
# 
# region = Object()
# region.save_kms_region = 'us-east-2'
# credstash.setKmsRegion(region) # NOT A STRING! needs to have save_kms_region field

print(credstash.getSecret('foo'))