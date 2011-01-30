package org.jspresso.framework.application.frontend.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import org.jspresso.framework.action.ActionException;
import org.jspresso.framework.action.IActionHandler;
import org.jspresso.framework.binding.ICollectionConnector;
import org.jspresso.framework.model.component.IComponent;
import org.jspresso.framework.model.descriptor.ICollectionDescriptorProvider;
import org.jspresso.framework.model.descriptor.IModelDescriptorAware;
import org.jspresso.framework.util.accessor.IAccessorFactory;
import org.jspresso.framework.util.accessor.ICollectionAccessor;

public class UnlinkComponentAction<E, F, G> extends FrontendAction<E, F, G> {

  @Override
  public boolean execute(IActionHandler actionHandler,
      Map<String, Object> context) {


    ICollectionConnector collectionConnector = (ICollectionConnector)getModelConnector(context);
    if (collectionConnector == null) {
      return false;
    }
    Class<?> elementComponentContract = 
      getModelDescriptor(context).getCollectionDescriptor().getElementDescriptor().getComponentContract();
    
    int[] selectedIndices = getSelectedIndices(context);
    if (selectedIndices != null) {
      Object master = collectionConnector.getParentConnector().getConnectorValue();
      ICollectionAccessor collectionAccessor = 
        getAccessorFactory(context)
          .createCollectionPropertyAccessor(
              collectionConnector.getId(),
              collectionConnector.getModelProvider().getModelDescriptor().getComponentDescriptor().getComponentContract(),
              elementComponentContract);
      
      if (collectionAccessor instanceof IModelDescriptorAware) {
        ((IModelDescriptorAware) collectionAccessor).setModelDescriptor(getModelDescriptor(context));
      }
      
      try {
        Collection<?> existingCollection = collectionAccessor.getValue(master);
        // Traverse the collection reversly for performance reasons.
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
          int selectedIndex = selectedIndices[i];
          IComponent nextDetailToRemove =
            (IComponent) collectionConnector.getChildConnector(selectedIndex).getConnectorValue();
  
          if (existingCollection.contains(nextDetailToRemove)) {
            collectionAccessor.removeFromValue(master, nextDetailToRemove);
          }
  
        }
     
      } catch (IllegalAccessException ex) {
        throw new ActionException(ex);
      } catch (InvocationTargetException ex) {
        if (ex.getCause() instanceof RuntimeException) {
          throw (RuntimeException) ex.getCause();
        }
        throw new ActionException(ex.getCause());
      } catch (NoSuchMethodException ex) {
        throw new ActionException(ex);
      }
    }
    
    return super.execute(actionHandler, context);
  }

  
  
  /**
   * refined to return a collection connector.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected ICollectionConnector getModelConnector(Map<String, Object> context) {
    return (ICollectionConnector) super.getModelConnector(context);
  }

  /**
   * Refined to return a collection descriptor.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected ICollectionDescriptorProvider<?> getModelDescriptor(
      Map<String, Object> context) {
    return (ICollectionDescriptorProvider<?>) super.getModelDescriptor(context);
  }
  
  /**
   * Gets the accessorFactory.
   * 
   * @param context
   *          the action context.
   * @return the accessorFactory.
   */
  protected IAccessorFactory getAccessorFactory(Map<String, Object> context) {
    return getBackendController(context).getAccessorFactory();
  }
  
}
