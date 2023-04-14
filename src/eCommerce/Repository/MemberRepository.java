package eCommerce.Repository;

import java.util.List;
import eCommerce.Entity.MemberEntity;

public interface MemberRepository {

  List<MemberEntity> login(Integer MEMBER_NO, String PASSWORD);

  int insert(Integer MEMBER_NO, String PASSWORD, String NAME, Integer AGE, char SEX, String ZIP,
      String ADDRESS, String TEL);

  Integer createNumber();

  void updatePassword(Integer MEMBER_NO, String PASSWORD);

  void updateMember(Integer MEMBER_NO, String NAME, Integer AGE, char SEX, String ZIP,
      String ADDRESS, String TEL);

  void deleteMember(Integer MEMBER_NO);

}
