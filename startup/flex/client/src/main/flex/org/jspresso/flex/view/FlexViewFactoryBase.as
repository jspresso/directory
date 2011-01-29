package org.jspresso.flex.view
{
  import flash.display.DisplayObject;
  
  import mx.containers.ApplicationControlBar;
  import mx.controls.Button;
  import mx.core.Container;
  import mx.core.UIComponent;
  
  import org.jspresso.framework.action.IActionHandler;
  import org.jspresso.framework.application.frontend.command.remote.IRemoteCommandHandler;
  import org.jspresso.framework.gui.remote.RActionField;
  import org.jspresso.framework.gui.remote.RComponent;
  import org.jspresso.framework.util.remote.registry.IRemotePeerRegistry;
  import org.jspresso.framework.view.flex.EnhancedFlexViewFactory;

  public class FlexViewFactoryBase extends EnhancedFlexViewFactory
  {

    public function FlexViewFactoryBase(remotePeerRegistry:IRemotePeerRegistry, actionHandler:IActionHandler, commandHandler:IRemoteCommandHandler) {
      super(remotePeerRegistry, actionHandler, commandHandler);
    }
    
    protected override function createToolBar(remoteComponent:RComponent, component:UIComponent):ApplicationControlBar {
      var controlBar:ApplicationControlBar = super.createToolBar(remoteComponent, component);
      for(var i:int = 0; i < controlBar.numChildren; i++) {
        var child:DisplayObject = controlBar.getChildAt(i);
        if(child is Button) {
          (child as UIComponent).styleName = "grey";
        }
      }
      return controlBar;
    }

    protected override function createSecondaryToolBar(remoteComponent:RComponent, component:UIComponent):ApplicationControlBar {
      var controlBar:ApplicationControlBar = super.createSecondaryToolBar(remoteComponent, component);
      for(var i:int = 0; i < controlBar.numChildren; i++) {
        var child:DisplayObject = controlBar.getChildAt(i);
        if(child is Button) {
          (child as UIComponent).styleName = "blue";
        }
      }
      return controlBar;
    }
    
    protected override function createActionField(remoteActionField:RActionField):UIComponent {
      var actionField:UIComponent = super.createActionField(remoteActionField);
      if(actionField is Container) {
        for(var i:int = 0; i < (actionField as Container).numChildren; i++) {
          var child:DisplayObject = (actionField as Container).getChildAt(i);
          if(child is Button) {
            (child as UIComponent).styleName = "transparent"
          }
        }
      }
      return actionField;
    }
  }
}