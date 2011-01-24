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
package com.example.gui.remote;

import org.jspresso.framework.gui.remote.RAction;
import org.jspresso.framework.gui.remote.RComponent;
import org.jspresso.framework.state.remote.RemoteCompositeValueState;

/**
 * TODO Comment needed.
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class RResourceChart extends RComponent {

  private static final long         serialVersionUID = -869410199951805912L;

  private RComponent                resourceColumn;
  private RemoteCompositeValueState resourcesState;
  private RAction                   reassignAction;

  /**
   * Constructs a new <code>RResourceChart</code> instance.
   */
  protected RResourceChart() {
    // TODO Auto-generated constructor stub
  }

  /**
   * Constructs a new <code>RResourceChart</code> instance.
   * 
   * @param guid
   */
  public RResourceChart(String guid) {
    super(guid);
    // TODO Auto-generated constructor stub
  }

  /**
   * Gets the resourceColumn.
   * 
   * @return the resourceColumn.
   */
  public RComponent getResourceColumn() {
    return resourceColumn;
  }

  /**
   * Sets the resourceColumn.
   * 
   * @param resourceColumn
   *          the resourceColumn to set.
   */
  public void setResourceColumn(RComponent resourceColumn) {
    this.resourceColumn = resourceColumn;
  }

  /**
   * Gets the resourcesState.
   * 
   * @return the resourcesState.
   */
  public RemoteCompositeValueState getResourcesState() {
    return resourcesState;
  }

  /**
   * Sets the resourcesState.
   * 
   * @param resourcesState
   *          the resourcesState to set.
   */
  public void setResourcesState(RemoteCompositeValueState resourcesState) {
    this.resourcesState = resourcesState;
  }

  /**
   * Gets the reassignAction.
   * 
   * @return the reassignAction.
   */
  public RAction getReassignAction() {
    return reassignAction;
  }

  /**
   * Sets the reassignAction.
   * 
   * @param reassignAction
   *          the reassignAction to set.
   */
  public void setReassignAction(RAction reassignAction) {
    this.reassignAction = reassignAction;
  }

}
