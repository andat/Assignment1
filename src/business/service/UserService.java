package business.service;

import util.PasswordEncryptionUtil;
import business.model.UserModel;
import dataAccess.ConnectionFactory;
import dataAccess.dbmodel.UserDTO;
import dataAccess.repository.IUserRepository;
import dataAccess.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService implements IUserService{
    private final IUserRepository repository;
    private PasswordEncryptionUtil encryptionUtil;

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
    public List<UserModel> findAllCashiers() {
        return this.findAll().stream().filter(u -> u.isAdmin() == false).collect(Collectors.toList());
    }

    @Override
    public UserModel findById(int id) {
        UserDTO u = repository.findById(id);
        UserModel result = new UserModel(u.getUserId(), u.getUsername(), u.getPassword(), u.isAdmin());
        return result;
    }

    @Override
    //adds a user to the database, after encrypting the entered password
    public boolean addUser(UserModel user) {
        String encryptedPass = encryptionUtil.encryptPasswordSHA256(user.getPassword());
        UserDTO u = new UserDTO(user.getId(), user.getUsername(), encryptedPass,
                user.isAdmin());
        int insertedId = repository.insert(u);
        return (insertedId != -1);
    }

    @Override
    public boolean editUser(UserModel user) {
        String encryptedPass = encryptionUtil.encryptPasswordSHA256(user.getPassword());
        UserDTO u = new UserDTO(user.getId(), user.getUsername(), encryptedPass, user.isAdmin());
        int updatedRows = repository.update(u);

        return (updatedRows != 0);
    }

    @Override
    public boolean deleteUser(int id) {
        int deletedRows = repository.delete(id);

        return (deletedRows != 0);
    }

    @Override
    public UserModel checkCredentials(String username, String password) {
        UserDTO u = repository.findByUsername(username);
        UserModel user = null;

        if(u != null){
            String storedPassword = u.getPassword();
            //System.out.println("stored: " + storedPassword);
            if(encryptionUtil.validatePassword(password, storedPassword))
                user =  new UserModel(u.getUserId(), u.getUsername(), u.getPassword(), u.isAdmin());
        }
        return user;
    }

//    public static void main(String args[]){
//        UserService us = new UserService();
//        us.addUser(new UserModel(-1, "anda", "master", true));
//    }
}
