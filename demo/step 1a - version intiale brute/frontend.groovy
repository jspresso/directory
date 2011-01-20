// Implement your application frontend here using the SJS DSL.
controller ('directory.controller',
    icon:'directory.png',
    context:'directory',
    language:'fr',
    workspaces:['directory.workspace'])

workspace('directory.workspace', 
    icon:'directory.png') { 
      
      filterModule('contact.module',
          component:'Contact')  
      
      filterModule('phoneNumber.module',
          component:'PhoneNumber')      
      
      filterModule('category.module',
        component:'Category')
    }

