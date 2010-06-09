// Implement your application frontend here using the SJS DSL.
controller ('directory.controller',
		icon:'directory.png',
		context:'directory',
		language:'en',
		workspaces:['directory.workspace'])

workspace('directory.workspace', 
		icon:'directory.png') { 
			
			filterModule('contact.module',
    			component:'Contact',
    			detailView:'contact.view')  
			
			filterModule('category.module',
					component:'Category') 
			
			filterModule('phoneNumber.module',
					component:'PhoneNumber',
					moduleView:'phoneNumber.table')          
		}