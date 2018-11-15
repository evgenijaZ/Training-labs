package edu.training.model.dao;

import java.util.List;

public interface IDao<E, K> {

    List<E> getAll();

    boolean update(E entity);

    E getByKey(K key);

    boolean deleteByKey(K key);

    boolean create(E entity);
}
