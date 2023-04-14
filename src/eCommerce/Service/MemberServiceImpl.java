package eCommerce.Service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import eCommerce.Entity.MemberEntity;
import eCommerce.Repository.MemberRepository;


@Service
public class MemberServiceImpl implements MemberService {


  private final MemberRepository memberRepository;

  @Autowired
  MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public List<MemberEntity> loginMember(Integer MEMBER_NO, String PASSWORD)
      throws SQLException, Exception {
    return memberRepository.login(MEMBER_NO, PASSWORD);
  }

  @Override
  @Transactional(rollbackForClassName = "Exception")
  public Integer insertMember(String PASSWORD, String NAME, Integer AGE, char SEX, String ZIP,
      String ADDRESS, String TEL) throws SQLException, Exception {

    Integer MEMBER_NO = memberRepository.createNumber();

    memberRepository.insert(MEMBER_NO, PASSWORD, NAME, AGE, SEX, ZIP, ADDRESS, TEL);


    return MEMBER_NO;
  }

  @Override
  @Transactional(rollbackForClassName = "Exception")
  public void changePassword(Integer MEMBER_NO, String PASSWORD) throws SQLException, Exception {
    memberRepository.updatePassword(MEMBER_NO, PASSWORD);

  }

  @Override
  @Transactional(rollbackForClassName = "Exception")
  public void changeMember(Integer MEMBER_NO, String NAME, Integer AGE, char SEX, String ZIP,
      String ADDRESS, String TEL) throws SQLException, Exception {
    memberRepository.updateMember(MEMBER_NO, NAME, AGE, SEX, ZIP, ADDRESS, TEL);

  }

  @Override
  @Transactional(rollbackForClassName = "Exception")
  public void deleteMember(Integer MEMBER_NO) throws SQLException, Exception {

    memberRepository.deleteMember(MEMBER_NO);
  }


}
