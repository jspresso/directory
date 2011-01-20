// Implement your application frontend here using the SJS DSL.
external (id:['abstractViewFactory'])

controller ('directory.controller',
    icon:'directory.png',
    context:'directory',
    language:'fr',
    workspaces:['directory.workspace', 'statistics.workspace', 'referential.workspace'])

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

workspace ('statistics.workspace',
    icon:'statistics.png')  {

      collectionModule ('statistics.categories.module',
        component:'Category',
        moduleView:'categorie.statistics.view',
        entry:'startupStatistics')
      
    }

workspace('referential.workspace',
    icon:'directory.png') {

      filterModule('category.module',
          component:'Category',
          moduleView:'Category.view')

      filterModule('activities.module',
          component:'Activity')
    }
    
bean ('viewFactoryBase', parent:'abstractViewFactory',
    custom:[defaultActionMapRenderingOptions : 'LABEL_ICON']) // Icons + labels


