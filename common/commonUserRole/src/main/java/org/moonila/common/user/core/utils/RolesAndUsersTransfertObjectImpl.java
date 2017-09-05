/**
 * License
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * -------------------------------------------------------------------------
 * RolesAndUsersTransfertObjectAssemblerImpl.java
 * -------------------------------------------------------------------------
 */

package org.moonila.common.user.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.moonila.common.user.core.to.BaseRoleGroupTO;
import org.moonila.common.user.core.to.BaseRoleTO;
import org.moonila.common.user.core.to.BaseUserTO;
import org.moonila.common.user.dao.bo.BaseRoleBO;
import org.moonila.common.user.dao.bo.BaseRoleGroupBO;
import org.moonila.common.user.dao.bo.BaseUserBO;

/**
 * @author strino
 * 
 */
public class RolesAndUsersTransfertObjectImpl {

	public static void toRoleGroupBO(BaseRoleGroupTO roleGroupTO, BaseRoleGroupBO roleGroupBO) {
		roleGroupBO.setId(roleGroupTO.getId());
		roleGroupBO.setName(roleGroupTO.getName());
		roleGroupBO.setDescription(roleGroupTO.getDescription());

	}

	public static BaseRoleGroupTO toRoleGroupTO(BaseRoleGroupBO roleGroupBO) {
		BaseRoleGroupTO roleGroupTO = new BaseRoleGroupTO();
		roleGroupTO.setId(roleGroupBO.getId());
		roleGroupTO.setName(roleGroupBO.getName());
		roleGroupTO.setDescription(roleGroupBO.getDescription());
		
		return roleGroupTO;
	}
	
	public static List<BaseRoleGroupTO> toAllGroups(List<BaseRoleGroupBO> allRoleGroupBO) {	
		List<BaseRoleGroupTO> allRoleGroupTO = new ArrayList<BaseRoleGroupTO>(); 
		for(BaseRoleGroupBO roleGroup : allRoleGroupBO){
			BaseRoleGroupTO roleGroupTO = toRoleGroupTO(roleGroup);
			allRoleGroupTO.add(roleGroupTO);
		}
		return allRoleGroupTO;
	}

	public static BaseRoleBO toRoleBO(BaseRoleTO roleTO) {
		BaseRoleBO roleBO = new BaseRoleBO();
		roleBO.setId(roleTO.getId());
		roleBO.setName(roleTO.getName());
		roleBO.setDescription(roleTO.getDescription());
		
		return roleBO;
	}

	public static BaseRoleTO toRoleTO(BaseRoleBO roleBO) {
		BaseRoleTO roleTO = new BaseRoleTO();
		roleTO.setId(roleBO.getId());
		roleTO.setName(roleBO.getName());
		roleTO.setDescription(roleBO.getDescription());
		
		return roleTO;
	}

	public static void toUserBO(BaseUserTO userTO,BaseUserBO userBO) {

		userBO.setId(userTO.getId());
		userBO.setName(userTO.getName());
		userBO.setDescription(userTO.getDescription());
		userBO.setLogin(userTO.getLogin());
		userBO.setPassword(userTO.getPassword());

	}

	public static BaseUserTO toUserTO(BaseUserBO userBO) {
		BaseUserTO userTO = new BaseUserTO();
		userTO.setId(userBO.getId());
		userTO.setName(userBO.getName());
		userTO.setLogin(userBO.getLogin());
		userTO.setPassword(userBO.getPassword());
		userTO.setDescription(userBO.getDescription());
		
		return userTO;
	}

}
