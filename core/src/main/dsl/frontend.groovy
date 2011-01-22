// Implement your application frontend here using the SJS DSL.
external (id:['abstractViewFactory'])

controller ('directory.controller',
    icon:'directory.png',
    context:'directory',
    language:'fr',
    actionMap:'mainActionMap',
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

      beanModule ('statistics.module', 
        icon:'statistics.png',
        component:'Statistics',
        moduleView:'Statistics.view',
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
    
actionMap('mainActionMap') {
  actionList ('main') {
    action ref:'helpAction' 
  }
}     
    
action ('helpAction', 
  parent:'staticDisplayUrlFrontAction',
  name:'help.name',
  icon: 'help.png',   
  custom: [baseUrl: 'http://www.jspresso.org'])   

bean ('viewFactoryBase', parent:'abstractViewFactory',
    custom:[defaultActionMapRenderingOptions : 'LABEL_ICON']) // Icons + labels


