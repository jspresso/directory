package com.example.directory.backend;

import java.util.HashSet;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jspresso.framework.action.IActionHandler;
import org.jspresso.framework.application.backend.action.BackendAction;
import org.jspresso.framework.application.backend.persistence.hibernate.HibernateBackendController;
import org.jspresso.framework.application.backend.session.EMergeMode;
import org.jspresso.framework.application.model.BeanModule;

import com.example.directory.model.Activity;
import com.example.directory.model.Category;
import com.example.directory.model.bean.Statistics;

public class StartupCategoriesStatisticsModuleAction extends BackendAction {
  
  @Override
  public boolean execute(IActionHandler actionHandler, Map<String, Object> context) {

    HibernateBackendController controller = (HibernateBackendController)getBackendController(context);

    DetachedCriteria criteria = DetachedCriteria.forClass(Activity.class);
    HashSet<Activity> activities = new HashSet<Activity>(controller.findByCriteria(criteria, EMergeMode.MERGE_EAGER, Activity.class));

    criteria = DetachedCriteria.forClass(Category.class);
    criteria.add(Restrictions.isNull("parentCategory"));    
    HashSet<Category> categories = new HashSet<Category>(controller.findByCriteria(criteria, EMergeMode.MERGE_EAGER, Category.class));
    
    Statistics statistics = new Statistics(categories, activities);
    /*
    @SuppressWarnings("unchecked")
    List<IEntity> categories = (List<IEntity>) controller.getTransactionTemplate().execute(
        new TransactionCallback() {
          public Object doInTransaction(
              @SuppressWarnings("unused") TransactionStatus status) {
            return controller.getHibernateTemplate().findByCriteria(criteria);
          }
        });
    
   //categories = (List<IEntity>)controller.merge(categories, EMergeMode.MERGE_EAGER);
   */
    
    
    
    
    
    BeanModule statisticsModule = (BeanModule)getModule(context);
    statisticsModule.setModuleObject(statistics);

    return super.execute(actionHandler, context);
  }

}

