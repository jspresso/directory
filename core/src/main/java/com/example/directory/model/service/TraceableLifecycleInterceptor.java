package com.example.directory.model.service;

import java.util.Date;

import org.jspresso.framework.model.component.service.EmptyLifecycleInterceptor;
import org.jspresso.framework.model.entity.IEntityFactory;
import org.jspresso.framework.model.entity.IEntityLifecycleHandler;
import org.jspresso.framework.security.UserPrincipal;

import com.example.directory.model.Traceable;

/** 
 * Default lifecycle service for tracing.
 */
public class TraceableLifecycleInterceptor extends EmptyLifecycleInterceptor<Traceable> {

  /**
   * Sets the create timestamp.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public boolean onPersist(Traceable traceable, IEntityFactory entityFactory,
      UserPrincipal principal, IEntityLifecycleHandler entityLifecycleHandler) {
    traceable.setCreatedTimestamp(new Date());
    return true;
  }

  /**
   * Sets the last update timestamp 
   * <p>
   * {@inheritDoc}
   */
  @Override
  public boolean onUpdate(Traceable traceable, IEntityFactory entityFactory,
      UserPrincipal principal, IEntityLifecycleHandler entityLifecycleHandler) {
    
    traceable.setUpdatedTimestamp(new Date());
    return true;
  }
}
