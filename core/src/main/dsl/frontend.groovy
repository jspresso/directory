// Implement your application frontend here using the SJS DSL.
external id:['abstractViewFactory']

workspace('Campaign.workspace') {
  
  filterModule ('Questionnaire.module', 
    component:'Questionnaire', 
    detailView:'Questionnaire.view')
  
  filterModule ('Campaign.module', 
    component:'Campaign')
  
}


controller 'directory.name',
  icon:'icon.png',
  context:'directory',
  language:'en',
  workspaces:['Campaign.workspace']

  
bean ('viewFactoryBase', parent:'abstractViewFactory',
    custom:[defaultActionMapRenderingOptions : 'LABEL_ICON']) // Icons + labels
  