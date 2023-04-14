package eCommerce.Repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import eCommerce.Entity.MemberEntity;


@Repository
public class MemberRepositoryImpl implements MemberRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  MemberRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
      JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
  }

  private RowMapper<MemberEntity> RowMapper() {
    return (RowMapper<MemberEntity>) (rs, rowNum) -> {
      MemberEntity memberEntity = new MemberEntity();
      memberEntity.setMEMBER_NO(Integer.valueOf(rs.getString("MEMBER_NO")));
      memberEntity.setPASSWORD(rs.getString("PASSWORD"));
      memberEntity.setNAME(rs.getString("NAME"));
      memberEntity.setAGE(Integer.valueOf(rs.getString("AGE")));
      memberEntity.setSEX(rs.getString("SEX").charAt(0));
      memberEntity.setZIP(rs.getString("ZIP"));
      memberEntity.setADDRESS(rs.getString("ADDRESS"));
      memberEntity.setTEL(rs.getString("TEL"));
      memberEntity.setREGISTER_DATE(rs.getDate("REGISTER_DATE"));
      memberEntity.setDELETE_FLG(rs.getString("DELETE_FLG").charAt(0));
      memberEntity.setLAST_UPD_DATE(rs.getTimestamp("LAST_UPD_DATE"));

      return memberEntity;
    };
  }

  @Override
  public List<MemberEntity> login(Integer MEMBER_NO, String PASSWORD) {
    String sql = "select * from ONLINE_MEMBER where MEMBER_NO = :MEMBER_NO";
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("MEMBER_NO", MEMBER_NO);

    return namedParameterJdbcTemplate.query(sql, param, RowMapper());
  }

  @Override
  public int insert(Integer MEMBER_NO, String PASSWORD, String NAME, Integer AGE, char SEX,
      String ZIP, String ADDRESS, String TEL) {
    String sql = "insert into ONLINE_MEMBER values "
        + "(:MEMBER_NO, :PASSWORD, :NAME, :AGE, :SEX, :ZIP, :ADDRESS, :TEL, NOW(), '0', NOW())";
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("MEMBER_NO", MEMBER_NO);
    param.addValue("PASSWORD", PASSWORD);
    param.addValue("NAME", NAME);
    param.addValue("AGE", AGE);
    if (SEX == 'm') {
      param.addValue("SEX", "m");
    }
    if (SEX == 'f') {
      param.addValue("SEX", "f");
    }
    param.addValue("ZIP", ZIP);
    param.addValue("ADDRESS", ADDRESS);
    param.addValue("TEL", TEL);

    return namedParameterJdbcTemplate.update(sql, param);
  }

  @Override
  public Integer createNumber() {
    String sql = "select max(MEMBER_NO) from ONLINE_MEMBER";


    Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
    if (result == null) {
      return 1;
    }

    return (result + 1);
  }

  @Override
  public void updatePassword(Integer MEMBER_NO, String PASSWORD) {
    String sql =
        "update ONLINE_MEMBER set PASSWORD = :PASSWORD, LAST_UPD_DATE = NOW() where MEMBER_NO = :MEMBER_NO";
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("MEMBER_NO", MEMBER_NO);
    param.addValue("PASSWORD", PASSWORD);

    namedParameterJdbcTemplate.update(sql, param);

  }

  @Override
  public void updateMember(Integer MEMBER_NO, String NAME, Integer AGE, char SEX, String ZIP,
      String ADDRESS, String TEL) {
    String sql =
        "update ONLINE_MEMBER set NAME = :NAME, AGE = :AGE, SEX = :SEX, ZIP = :ZIP, ADDRESS = :ADDRESS, TEL = :TEL, LAST_UPD_DATE = NOW() where MEMBER_NO = :MEMBER_NO";
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("MEMBER_NO", MEMBER_NO);
    param.addValue("NAME", NAME);
    param.addValue("AGE", AGE);
    if (SEX == 'm') {
      param.addValue("SEX", "m");
    }
    if (SEX == 'f') {
      param.addValue("SEX", "f");
    }
    param.addValue("ZIP", ZIP);
    param.addValue("ADDRESS", ADDRESS);
    param.addValue("TEL", TEL);

    namedParameterJdbcTemplate.update(sql, param);

  }

  @Override
  public void deleteMember(Integer MEMBER_NO) {
    String sql =
        "update ONLINE_MEMBER set DELETE_FLG = 1, LAST_UPD_DATE = NOW() where MEMBER_NO = :MEMBER_NO";
    MapSqlParameterSource param = new MapSqlParameterSource();
    param.addValue("MEMBER_NO", MEMBER_NO);

    namedParameterJdbcTemplate.update(sql, param);

  }
}
