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
            center { border (parent:'Contact.readonly.view') }
          }

          table (model:'Customer-addresses', actionMap:'masterDetailActionMap', )
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
      border (cascadingModels:true, borderType:'TITLED', icon:'bookmark.png') {
        west {
          polarChart (model:'Statistics-categories',
            pieSeries:'categoryname', label:'categoryname')
          //table (model:'Statistics-categories', columns:['categoryname'])
        }
        center {
          table (model:'Category-contacts')
        }
      }
    }
    evenCell {
      border (cascadingModels:true, borderType:'TITLED', icon:'activity.png') {
        west {
          polarChart (model:'Statistics-activities',
            pieSeries:'activityname', label:'activityname')
          //table (model:'Statistics-activities', columns:['activityname'])
        }
        center {
          table (model:'Activity-contacts')
        }
      }
    }
  }

}