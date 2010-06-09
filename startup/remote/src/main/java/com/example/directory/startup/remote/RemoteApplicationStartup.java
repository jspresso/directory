package com.example.directory.startup.remote;

import org.jspresso.framework.application.startup.remote.RemoteStartup;

/**
 * Remote application startup class.
 */
public class RemoteApplicationStartup extends RemoteStartup {

  /**
   * Returns the "directory-remote-context" value.
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected String getApplicationContextKey() {
    return "directory-remote-context";
  }
}
