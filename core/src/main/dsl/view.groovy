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
            barSeries (valueField:'contactsCountActive', background:'0xE0FF7F50')
            barSeries (valueField:'contactsCountInactive', background:'0xE0FF6347')            
          }        

        }
        east {
          listView(model:'Activity-contacts', rendered:'fullname', preferredWidth:250, background:'0x00FFFFFF')
        }
      }
    }
    
    evenCell {
      border (cascadingModels:true, borderType:'TITLED', icon:'bookmark.png') {
        west {
          polarChart (model:'Statistics-categories', label:'category.categoryname', preferredWidth:300) {
            pieSeries (valueField:'category.allContactsCount', background:['0xE0FF7F50', '0xE0FF6347', '0xE0FF8C00'])
          }
        }
        center {        
          cartesianChart(model:'CategoryStat-activities', label:'activity.activityname') {
            barSeries (valueField:'contactsCount', background:'0xE0FF7F50')
          } 
        }
        east {
          listView(model:'ActivityStat-contacts', rendered:'fullname', preferredWidth:250, background:'0x00FFFFFF')
        }      
      }
    }

  }
}


