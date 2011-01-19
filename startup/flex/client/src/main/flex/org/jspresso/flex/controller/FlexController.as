package org.jspresso.flex.controller
{
  import flash.net.registerClassAlias;
  
  import org.jspresso.flex.view.FlexViewFactory;
  
  import mx.rpc.remoting.mxml.RemoteObject;
  
  import org.jspresso.framework.application.frontend.controller.flex.DefaultFlexController;
  import org.jspresso.framework.view.flex.DefaultFlexViewFactory;

  public class FlexController extends FlexControllerBase
  {
    public function FlexController(remoteController:RemoteObject, userLanguage:String)
    {
      super(remoteController, userLanguage);
    }
    
    protected override function createViewFactory():DefaultFlexViewFactory {
      return new FlexViewFactory(this, this, this);
    }

    protected override function registerRemoteClasses():void {
      super.registerRemoteClasses();
    }
  }
}