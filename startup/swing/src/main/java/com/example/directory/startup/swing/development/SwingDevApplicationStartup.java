package com.example.directory.startup.swing.development;

import com.example.directory.development.TestDataPersister;
import com.example.directory.startup.swing.SwingApplicationStartup;

/**
 * Swing development application startup class.
 */
public class SwingDevApplicationStartup extends SwingApplicationStartup {

  /**
   * Sets up some test data before actually starting.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public void start() {
    new TestDataPersister(getApplicationContext()).persistTestData();
    super.start();
  }
}
