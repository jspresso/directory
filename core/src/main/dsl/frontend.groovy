// Implement your application frontend here using the SJS DSL.
controller ('directory.controller',
		icon:'directory.png',
		context:'directory',
		language:'fr',
		workspaces:['directory.workspace'])

workspace('directory.workspace', 
		icon:'directory.png') { 
			
      filterModule('customer.module',
        component:'Customer',
        detailView:'Customer.view')
    
			filterModule('contact.module',
    			component:'Contact',
    			detailView:'Contact.view')  
			
			filterModule('category.module',
					component:'Category') 
			
			filterModule('phoneNumber.module',
					component:'PhoneNumber',
					moduleView:'PhoneNumber.table')          
		}

/*    
bean 'viewFactoryBase', parent:'abstractViewFactory',
custom: [defaultActionMapRenderingOptions:'LABEL_ICON']
*/
  