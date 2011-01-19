package org.jspresso.flex.controller
{
  import flash.events.MouseEvent;
  
  import flexlib.containers.WindowShade;
  
  import mx.containers.ApplicationControlBar;
  import mx.containers.Canvas;
  import mx.containers.HBox;
  import mx.containers.VBox;
  import mx.containers.ViewStack;
  import mx.controls.Button;
  import mx.controls.ButtonBar;
  import mx.controls.PopUpButton;
  import mx.core.ScrollPolicy;
  import mx.core.UIComponent;
  import mx.events.FlexEvent;
  import mx.events.ItemClickEvent;
  import mx.rpc.remoting.mxml.RemoteObject;
  
  import org.jspresso.framework.application.frontend.controller.flex.DefaultFlexController;
  import org.jspresso.framework.gui.remote.RAction;
  import org.jspresso.framework.gui.remote.RActionList;
  import org.jspresso.framework.view.flex.CollapsibleAccordion;

  public class FlexControllerBase extends DefaultFlexController
  {

    public function FlexControllerBase(remoteController:RemoteObject, userLanguage:String) {
      super(remoteController, userLanguage);
    }
    
    protected override function createWorkspaceAccordionSection(wsLabel:String, wsName:String):Canvas {
      var wsAccSection:Canvas = super.createWorkspaceAccordionSection(wsLabel,wsName);
      wsAccSection.styleName = "accordionContentStyle";
      return wsAccSection;
    }
    
    protected override function createWorkspaceViewStack():ViewStack {
      var wsViewStack:ViewStack = super.createWorkspaceViewStack();
      wsViewStack.styleName = "centralViewStack";
      return wsViewStack;
    }
    
    protected override function createWorkspaceAccordion(workspaceNames:Array, workspaceActions:RActionList):CollapsibleAccordion {
      var wsAccordion:CollapsibleAccordion = super.createWorkspaceAccordion(workspaceNames, workspaceActions);

      wsAccordion.accordianStyle = "leftAccordion";
      wsAccordion.drawerButtonStyle = "leftAccordionVerticalHeader";
      wsAccordion.closeButtonStyle = "accordionCloseButton";
      wsAccordion.barSize = 43;

      return wsAccordion;
    }
    
    protected override function assembleApplicationContent(navigationAccordion:CollapsibleAccordion,
                                                           mainViewStack:ViewStack,
                                                           exitAction:RAction,
                                                           navigationActions:Array,
                                                           actions:Array,
                                                           secondaryActions:Array,
                                                           helpActions:Array):UIComponent {
      var appContent:HBox = new HBox();
      appContent.percentHeight = 100.0;
      appContent.percentWidth = 100.0;
      appContent.setStyle("paddingTop", 0);
      
      var appCanvas:Canvas = new Canvas();
      appCanvas.percentHeight = 100.0;
      appCanvas.percentWidth = 100.0;
      appCanvas.horizontalScrollPolicy = ScrollPolicy.OFF;
      appCanvas.verticalScrollPolicy = ScrollPolicy.OFF;
      appContent.addChild(appCanvas);
      
      var appBackground:Canvas = new Canvas();
      appBackground.styleName = "applicationBackground";
      appBackground.width = 1024;
      appBackground.height = 768;
      appCanvas.addChild(appBackground);
      
      var appVBox:VBox = new VBox();
      appVBox.setStyle("verticalGap", 2);
      appVBox.percentHeight = 100.0;
      appVBox.percentWidth = 100.0;
      appVBox.horizontalScrollPolicy = ScrollPolicy.OFF;
      appVBox.verticalScrollPolicy = ScrollPolicy.OFF;
      appCanvas.addChild(appVBox);

      var collapsibleTop:WindowShade = new WindowShade();
      collapsibleTop.percentWidth = 100.0;
      var headerButton:Button = new Button();
      headerButton.maxHeight = 0;
      headerButton.maxWidth = 0;
      headerButton.visible = false;
      collapsibleTop.headerRenderer = new UIComponentSingletonFactory(headerButton);
      var topCbHBox:HBox = new HBox();
      topCbHBox.setStyle("verticalAlign","top");
      topCbHBox.percentWidth = 100.0;
      topCbHBox.addEventListener(MouseEvent.CLICK, function(e:MouseEvent):void {
        collapsibleTop.opened = !collapsibleTop.opened;
      });
      topCbHBox.setStyle("verticalGap", 2);
      topCbHBox.height = 25;
      appVBox.addChild(topCbHBox);
      appVBox.addChild(collapsibleTop);
      
      //      var topControlBar:ApplicationControlBar = new ApplicationControlBar();
      //      topControlBar.styleName = "topControlBar";
      //      topControlBar.height = 105;
      //      topControlBar.percentWidth = 100.0;
      //      collapsibleTop.addChild(topControlBar);

      var topControlBar:Canvas = new Canvas();
      //topControlBar.styleName = "topControlBar";
      topControlBar.height = 90;
      topControlBar.percentWidth = 100.0;
      collapsibleTop.addChild(topControlBar);
      
      var topCbOuterCanvas:Canvas = new Canvas();
      topCbOuterCanvas.percentHeight = 100.0;
      topCbOuterCanvas.percentWidth = 100.0;
      topCbOuterCanvas.horizontalScrollPolicy = ScrollPolicy.OFF;
      topCbOuterCanvas.verticalScrollPolicy = ScrollPolicy.OFF;
      topControlBar.addChild(topCbOuterCanvas);
      
      var topCbCanvas:Canvas = new Canvas();
      topCbCanvas.width = 1024;
      topCbCanvas.height = 90;
      topCbCanvas.styleName="topControlBarCanvas";
      topCbOuterCanvas.addChild(topCbCanvas);
      
      var nextPreviousButtonBarHBox:HBox = new HBox();
      nextPreviousButtonBarHBox.setStyle("horizontalAlign", "right");
      nextPreviousButtonBarHBox.width=332;
      topCbHBox.addChild(nextPreviousButtonBarHBox);
      
      var nextPreviousButtonBar:ButtonBar = new ButtonBar();
      nextPreviousButtonBar.styleName="nextPreviousButtonBar";
      //nextPreviousButtonBar.dataProvider = navigationActions;
      
      var nextPrevDp:Array = new Array();
      var index:int= 0;
      for(var i:int= 0; i < navigationActions.length; i++) {
        var al:RActionList = navigationActions[i];
        for(var j:int= 0; j < al.actions.length; j++) {
          var item:Object = new Object();
          item["action"] = al.actions[j];
          //item["label"] = (al.actions[j] as RAction).name;
          nextPrevDp[index] = item;
          index++;
        }
      }
      nextPreviousButtonBar.dataProvider = nextPrevDp;
      nextPreviousButtonBar.addEventListener(ItemClickEvent.ITEM_CLICK, function(event:ItemClickEvent):void {
        execute(event.item["action"] as RAction);
      });
      nextPreviousButtonBarHBox.addChild(nextPreviousButtonBar);
      
      var topButtonBarHBox:HBox = new HBox();
      topButtonBarHBox.styleName="topButtonBar";
      topButtonBarHBox.percentHeight = 100.0;
      topButtonBarHBox.percentWidth = 100.0;
      if(actions != null) {
        var leftHB:HBox = new HBox();
        leftHB.width = 14;
        leftHB.height = 30;
        leftHB.styleName="topPopUpLeft";
        topButtonBarHBox.addChild(leftHB);
        for(i = 0; i < actions.length; i++) {
          var popupB:Button = getViewFactory().createPopupButton(actions[i] as RActionList);
          popupB.styleName = "top";
          topButtonBarHBox.addChild(popupB);
        }
        var rightHB:HBox = new HBox();
        rightHB.width = 14;
        rightHB.height = 30;
        rightHB.styleName="topPopUpRight";
        topButtonBarHBox.addChild(rightHB);
      }
      topCbHBox.addChild(topButtonBarHBox);

      var exitButtonOuterHBox:HBox = new HBox();
      exitButtonOuterHBox.setStyle("horizontalAlign","right")
      exitButtonOuterHBox.width=130;
      exitButtonOuterHBox.percentHeight=100.0;
      topCbHBox.addChild(exitButtonOuterHBox);
      
      var exitButtonHBox:HBox = new HBox();
      exitButtonHBox.setStyle("horizontalAlign","right")
      exitButtonHBox.percentWidth=100.0;
      exitButtonHBox.percentHeight=100.0;
      exitButtonHBox.verticalScrollPolicy = ScrollPolicy.OFF;
      exitButtonOuterHBox.addChild(exitButtonHBox);
      
      var exitButton:Button = new Button;
      exitButton.styleName = "quitButton";
      exitButton.labelPlacement="left";
      exitButton.label = exitAction.name;
      exitButton.addEventListener(MouseEvent.CLICK, function(event:MouseEvent):void {
        execute(exitAction);
      });
      exitButtonHBox.addChild(exitButton);
      
      var filler:HBox = new HBox;
      filler.width = 10;
      exitButtonHBox.addChild(filler);

      var contentVBox:VBox = new VBox();
      contentVBox.percentWidth = 100.0;
      contentVBox.percentHeight = 100.0;
      appVBox.addChild(contentVBox);
      
      var contentHBox:HBox = new HBox();
      contentHBox.percentWidth = 100.0;
      contentHBox.percentHeight = 100.0;
      contentHBox.styleName = "centralHBox";
      contentVBox.addChild(contentHBox);
      
      navigationAccordion.percentHeight = 90.0;
      //navigationAccordion.initialWidth = 190;
      contentHBox.addChild(navigationAccordion);
      
      mainViewStack.percentWidth = 100.0;
      mainViewStack.percentHeight = 100.0;
      contentHBox.addChild(mainViewStack);
      
      return appContent;
    }
    
  }
}

import mx.core.IFactory;
import mx.core.UIComponent;

internal class UIComponentSingletonFactory implements IFactory {
  private var _instance:UIComponent;
  
  public function UIComponentSingletonFactory(instance:UIComponent) {
    this._instance = instance;
  }
  
  public function newInstance():* {
    return this._instance;
  }
}