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
 * UserDAO.java
 * -------------------------------------------------------------------------
 */

package org.moonila.common.user.dao.persistence;

import org.moonila.common.dao.persistence.GenericInterfaceDao;
import org.moonila.common.user.dao.bo.BaseUserBO;

/**
 * @author strino
 * 
 */
public interface UserDAO extends GenericInterfaceDao<BaseUserBO, String> {

	public BaseUserBO getUserByLogin(String login);

}
