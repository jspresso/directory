actionMap('masterDetailChooseActionMap') {
  actionList ('CHOOSE') {
    action ref:'chooseEntityFrontAction'
    action ref:'unlinkComponentFrontAction'
  }
}

action ('chooseEntityFrontAction', parent:'lovAction',
  class:'org.jspresso.framework.application.frontend.action.lov.MasterDetailLovAction',
  icon:'classpath:org/jspresso/framework/application/images/edit_add-48x48.png',
  name:'choose.entity.name',
  description:'choose.entity.description',
  custom:[
    okAction_ref:'chooseEntityOkFrontAction'])

action ('chooseEntityOkFrontAction', 
  parent:'lovOkFrontAction',
  next:'addAnyToMasterFrontAction')

action ('unlinkComponentFrontAction', parent:'staticYesNoFrontAction',
  collectionBased:true,
  icon:'classpath:org/jspresso/framework/application/images/edit_remove-48x48.png',
  name:'unlink.component.name',
  description:'unlink.component.description',
  custom:['messageCode':'unlink.component.confirmation',
          'yesAction_ref':'unlinkComponentOkFrontAction'])

action ('unlinkComponentOkFrontAction', 
  class:'org.jspresso.framework.application.frontend.action.UnlinkComponentAction',
  icon:'classpath:org/jspresso/framework/application/images/edit_remove-48x48.png',
  name:'unlink.component.name',
  description:'unlink.component.description')

