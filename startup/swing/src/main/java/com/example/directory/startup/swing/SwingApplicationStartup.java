package com.example.directory.startup.swing;

import org.jspresso.framework.application.startup.swing.SwingStartup;

/**
 * Swing application startup class.
 */
public class SwingApplicationStartup extends SwingStartup {

  /**
   * Returns the "directory-swing-context" value.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected String getApplicationContextKey() {
    return "directory-swing-context";
  }
}
