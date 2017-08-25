package org.moonila.planning.manager.core.service;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.moonila.common.crud.ICrudManager;
import org.moonila.planning.manager.core.AppConfigPropertiesEnum;
import org.moonila.planning.manager.core.AppConfigPropertiesService;
import org.moonila.planning.manager.core.OtherBean;
import org.moonila.planning.manager.core.to.ProjectTO;
import org.moonila.planning.manager.core.utils.GenericUnitCase;
import org.moonila.planning.manager.dao.bo.ProjectBO;
import org.moonila.planning.manager.dao.persistence.admin.IProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PlanningManagerImplTest extends GenericUnitCase {

    @Autowired
    @Qualifier("projectManager")
    private ICrudManager<ProjectTO> projectManager;

    @Autowired
    @Qualifier("projectDao")
    private IProjectDao projectDao;


    @Before
    public void init() throws Exception {
        ProjectBO keyWord1 = new ProjectBO("ztest1");
        ProjectBO keyWord2 = new ProjectBO("test2");
        ProjectBO keyWord3 = new ProjectBO("aTest2");
        ProjectBO keyWord4 = new ProjectBO("test1");
        ProjectBO keyWord5 = new ProjectBO("btest1");

        projectDao.saveOrUpdate(keyWord1);
        projectDao.saveOrUpdate(keyWord2);
        projectDao.saveOrUpdate(keyWord3);
        projectDao.saveOrUpdate(keyWord4);
        projectDao.saveOrUpdate(keyWord5);
    }
    

    @Test
    public void testGetALLKeyWords() {
    	
    	List<ProjectTO> getAllProjects = projectManager.getAllObject();
    	assertEquals(getAllProjects.size(), 5);
      
    }
    
    public long nbj(int size) {
    	  if (size > 1) {
        		return size - 1;
        } else {
        	return 28;
        }
    }
    
    @Test
    public void testGetSize() {
    	
    	long toto = nbj(1);
    	System.out.println("toto : " +toto);
    	
    	toto = nbj(0);
    	System.out.println("toto : " +toto);
    	
    	toto = nbj(2);
    	System.out.println("toto : " +toto);
    	
    }
    
    @Test
    public void testGetProperties(){
    	AppConfigPropertiesService appCongi = AppConfigPropertiesService.getInstance();
    	
    	Map<String,String> toto = appCongi.getProperties();
    	
    	for(Map.Entry<String,String> key : toto.entrySet()){
    		System.out.println(" key "+key.getKey() +" value "+key.getValue()  );
    	}
    	
    	String truc = toto.get(AppConfigPropertiesEnum.PWD.toString());
    	System.out.println(truc);
    	OtherBean machin = new OtherBean("letrucccon");
    	String log = machin.getLogin();
    	System.out.println("log " +log);
    }

}
