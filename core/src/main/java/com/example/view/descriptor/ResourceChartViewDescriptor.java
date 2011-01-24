/*
 * Copyright (c) 2005-2010 Vincent Vandenschrick. All rights reserved.
 *
 *  This file is part of the Jspresso framework.
 *
 *  Jspresso is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Jspresso is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Jspresso.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.example.view.descriptor;

import org.jspresso.framework.model.descriptor.ICollectionPropertyDescriptor;
import org.jspresso.framework.view.descriptor.basic.BasicCollectionViewDescriptor;

/**
 * TODO Comment needed.
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class ResourceChartViewDescriptor extends BasicCollectionViewDescriptor {

  private ICollectionPropertyDescriptor<?> resourcesModelDescriptor;
  private String                           resourceIdProperty;
  private String                           resourceLabelProperty;

  private String                           taskResourceIdProperty;
  private String                           taskResourceProperty;
  private String                           taskLabelProperty;
  private String                           taskStartDateProperty;
  private String                           taskEndDateProperty;

  /**
   * Gets the taskLabelProperty.
   * 
   * @return the taskLabelProperty.
   */
  public String getTaskLabelProperty() {
    return taskLabelProperty;
  }

  /**
   * Sets the taskLabelProperty.
   * 
   * @param taskLabelProperty
   *          the taskLabelProperty to set.
   */
  public void setTaskLabelProperty(String taskLabelProperty) {
    this.taskLabelProperty = taskLabelProperty;
  }

  /**
   * Gets the resourceIdProperty.
   * 
   * @return the resourceIdProperty.
   */
  public String getResourceIdProperty() {
    return resourceIdProperty;
  }

  /**
   * Sets the resourceIdProperty.
   * 
   * @param resourceIdProperty
   *          the resourceIdProperty to set.
   */
  public void setResourceIdProperty(String resourceIdProperty) {
    this.resourceIdProperty = resourceIdProperty;
  }

  /**
   * Gets the resourceLabelProperty.
   * 
   * @return the resourceLabelProperty.
   */
  public String getResourceLabelProperty() {
    return resourceLabelProperty;
  }

  /**
   * Sets the resourceLabelProperty.
   * 
   * @param resourceLabelProperty
   *          the resourceLabelProperty to set.
   */
  public void setResourceLabelProperty(String resourceLabelProperty) {
    this.resourceLabelProperty = resourceLabelProperty;
  }

  /**
   * Gets the resourcesModelDescriptor.
   * 
   * @return the resourcesModelDescriptor.
   */
  public ICollectionPropertyDescriptor<?> getResourcesModelDescriptor() {
    return resourcesModelDescriptor;
  }

  /**
   * Sets the resourcesModelDescriptor.
   * 
   * @param resourcesModelDescriptor
   *          the resourcesModelDescriptor to set.
   */
  public void setResourcesModelDescriptor(
      ICollectionPropertyDescriptor<?> resourcesModelDescriptor) {
    this.resourcesModelDescriptor = resourcesModelDescriptor;
  }

  /**
   * Gets the taskResourceIdProperty.
   * 
   * @return the taskResourceIdProperty.
   */
  public String getTaskResourceIdProperty() {
    return taskResourceIdProperty;
  }

  /**
   * Sets the taskResourceIdProperty.
   * 
   * @param taskResourceIdProperty
   *          the taskResourceIdProperty to set.
   */
  public void setTaskResourceIdProperty(String taskResourceIdProperty) {
    this.taskResourceIdProperty = taskResourceIdProperty;
  }

  /**
   * Gets the taskStartDateProperty.
   * 
   * @return the taskStartDateProperty.
   */
  public String getTaskStartDateProperty() {
    return taskStartDateProperty;
  }

  /**
   * Sets the taskStartDateProperty.
   * 
   * @param taskStartDateProperty
   *          the taskStartDateProperty to set.
   */
  public void setTaskStartDateProperty(String taskStartDateProperty) {
    this.taskStartDateProperty = taskStartDateProperty;
  }

  /**
   * Gets the taskEndDateProperty.
   * 
   * @return the taskEndDateProperty.
   */
  public String getTaskEndDateProperty() {
    return taskEndDateProperty;
  }

  /**
   * Sets the taskEndDateProperty.
   * 
   * @param taskEndDateProperty
   *          the taskEndDateProperty to set.
   */
  public void setTaskEndDateProperty(String taskEndDateProperty) {
    this.taskEndDateProperty = taskEndDateProperty;
  }

  /**
   * Sets the taskResourceProperty.
   * 
   * @param taskResourceProperty
   *          the taskResourceProperty to set.
   */
  public void setTaskResourceProperty(String taskResourceProperty) {
    this.taskResourceProperty = taskResourceProperty;
  }

  /**
   * Gets the taskResourceProperty.
   * 
   * @return the taskResourceProperty.
   */
  public String getTaskResourceProperty() {
    return taskResourceProperty;
  }

}
