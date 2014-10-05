package com.wifihi.persistence.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.wifihi.persistence.dao.iface.WifimanageDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseWifimanageDAO extends com.wifihi.persistence.dao._RootDAO {

	public BaseWifimanageDAO () {}
	
	public BaseWifimanageDAO (Session session) {
		super(session);
	}

	// query name references


	public static WifimanageDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static WifimanageDAO getInstance () {
		if (null == instance) instance = new com.wifihi.persistence.dao.WifimanageDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.wifihi.persistence.Wifimanage.class;
	}

    public Order getDefaultOrder () {
		return Order.asc("DisplayName");
    }

	/**
	 * Cast the object as a com.wifihi.persistence.Wifimanage
	 */
	public com.wifihi.persistence.Wifimanage cast (Object object) {
		return (com.wifihi.persistence.Wifimanage) object;
	}

	public com.wifihi.persistence.Wifimanage get(java.lang.Long key)
	{
		return (com.wifihi.persistence.Wifimanage) get(getReferenceClass(), key);
	}

	public com.wifihi.persistence.Wifimanage get(java.lang.Long key, Session s)
	{
		return (com.wifihi.persistence.Wifimanage) get(getReferenceClass(), key, s);
	}

	public com.wifihi.persistence.Wifimanage load(java.lang.Long key)
	{
		return (com.wifihi.persistence.Wifimanage) load(getReferenceClass(), key);
	}

	public com.wifihi.persistence.Wifimanage load(java.lang.Long key, Session s)
	{
		return (com.wifihi.persistence.Wifimanage) load(getReferenceClass(), key, s);
	}

	public com.wifihi.persistence.Wifimanage loadInitialize(java.lang.Long key, Session s) 
	{ 
		com.wifihi.persistence.Wifimanage obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.wifihi.persistence.Wifimanage> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.wifihi.persistence.Wifimanage> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.wifihi.persistence.Wifimanage> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param wifimanage a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Long save(com.wifihi.persistence.Wifimanage wifimanage)
	{
		return (java.lang.Long) super.save(wifimanage);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param wifimanage a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Long save(com.wifihi.persistence.Wifimanage wifimanage, Session s)
	{
		return (java.lang.Long) save((Object) wifimanage, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param wifimanage a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.wifihi.persistence.Wifimanage wifimanage)
	{
		saveOrUpdate((Object) wifimanage);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param wifimanage a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.wifihi.persistence.Wifimanage wifimanage, Session s)
	{
		saveOrUpdate((Object) wifimanage, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param wifimanage a transient instance containing updated state
	 */
	public void update(com.wifihi.persistence.Wifimanage wifimanage) 
	{
		update((Object) wifimanage);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param wifimanage a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.wifihi.persistence.Wifimanage wifimanage, Session s)
	{
		update((Object) wifimanage, s);
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
	 * @param wifimanage the instance to be removed
	 */
	public void delete(com.wifihi.persistence.Wifimanage wifimanage)
	{
		delete((Object) wifimanage);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param wifimanage the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.wifihi.persistence.Wifimanage wifimanage, Session s)
	{
		delete((Object) wifimanage, s);
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
	public void refresh (com.wifihi.persistence.Wifimanage wifimanage, Session s)
	{
		refresh((Object) wifimanage, s);
	}


}