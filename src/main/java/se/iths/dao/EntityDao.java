package se.iths.dao;

import java.util.List;
import java.util.Optional;

public interface EntityDao<T> {
        void create(T t);
        Optional<T> getById(Long id);
        List<T> getAll();
        void delete(Long id);
        void update(T t);
}


