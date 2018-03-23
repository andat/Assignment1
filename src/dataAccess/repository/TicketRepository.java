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
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return ticket;
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
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(connection);
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
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
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
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(connection);
        }
        return rowsDeleted;
    }

    private TicketDTO getTicketFromResultSet(ResultSet rs) throws SQLException {
        TicketDTO ticket = new TicketDTO();
        ticket.setTicketId(rs.getInt("ticket_id"));
        ticket.setShowId(rs.getInt("show_id"));
        ticket.setSeatId(rs.getInt("seat_id"));
        ticket.setBooked(rs.getBoolean("booked"));
        return ticket;
    }
}
