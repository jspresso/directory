package com.example.directory.webapp;

import org.jspresso.framework.application.startup.development.AbstractTestDataContextListener;
import org.springframework.beans.factory.BeanFactory;

import com.example.directory.development.TestDataPersister;

/**
 * A simple listener to hook in webapp startup and persist sample data.
 */
public class TestDataContextListener extends AbstractTestDataContextListener {

  /**
   * {@inheritDoc}
   */
  @Override
  public void persistTestData(BeanFactory beanFactory) {
    new TestDataPersister(beanFactory).persistTestData();
  }

}
