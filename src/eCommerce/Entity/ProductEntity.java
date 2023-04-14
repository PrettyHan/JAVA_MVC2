package eCommerce.Entity;

import java.sql.Date;
import java.sql.Timestamp;

public class ProductEntity {

  private String PRODUCT_CODE;

  private Integer CATEGORY_ID;

  private String PRODUCT_NAME;

  private String MAKER;

  private Integer STOCK_COUNT;

  private Date REGISTER_DATE;

  private Long UNIT_PRICE;

  private String PICTURE_NAME;

  private String MEMO;

  private char DELETE_FLG;

  private Timestamp LAST_UPD_DATE;

  public String getPRODUCT_CODE() {
    return PRODUCT_CODE;
  }

  public Integer getCATEGORY_ID() {
    return CATEGORY_ID;
  }

  public String getPRODUCT_NAME() {
    return PRODUCT_NAME;
  }

  public String getMAKER() {
    return MAKER;
  }

  public Integer getSTOCK_COUNT() {
    return STOCK_COUNT;
  }

  public Date getREGISTER_DATE() {
    return REGISTER_DATE;
  }

  public String getPICTURE_NAME() {
    return PICTURE_NAME;
  }

  public String getMEMO() {
    return MEMO;
  }

  public char getDELETE_FLG() {
    return DELETE_FLG;
  }

  public Timestamp getLAST_UPD_DATE() {
    return LAST_UPD_DATE;
  }

  public void setPRODUCT_CODE(String pRODUCT_CODE) {
    PRODUCT_CODE = pRODUCT_CODE;
  }

  public void setCATEGORY_ID(Integer cATEGORY_ID) {
    CATEGORY_ID = cATEGORY_ID;
  }

  public void setPRODUCT_NAME(String pRODUCT_NAME) {
    PRODUCT_NAME = pRODUCT_NAME;
  }

  public void setMAKER(String mAKER) {
    MAKER = mAKER;
  }

  public void setSTOCK_COUNT(Integer sTOCK_COUNT) {
    STOCK_COUNT = sTOCK_COUNT;
  }

  public void setREGISTER_DATE(Date rEGISTER_DATE) {
    REGISTER_DATE = rEGISTER_DATE;
  }


  public void setPICTURE_NAME(String pICTURE_NAME) {
    PICTURE_NAME = pICTURE_NAME;
  }

  public void setMEMO(String mEMO) {
    MEMO = mEMO;
  }

  public void setDELETE_FLG(char dELETE_FLG) {
    DELETE_FLG = dELETE_FLG;
  }

  public void setLAST_UPD_DATE(Timestamp lAST_UPD_DATE) {
    LAST_UPD_DATE = lAST_UPD_DATE;
  }

  public Long getUNIT_PRICE() {
    return UNIT_PRICE;
  }

  public void setUNIT_PRICE(Long uNIT_PRICE) {
    UNIT_PRICE = uNIT_PRICE;
  }


}
