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
package com.example.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Map;

import org.jspresso.framework.action.ActionException;
import org.jspresso.framework.action.IAction;
import org.jspresso.framework.action.IActionHandler;
import org.jspresso.framework.application.backend.action.BackendAction;
import org.jspresso.framework.binding.ICollectionConnector;
import org.jspresso.framework.binding.ICompositeValueConnector;
import org.jspresso.framework.binding.IConfigurableCollectionConnectorProvider;
import org.jspresso.framework.binding.IValueConnector;
import org.jspresso.framework.binding.model.ModelRefPropertyConnector;
import org.jspresso.framework.gui.remote.RComponent;
import org.jspresso.framework.model.descriptor.ICollectionDescriptorProvider;
import org.jspresso.framework.model.descriptor.IComponentDescriptor;
import org.jspresso.framework.state.remote.IRemoteStateOwner;
import org.jspresso.framework.state.remote.RemoteCompositeValueState;
import org.jspresso.framework.util.event.IItemSelectable;
import org.jspresso.framework.util.event.ISelectionChangeListener;
import org.jspresso.framework.util.event.ItemSelectionEvent;
import org.jspresso.framework.util.event.SelectionChangeEvent;
import org.jspresso.framework.view.IView;
import org.jspresso.framework.view.descriptor.IViewDescriptor;
import org.jspresso.framework.view.descriptor.basic.BasicPropertyViewDescriptor;
import org.jspresso.framework.view.remote.EnhancedRemoteViewFactory;

import com.example.gui.remote.RResourceChart;
import com.example.view.descriptor.ResourceChartViewDescriptor;

