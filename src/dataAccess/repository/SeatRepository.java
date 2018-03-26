package dataAccess.repository;

import dataAccess.ConnectionFactory;
import dataAccess.dbmodel.SeatDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatRepository implements ISeatRepository{
    private final ConnectionFactory connectionFactory;

    public SeatRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<SeatDTO> findAll() {
        Connection connection = connectionFactory.getConnection();
        List<SeatDTO> seats = new ArrayList<SeatDTO>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM `seat`";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                seats.add(getSeatFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seats;
    }

    @Override
    public SeatDTO findById(int id) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement findStatement = null;
        String findStatementString = "SELECT * FROM `seat` WHERE seat_id = ?";
        ResultSet rs = null;
        SeatDTO seat = null;

        try {
            findStatement = connection.prepareStatement(findStatementString);
            findStatement.setInt(1, id);
            rs = findStatement.executeQuery();

            if(rs.next())
                seat = getSeatFromResultSet(rs);
            //TODO: warning

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.close(rs);
            connectionFactory.close(findStatement);
            connectionFactory.close(connection);
        }
        return seat;
    }

    @Override
    public int insert(SeatDTO seat){
        Connection connection = connectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        String insertStatementString = "INSERT INTO `seat` (row, number) VALUES(?, ?)";
        int insertedId = -1;

        try {
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, seat.getRow());
            insertStatement.setInt(2, seat.getNumber());
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
    public int update(SeatDTO seat) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        String updateStatementString = "UPDATE `seat` SET row = ?, number = ? WHERE seat_id = ?";
        int updatedRows = 0;

        try {
            updateStatement = connection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, seat.getRow());
            updateStatement.setInt(2, seat.getNumber());
            updateStatement.setInt(3, seat.getSeatId());
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
        String deleteStatementString = "DELETE FROM `seat` WHERE seat_id = ?";
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


    private SeatDTO getSeatFromResultSet(ResultSet rs) throws SQLException {
        SeatDTO seat = new SeatDTO();
        seat.setSeatId(rs.getInt("seat_id"));
        seat.setRow(rs.getString("row"));
        seat.setNumber(rs.getInt("number"));
        return seat;
    }
}
