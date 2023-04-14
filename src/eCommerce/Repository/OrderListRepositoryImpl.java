package eCommerce.Repository;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import eCommerce.Entity.OrderListEntity;


@Repository
public class OrderListRepositoryImpl implements OrderListRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  OrderListRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
      JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
  }

  private RowMapper<OrderListEntity> RowMapper() {
    return (RowMapper<OrderListEntity>) (rs, rowNum) -> {
      OrderListEntity orderListEntity = new OrderListEntity();
      orderListEntity.setLIST_NO(Integer.valueOf(rs.getString("LIST_NO")));
      orderListEntity.setCOLLECT_NO(rs.getString("COLLECT_NO"));
      orderListEntity.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
      orderListEntity.setORDER_COUNT(Integer.valueOf(rs.getString("ORDER_COUNT")));
      orderListEntity.setORDER_PRICE(rs.getObject("ORDER_PRICE", BigInteger.class));

      return orderListEntity;
    };
  }

  @Override
  public Integer createCollectNo() {
    String sql = "select MAX(COLLECT_NO+0) from ONLINE_ORDER_LIST";

    Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
    if (result == null) {
      return 1;
    }
    return (result + 1);
  }

  @Override
  public void insert(Integer COLLECT_NO, String PRODUCT_CODE, Integer ORDER_COUNT,
      Long ORDER_PRICE) {
    MapSqlParameterSource param = new MapSqlParameterSource();

    String sql = "insert into ONLINE_ORDER_LIST values "
        + "(:LIST_NO, :COLLECT_NO, :PRODUCT_CODE, :ORDER_COUNT, :ORDER_PRICE)";

    param.addValue("LIST_NO", null);
    param.addValue("COLLECT_NO", COLLECT_NO);
    param.addValue("PRODUCT_CODE", PRODUCT_CODE);
    param.addValue("ORDER_COUNT", ORDER_COUNT);
    param.addValue("ORDER_PRICE", ORDER_PRICE);

    namedParameterJdbcTemplate.update(sql, param);

  }
}
