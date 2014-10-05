package com.wifihi.persistence.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.wifihi.persistence.dao.iface.OrderdetailDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseOrderdetailDAO extends com.wifihi.persistence.dao._RootDAO {

	public BaseOrderdetailDAO () {}
	
	public BaseOrderdetailDAO (Session session) {
		super(session);
	}

	// query name references


	public static OrderdetailDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static OrderdetailDAO getInstance () {
		if (null == instance) instance = new com.wifihi.persistence.dao.OrderdetailDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.wifihi.persistence.Orderdetail.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.wifihi.persistence.Orderdetail
	 */
	public com.wifihi.persistence.Orderdetail cast (Object object) {
		return (com.wifihi.persistence.Orderdetail) object;
	}

	public com.wifihi.persistence.Orderdetail get(java.lang.String key)
	{
		return (com.wifihi.persistence.Orderdetail) get(getReferenceClass(), key);
	}

	public com.wifihi.persistence.Orderdetail get(java.lang.String key, Session s)
	{
		return (com.wifihi.persistence.Orderdetail) get(getReferenceClass(), key, s);
	}

	public com.wifihi.persistence.Orderdetail load(java.lang.String key)
	{
		return (com.wifihi.persistence.Orderdetail) load(getReferenceClass(), key);
	}

	public com.wifihi.persistence.Orderdetail load(java.lang.String key, Session s)
	{
		return (com.wifihi.persistence.Orderdetail) load(getReferenceClass(), key, s);
	}

	public com.wifihi.persistence.Orderdetail loadInitialize(java.lang.String key, Session s) 
	{ 
		com.wifihi.persistence.Orderdetail obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.wifihi.persistence.Orderdetail> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.wifihi.persistence.Orderdetail> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.wifihi.persistence.Orderdetail> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param orderdetail a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.String save(com.wifihi.persistence.Orderdetail orderdetail)
	{
		return (java.lang.String) super.save(orderdetail);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param orderdetail a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.String save(com.wifihi.persistence.Orderdetail orderdetail, Session s)
	{
		return (java.lang.String) save((Object) orderdetail, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param orderdetail a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.wifihi.persistence.Orderdetail orderdetail)
	{
		saveOrUpdate((Object) orderdetail);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param orderdetail a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.wifihi.persistence.Orderdetail orderdetail, Session s)
	{
		saveOrUpdate((Object) orderdetail, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param orderdetail a transient instance containing updated state
	 */
	public void update(com.wifihi.persistence.Orderdetail orderdetail) 
	{
		update((Object) orderdetail);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param orderdetail a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.wifihi.persistence.Orderdetail orderdetail, Session s)
	{
		update((Object) orderdetail, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.String id)
	{
		delete((Object) load(id));
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param id the instance ID to be removed
	 * @param s the Session
	 */
	public void delete(java.lang.String id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param orderdetail the instance to be removed
	 */
	public void delete(com.wifihi.persistence.Orderdetail orderdetail)
	{
		delete((Object) orderdetail);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param orderdetail the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.wifihi.persistence.Orderdetail orderdetail, Session s)
	{
		delete((Object) orderdetail, s);
	}
	
	/**
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
	 * For example 
	 * <ul> 
	 * <li>where a database trigger alters the object state upon insert or update</li>
	 * <li>after executing direct SQL (eg. a mass update) in the same session</li>
	 * <li>after inserting a Blob or Clob</li>
	 * </ul>
	 */
	public void refresh (com.wifihi.persistence.Orderdetail orderdetail, Session s)
	{
		refresh((Object) orderdetail, s);
	}


}