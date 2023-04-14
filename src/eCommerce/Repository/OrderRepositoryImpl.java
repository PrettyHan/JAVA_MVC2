package eCommerce.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import eCommerce.Entity.OrderEntity;


@Repository
public class OrderRepositoryImpl implements OrderRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  OrderRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
      JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
  }

  private RowMapper<OrderEntity> RowMapper() {
    return (RowMapper<OrderEntity>) (rs, rowNum) -> {
      OrderEntity orderEntity = new OrderEntity();
      orderEntity.setORDER_NO(Integer.valueOf(rs.getString("ORDER_NO")));
      orderEntity.setMEMBER_NO(Integer.valueOf(rs.getString("MEMBER_NO")));
      orderEntity.setTOTAL_MONEY(rs.getLong("TOTAL_MONEY"));
      orderEntity.setTOTAL_MONEY(rs.getLong("TOTAL_TAX"));
      orderEntity.setORDER_DATE(rs.getDate("ORDER_DATE"));
      orderEntity.setCOLLECT_NO(rs.getString("COLLECT_NO"));
      orderEntity.setLAST_UPD_DATE(rs.getTimestamp("LAST_UPD_DATE"));

      return orderEntity;
    };
  }

  @Override
  public void insert(Integer MEMBER_NO, Long TOTAL_MONEY, Long TOTAL_TAX, Integer COLLECT_NO) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    String sql = "insert into ONLINE_ORDER values "
        + "(:ORDER_NO, :MEMBER_NO, :TOTAL_MONEY, :TOTAL_TAX, NOW(), :COLLECT_NO, NOW())";

    param.addValue("ORDER_NO", null);
    param.addValue("MEMBER_NO", MEMBER_NO);
    param.addValue("TOTAL_MONEY", TOTAL_MONEY);
    param.addValue("TOTAL_TAX", TOTAL_TAX);
    param.addValue("COLLECT_NO", COLLECT_NO);

    namedParameterJdbcTemplate.update(sql, param);


  }
}
