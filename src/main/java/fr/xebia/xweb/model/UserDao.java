package fr.xebia.xweb.model;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: jean
 * Date: 13/03/12
 * Time: 23:26
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class UserDao {
    @Resource
    private DataSource dataSource;

    public User load(Long id) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        String query = "select * from users where id=:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        List<User> users = template.query(query, params, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return new User(resultSet.getLong("id"), resultSet.getString("name"));
            }
        });
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }
}
