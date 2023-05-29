package dao;

import java.sql.SQLException;
import java.util.List;

public interface IEmployee<E> {
    public void insert( E e) throws SQLException;
    public E select(String id);

    public boolean update(E e)throws SQLException;
    public boolean delete(String id)throws SQLException;

}
