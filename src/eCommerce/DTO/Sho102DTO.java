package eCommerce.DTO;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

public class Sho102DTO {

  private String category_name;

  private String goods_name;

  private String goods_maker;

  @Pattern(regexp = "[\\d]*", message = "商品価格（下限）は半角数字で入力してください")
  private String lower_limit;

  @Pattern(regexp = "[\\d]*", message = "商品価格（上限）は半角数字で入力してください")
  private String upper_limit;

  /** 商品価格の上限下限の整合性チェック */
  @AssertTrue(message = "商品価格（下限）は商品価格（上限）よりも小さい数値を入力してください")
  public boolean isGoodsPriceScope() {

    if (lower_limit == null || lower_limit.isEmpty()) {
      return true;
    }
    if (upper_limit == null || upper_limit.isEmpty()) {
      return true;
    }

    try {
      int startPrice = Integer.parseInt(lower_limit);
      int endPrice = Integer.parseInt(upper_limit);

      if (endPrice < startPrice) {
        return false;
      }

      return true;

    } catch (NumberFormatException e) {
      return true;
    }

  }

  public String getCategory_name() {
    return category_name;
  }

  public String getGoods_name() {
    return goods_name;
  }



  public String getLower_limit() {
    return lower_limit;
  }

  public String getUpper_limit() {
    return upper_limit;
  }

  public void setCategory_name(String category_name) {
    this.category_name = category_name;
  }

  public void setGoods_name(String goods_name) {
    this.goods_name = goods_name;
  }



  public void setLower_limit(String lower_limit) {
    this.lower_limit = lower_limit;
  }

  public void setUpper_limit(String upper_limit) {
    this.upper_limit = upper_limit;
  }

  public String getGoods_maker() {
    return goods_maker;
  }

  public void setGoods_maker(String goods_maker) {
    this.goods_maker = goods_maker;
  }



}
