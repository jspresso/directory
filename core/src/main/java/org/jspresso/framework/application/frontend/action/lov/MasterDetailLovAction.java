package org.jspresso.framework.application.frontend.action.lov;

import java.util.Map;

import org.jspresso.framework.action.IActionHandler;
import org.jspresso.framework.model.descriptor.ICollectionDescriptor;
import org.jspresso.framework.model.descriptor.ICollectionPropertyDescriptor;
import org.jspresso.framework.model.entity.IEntity;

public class MasterDetailLovAction<E, F, G> extends LovAction<E, F, G> {

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
