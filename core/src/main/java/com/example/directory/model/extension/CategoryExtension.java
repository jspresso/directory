package com.example.directory.model.extension;

import java.util.HashSet;
import java.util.Set;

import org.jspresso.framework.model.component.AbstractComponentExtension;

import com.example.directory.model.Category;
import com.example.directory.model.Contact;

/**
 * Generated by SJSPlug for Jspresso
 */
public class CategoryExtension extends AbstractComponentExtension<Category> {

  public CategoryExtension(Category component) {
    super(component);

    registerNotificationForwarding(component, "contacts", "contactsCount");
  }

  /**
   * generated by SJSPlug for Jspresso
   * 
   * @return
   */
  public Integer getAllContactsCount() {
    return getAllContacts().size();
  }

  /**
   * generated by SJSPlug for Jspresso
   * @return
   */
  public Set<Contact> getAllContacts() {
    HashSet<Contact> s = new HashSet<Contact>();
    s.addAll(getComponent().getContacts());
    for (Category c : getComponent().getSubCategories()) {
      s.addAll(c.getContacts());
    }    
    return s;
  }

}
