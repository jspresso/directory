// Implement your domain here using the SJS DSL.
Interface('Traceable',
  interceptors:'TraceableLifecycleInterceptor', 
  uncloned:['createdTimestamp', 'updatedTimestamp'])
{
  date_time 'createdTimestamp', readOnly:true
  date_time 'updatedTimestamp', readOnly:true 
}

Entity ('Contact', icon:'user.png', 
  extend:['Traceable'],  
  extension:'ContactExtension',
  rendered:['lastname', 'firstname', 'category', 'status'],
  ordering:['lastname':'ASCENDING'],
  toString:'fullname', 
  autoComplete:'lastname') 
{
  
  string_64 'lastname', mandatory:true, unicityScope:'name'
  string_64 'firstname', mandatory:true, unicityScope:'name'
  text 'comments'
  
  set 'phoneNumbers', composition:true, ref:'PhoneNumber'
  reference 'category', ref:'Category', composition:false, reverse:'Category-contacts'
  
  enumeration 'status', values:['0', '1'], enumName:'contact.status'
  set 'activities', ref:'Activity', reverse:'Activity-contacts'    
  
  string 'fullname', computed:true
}

Entity ('PhoneNumber', icon:'phone.png',
  queryable:['type', 'contact.category', 'contact.lastname', 'contact.firstname'],
  rendered:['number', 'contact.lastname', 'contact.firstname', 'type'])
{ 
  string_24 'number', mandatory:true, regex:'^[0-9\\+ ]*$', regexSample: '+33 1 123456...'
  reference 'contact', ref:'Contact', reverse:'Contact-phoneNumbers' 
  enumeration 'type', values:['mobile', 'home', 'work'], enumName:'number.type'                                                                     
}

Entity ('Category', icon:'bookmark.png', 
  extension:'CategoryExtension')
{   
  string_64 'categoryname', mandatory:true  
  
  set 'subCategories', ref:'Category', composition:false          
  reference 'parentCategory', ref:'Category', reverse:'Category-subCategories'
  
  set 'contacts', ref:'Contact'  
  
  set 'allContacts', ref:'Contact', computed:true
  integer 'allContactsCount', computed:true
}         

Entity ('Address', 
  extension:'AddressExtension', 
  rendered:['street', 'city', 'zip', 'country'],
  icon:'address.png') 
{
  string_256 'street', mandatory:true
  string_128 'city', mandatory:true
  string_16 'zip', mandatory:true
  string_32 'country', mandatory:true 
  
  string 'fullDescription', computed:true
}                

Entity ('Activity', icon:'activity.png',
  extension:'ActivityExtension', 
  rendered:['activityname', 'activitydescription'])
{
  string_64 'activityname', mandatory:true
  string_256 'activitydescription' 
  
  set 'contacts', ref:'Contact'
  
  integer 'contactsCount', computed:true
  integer 'contactsCountActive', computed:true
  integer 'contactsCountInactive', computed:true
}


namespace('bean') {

  Component ('Statistics', icon:'statistics.png') {
    set 'activities', ref:'Activity', readOnly:true
    set 'categories', ref:'CategoryStat', readOnly:true
  }
  
  Component ('CategoryStat') {
    reference 'category', ref:'Category', readOnly:true
    set 'activities', ref:'ActivityStat', readOnly:true
  }
  
  Component ('ActivityStat') {
    reference 'activity', ref:'Activity', readOnly:true
    set 'contacts', ref:'Contact', readOnly:true
  
    integer 'contactsCount', readOnly:true
  }
  
}

