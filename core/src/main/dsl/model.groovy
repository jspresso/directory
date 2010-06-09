// Implement your domain here using the SJS DSL.
Entity ('Contact', icon:'user.png') {
	string_64 'lastname', mandatory:true, unicityScope:'name'
	string_64 'firstname', mandatory:true, unicityScope:'name'
	
	set 'phoneNumbers', composition:true, ref:'PhoneNumber'
	reference 'category', ref:'Category'       
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
}                                             