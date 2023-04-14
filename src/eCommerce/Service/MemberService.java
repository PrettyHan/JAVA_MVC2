package eCommerce.Service;

import java.sql.SQLException;
import java.util.List;
import eCommerce.Entity.MemberEntity;

public interface MemberService {

  List<MemberEntity> loginMember(Integer MEMBER_NO, String PASSWORD) throws SQLException, Exception;

  Integer insertMember(String PASSWORD, String NAME, Integer AGE, char SEX, String ZIP,
      String ADDRESS, String TEL) throws SQLException, Exception;

  void changePassword(Integer MEMBER_NO, String pASSWORD) throws SQLException, Exception;

  void changeMember(Integer MEMBER_NO, String nAME, Integer aGE, char sEX, String zIP,
      String aDDRESS, String tEL) throws SQLException, Exception;

  void deleteMember(Integer MEMBER_NO) throws SQLException, Exception;
}
