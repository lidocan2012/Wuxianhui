package com.wifihi.persistence.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.wifihi.persistence.dao.iface.UserwifivisitinghistoryDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseUserwifivisitinghistoryDAO extends com.wifihi.persistence.dao._RootDAO {

	public BaseUserwifivisitinghistoryDAO () {}
	
	public BaseUserwifivisitinghistoryDAO (Session session) {
		super(session);
	}

	// query name references


	public static UserwifivisitinghistoryDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static UserwifivisitinghistoryDAO getInstance () {
		if (null == instance) instance = new com.wifihi.persistence.dao.UserwifivisitinghistoryDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.wifihi.persistence.Userwifivisitinghistory.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.wifihi.persistence.Userwifivisitinghistory
	 */
	public com.wifihi.persistence.Userwifivisitinghistory cast (Object object) {
		return (com.wifihi.persistence.Userwifivisitinghistory) object;
	}

	public com.wifihi.persistence.Userwifivisitinghistory get(java.lang.Long key)
	{
		return (com.wifihi.persistence.Userwifivisitinghistory) get(getReferenceClass(), key);
	}

	public com.wifihi.persistence.Userwifivisitinghistory get(java.lang.Long key, Session s)
	{
		return (com.wifihi.persistence.Userwifivisitinghistory) get(getReferenceClass(), key, s);
	}

	public com.wifihi.persistence.Userwifivisitinghistory load(java.lang.Long key)
	{
		return (com.wifihi.persistence.Userwifivisitinghistory) load(getReferenceClass(), key);
	}

	public com.wifihi.persistence.Userwifivisitinghistory load(java.lang.Long key, Session s)
	{
		return (com.wifihi.persistence.Userwifivisitinghistory) load(getReferenceClass(), key, s);
	}

	public com.wifihi.persistence.Userwifivisitinghistory loadInitialize(java.lang.Long key, Session s) 
	{ 
		com.wifihi.persistence.Userwifivisitinghistory obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.wifihi.persistence.Userwifivisitinghistory> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.wifihi.persistence.Userwifivisitinghistory> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.wifihi.persistence.Userwifivisitinghistory> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param userwifivisitinghistory a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Long save(com.wifihi.persistence.Userwifivisitinghistory userwifivisitinghistory)
	{
		return (java.lang.Long) super.save(userwifivisitinghistory);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param userwifivisitinghistory a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Long save(com.wifihi.persistence.Userwifivisitinghistory userwifivisitinghistory, Session s)
	{
		return (java.lang.Long) save((Object) userwifivisitinghistory, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param userwifivisitinghistory a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.wifihi.persistence.Userwifivisitinghistory userwifivisitinghistory)
	{
		saveOrUpdate((Object) userwifivisitinghistory);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param userwifivisitinghistory a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.wifihi.persistence.Userwifivisitinghistory userwifivisitinghistory, Session s)
	{
		saveOrUpdate((Object) userwifivisitinghistory, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param userwifivisitinghistory a transient instance containing updated state
	 */
	public void update(com.wifihi.persistence.Userwifivisitinghistory userwifivisitinghistory) 
	{
		update((Object) userwifivisitinghistory);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param userwifivisitinghistory a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.wifihi.persistence.Userwifivisitinghistory userwifivisitinghistory, Session s)
	{
		update((Object) userwifivisitinghistory, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.Long id)
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
	public void delete(java.lang.Long id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param userwifivisitinghistory the instance to be removed
	 */
	public void delete(com.wifihi.persistence.Userwifivisitinghistory userwifivisitinghistory)
	{
		delete((Object) userwifivisitinghistory);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param userwifivisitinghistory the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.wifihi.persistence.Userwifivisitinghistory userwifivisitinghistory, Session s)
	{
		delete((Object) userwifivisitinghistory, s);
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
	public void refresh (com.wifihi.persistence.Userwifivisitinghistory userwifivisitinghistory, Session s)
	{
		refresh((Object) userwifivisitinghistory, s);
	}


}