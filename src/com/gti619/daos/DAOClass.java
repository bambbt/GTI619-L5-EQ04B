package com.gti619.daos;

public interface DAOClass<T> {
		
		void save(T object);
		void update(T object);
		void delete(T object);
		T findById(T object);
}
