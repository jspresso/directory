import org.jspresso.contrib.sjs.domain.Domain;
import org.jspresso.contrib.sjs.front.Front;

def domainBuilder = new Domain()

String errorOutput = project.properties['outputDir']+"/../../sjsErrors.log";

domainBuilder.Domain(projectName:'directory', mute:true) {
  namespace('com.example.directory') {
    include(project.properties['srcDir']+'/model.groovy')
  }
}
if(!domainBuilder.isOK()) {
  println domainBuilder.getErrorDomain()
  new File(errorOutput).write('SJS defined domain is invalid :\n' + domainBuilder.getErrorDomain())
  fail('SJS defined domain is invalid :\n' + domainBuilder.getErrorDomain())
}

def frontendBuilder = new Front(domainBuilder.getReferenceDomain())
frontendBuilder.Front(){
  namespace('com.example.directory'){
    view {
      include(project.properties['srcDir']+'/view.groovy')
    }
    frontend {
      include(project.properties['srcDir']+'/frontend.groovy')
    }
    backend {
      include(project.properties['srcDir']+'/backend.groovy')
    }
  } 
}
if(frontendBuilder.getNbrError() != 0) {
  println frontendBuilder.getError()
  new File(errorOutput).write('SJS defined frontend / views is invalid :\n' + frontendBuilder.getErrorDomain())
  fail('SJS defined frontend / views is invalid :\n' + frontendBuilder.getErrorDomain())
}

// write files
domainBuilder.writeDomainFile(project.properties['outputDir'], project.properties['modelOutputFileName'])
frontendBuilder.writeOutputFile('backend',project.properties['outputDir'],project.properties['backOutputFileName'])
frontendBuilder.writeOutputFile('view',project.properties['outputDir'],project.properties['viewOutputFileName'])
frontendBuilder.writeOutputFile('frontend',project.properties['outputDir'],project.properties['frontOutputFileName'])

//
new File(errorOutput).write("")
println "SJS Finished"
