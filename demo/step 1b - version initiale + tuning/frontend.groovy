// Implement your application frontend here using the SJS DSL.
controller ('directory.controller',
    icon:'directory.png',
    context:'directory',
    language:'fr',
    workspaces:['directory.workspace'])

workspace('directory.workspace', 
    icon:'directory.png') { 
      
      filterModule('contact.module',
          component:'Contact',
          detailView:'Contact.view') // STEP 1B
      
      filterModule('phoneNumber.module',
          component:'PhoneNumber')      
      
      filterModule('category.module',
        component:'Category')
    }
