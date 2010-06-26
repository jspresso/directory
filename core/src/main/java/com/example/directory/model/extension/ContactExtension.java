package com.example.directory.model.extension;

import org.jspresso.framework.model.component.AbstractComponentExtension;

import com.example.directory.model.Contact;

public class ContactExtension extends AbstractComponentExtension<Contact> {

  public ContactExtension(Contact component) {
    super(component);

    registerNotificationForwarding(component, "firstname", "fullname");
    registerNotificationForwarding(component, "lastname", "fullname");
  }
  
  public String getFullName() {
    StringBuffer sb = new StringBuffer();
    if (getComponent().getFirstname()!=null) {
      sb.append(getComponent().getFirstname()).append(' ');
    }
    if (getComponent().getLastname()!=null) {
      sb.append(getComponent().getLastname());
    }
    return sb.toString().trim();
  }
}