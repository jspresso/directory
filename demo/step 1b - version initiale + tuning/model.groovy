// Implement your domain here using the SJS DSL.
Entity ('Contact', icon:'user.png') {
  string_64 'lastname', mandatory:true
  string_64 'firstname', mandatory:true
  text 'comments'
  
  set 'phoneNumbers', composition:true, ref:'PhoneNumber'
  reference 'category', ref:'Category'
  enumeration 'status', values:['active', 'inactive'], enumName:'contact.status'
}

Entity ('PhoneNumber', icon:'phone.png',
  queryable:['contact.lastname', 'contact.firstname', 'type'], // STEP 1B
  rendered:['contact.lastname', 'contact.firstname', 'type', 'number']) { // STEP 1B
    string_24 'number', mandatory:true
    enumeration 'type', values:['mobile', 'home', 'work'], enumName:'number.type'      
    reference 'contact', ref:'Contact', reverse:'Contact-phoneNumbers' // STEP 1B
  }

Entity ('Category', icon:'usergroup.png') {   
  string_64 'categoryname', mandatory:true  
  set 'subCategories', ref:'Category'          
}         
