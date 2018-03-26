package dataAccess.repository;

import dataAccess.ConnectionFactory;
import dataAccess.dbmodel.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final ConnectionFactory connectionFactory;

    public UserRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<UserDTO> findAll() {
        Connection connection = connectionFactory.getConnection();
        List<UserDTO> users = new ArrayList<UserDTO>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM `user`";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public UserDTO findById(int id) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement findStatement = null;
        String findStatementString = "SELECT * FROM `user` WHERE user_id = ?";
        ResultSet rs = null;
        UserDTO user = null;

        try {
            findStatement = connection.prepareStatement(findStatementString);
            findStatement.setInt(1, id);
            rs = findStatement.executeQuery();

            if(rs.next())
                user = getUserFromResultSet(rs);
            //TODO: warning

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.close(rs);
            connectionFactory.close(findStatement);
            connectionFactory.close(connection);
        }
        return user;
    }

    @Override
    public int insert(UserDTO user){
        Connection connection = connectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        String insertStatementString = "INSERT INTO `user` (username, password, is_admin) VALUES(?, ?, ?)";
        int insertedId = -1;

        try {
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, user.getUsername());
            insertStatement.setString(2, user.getPassword());
            insertStatement.setBoolean(3, user.isAdmin());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.close(insertStatement);
            connectionFactory.close(connection);
        }
        return insertedId;
    }

    @Override
    public int update(UserDTO user) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        String updateStatementString = "UPDATE `user` SET user_id = ? username = ?, password=?, is_admin=? WHERE user_id = ?";
        int updatedRows = 0;

        try {
            updateStatement = connection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, user.getUsername());
            updateStatement.setString(2, user.getPassword());
            updateStatement.setBoolean(3, user.isAdmin());
            updateStatement.setInt(4, user.getUserId());
            updatedRows = updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.close(updateStatement);
            connectionFactory.close(connection);
        }
        return updatedRows;
    }

    @Override
    public int delete(int id) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement deleteStatement= null;
        String deleteStatementString = "DELETE FROM `user` WHERE user_id = ?";
        int rowsDeleted=0;

        try{
            deleteStatement = connection.prepareStatement(deleteStatementString);
            deleteStatement.setInt(1, id);
            rowsDeleted = deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.close(deleteStatement);
            connectionFactory.close(connection);
        }
        return rowsDeleted;
    }


    private UserDTO getUserFromResultSet(ResultSet rs) throws SQLException {
        UserDTO user = new UserDTO();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setIsAdmin(rs.getBoolean("is_admin"));
        return user;
    }
}
