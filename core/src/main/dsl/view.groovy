// Implement your application view here using the SJS DSL.
border('Customer.view') {
  north {
    form (model:'Customer')
  }
  center {
    tabs {
      views {
        border (cascadingModels:true) {
          north {
            table (model:'Customer-contacts', actionMap:'masterDetailActionMap')
          }
          center {
            border (parent:'Contact.view', actionMap:'masterDetailActionMap')
          }
        }
        
        table (model:'Customer-addresses', actionMap:'masterDetailActionMap')
        
      }   
    }
  }
}

border ('Contact.view', 
    actionMap:'beanModuleActionMap') {
      north {
        form (model:'Contact', columnCount:2)
      }
      center {
        tabs (renderingOptions:'LABEL') {
          
          views {
            table (model:'Contact-phoneNumbers',
                columns:['type', 'number'],
                actionMap:'masterDetailActionMap')
            
            form (model:'Contact', 
                fields:['comments'], labelsPosition:'NONE', name:'comments')
            
            table (model:'Contact-activities',
                actionMap:'masterDetailActionMap')
          }
        }
      }
    }

table ('PhoneNumber.table', 
    parent:'decoratedView', readOnly:true) { 
      actionMap {
        actionList('QUERY') {
          action ref:'queryModuleFilterAction'
        }
      }
    }