
package org.moonila.common.dao.utils;

/**
 *@author Sandra Trino
 * 
 */
public class DAOException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -1183529446352527615L;

    /**
     * 
     */
    public DAOException() {
        super();
    }

    /**
     * @param message
     */
    public DAOException(final String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public DAOException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public DAOException(final Throwable cause) {
        super(cause);
    }

}
