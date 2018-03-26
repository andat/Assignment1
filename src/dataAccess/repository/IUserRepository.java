package dataAccess.repository;

import dataAccess.dbmodel.UserDTO;

import java.util.List;

public interface IUserRepository {
    public List<UserDTO> findAll();
    public UserDTO findById(int id);
    public UserDTO findByUsername(String username);
    public int insert(UserDTO user);
    public int update(UserDTO user);
    public int delete(int id);
}
