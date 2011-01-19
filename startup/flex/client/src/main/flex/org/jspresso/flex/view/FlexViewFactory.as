package org.jspresso.flex.view
{
  import flash.events.MouseEvent;
  
  import mx.binding.utils.BindingUtils;
  import mx.collections.ArrayCollection;
  import mx.collections.ICollectionView;
  import mx.collections.IList;
  import mx.containers.ApplicationControlBar;
  import mx.controls.Button;
  import mx.controls.VRule;
  //import mx.controls.advancedDataGridClasses.AdvancedDataGridColumn;
  import mx.core.UIComponent;
  import mx.events.CollectionEvent;
  import mx.events.CollectionEventKind;
  import mx.events.PropertyChangeEvent;
  
  import org.jspresso.framework.action.IActionHandler;
  import org.jspresso.framework.application.frontend.command.remote.IRemoteCommandHandler;
  import org.jspresso.framework.gui.remote.RComponent;
  import org.jspresso.framework.state.remote.RemoteCompositeValueState;
  import org.jspresso.framework.util.array.ArrayUtil;
  import org.jspresso.framework.util.remote.registry.IRemotePeerRegistry;
  import org.jspresso.framework.view.flex.DefaultFlexViewFactory;

  public class FlexViewFactory extends FlexViewFactoryBase
  {

    public function FlexViewFactory(remotePeerRegistry:IRemotePeerRegistry, actionHandler:IActionHandler, commandHandler:IRemoteCommandHandler) {
      super(remotePeerRegistry, actionHandler, commandHandler);
    }
    
    protected override function createDefaultToolBar(remoteComponent:RComponent, component:UIComponent):ApplicationControlBar {
      return null;
    }

  }
}