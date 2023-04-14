package eCommerce.Entity;

import java.sql.Date;
import java.sql.Timestamp;

public class OrderEntity {

  private Integer ORDER_NO;

  private Integer MEMBER_NO;

  private Long TOTAL_MONEY;

  private Long TOTAL_TAX;

  private Date ORDER_DATE;

  private String COLLECT_NO;

  private Timestamp LAST_UPD_DATE;

  public Integer getORDER_NO() {
    return ORDER_NO;
  }

  public Integer getMEMBER_NO() {
    return MEMBER_NO;
  }

  public Date getORDER_DATE() {
    return ORDER_DATE;
  }

  public String getCOLLECT_NO() {
    return COLLECT_NO;
  }

  public Timestamp getLAST_UPD_DATE() {
    return LAST_UPD_DATE;
  }

  public void setORDER_NO(Integer oRDER_NO) {
    ORDER_NO = oRDER_NO;
  }

  public void setMEMBER_NO(Integer mEMBER_NO) {
    MEMBER_NO = mEMBER_NO;
  }


  public void setORDER_DATE(Date oRDER_DATE) {
    ORDER_DATE = oRDER_DATE;
  }

  public void setCOLLECT_NO(String cOLLECT_NO) {
    COLLECT_NO = cOLLECT_NO;
  }

  public void setLAST_UPD_DATE(Timestamp lAST_UPD_DATE) {
    LAST_UPD_DATE = lAST_UPD_DATE;
  }

  public Long getTOTAL_MONEY() {
    return TOTAL_MONEY;
  }

  public void setTOTAL_MONEY(Long tOTAL_MONEY) {
    TOTAL_MONEY = tOTAL_MONEY;
  }

  public Long getTOTAL_TAX() {
    return TOTAL_TAX;
  }

  public void setTOTAL_TAX(Long tOTAL_TAX) {
    TOTAL_TAX = tOTAL_TAX;
  }



}
