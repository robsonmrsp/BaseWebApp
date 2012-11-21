package br.com.mr.baseapp.server;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


public /*abstract */class Dao<A> {

	Class<A> clazz;
	
	public Dao(Class<A> clazz) {
		this.clazz = clazz;
	}

	@Inject
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
//	@Transactional
	public A find(Serializable key){
		return (A)sessionFactory.getCurrentSession().get(clazz, key);
	}
	@Transactional
	public void save(A a){
		sessionFactory.getCurrentSession().saveOrUpdate(a);
	}	
	@Transactional
	public void delete(A a){
		sessionFactory.getCurrentSession().saveOrUpdate(a);
	}
}
