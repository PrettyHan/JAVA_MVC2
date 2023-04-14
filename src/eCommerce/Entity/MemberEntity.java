package eCommerce.Entity;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberEntity {

  private Integer MEMBER_NO;

  private String PASSWORD;

  private String NAME;

  private Integer AGE;

  private char SEX;

  private String ZIP;

  private String ADDRESS;

  private String TEL;

  private Date REGISTER_DATE;

  private char DELETE_FLG;

  private Timestamp LAST_UPD_DATE;

  public Integer getMEMBER_NO() {
    return MEMBER_NO;
  }

  public String getPASSWORD() {
    return PASSWORD;
  }

  public String getNAME() {
    return NAME;
  }

  public Integer getAGE() {
    return AGE;
  }

  public char getSEX() {
    return SEX;
  }

  public String getZIP() {
    return ZIP;
  }

  public String getADDRESS() {
    return ADDRESS;
  }

  public String getTEL() {
    return TEL;
  }

  public Date getREGISTER_DATE() {
    return REGISTER_DATE;
  }

  public char getDELETE_FLG() {
    return DELETE_FLG;
  }

  public Timestamp getLAST_UPD_DATE() {
    return LAST_UPD_DATE;
  }

  public void setMEMBER_NO(Integer mEMBER_NO) {
    MEMBER_NO = mEMBER_NO;
  }

  public void setPASSWORD(String pASSWORD) {
    PASSWORD = pASSWORD;
  }

  public void setNAME(String nAME) {
    NAME = nAME;
  }

  public void setAGE(Integer aGE) {
    AGE = aGE;
  }

  public void setSEX(char sEX) {
    SEX = sEX;
  }

  public void setZIP(String zIP) {
    ZIP = zIP;
  }

  public void setADDRESS(String aDDRESS) {
    ADDRESS = aDDRESS;
  }

  public void setTEL(String tEL) {
    TEL = tEL;
  }

  public void setREGISTER_DATE(Date rEGISTER_DATE) {
    REGISTER_DATE = rEGISTER_DATE;
  }

  public void setDELETE_FLG(char dELETE_FLG) {
    DELETE_FLG = dELETE_FLG;
  }

  public void setLAST_UPD_DATE(Timestamp lAST_UPD_DATE) {
    LAST_UPD_DATE = lAST_UPD_DATE;
  }



}
