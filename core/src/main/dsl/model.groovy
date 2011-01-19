// Questionnaire
Entity ('Questionnaire') {
  string_32 'title', mandatory:true
  list 'questions', ref:'Question'
}

Entity ('Question') {
  html 'title', mandatory:true  
  list 'proposals', ref:'Proposition'
}

Entity ('Proposition') {
  string_256 'title', mandatory:true
  bool 'correct'
} 
   
// Campaign
Entity ('Campaign') {
  enumeration 'status', values:['01', '02', '03'], enumName:'compaign.status', mandatory:true // Temporaty, In progress, Finished
  date 'startDate'
  date 'endDate'
  
  reference 'questionnaire', ref:'Questionnaire', mandatory:true  
  set 'records', ref:'QuestionnaireRecord'
}

Entity ('QuestionnaireRecord') {
  date_time 'date'
  list 'questions', ref:'QuestionRecord'
}

Entity ('QuestionRecord') {
  reference 'question', ref:'Question'
  list 'answers', ref:'PropositionRecord'
}

Entity ('PropositionRecord') {
  reference 'proposition', ref:'Proposition'
  bool 'correct'
}
