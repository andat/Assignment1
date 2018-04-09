package dataAccess.repository;

import dataAccess.dbmodel.ShowDTO;

import java.util.List;

public interface IShowRepository {
    public List<ShowDTO> findAll();
    public ShowDTO findById(int id);
    public int insert(ShowDTO show);
    public int update(ShowDTO show);
    public int delete(int id);
}
