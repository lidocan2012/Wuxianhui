package com.wifihi.persistence.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.wifihi.persistence.dao.iface.MenuDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseMenuDAO extends com.wifihi.persistence.dao._RootDAO {

	public BaseMenuDAO () {}
	
	public BaseMenuDAO (Session session) {
		super(session);
	}

	// query name references


	public static MenuDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static MenuDAO getInstance () {
		if (null == instance) instance = new com.wifihi.persistence.dao.MenuDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.wifihi.persistence.Menu.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.wifihi.persistence.Menu
	 */
	public com.wifihi.persistence.Menu cast (Object object) {
		return (com.wifihi.persistence.Menu) object;
	}

	public com.wifihi.persistence.Menu get(java.lang.Short key)
	{
		return (com.wifihi.persistence.Menu) get(getReferenceClass(), key);
	}

	public com.wifihi.persistence.Menu get(java.lang.Short key, Session s)
	{
		return (com.wifihi.persistence.Menu) get(getReferenceClass(), key, s);
	}

	public com.wifihi.persistence.Menu load(java.lang.Short key)
	{
		return (com.wifihi.persistence.Menu) load(getReferenceClass(), key);
	}

	public com.wifihi.persistence.Menu load(java.lang.Short key, Session s)
	{
		return (com.wifihi.persistence.Menu) load(getReferenceClass(), key, s);
	}

	public com.wifihi.persistence.Menu loadInitialize(java.lang.Short key, Session s) 
	{ 
		com.wifihi.persistence.Menu obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.wifihi.persistence.Menu> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.wifihi.persistence.Menu> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.wifihi.persistence.Menu> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param menu a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Short save(com.wifihi.persistence.Menu menu)
	{
		return (java.lang.Short) super.save(menu);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param menu a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Short save(com.wifihi.persistence.Menu menu, Session s)
	{
		return (java.lang.Short) save((Object) menu, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param menu a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.wifihi.persistence.Menu menu)
	{
		saveOrUpdate((Object) menu);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param menu a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.wifihi.persistence.Menu menu, Session s)
	{
		saveOrUpdate((Object) menu, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param menu a transient instance containing updated state
	 */
	public void update(com.wifihi.persistence.Menu menu) 
	{
		update((Object) menu);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param menu a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.wifihi.persistence.Menu menu, Session s)
	{
		update((Object) menu, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.Short id)
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
	public void delete(java.lang.Short id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param menu the instance to be removed
	 */
	public void delete(com.wifihi.persistence.Menu menu)
	{
		delete((Object) menu);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param menu the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.wifihi.persistence.Menu menu, Session s)
	{
		delete((Object) menu, s);
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
	public void refresh (com.wifihi.persistence.Menu menu, Session s)
	{
		refresh((Object) menu, s);
	}


}