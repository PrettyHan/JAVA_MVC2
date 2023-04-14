package eCommerce.Repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import eCommerce.Entity.CategoryEntity;


@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  CategoryRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
      JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
  }

  private RowMapper<CategoryEntity> RowMapper() {
    return (RowMapper<CategoryEntity>) (rs, rowNum) -> {
      CategoryEntity categoryEntity = new CategoryEntity();
      categoryEntity.setCTGR_ID(Integer.valueOf(rs.getString("CTGR_ID")));
      categoryEntity.setNAME(rs.getString("NAME"));
      categoryEntity.setLAST_UPD_DATE(rs.getTimestamp("LAST_UPD_DATE"));

      return categoryEntity;
    };
  }

  @Override
  public Integer findIdByName(String CATEGORY_NAME) {
    String sql = "select CTGR_ID from ONLINE_CATEGORY where NAME = :CATEGORY_NAME";
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("CATEGORY_NAME", CATEGORY_NAME);


    return namedParameterJdbcTemplate.queryForObject(sql, param, Integer.class);
  }

  @Override
  public List<CategoryEntity> findAll() {
    String sql = "select * from ONLINE_CATEGORY where 1 = 1";

    return namedParameterJdbcTemplate.query(sql, RowMapper());
  }
}
