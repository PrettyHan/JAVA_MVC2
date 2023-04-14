package eCommerce.Repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import eCommerce.Entity.ProductEntity;


@Repository
public class ProductRepositoryImpl implements ProductRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  ProductRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
      JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
  }

  private RowMapper<ProductEntity> RowMapper() {
    return (RowMapper<ProductEntity>) (rs, rowNum) -> {
      ProductEntity productEntity = new ProductEntity();
      productEntity.setPRODUCT_CODE(rs.getString("PRODUCT_CODE"));
      productEntity.setCATEGORY_ID(Integer.valueOf(rs.getString("CATEGORY_ID")));
      productEntity.setPRODUCT_NAME(rs.getString("PRODUCT_NAME"));
      productEntity.setMAKER(rs.getString("MAKER"));
      productEntity.setSTOCK_COUNT(Integer.valueOf(rs.getString("STOCK_COUNT")));
      productEntity.setREGISTER_DATE(rs.getDate("REGISTER_DATE"));
      productEntity.setUNIT_PRICE(rs.getLong("UNIT_PRICE"));
      productEntity.setPICTURE_NAME(rs.getString("PICTURE_NAME"));
      productEntity.setMEMO(rs.getString("MEMO"));
      productEntity.setDELETE_FLG(rs.getString("DELETE_FLG").charAt(0));
      productEntity.setLAST_UPD_DATE(rs.getTimestamp("LAST_UPD_DATE"));

      return productEntity;
    };
  }

  @Override
  public List<ProductEntity> findByInfo(Integer PAGE, Integer CATEGORY_ID, String PRODUCT_NAME,
      String MAKER, String UPPER_LIMIT, String LOWER_LIMIT) {

    MapSqlParameterSource param = new MapSqlParameterSource();
    StringBuilder sb = new StringBuilder();
    sb.append("select * from ONLINE_PRODUCT where 1=1 and DELETE_FLG = 0");

    if (CATEGORY_ID != null) {
      sb.append(" and CATEGORY_ID = :CATEGORY_ID");
      param.addValue("CATEGORY_ID", CATEGORY_ID);
    }

    if (PRODUCT_NAME != null && PRODUCT_NAME != "") {
      sb.append(" and PRODUCT_NAME like :PRODUCT_NAME");
      param.addValue("PRODUCT_NAME", "%" + PRODUCT_NAME + "%");
    }
    if (MAKER != null && MAKER != "") {
      sb.append(" and MAKER like :MAKER");
      param.addValue("MAKER", "%" + MAKER + "%");
    }
    if (LOWER_LIMIT != null && LOWER_LIMIT != "") {
      sb.append(" and UNIT_PRICE >= :LOWER_LIMIT");
      param.addValue("LOWER_LIMIT", Integer.valueOf(LOWER_LIMIT));
    }
    if (UPPER_LIMIT != null && UPPER_LIMIT != "") {
      sb.append(" and UNIT_PRICE <= :UPPER_LIMIT");
      param.addValue("UPPER_LIMIT", Integer.valueOf(UPPER_LIMIT));
    }

    sb.append(" and DELETE_FLG = '0'");
    sb.append(" limit :PAGE,10");
    param.addValue("PAGE", PAGE);

    return this.namedParameterJdbcTemplate.query(sb.toString(), param, RowMapper());
  }

  @Override
  public Integer findMax() {
    String sql = "select COUNT(*) from ONLINE_PRODUCT";

    return jdbcTemplate.queryForObject(sql, Integer.class);
  }

  @Override
  public List<ProductEntity> findByCode(String PRODUCT_CODE) {
    MapSqlParameterSource param = new MapSqlParameterSource();

    String sql = "select * from ONLINE_PRODUCT where PRODUCT_CODE = :PRODUCT_CODE";
    param.addValue("PRODUCT_CODE", PRODUCT_CODE);

    return this.namedParameterJdbcTemplate.query(sql, param, RowMapper());
  }

  @Override
  public Integer findStock(String order_code) {
    MapSqlParameterSource param = new MapSqlParameterSource();

    String sql = "select * from ONLINE_PRODUCT where PRODUCT_CODE = :PRODUCT_CODE";
    param.addValue("PRODUCT_CODE", order_code);


    return this.namedParameterJdbcTemplate.query(sql, param, RowMapper()).get(0).getSTOCK_COUNT();
  }

  @Override
  public void updateStock(String PRODUCT_CODE, Integer UPDATE_STOCK) {
    MapSqlParameterSource param = new MapSqlParameterSource();

    String sql =
        "update ONLINE_PRODUCT set STOCK_COUNT = :UPDATE_STOCK, LAST_UPD_DATE = NOW() where PRODUCT_CODE = :PRODUCT_CODE";
    param.addValue("PRODUCT_CODE", PRODUCT_CODE);
    param.addValue("UPDATE_STOCK", UPDATE_STOCK);

    this.namedParameterJdbcTemplate.update(sql, param);


  }

  @Override
  public List<ProductEntity> findPage(Integer PAGE, Integer CATEGORY_ID, String PRODUCT_NAME,
      String MAKER, String UPPER_LIMIT, String LOWER_LIMIT) {
    MapSqlParameterSource param = new MapSqlParameterSource();
    StringBuilder sb = new StringBuilder();
    sb.append("select * from ONLINE_PRODUCT where 1=1 and DELETE_FLG = 0");

    if (CATEGORY_ID != null) {
      sb.append(" and CATEGORY_ID = :CATEGORY_ID");
      param.addValue("CATEGORY_ID", CATEGORY_ID);
    }

    if (PRODUCT_NAME != null && PRODUCT_NAME != "") {
      sb.append(" and PRODUCT_NAME like :PRODUCT_NAME");
      param.addValue("PRODUCT_NAME", "%" + PRODUCT_NAME + "%");
    }
    if (MAKER != null && MAKER != "") {
      sb.append(" and MAKER like :MAKER");
      param.addValue("MAKER", "%" + MAKER + "%");
    }
    if (LOWER_LIMIT != null && LOWER_LIMIT != "") {
      sb.append(" and UNIT_PRICE >= :LOWER_LIMIT");
      param.addValue("LOWER_LIMIT", Integer.valueOf(LOWER_LIMIT));
    }
    if (UPPER_LIMIT != null && UPPER_LIMIT != "") {
      sb.append(" and UNIT_PRICE <= :UPPER_LIMIT");
      param.addValue("UPPER_LIMIT", Integer.valueOf(UPPER_LIMIT));
    }

    sb.append(" and DELETE_FLG = '0'");

    return this.namedParameterJdbcTemplate.query(sb.toString(), param, RowMapper());
  }
}
