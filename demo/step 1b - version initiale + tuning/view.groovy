// Implement your views here using the SJS DSL.
border ('Contact.view', borderType:'TITLED') {
  north {
    form (model:'Contact', columnCount:2, fields:['lastname', 'firstname'])
  }
  center {
    tabs (renderingOptions:'LABEL') {
       
      views {
        table (model:'Contact-phoneNumbers',
            columns:['type', 'number'],
            actionMap:'masterDetailActionMap')
        
        form (model:'Contact',
            fields:['comments'], labelsPosition:'NONE', name:'comments')
        
      }
    }
  }
}
