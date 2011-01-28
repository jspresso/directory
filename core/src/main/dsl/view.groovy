// Implement your application view here using the SJS DSL.
border('Customer.view',
  actionMap:'beanModuleActionMap') {
    north { form (model:'Customer') }
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

          table (model:'Customer-addresses', actionMap:'masterDetailActionMap')
        }
      }
    }
  }

border ('Contact.view', parent:'Contact.readonly.view',  actionMap:'beanModuleActionMap')

border ('Contact.readonly.view', borderType:'TITLED') {
  north {
    form (model:'Contact', columnCount:3, fields:['lastname', 'firstname', 'status'])
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
    split_horizontal {
      left {
        table (model:'Category-subCategories', actionMap:'masterDetailActionMap')
      }
      right {
        tree (parent:'Category.tree', preferredWidth:100)
      }
    }
  }
}

tree ('Category.tree', rendered:'categoryname') { subTree ('Category-subCategories.treenode') }

treeNode('Category-subCategories.treenode',
  rendered:'categoryname')

table ('PhoneNumber.table',
  parent:'decoratedView', readOnly:true) {
    actionMap {
      actionList('QUERY') {  action ref:'queryModuleFilterAction' }
    }
  }

evenGrid('Statistics.view', drivingDimension:'COLUMN', drivingCellCount:2) {
  cells {
    
    evenCell {
      border (cascadingModels:true, borderType:'TITLED', icon:'activity.png') {
        center {
          
          cartesianChart(model:'Statistics-activities', label:'activityname') {
            barSeries (valueField:'contactsCountActive', background:'0xE094c905')
            barSeries (valueField:'contactsCountInactive', background:'0xE0fb5501')            
          }        

        }
        east {
          listView (parent:'Contact.list', model:'Activity-contacts')
        }
      }
    }
    
    evenCell {
      border (cascadingModels:true, borderType:'TITLED', icon:'bookmark.png') {
        center { 
                   
          polarChart (model:'Statistics-categories', label:'categoryname') {
            pieSeries (valueField:'contactsCount',
              background:['0x9ac808', '0x90e454ac', '0x903172cf', '0xfdeb01'])
          }
        }
        east {
          listView (parent:'Contact.list', model:'Category-contacts')
        }
      }
    }

  }
}

listView('Contact.list', rendered:'fullname', preferredWidth:250, background:'0x00FFFFFF')
