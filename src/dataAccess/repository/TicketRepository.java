package dataAccess.repository;

import dataAccess.ConnectionFactory;
import dataAccess.dbmodel.TicketDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository implements ITicketRepository{
    private final ConnectionFactory connectionFactory;
    
    public TicketRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    
    @Override
    public List<TicketDTO> findAll() {
        Connection connection = connectionFactory.getConnection();
        List<TicketDTO> tickets = new ArrayList<TicketDTO>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM `ticket`";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                tickets.add(getTicketFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public TicketDTO findById(int id) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement findStatement = null;
        String findStatementString = "SELECT * FROM `ticket` WHERE ticket_id = ?";
        TicketDTO ticket = null;
        ResultSet rs = null;

        try {
            findStatement = connection.prepareStatement(findStatementString);
            findStatement.setInt(1, id);
            rs = findStatement.executeQuery();

            if(rs.next())
                ticket = getTicketFromResultSet(rs);
            //TODO: warning

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.close(rs);
            connectionFactory.close(findStatement);
            connectionFactory.close(connection);
        }
        return ticket;
    }

    @Override
    public TicketDTO findBySeat(int showId, int seatId) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement findStatement = null;
        String findStatementString = "SELECT * FROM `ticket` WHERE show_id = ? AND seat_id = ?";
        TicketDTO ticket = null;
        ResultSet rs = null;

        try {
            findStatement = connection.prepareStatement(findStatementString);
            findStatement.setInt(1, showId);
            findStatement.setInt(1, seatId);
            rs = findStatement.executeQuery();

            if(rs.next())
                ticket = getTicketFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.close(rs);
            connectionFactory.close(findStatement);
            connectionFactory.close(connection);
        }
        return ticket;
    }

    @Override
    public List<TicketDTO> findTicketsSold(int showId, boolean sold) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement findStatement = null;
        String findStatementString = "SELECT * FROM `ticket` WHERE show_id = ? AND booked = ?";
        List<TicketDTO> tickets = new ArrayList<>();
        ResultSet rs = null;

        try {
            findStatement = connection.prepareStatement(findStatementString);
            findStatement.setInt(1, showId);
            findStatement.setBoolean(2, sold);
            rs = findStatement.executeQuery();

            while (rs.next()) {
                tickets.add(getTicketFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.close(rs);
            connectionFactory.close(findStatement);
            connectionFactory.close(connection);
        }
        return tickets;
    }

    @Override
    public int insert(TicketDTO ticket) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        String insertStatementString = "INSERT INTO `ticket` (show_id, seat_id, booked) VALUES(?, ?, ?)";
        int insertedId = -1;

        try {
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, ticket.getShowId());
            insertStatement.setInt(2, ticket.getSeatId() );
            insertStatement.setBoolean(3, ticket.isBooked());
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
    public int update(TicketDTO ticket) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        String updateStatementString = "UPDATE `ticket` SET show_id = ?, seat_id=?, booked=? WHERE ticket_id = ?";
        int updatedRows = 0;

        try {
            updateStatement = connection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, ticket.getShowId());
            updateStatement.setInt(2, ticket.getSeatId());
            updateStatement.setBoolean(3, ticket.isBooked());
            updateStatement.setInt(4, ticket.getTicketId());
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
        String deleteStatementString = "DELETE FROM `ticket` WHERE ticket_id = ?";
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

    private TicketDTO getTicketFromResultSet(ResultSet rs) throws SQLException {
        TicketDTO ticket = new TicketDTO(rs.getInt("ticket_id"), rs.getInt("show_id"),
                rs.getInt("seat_id"), rs.getBoolean("booked"));
        return ticket;
    }
}
