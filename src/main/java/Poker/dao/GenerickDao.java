package Poker.dao;

import java.util.List;

public interface GenerickDao<T> {

    public T create(T obj);
    public T read(int id);
    public void delete(int id);
    public void update(T obj);
    public List<T> getAll();

}
