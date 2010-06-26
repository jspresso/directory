// Implement your domain here using the SJS DSL.
Interface('Traceable',
  interceptors:'TraceableLifecycleInterceptor',
  uncloned:['createdTimestamp', 'updatedTimestamp'])
  {
    date_time 'createdTimestamp', readOnly:true
    date_time 'updatedTimestamp', readOnly:true
  }
  
Entity('Customer', icon:'customer.png', 
    extend:['Traceable'], 
    ordering:['customername':'ASCENDING']) {
  string_64 'customername', mandatory:true, unicityScope:'name'
  date 'sinceDate', mandatory:true
  text 'comments'
  
  set 'contacts', ref:'Contact'
  set 'addresses', ref:'Address'
}

Entity ('Contact', icon:'user.png', 
    extend:['Traceable'], 
    extension:'ContactExtension',
    ordering:['lastname':'ASCENDING']) {
  string_64 'lastname', mandatory:true, unicityScope:'name'
  string_64 'firstname', mandatory:true, unicityScope:'name'
  text 'comments'
  
  reference 'customer', ref:'Customer', unicityScope:'name', reverse:'Customer-contacts'
  
  set 'phoneNumbers', composition:true, ref:'PhoneNumber'
  reference 'category', ref:'Category', composition:false
  enumeration 'status', values:['0', '1'], enumName:'contact.status'
  set 'activities', ref:'Activity'    
}

Entity ('PhoneNumber', icon:'phone.png',
    queryable:['contact.customer', 'contact.category', 'contact.lastname', 'contact.firstname'],
    rendered:['number', 'contact.lastname', 'contact.firstname', 'type', 'contact.customer']) { 
      string_24 'number', mandatory:true, regex:'^[0-9\\+ ]*$', regexSample: '+33 1 123456...'
      reference 'contact', ref:'Contact', reverse:'Contact-phoneNumbers' 
      enumeration 'type', values:['mobile', 'home', 'work'], enumName:'number.type'                                                                     
    }

Entity ('Category', icon:'usergroup.png') {   
  string_64 'categoryname', mandatory:true  
  set 'subCategories', ref:'Category', composition:false          
}         

Entity ('Address', icon:'address.png') {
  string_256 'street', mandatory:true
  string_128 'city', mandatory:true
  string_16 'zip', mandatory:true
  string_32 'country', mandatory:true
}                

Entity ('Activity', icon:'activity.png') {
  string_64 'activityname', mandatory:true
  string_256 'activitydescription' 
}