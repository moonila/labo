/**
 * Dragon - SOA Governance Platform.
 * Copyright (c) 2009 EBM Websourcing, http://www.ebmwebsourcing.com/
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
 * RolesEnum.java
 * -------------------------------------------------------------------------
 */

package org.moonila.common.user.core.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of supported tmodel types
 * 
 * @author ofabre - eBM Websourcing
 * 
 */
public enum RolesEnum {
    MANAGEADMINISTRATION("MANAGEADMINISTRATION"), MANAGEORGA("MANAGEORGA"), VIEWORGA("VIEWORGA"),
    MANAGESERVICE("MANAGESERVICE"), VIEWSERVICE("VIEWSERVICE"), MANAGERUNTIMEMANAGER(
            "MANAGERUNTIMEMANAGER"), VIEWRUNTIMEMANAGER("VIEWRUNTIMEMANAGER");
    private final String type;

    private static final Map<String, RolesEnum> stringToEnum = new HashMap<String, RolesEnum>();

    static {
        for (RolesEnum type : values()) {
            stringToEnum.put(type.toString(), type);
        }
    }

    private RolesEnum(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    /**
     * Return the RolesEnum related to the given string or null if it isn't
     * supported
     * 
     * @param type
     *            the string representing a potential RolesEnum
     * @return the {@link RolesEnum} enum related to the given String
     */
    public static RolesEnum fromString(String type) {
        return stringToEnum.get(type);
    }

    public static String[] stringValues() {
        return stringToEnum.keySet().toArray(new String[0]);
    }

}
