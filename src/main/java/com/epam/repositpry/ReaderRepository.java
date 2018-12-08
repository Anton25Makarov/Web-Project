package com.epam.repositpry;

import com.epam.model.Reader;
import com.epam.specification.SqlSpecification;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReaderRepository implements Repository<Reader> {

    @Override
    public List<Reader> query(SqlSpecification readerSqlSpecification) {
        return null;
    }

    @Override
    public Optional<Reader> queryForSingleResult(SqlSpecification sqlSpecification, String... sqlValues)
            throws SQLException {
        return Optional.empty();
    }
}
