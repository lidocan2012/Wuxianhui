package com.wifihi.persistence.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.wifihi.persistence.dao.iface.MenuuserDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseMenuuserDAO extends com.wifihi.persistence.dao._RootDAO {

	public BaseMenuuserDAO () {}
	
	public BaseMenuuserDAO (Session session) {
		super(session);
	}

	// query name references


	public static MenuuserDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static MenuuserDAO getInstance () {
		if (null == instance) instance = new com.wifihi.persistence.dao.MenuuserDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.wifihi.persistence.Menuuser.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.wifihi.persistence.Menuuser
	 */
	public com.wifihi.persistence.Menuuser cast (Object object) {
		return (com.wifihi.persistence.Menuuser) object;
	}

	public com.wifihi.persistence.Menuuser get(com.wifihi.persistence.Menuuser key)
	{
		return (com.wifihi.persistence.Menuuser) get(getReferenceClass(), key);
	}

	public com.wifihi.persistence.Menuuser get(com.wifihi.persistence.Menuuser key, Session s)
	{
		return (com.wifihi.persistence.Menuuser) get(getReferenceClass(), key, s);
	}

	public com.wifihi.persistence.Menuuser load(com.wifihi.persistence.Menuuser key)
	{
		return (com.wifihi.persistence.Menuuser) load(getReferenceClass(), key);
	}

	public com.wifihi.persistence.Menuuser load(com.wifihi.persistence.Menuuser key, Session s)
	{
		return (com.wifihi.persistence.Menuuser) load(getReferenceClass(), key, s);
	}

	public com.wifihi.persistence.Menuuser loadInitialize(com.wifihi.persistence.Menuuser key, Session s) 
	{ 
		com.wifihi.persistence.Menuuser obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.wifihi.persistence.Menuuser> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.wifihi.persistence.Menuuser> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.wifihi.persistence.Menuuser> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param menuuser a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public com.wifihi.persistence.Menuuser save(com.wifihi.persistence.Menuuser menuuser)
	{
		return (com.wifihi.persistence.Menuuser) super.save(menuuser);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param menuuser a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public com.wifihi.persistence.Menuuser save(com.wifihi.persistence.Menuuser menuuser, Session s)
	{
		return (com.wifihi.persistence.Menuuser) save((Object) menuuser, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param menuuser a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.wifihi.persistence.Menuuser menuuser)
	{
		saveOrUpdate((Object) menuuser);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param menuuser a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.wifihi.persistence.Menuuser menuuser, Session s)
	{
		saveOrUpdate((Object) menuuser, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param menuuser a transient instance containing updated state
	 */
	public void update(com.wifihi.persistence.Menuuser menuuser) 
	{
		update((Object) menuuser);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param menuuser a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.wifihi.persistence.Menuuser menuuser, Session s)
	{
		update((Object) menuuser, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param menuuser the instance to be removed
	 */
	public void delete(com.wifihi.persistence.Menuuser menuuser)
	{
		delete((Object) menuuser);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param menuuser the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.wifihi.persistence.Menuuser menuuser, Session s)
	{
		delete((Object) menuuser, s);
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
	public void refresh (com.wifihi.persistence.Menuuser menuuser, Session s)
	{
		refresh((Object) menuuser, s);
	}


}