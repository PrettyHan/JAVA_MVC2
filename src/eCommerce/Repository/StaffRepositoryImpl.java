package eCommerce.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import eCommerce.Entity.StaffEntity;


@Repository
public class StaffRepositoryImpl implements StaffRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  StaffRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
      JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
  }

  private RowMapper<StaffEntity> RowMapper() {
    return (RowMapper<StaffEntity>) (rs, rowNum) -> {
      StaffEntity staffEntity = new StaffEntity();
      staffEntity.setSTAFF_NO(Integer.valueOf(rs.getString("STAFF_NO")));
      staffEntity.setPASSWORD(rs.getString("PASSWORD"));
      staffEntity.setNAME(rs.getString("NAME"));
      staffEntity.setAGE(Integer.valueOf(rs.getString("AGE")));
      staffEntity.setSEX(rs.getString("SEX").charAt(0));
      staffEntity.setREGISTER_DATE(rs.getDate("REGISTER_DATE"));
      staffEntity.setLAST_UPD_DATE(rs.getTimestamp("LAST_UPD_DATE"));

      return staffEntity;
    };
  }
}
