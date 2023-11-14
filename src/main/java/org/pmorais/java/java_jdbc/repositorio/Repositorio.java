package org.pmorais.java.java_jdbc.repositorio;

import java.util.List;

public interface Repositorio<T> {

	List<T> findAll();
	T findById(Integer id);
	void insert(T t);
	void delete(Integer id);
}
