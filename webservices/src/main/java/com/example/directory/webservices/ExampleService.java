package com.example.directory.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jspresso.framework.application.backend.persistence.hibernate.HibernateBackendController;
import org.jspresso.framework.application.backend.session.EMergeMode;
import org.jspresso.framework.model.persistence.hibernate.criterion.EnhancedDetachedCriteria;

/**
 * A web service skeleton.
 */
@Path("/example")
public class ExampleService extends AbstractService {

  /**
   * Retrieves an employee by its name.
   * 
   * @param name
   *          the name of the employee to retrieve.
   * @return the employee simplified DTO.
   */
  @GET
  @Path("/hello/{name}")
  @Produces({
      MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML
  })
  public ResponseDto sayHello(@PathParam("name") String name) {
    
    /*
     * The following commented lines are an example of retrieving
     * an entity using an Hibernate query executed through the
     * Jspresso framework.
     * There are a bunch of helper methods coming from the superclass
     * that can be used to execute actions, start transactions, ...
     */
    
    // DetachedCriteria crit = EnhancedDetachedCriteria.forClass(MyEntity.class);
    // crit.add(Restrictions.eq("name", name));
    // MyEntity e = ((HibernateBackendController) getBackendController())
    //   .findFirstByCriteria(crit, EMergeMode.MERGE_KEEP, MyEntity.class);
    // if (e != null) {
    //   return new EmployeeDto(e);
    // }
    // return null;
    
    return new ResponseDto("Hello from " + name);
  }

  // ////////////////////////////////////////////// //
  // Data transfer objects used for JAXB marshaling //
  // ////////////////////////////////////////////// //

  /**
   * Example response DTO.
   */
  @SuppressWarnings("unused")
  @XmlRootElement(name = "response")
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class ResponseDto {

    public String         content;

    /**
     * Default required constructor.
     */
    public ResponseDto() {
      // Empty constructor.
    }

    /**
     * Constructs a new <code>ResponseDto</code> instance.
     * 
     * @param response the response to create the DTO for.
     */
    public ResponseDto(String response) {
      this.content = response;
    }
  }
}
