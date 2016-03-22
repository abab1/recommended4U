package com.recommendations.dao;

import com.recommendations.entity.DomainObject;

public interface IGenericDao<T extends DomainObject> {
	
	public void addEntity(T entity);

}
