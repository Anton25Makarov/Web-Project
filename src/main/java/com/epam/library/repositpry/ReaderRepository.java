package com.epam.library.repositpry;

import com.epam.library.builder.Builder;
import com.epam.library.builder.ReaderBuilder;
import com.epam.library.exception.RepositoryException;
import com.epam.library.model.Reader;
import com.epam.library.specification.SqlSpecification;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReaderRepository extends AbstractRepository<Reader> {
    private static final String SELECT_QUERY = "select * from reader ";
    private static final String REMOVE_QUERY = "delete from reader where id = ?";
    private static final String INSERT_QUERY =
            "insert into reader (name, surname, login, password, telephone)\n" +
                    "values (?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY =
            "update reader\n" +
                    "set name      = ?,\n" +
                    "    surname   = ?,\n" +
                    "    login     = ?,\n" +
                    "    password  = ?,\n" +
                    "    telephone = ?\n" +
                    "where id = ?;";


    public ReaderRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Reader> query(SqlSpecification specification) throws RepositoryException {
        try {
            String query = SELECT_QUERY + specification.toSql();
            List<String> parameters = specification.getParameters();

            return executeQuery(query, parameters);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Optional<Reader> queryForSingleResult(SqlSpecification specification) throws RepositoryException {
        try {

            String query = SELECT_QUERY + specification.toSql();
            List<String> parameters = specification.getParameters();

            Builder<Reader> builder = getBuilder();

            return executeQueryForSingleResult(builder, query, parameters);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void save(Reader reader) throws RepositoryException {

        Map<Integer, Object> map = new HashMap<>();
        int i = 1;

        map.put(i++, reader.getName());
        map.put(i++, reader.getSurname());
        map.put(i++, reader.getLogin());
        map.put(i++, DigestUtils.md5Hex(reader.getPassword()));
        map.put(i++, reader.getTelephoneNumber());

        try {
            if (reader.getId() == null) {
                executeSave(map, INSERT_QUERY);
            } else {
                map.put(i, reader.getId());
                executeSave(map, UPDATE_QUERY);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void remove(Reader reader) throws RepositoryException {
        try {
            executeRemove(reader, REMOVE_QUERY);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    protected Builder<Reader> getBuilder() {
        return new ReaderBuilder();
    }

}
