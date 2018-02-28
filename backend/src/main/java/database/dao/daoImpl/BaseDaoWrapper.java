package database.dao.daoImpl;

import database.connectionFactory.ConnectionFactory;
import database.dao.BaseDao;
import entity.Entity;
import exception.DatabaseException;
import metadata.ErrorMessage;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import static metadata.ErrorMessage.ERROR_WHILE_EXECUTING;
import static metadata.ErrorMessage.ERROR_WHILE_EXECUTING_WITHOUT_PARAMS;

public abstract class BaseDaoWrapper<T extends Entity> implements BaseDao {

    private static final Logger LOGGER = Logger.getLogger(BaseDaoWrapper.class);

    public ArrayList<T> getByQuery(String query) {
        ArrayList<T> list = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                list.add(getEntity(resultSet));
            }
        } catch (SQLException ex) {
            String error = String.format(ERROR_WHILE_EXECUTING_WITHOUT_PARAMS, query);
            LOGGER.error(error);
            throw new DatabaseException(error, ex);
        }
        return list;

    }

    public ArrayList<T> getByQueryAndParams(String query, Object... params) {
        ArrayList<T> list = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(connection, query, params);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                list.add(getEntity(resultSet));
            }
        } catch (SQLException ex) {
            String error = String.format(ERROR_WHILE_EXECUTING, query, Arrays.toString(params));
            LOGGER.error(error);
            throw new DatabaseException(error, ex);
        }
        return list;
    }


    private PreparedStatement createPreparedStatement(Connection con, String query, Object... params) throws SQLException {
        PreparedStatement ps = con.prepareStatement(query);
        int count = 1;
        for (Object param : params) {
            ps.setObject(count, param);
            count++;
        }
        return ps;
    }

    public void addUpdateEntity(String query) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);

        } catch (SQLException e) {
            String error = String.format(ErrorMessage.CAN_NOT_ADD_OR_UPDATE, query);
            LOGGER.error(error, e);
            throw new DatabaseException(error, e);

        }
    }


    protected abstract T getEntity(ResultSet rs) throws SQLException;

    public abstract LinkedHashMap<String, String> entityToLinkedHashMap(T entity);

    public abstract String getValues(T entity);
}
