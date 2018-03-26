package business.service;

import business.model.ShowModel;

import java.util.List;

public interface IShowService {
    public List<ShowModel> findAll();
    public ShowModel findById(int id);
    public boolean edit(ShowModel show);
    public boolean delete(int id);
}
