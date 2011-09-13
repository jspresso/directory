package com.example.directory.webservices;

import org.jspresso.framework.application.startup.AbstractBackendStartup;

/**
 * Abstract base service implementation.
 */
public abstract class AbstractService extends AbstractBackendStartup {
  
  /**
   * Constructs a new <code>AbstractService</code> instance.
   * 
   */
  protected AbstractService() {
    start();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getApplicationContextKey() {
    return "directory-backend-context";
  }

  /**
   * Overrides default bean ref locator.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected String getBeanFactorySelector() {
    return "com/example/directory/beanRefFactory.xml";
  }
}
