// Implement your application view here using the SJS DSL.
border('Customer.view', 
    actionMap:'beanModuleActionMap') {
  north {
    form (model:'Customer')
  }
  center {
    tabs (renderingOptions:'LABEL') {
      views {
        border (cascadingModels:true) {
          north {
            table (model:'Customer-contacts', actionMap:'masterDetailActionMap')
          }
          center {
            border (parent:'Contact.readonly.view')
          }
        }
        
        table (model:'Customer-addresses', actionMap:'masterDetailActionMap', )
        
      }   
    }
  }
}

border ('Contact.view', parent:'Contact.readonly.view',  actionMap:'beanModuleActionMap')    
    
border ('Contact.readonly.view') {
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

split_vertical('Category.view', cascadingModels:true) {
  top {
    table (validationModel:'Category', parent:'decoratedView', actionMap:'filterableBeanCollectionModuleActionMap') 
  }
  bottom {
    split_horizontal(right:'Category.tree') {
      left {
        table (model:'Category-subCategories', actionMap:'masterDetailActionMap')
      }
    }
  }
}   

tree ('Category.tree', rendered:'categoryname') {
  subTree ('Category-subCategories.treenode')
}

treeNode('Category-subCategories.treenode', 
  rendered:'categoryname')

table ('PhoneNumber.table', 
    parent:'decoratedView', readOnly:true) { 
      actionMap {
        actionList('QUERY') {
          action ref:'queryModuleFilterAction'
        }
      }
    }