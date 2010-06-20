package com.example.directory.development;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import org.jspresso.framework.application.startup.development.AbstractTestDataPersister;
import org.jspresso.framework.model.entity.IEntity;
import org.springframework.beans.factory.BeanFactory;

import com.example.directory.model.Activity;
import com.example.directory.model.Category;
import com.example.directory.model.Contact;
import com.example.directory.model.Customer;
import com.example.directory.model.PhoneNumber;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * Persists some test data for the application.
 */
@SuppressWarnings("unchecked")
public class TestDataPersister extends AbstractTestDataPersister {

  HashMap<String, Category> categories;
  HashMap<String, Activity> activities; 
  HashMap<String, Customer> customers;
  
  Vector<String> phonetypes = new Vector(
      Arrays.asList(new String[] {"mobile", "home", "work"}));

  
  Vector<String> contacts = new Vector(
      Arrays.asList(new String[] {"Slim Elu/Carlos", 
      "Gates/Bill", 
      "Buffer/Warren", 
      "Ambani/Mukesh", 
      "Mittal/Laksmi", 
      "Ellison/Lawrence ",
      "Pringuet/Pierre",
      "Hermelin/Paul",
      "Lafont/Bruno",
      "Rodes Vila/Fernando",
      "Tricoire/Jean-Pascal",
      "De La Tour d'Artaise/Thiery",
      "Clamadieu/Jean-Pierre",
      "Arnault/Bernard", 
      "Batista/Eike", 
      "Ortega/Amancio ", 
      "Albrecht/Karl", 
      "Kamprad/Ingvar", 
      "Viehbacher/Christopher", 
      "Truan/Antonio", 
      "Levy/Maurice",
      "Agon/Jean-Paul",
      "De Castries/Henri",
      "Riboud/Franck",
      "Olofsson/Lars",
      "Pinaulf/François-Henri",        
      "De Margerie/Christophe",        
      "Bouygues/Martin",        
      "Prot/Baudouin",   
      "Breton/Thierry",
      "Kessler/Denis",
      "Proglio/Henri",
      "Dinin/Alain",
      "Kron/Patrick"}));  
  
  Random random = new Random();
    
  /**
   * Constructs a new <code>TestDataPersister</code> instance.
   * 
   * @param beanFactory
   *            the spring bean factory to use.
   */
  public TestDataPersister(BeanFactory beanFactory) {
    super(beanFactory);
  }

  /**
   * Creates some test data using the passed in Spring application context.
   */
  @Override
  public void persistTestData() {
    
    categories = createCategories();
    activities = createActivities();
    
    //contacts = createContacts();
    customers = createCustomers();
    
  }
  
  private HashMap<String, Customer> createCustomers() {
    HashMap<String, Customer> map = new HashMap<String, Customer>();
    
    createCustomer("Shell", map);
    createCustomer("Exxon Mobile", map);
    createCustomer("Wall-Mart", map);
    createCustomer("BP", map);
    createCustomer("Total", map);
    createCustomer("Japan Post Holdings", map);
    createCustomer("General Electric", map);
    createCustomer("China National Petroleum Corporation", map);
    createCustomer("ENI", map);
    createCustomer("General Motors", map);
    createCustomer("Allianz", map);
    
    saveOrUpdateAll(map.values());
    return map;
  }
  private Customer createCustomer(String name, HashMap<String, Customer> map) {
    Customer entity = createEntityInstance(Customer.class);
    entity.setCustomername(name);
    entity.setComments("This is my comment for " + name + "!");

    int max = 1 + random.nextInt(3);
    for (int i=0; i<max; i++) {
      entity.addToContacts(createContact());
    }
    
    entity.setSinceDate(new Date(new Date().getTime() - new Random().nextInt(5000)*1000*3600*24));

    map.put(name, entity);
    return entity;
  }
  
  private Contact createContact() {
      
    int i = random.nextInt(contacts.size());    
    String id = contacts.remove(i);    
    Contact entity = createContact(id.substring(0, id.indexOf('/')), id.substring(1+id.indexOf('/')));

    saveOrUpdate(entity);
    return entity;
  }
  private Contact createContact(String lastname, String firstname) {
    Contact entity = createEntityInstance(Contact.class);
    entity.setFirstname(firstname);
    entity.setLastname(lastname);
    entity.setComments("This is my comment for " + firstname + " " + lastname + "!");
    entity.setCategory(getRandom(categories.values()));
    entity.setStatus(getRandom("0", "1"));
    
    int max = 1 + random.nextInt(4);
    for (int i=0; i<max; i++) {
      entity.addToActivities(getRandom(activities.values()));
    }
    
    max = 1 + random.nextInt(4);
    for (int i=0; i<max; i++) {
      entity.addToPhoneNumbers(createPhoneNumber());
    }
   
    return entity;
  }
  
  
  private HashMap<String, Category> createCategories() {
    HashMap<String, Category> map = new HashMap<String, Category>();
    createCategory("office", map);
    createCategory("home", map);
    createCategory("family", map);
    
    createCategory("sport", map);
    createCategory("squash", map);
    createCategory("soccer", map);
    createCategory("golf", map);    
    map.get("sport").addToSubCategories(map.get("squash"));
    map.get("sport").addToSubCategories(map.get("soccer"));
    map.get("sport").addToSubCategories(map.get("golf"));
    
    saveOrUpdateAll(map.values());
    return map;
  }
  private Category createCategory(String name, HashMap<String, Category> map) {
    Category entity = createEntityInstance(Category.class);
    entity.setCategoryname(name);
    
    map.put(name, entity);
    return entity;
  }
  
  private HashMap<String, Activity> createActivities() {
    HashMap<String, Activity> map = new HashMap<String, Activity>();
    createActivity("Transport", map);
    createActivity("Game", map);
    createActivity("Bank", map); 
    createActivity("Finance", map); 
    createActivity("Oil & Gas", map); 
    createActivity("Electricity", map); 
    createActivity("Automotive", map); 
    createActivity("Retail", map);     
    createActivity("others", map); 
    saveOrUpdateAll(map.values());
    return map;
  }
  private Activity createActivity(String name, HashMap<String, Activity> map) {
    Activity entity = createEntityInstance(Activity.class);
    entity.setActivityname(name);
    entity.setActivitydescription(name + " description");
    map.put(name, entity);
    return entity;
  }
  
  private PhoneNumber createPhoneNumber() {
    PhoneNumber entity = createEntityInstance(PhoneNumber.class);
    entity.setType(getRandom(phonetypes));
     
    StringBuffer sb = new StringBuffer("+33 ");
    sb.append((random.nextInt(1)+1)*6).append(" ");    
    for (int i=0; i<4; i++) {
      sb.append(random.nextInt(9)).append(random.nextInt(9)).append(" ");
    }
    entity.setNumber(sb.toString());
    
    saveOrUpdate(entity);
    return entity;
  }
  
  /**
   * saveOrUpdateAll
   * @param entities
   */
  private void saveOrUpdateAll(Collection<? extends IEntity> entities) {
    for (IEntity entity : entities) {
      saveOrUpdate(entity);
    }
  }
  
  /**
   * getRandom
   */
   private <E> E getRandom(E... array) {
    int rand = new Random().nextInt(array.length);
    return array[rand];
  }
  
  @SuppressWarnings("unchecked")
  private <E> E getRandom(Collection<E> collection) {
    return (E) getRandom(collection.toArray());
  }
  
}
