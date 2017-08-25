package org.moonila.planning.manager.gui.admin;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.moonila.common.user.core.to.BaseRoleTO;
import org.moonila.common.user.core.to.BaseUserTO;
import org.moonila.common.user.gui.admin.UserGUI;


@ManagedBean
@SessionScoped
public class UserGUI_Custom extends UserGUI{

	/**
	 * 
	 */
	private static final long serialVersionUID = -165153909154384132L;
	/**
     * 
     */
	

//	private final static Logger logger = LoggerFactory.getLogger(UserCustomGUI.class);

	private List<BaseUserTO> allUserList;
	private List<BaseRoleTO> allRoleList;
	private String newUserName;
	private String newUserPassword;
	private String newUserLogin;
	private String roleId;

//	private UserManager userManager = UserRoleManagerFactory.getUserManager();

	public UserGUI_Custom() {

	}

	@PostConstruct
	@Override
	public void init() {
		super.init();
		System.out.println("Je regarde si ça fonctione !!!!!");
	}




}
