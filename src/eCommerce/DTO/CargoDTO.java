package eCommerce.DTO;

import java.util.List;

public class CargoDTO {

  private List<CargoDTO> cargoList;

  private String product_code;

  private String seletedProduct_code;

  private Integer product_quantity;

  private String product_name;

  private String maker;

  private Long unit_price;

  private Long total_price;


  public String getProduct_name() {
    return product_name;
  }

  public String getMaker() {
    return maker;
  }

  public Long getUnit_price() {
    return unit_price;
  }


  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }

  public void setMaker(String maker) {
    this.maker = maker;
  }

  public void setUnit_price(Long unit_price) {
    this.unit_price = unit_price;
  }


  public List<CargoDTO> getCargoList() {
    return cargoList;
  }

  public String getProduct_code() {
    return product_code;
  }


  public void setCargoList(List<CargoDTO> cargoList) {
    this.cargoList = cargoList;
  }

  public void setProduct_code(String product_code) {
    this.product_code = product_code;
  }

  public Integer getProduct_quantity() {
    return product_quantity;
  }

  public void setProduct_quantity(Integer product_quantity) {
    this.product_quantity = product_quantity;
  }

  public Long getTotal_price() {
    return total_price;
  }

  public void setTotal_price(Long total_price) {
    this.total_price = total_price;
  }

  public String getSeletedProduct_code() {
    return seletedProduct_code;
  }

  public void setSeletedProduct_code(String seletedProduct_code) {
    this.seletedProduct_code = seletedProduct_code;
  }

}
