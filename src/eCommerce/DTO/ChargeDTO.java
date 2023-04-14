package eCommerce.DTO;

public class ChargeDTO {

  private Long TOTAL_SUB;

  private Long TOTAL_TAX;

  private Long TOTAL_MONEY;

  public Long getTOTAL_SUB() {
    return TOTAL_SUB;
  }

  public Long getTOTAL_TAX() {
    return TOTAL_TAX;
  }

  public Long getTOTAL_MONEY() {
    return TOTAL_MONEY;
  }

  public void setTOTAL_SUB(Long tOTAL_SUB) {
    TOTAL_SUB = tOTAL_SUB;
  }

  public void setTOTAL_TAX(Long tOTAL_TAX) {
    TOTAL_TAX = tOTAL_TAX;
  }

  public void setTOTAL_MONEY(Long tOTAL_MONEY) {
    TOTAL_MONEY = tOTAL_MONEY;
  }



}
