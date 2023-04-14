package eCommerce.Entity;

import java.math.BigInteger;

public class OrderListEntity {

  private Integer LIST_NO;

  private String COLLECT_NO;

  private String PRODUCT_CODE;

  private Integer ORDER_COUNT;

  private BigInteger ORDER_PRICE;

  public Integer getLIST_NO() {
    return LIST_NO;
  }

  public String getCOLLECT_NO() {
    return COLLECT_NO;
  }

  public String getPRODUCT_CODE() {
    return PRODUCT_CODE;
  }

  public Integer getORDER_COUNT() {
    return ORDER_COUNT;
  }

  public BigInteger getORDER_PRICE() {
    return ORDER_PRICE;
  }

  public void setLIST_NO(Integer lIST_NO) {
    LIST_NO = lIST_NO;
  }

  public void setCOLLECT_NO(String cOLLECT_NO) {
    COLLECT_NO = cOLLECT_NO;
  }

  public void setPRODUCT_CODE(String pRODUCT_CODE) {
    PRODUCT_CODE = pRODUCT_CODE;
  }

  public void setORDER_COUNT(Integer oRDER_COUNT) {
    ORDER_COUNT = oRDER_COUNT;
  }

  public void setORDER_PRICE(BigInteger oRDER_PRICE) {
    ORDER_PRICE = oRDER_PRICE;
  }



}
