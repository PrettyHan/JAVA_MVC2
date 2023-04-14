package eCommerce.DTO;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Mem101DTO {

  @NotEmpty(message = "氏名は必須入力です。")
  @Size(max = 20, message = "氏名は20桁以下で入力してください。")
  private String name;

  @NotEmpty(message = "パスワードは必須入力です。")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "パスワードは半角英数字で入力してください。")
  @Size(max = 8, message = "パスワードは8桁以下で入力してください。")
  private String password;

  @NotEmpty(message = "確認用パスワードは必須入力です。")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "確認用パスワードは半角英数字で入力してください。")
  @Size(max = 8, message = "確認用パスワードは8桁以下で入力してください。")
  private String confirmPassword;

  @NotEmpty(message = "年齢は必須入力です。")
  @Pattern(regexp = "^[0-9]*$", message = "年齢は半角数字で入力してください。")
  private String age;

  @NotNull(message = "性別は必須項目です。")
  private char sex;

  @NotEmpty(message = "郵便番号は必須入力です。")
  @Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号はXXX-XXXXの形式で入力してください")
  private String zip;

  @NotEmpty(message = "住所は必須入力です。")
  @Size(max = 50, message = "住所は50桁以下で入力してください。")
  private String address;

  @NotEmpty(message = "電話番号は必須入力です。")
  @Pattern(regexp = "^[0-9-]*$", message = "電話番号は半角数字とハイフンで入力してください。")
  @Size(max = 20, message = "電話番号は20文字以内に入力してください。")
  private String tel;

  @AssertTrue(message = "パスワードと確認用パスワードが一致していません")
  public boolean isPasswordValidationCheck() {
    return this.password.equals(confirmPassword);
  }

  @AssertTrue(message = "性別は必須項目です。")
  public boolean isSexValidationCheck() {

    if (this.sex != 'm' && this.sex != 'f') {
      return false;
    }

    return true;
  }

  @AssertTrue(message = "年齢は正の数で入力してください。")
  public boolean isAgeValidationCheck() {
    try {
      if (this.age != null && !this.age.isEmpty()) {
        int age = Integer.parseInt(this.age);
        if (age <= 0) {
          return false;
        }
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public String getAge() {
    return age;
  }

  public char getSex() {
    return sex;
  }

  public String getZip() {
    return zip;
  }

  public String getAddress() {
    return address;
  }

  public String getTel() {
    return tel;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public void setSex(char sex) {
    this.sex = sex;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }



}
