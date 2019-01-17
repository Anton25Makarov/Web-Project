package com.epam.library.repositpry;

import com.epam.library.builder.Builder;
import com.epam.library.builder.EmployeeBuilder;
import com.epam.library.model.Employee;
import com.epam.library.specification.SqlSpecification;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EmployeeRepository extends AbstractRepository<Employee> {
    private static final String SHOW_COLUMNS_QUERY = "show columns from `employee`";
    private static final String SELECT_QUERY = "select * from employee ";
    private static final String REMOVE_QUERY = "delete from employee where id = ?";
    private static final String INSERT_QUERY =
            "insert into employee (login, password, name, surname, is_admin)\n" +
                    "values (?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY =
            "update employee\n" +
                    "set login    = ?,\n" +
                    "    password = ?,\n" +
                    "    name     = ?,\n" +
                    "    surname  = ?,\n" +
                    "    is_admin = ?\n" +
                    "where id = ?;";

    public EmployeeRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Employee> query(SqlSpecification specification) throws SQLException {
        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();

        return executeQuery(query, parameters);
    }

    @Override
    public Optional<Employee> queryForSingleResult(SqlSpecification specification) throws SQLException {

        String query = SELECT_QUERY + specification.toSql();
        List<String> parameters = specification.getParameters();

        Builder<Employee> builder = getBuilder();

        return executeQueryForSingleResult(builder, query, parameters);
    }

    @Override
    public void save(Employee employee) throws SQLException {

        Map<Integer, Object> map = new HashMap<>();
        int i = 1;

        map.put(i++, employee.getLogin());
        map.put(i++, DigestUtils.md5Hex(employee.getPassword()));
        map.put(i++, employee.getName());
        map.put(i++, employee.getSurname());
        map.put(i++, employee.isAdmin());


        if (employee.getId() == null) {
            executeSave(map, INSERT_QUERY);
        } else {
            map.put(i, UPDATE_QUERY);
        }
    }

    @Override
    public void remove(Employee employee) throws SQLException {
        executeRemove(employee, REMOVE_QUERY);
    }

    protected Builder<Employee> getBuilder() {
        return new EmployeeBuilder();
    }

}
//map<Str, obj> fields
// fields.put(NAME_OF_COLUMN, employee.getId);
