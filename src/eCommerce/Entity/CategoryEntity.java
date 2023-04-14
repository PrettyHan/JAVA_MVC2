package eCommerce.Entity;

import java.sql.Timestamp;

public class CategoryEntity {
  private Integer CTGR_ID;

  private String NAME;

  private Timestamp LAST_UPD_DATE;

  public Integer getCTGR_ID() {
    return CTGR_ID;
  }

  public String getNAME() {
    return NAME;
  }

  public Timestamp getLAST_UPD_DATE() {
    return LAST_UPD_DATE;
  }

  public void setCTGR_ID(Integer cTGR_ID) {
    CTGR_ID = cTGR_ID;
  }

  public void setNAME(String nAME) {
    NAME = nAME;
  }

  public void setLAST_UPD_DATE(Timestamp lAST_UPD_DATE) {
    LAST_UPD_DATE = lAST_UPD_DATE;
  }


}
