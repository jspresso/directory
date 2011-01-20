// Implement your views here using the SJS DSL.
border ('Questionnaire.view', actionMap:'beanModuleActionMap') {
  north {
    form (model:'Questionnaire')
  } 
  center {
    split_horizontal (cascadingModels:true) {
      left {
        table (model:'Questionnaire-questions', actionMap:'masterDetailActionMap')
      }
      right {
        table (model:'Question-proposals', actionMap:'masterDetailActionMap')
      }
    }    
  }
}