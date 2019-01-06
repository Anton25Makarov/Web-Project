package com.epam.library.repositpry;

import com.epam.library.builder.Builder;
import com.epam.library.builder.ReaderBuilder;
import com.epam.library.model.Reader;
import com.epam.library.specification.SqlSpecification;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReaderRepository extends AbstractRepository<Reader> {
    private static final String SELECT_QUERY = "select * from reader ";


    public ReaderRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Reader> query(SqlSpecification readerSqlSpecification) {
        return null;
    }

    @Override
    public Optional<Reader> queryForSingleResult(SqlSpecification specification)
            throws SQLException {

        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();

        Builder<Reader> builder = getBuilder();

        return executeQueryForSingleResult(builder, query, parameters);
    }

    @Override
    public boolean save(Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Reader reader) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Builder<Reader> getBuilder() {
        return new ReaderBuilder();
    }
}
