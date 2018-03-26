package business.service;

import business.model.UserModel;
import dataAccess.ConnectionFactory;
import dataAccess.dbmodel.UserDTO;
import dataAccess.repository.IUserRepository;
import dataAccess.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService{
    private final IUserRepository repository;

    public UserService() {
        this.repository = new UserRepository(ConnectionFactory.getSingleInstance());
    }


    @Override
    public List<UserModel> findAll() {
        List<UserDTO> userList = repository.findAll();
        List<UserModel> result = new ArrayList<>();
        for(UserDTO u : userList){
            result.add(new UserModel(u.getUserId(), u.getUsername(), u.getPassword(), u.isAdmin()));
        }
        return result;
    }


    @Override
    public UserModel findById(int id) {
        UserDTO u = repository.findById(id);
        UserModel result = new UserModel(u.getUserId(), u.getUsername(), u.getPassword(), u.isAdmin());
        return result;
    }

    @Override
    public boolean addUser(UserModel user) {
        UserDTO u = new UserDTO();
        u.setUserId(user.getId());
        return false;
    }

    @Override
    public boolean editUser(int user_id, String username, String password, boolean is_admin) {
        return false;
    }

    @Override
    public boolean deleteUser(UserModel user) {
        return false;
    }
}
