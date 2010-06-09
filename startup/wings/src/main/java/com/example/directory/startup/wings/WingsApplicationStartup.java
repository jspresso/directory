package com.example.directory.startup.wings;

import org.jspresso.framework.application.startup.wings.WingsStartup;

/**
 * Wings application startup class.
 */
public class WingsApplicationStartup extends WingsStartup {

  /**
   * Returns the "directory-wings-context" value.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected String getApplicationContextKey() {
    return "directory-wings-context";
  }
}
