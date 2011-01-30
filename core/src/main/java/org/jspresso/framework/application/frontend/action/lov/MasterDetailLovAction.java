package org.jspresso.framework.application.frontend.action.lov;

import java.util.Map;

import org.jspresso.framework.action.IActionHandler;
import org.jspresso.framework.model.descriptor.ICollectionDescriptor;
import org.jspresso.framework.model.descriptor.ICollectionPropertyDescriptor;
import org.jspresso.framework.model.entity.IEntity;

/**
 * Choose one detail and attach it to its master
 * 
 * @author Maxime Hamm
 * @param <E>
 *          the actual gui component type used.
 * @param <F>
 *          the actual icon type used.
 * @param <G>
 *          the actual action type used.
 */
public class MasterDetailLovAction<E, F, G> extends LovAction<E, F, G> {

  /**
   * execute action 
   */
  @Override
  public boolean execute(IActionHandler actionHandler,
      Map<String, Object> context) {
    
    @SuppressWarnings("unchecked")
    ICollectionPropertyDescriptor<IEntity> cpd = (ICollectionPropertyDescriptor<IEntity>)getModelDescriptor(context);
    ICollectionDescriptor<IEntity> cd = cpd.getReferencedDescriptor();
    setEntityDescriptor(cd.getElementDescriptor());
    
    return super.execute(actionHandler, context);
  }
  
}
