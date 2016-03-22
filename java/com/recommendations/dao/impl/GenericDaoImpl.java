package com.recommendations.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.recommendations.dao.IGenericDao;
import com.recommendations.entity.DomainObject;

public abstract class GenericDaoImpl<T extends DomainObject> implements IGenericDao<T>{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addEntity(T entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

}
