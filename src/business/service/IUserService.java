package business.service;

import business.model.UserModel;

import java.util.List;

public interface IUserService {
    public List<UserModel> findAll();
    public UserModel findById(int id);
    public boolean addUser(UserModel user);
    public boolean editUser(int user_id, String username, String password, boolean is_admin);
    public boolean deleteUser(UserModel user);
}
