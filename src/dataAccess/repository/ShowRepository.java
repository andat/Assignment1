package dataAccess.repository;

import dataAccess.ConnectionFactory;
import dataAccess.dbmodel.ShowDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowRepository implements IShowRepository{
    private final ConnectionFactory connectionFactory;

    public ShowRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<ShowDTO> findAll() {
        Connection connection = connectionFactory.getConnection();
        List<ShowDTO> shows = new ArrayList<ShowDTO>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM `show`";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                shows.add(getShowFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shows;
    }

    @Override
    public ShowDTO findById(int id) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement findStatement = null;
        String findStatementString = "SELECT * FROM `show` WHERE show_id = ?";
        ShowDTO show = null;
        ResultSet rs = null;

        try {
            findStatement = connection.prepareStatement(findStatementString);
            findStatement.setInt(1, id);
            rs = findStatement.executeQuery();

            if(rs.next())
                show = getShowFromResultSet(rs);
            //TODO: warning

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionFactory.close(rs);
            connectionFactory.close(findStatement);
            connectionFactory.close(connection);
        }
        return show;
    }

    @Override
    public int insert(ShowDTO show) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        String insertStatementString = "INSERT INTO `show` (title, genre, distribution, date, no_of_tickets) VALUES(?, ?, ?, ?, ?)";
        int insertedId = -1;

        try {
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, show.getTitle());
            insertStatement.setString(2, show.getGenre());
            insertStatement.setString(3, show.getDistribution());
            insertStatement.setDate(4, show.getDate());
            insertStatement.setInt(5, show.getNoOfTickets());
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
    public int update(ShowDTO show) {
        Connection connection = connectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        String updateStatementString = "UPDATE `show` SET title = ?, genre=?, distribution=?, date = ?, no_of_tickets =?  WHERE ticket_id = ?";
        int updatedRows = 0;

        try {
            updateStatement = connection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, show.getTitle());
            updateStatement.setString(2, show.getGenre());
            updateStatement.setString(3, show.getDistribution());
            updateStatement.setDate(4, show.getDate());
            updateStatement.setInt(5, show.getNoOfTickets());
            updateStatement.setInt(6, show.getShowId());
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
        String deleteStatementString = "DELETE FROM `show` WHERE show_id = ?";
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

    private ShowDTO getShowFromResultSet(ResultSet rs) throws SQLException {
        ShowDTO show = new ShowDTO();
        show.setShowId(rs.getInt("show_id"));
        show.setTitle(rs.getString("title"));
        show.setGenre(rs.getString("genre"));
        show.setDistribution(rs.getString("distribution"));
        show.setDate(rs.getDate("date"));
        show.setNoOfTickets(rs.getInt("no_of_tickets"));
        return show;
    }
}
