// Implement your application frontend here using the SJS DSL.
controller ('directory.controller',
    icon:'directory.png',
    context:'directory',
    language:'fr',
    workspaces:['directory.workspace', 'referential.workspace'])

workspace('directory.workspace', 
    icon:'directory.png') { 
      
      filterModule('customer.module',
          component:'Customer',
          detailView:'Customer.view')
      
      filterModule('contact.module',
          component:'Contact',
          detailView:'Contact.view')  
      
      filterModule('phoneNumber.module',
          component:'PhoneNumber',
          moduleView:'PhoneNumber.table')          
    }

workspace('referential.workspace',
    icon:'directory.png') { 
      
      filterModule('category.module',
          component:'Category', 
          moduleView:'Category.view') 
      
      filterModule('activities.module',
          component:'Activity')
    }

/*    
 bean 'viewFactoryBase', parent:'abstractViewFactory',
 custom: [defaultActionMapRenderingOptions:'LABEL_ICON']
 */
