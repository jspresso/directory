// Implement your domain here using the SJS DSL.
Entity('Customer', icon:'customer.png') {
  string_64 'customerName', mandatory:true, unicityScope:'name'
  date 'sinceDate', mandatory:true
  text 'comments'
  
  set 'contacts', ref:'Contact'
  set 'addresses', ref:'Address'
}

Entity ('Contact', icon:'user.png') {
  string_64 'lastname', mandatory:true, unicityScope:'name'
  string_64 'firstname', mandatory:true, unicityScope:'name'
  text 'comments'
  
  set 'phoneNumbers', composition:true, ref:'PhoneNumber'
  reference 'category', ref:'Category'   
  enumeration 'status', values:['0', '1'], enumName:'contact.status'
  set 'activities', ref:'Activity'    
}

Entity ('PhoneNumber', icon:'phone.png',
    queryable:['contact.lastname', 'contact.firstname', 'type', 'contact.category'],            
    rendered:['number', 'contact.lastname', 'contact.firstname', 'type', 'contact.category']) { 
      enumeration 'type', values:['mobile', 'home', 'work'], enumName:'number.type'                                                                     
      string_24 'number', mandatory:true, regex:'^[0-9\\+ ]*$', regexSample: '+33 1 123456...'
      reference 'contact', ref:'Contact', reverse:'Contact-phoneNumbers' 
    }

Entity ('Category', icon:'usergroup.png') {   
  string_64 'categoryname', mandatory:true  
  set 'subCategories', ref:'Category'          
}         

Entity ('Address', icon:'address.png') {
  string_256 'street', mandatory:true
  string_128 'city', mandatory:true
  string_16 'zip', mandatory:true
  string_32 'country', mandatory:true
}                

Entity ('Activity') {
  string_64 'activityname', mandatory:true
  string_256 'activitydescription' 
}