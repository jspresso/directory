package com.example.directory.startup.ulc.development;

import com.example.directory.development.TestDataPersister;
import com.example.directory.startup.ulc.UlcApplicationStartup;

/**
 * ULC development application startup class.
 */
public class UlcDevApplicationStartup extends UlcApplicationStartup {

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
