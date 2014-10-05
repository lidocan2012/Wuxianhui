package com.wifihi.persistence.dao.iface;

import java.io.Serializable;

public interface TablemanageDAO {
	public com.wifihi.persistence.Tablemanage get(com.wifihi.persistence.TablemanagePK key);

	public com.wifihi.persistence.Tablemanage load(com.wifihi.persistence.TablemanagePK key);

	public java.util.List<com.wifihi.persistence.Tablemanage> findAll ();


	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param tablemanage a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public com.wifihi.persistence.TablemanagePK save(com.wifihi.persistence.Tablemanage tablemanage);

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param tablemanage a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.wifihi.persistence.Tablemanage tablemanage);

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param tablemanage a transient instance containing updated state
	 */
	public void update(com.wifihi.persistence.Tablemanage tablemanage);

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(com.wifihi.persistence.TablemanagePK id);

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param tablemanage the instance to be removed
	 */
	public void delete(com.wifihi.persistence.Tablemanage tablemanage);


}