/**
 * TODO Comment needed.
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class RemoteViewFactory extends EnhancedRemoteViewFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  protected IView<RComponent> createCustomView(IViewDescriptor viewDescriptor,
      IActionHandler actionHandler, Locale locale) {
    if (viewDescriptor instanceof ResourceChartViewDescriptor) {
      return createResourceChartView(
          (ResourceChartViewDescriptor) viewDescriptor, actionHandler, locale);
    }
    return super.createCustomView(viewDescriptor, actionHandler, locale);
  }

  private IView<RComponent> createResourceChartView(
      ResourceChartViewDescriptor viewDescriptor, IActionHandler actionHandler,
      Locale locale) {

    ICollectionDescriptorProvider<?> tasksModelDescriptor = ((ICollectionDescriptorProvider<?>) viewDescriptor
        .getModelDescriptor());

    ICompositeValueConnector taskConnectorPrototype = getConnectorFactory()
        .createCompositeValueConnector(
            tasksModelDescriptor.getName() + "Element",
            viewDescriptor.getTaskLabelProperty());
    String taskResourceId = viewDescriptor.getTaskResourceIdProperty();
    if (taskResourceId == null) {
      taskResourceId = viewDescriptor.getTaskResourceProperty() + ".id";
    }
    taskConnectorPrototype.addChildConnector(getConnectorFactory()
        .createValueConnector(taskResourceId));
    taskConnectorPrototype.addChildConnector(getConnectorFactory()
        .createValueConnector(viewDescriptor.getTaskStartDateProperty()));
    taskConnectorPrototype.addChildConnector(getConnectorFactory()
        .createValueConnector(viewDescriptor.getTaskEndDateProperty()));

    final ICollectionConnector tasksConnector = getConnectorFactory()
        .createCollectionConnector(tasksModelDescriptor.getName(),
            getMvcBinder(), taskConnectorPrototype);

    ICollectionDescriptorProvider<?> resourcesModelDescriptor = viewDescriptor
        .getResourcesModelDescriptor();
    IComponentDescriptor<?> resourceDescriptor = resourcesModelDescriptor
        .getCollectionDescriptor().getElementDescriptor();

    ICompositeValueConnector resourceConnectorPrototype = getConnectorFactory()
        .createCompositeValueConnector(
            resourcesModelDescriptor.getName() + "Element",
            viewDescriptor.getResourceLabelProperty());
    resourceConnectorPrototype.addChildConnector(getConnectorFactory()
        .createValueConnector(viewDescriptor.getResourceIdProperty()));

    ICollectionConnector resourcesConnector = getConnectorFactory()
        .createCollectionConnector(resourcesModelDescriptor.getName(),
            getMvcBinder(), resourceConnectorPrototype);

    RResourceChart viewComponent = createRResourceChart(resourcesConnector,
        tasksConnector);

    BasicPropertyViewDescriptor bpvd = new BasicPropertyViewDescriptor();
    bpvd.setModelDescriptor(resourceDescriptor
        .getPropertyDescriptor(viewDescriptor.getResourceLabelProperty()));
    RComponent resourceColumn = createPropertyView(bpvd, actionHandler, locale)
        .getPeer();
    viewComponent.setResourceColumn(resourceColumn);

    final IConfigurableCollectionConnectorProvider wrapperConnector = getConnectorFactory()
        .createConfigurableCollectionConnectorProvider(
            ModelRefPropertyConnector.THIS_PROPERTY, null);
    wrapperConnector.addChildConnector(resourcesConnector);
    wrapperConnector.addChildConnector(tasksConnector);
    wrapperConnector.setCollectionConnectorProvider(tasksConnector);
    tasksConnector.addSelectionChangeListener(new ISelectionChangeListener() {

      public void selectionChange(SelectionChangeEvent evt) {
        ((IItemSelectable) wrapperConnector)
            .fireSelectedItemChange(new ItemSelectionEvent(wrapperConnector,
                tasksConnector.getChildConnector(evt.getLeadingIndex())));
      }
    });
    // wrapperConnector.setTracksChildrenSelection(true);

    IView<RComponent> view = constructView(viewComponent, viewDescriptor,
        wrapperConnector);
    if (viewDescriptor.getTaskResourceProperty() != null) {
      IAction reassignAction = createReassignAction(viewDescriptor
          .getTaskResourceProperty(), viewDescriptor.getResourceIdProperty(),
          viewComponent, resourcesConnector, tasksConnector);
      if (reassignAction != null) {
        viewComponent.setReassignAction(getActionFactory().createAction(
            reassignAction, actionHandler, view, locale));
      }
    }
    return view;
  }

  /**
   * Creates the reassign action.
   * 
   * @param taskResourceProperty
   *          the property to use to assign the new resource.
   * @param resourceIdProperty
   *          the property to use to retrieve the resource id.
   * @param viewComponent
   *          the resource chart.
   * @param resourcesConnector
   *          the resources connector.
   * @param tasksConnector
   *          the tasks connector.
   * @return the created action.
   */
  protected IAction createReassignAction(final String taskResourceProperty,
      final String resourceIdProperty, RResourceChart viewComponent,
      final ICollectionConnector resourcesConnector,
      final ICollectionConnector tasksConnector) {

    return new BackendAction() {

      /**
       * {@inheritDoc}
       */
      @Override
      public boolean execute(IActionHandler actionHandler, Map<String, Object> context) {
        Object resource = null;
        Object task = null;

        if (tasksConnector.getSelectedIndices().length > 0) {
          task = tasksConnector.getChildConnector(
              tasksConnector.getSelectedIndices()[0]).getConnectorValue();
          String resourceId = getActionCommand(context);
          if (resourceId != null) {
            for (int i = resourcesConnector.getChildConnectorCount() - 1; resource == null
                && i >= 0; i--) {
              ICompositeValueConnector resourceConnector = (ICompositeValueConnector) resourcesConnector
                  .getChildConnector(i);
              if (resourceId.equals(resourceConnector.getChildConnector(
                  resourceIdProperty).getConnectorValue())) {
                resource = resourceConnector.getConnectorValue();
              }
            }
          }
        }
        if (task != null && resource != null) {
          try {
            getAccessorFactory(context).createPropertyAccessor(
                taskResourceProperty, task.getClass()).setValue(task, resource);
          } catch (IllegalAccessException ex) {
            throw new ActionException(ex);
          } catch (InvocationTargetException ex) {
            if (ex.getCause() instanceof RuntimeException) {
              throw (RuntimeException) ex.getCause();
            }
            throw new ActionException(ex.getCause());
          } catch (NoSuchMethodException ex) {
            throw new ActionException(ex);
          }
        }
        return true;
      }
    };
  }

  /**
   * Creates a remote resource chart.
   * 
   * @param resourcesConnector
   *          the resources connector.
   * @param tasksConnector
   *          the tasks connector.
   * @return the created remote component.
   */
  protected RResourceChart createRResourceChart(
      IValueConnector resourcesConnector, IValueConnector tasksConnector) {
    RResourceChart component = new RResourceChart(getGuidGenerator()
        .generateGUID());
    if (tasksConnector instanceof IRemoteStateOwner) {
      component.setState(((IRemoteStateOwner) tasksConnector).getState());
    }
    if (resourcesConnector instanceof IRemoteStateOwner) {
      component
          .setResourcesState((RemoteCompositeValueState) ((IRemoteStateOwner) resourcesConnector)
              .getState());
    }
    return component;
  }
}
