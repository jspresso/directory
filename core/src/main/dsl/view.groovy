// Implement your application view here using the SJS DSL.
split_vertical ('contact.view', 
		actionMap:'beanModuleActionMap') {
			top {
				form (model:'Contact')
			}
			bottom {
				table (model:'Contact-phoneNumbers',
				columns:['type', 'number'],        
				actionMap:'masterDetailActionMap')
			}
		}

table ('phoneNumber.table', 
		parent:'decoratedView', readOnly:true) { 
			actionMap {
				actionList('QUERY') {
					action ref:'queryModuleFilterAction'
				}
			}
		}