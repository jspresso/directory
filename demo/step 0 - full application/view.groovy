// Implement your application view here using the SJS DSL.
border('Customer.view',
  actionMap:'beanModuleActionMap') {
    north { 
      form (model:'Customer', columnCount:3) 
    }
    center {
      tabs (renderingOptions:'LABEL') {
        views {
          
          border (cascadingModels:true) {
            center {
              table (model:'Customer-contacts', 
                actionMap:'masterDetailChooseActionMap', 
                columns:['fullname', 'category', 'status'])
            }
            east { 
              border (parent:'Contact.readonly.view', preferredWidth:450) 
            }
          }

          table (model:'Customer-addresses', 
            actionMap:'masterDetailActionMap')
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
            barSeries (valueField:'contactsCountActive', background:'0xE0ADFF2F')
            barSeries (valueField:'contactsCountInactive', background:'0xE0FFA500')            
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
            pieSeries (valueField:'category.allContactsCount', background:['0xE0ADFF2F', '0xE0FFA500', '0xE087CEEB'])
          }
        }
        center {        
          cartesianChart(model:'CategoryStat-activities', label:'activity.activityname', axisMinValue:0, axisMaxValue:10) {
            barSeries (valueField:'contactsCount', background:'0xE0ADFF2F')
          } 
        }
        east {
          listView(model:'ActivityStat-contacts', rendered:'fullname', preferredWidth:250, background:'0x00FFFFFF')
        }      
      }
    }

  }
}
