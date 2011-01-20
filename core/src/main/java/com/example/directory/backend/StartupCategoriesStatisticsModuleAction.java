package com.example.directory.backend;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.security.auth.spi.Users.User;
import org.jspresso.framework.action.IActionHandler;
import org.jspresso.framework.application.backend.action.BackendAction;
import org.jspresso.framework.application.backend.persistence.hibernate.HibernateBackendController;
import org.jspresso.framework.application.backend.session.EMergeMode;
import org.jspresso.framework.application.model.BeanCollectionModule;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.directory.model.Category;

public class StartupCategoriesStatisticsModuleAction extends BackendAction {
  
  @Override
  public boolean execute(IActionHandler actionHandler, Map<String, Object> context) {

    HibernateBackendController controller = (HibernateBackendController)getBackendController(context);

    DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);
    criteria.add(Restrictions.isNull("parentCategory"));
    List<Category> categories = controller.findByCriteria(criteria, EMergeMode.MERGE_EAGER, Category.class);
    
    BeanCollectionModule statisticsModule = (BeanCollectionModule)getModule(context);
    statisticsModule.setModuleObjects(categories);

    return super.execute(actionHandler, context);
  }

}
