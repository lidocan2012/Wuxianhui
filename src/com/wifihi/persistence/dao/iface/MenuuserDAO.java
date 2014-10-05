package com.wifihi.persistence.dao.iface;

import java.io.Serializable;

public interface MenuuserDAO {
	public com.wifihi.persistence.Menuuser get(com.wifihi.persistence.Menuuser key);

	public com.wifihi.persistence.Menuuser load(com.wifihi.persistence.Menuuser key);

	public java.util.List<com.wifihi.persistence.Menuuser> findAll ();


	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param menuuser a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public com.wifihi.persistence.Menuuser save(com.wifihi.persistence.Menuuser menuuser);

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param menuuser a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.wifihi.persistence.Menuuser menuuser);

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param menuuser a transient instance containing updated state
	 */
	public void update(com.wifihi.persistence.Menuuser menuuser);

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param menuuser the instance to be removed
	 */
	public void delete(com.wifihi.persistence.Menuuser menuuser);


}