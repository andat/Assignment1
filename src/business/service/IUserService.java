package business.service;

import business.model.UserModel;

import java.util.List;

public interface IUserService {
    public List<UserModel> findAll();
    public UserModel findById(int id);
    public boolean addUser(UserModel user);
    public boolean editUser(UserModel user);
    public boolean deleteUser(int id);
    public boolean checkCredentials(String username, String password);
}
