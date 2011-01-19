package com.example.directory.startup.ulc;

import org.jspresso.framework.application.startup.ulc.UlcStartup;

/**
 * ULC application startup class.
 */
public class UlcApplicationStartup extends UlcStartup {

  /**
   * Returns the "directory-ulc-context" value.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected String getApplicationContextKey() {
    return "directory-ulc-context";
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
