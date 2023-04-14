package eCommerce.Controller;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import eCommerce.DTO.CargoDTO;
import eCommerce.DTO.ChargeDTO;
import eCommerce.DiscoutPolicy.DiscountPolicy;
import eCommerce.Entity.MemberEntity;
import eCommerce.Service.CargoService;
import eCommerce.Service.OrderService;

@Controller
public class CargoController {

  private final CargoService cargoService;
  private final OrderService orderService;


  @Autowired
  CargoController(CargoService cargoService, OrderService orderService,
      DiscountPolicy discountPolicy) {
    this.cargoService = cargoService;
    this.orderService = orderService;
  }

  @GetMapping("/showCargo")
  public ModelAndView showCargo(HttpSession session) {

    return new ModelAndView("KGO101");
  }

  @PostMapping("/deleteCargo")
  public ModelAndView deleteCargo(@ModelAttribute(value = "CargoDTO") CargoDTO cargoDTO,
      HttpSession session) {
    List<CargoDTO> sessionCargo = (List<CargoDTO>) session.getAttribute("sessionCargo");
    List<CargoDTO> cargoList = cargoDTO.getCargoList();
    if (cargoList == null) {
      String message = "取り消し対象の商品がございません。";
      return new ModelAndView("KGO101", "error", message);
    }
    cargoList.removeIf(el -> el.getProduct_code() == null);

    String message = deleteValidationCheck(cargoList);
    if (message != null) {
      return new ModelAndView("KGO101", "error", message);
    }

    try {
      List<CargoDTO> deletedCargoList = cargoService.deleteCargo(cargoList, sessionCargo);

      session.setAttribute("sessionCargo", deletedCargoList);
    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }

    return new ModelAndView("KGO101");
  }

  @PostMapping("/deleteAllCargo")
  public ModelAndView deleteAllCargo(HttpSession session) {
    session.removeAttribute("sessionCargo");
    session.removeAttribute("charge");

    return new ModelAndView("MEN101");
  }


  @PostMapping("/confirmOrder")
  public ModelAndView confirmOrder(@Valid @ModelAttribute(value = "CargoDTO") CargoDTO cargoDTO,
      BindingResult errors, HttpSession session) {
    if (errors.hasErrors()) {
      String message = "購入数は1～999の数値で入力してください。";
      return new ModelAndView("KGO101", "error", message);
    }

    ChargeDTO charge = (ChargeDTO) session.getAttribute("charge");
    if (charge != null) {
      return new ModelAndView("KGO102");
    }
    if (cargoDTO.getCargoList() == null) {
      String message = "注文する商品がございません。";
      return new ModelAndView("KGO101", "error", message);
    }
    List<CargoDTO> cargoList = cargoDTO.getCargoList();
    cargoList
        .removeIf(el -> el.getSeletedProduct_code() == null && el.getProduct_quantity() == null);
    String message = insertValidationCheck(cargoList);
    if (message != null) {
      return new ModelAndView("KGO101", "error", message);
    }

    HashMap<String, Integer> checkMap = toHashMap(cargoList);

    try {
      if (!orderService.checkStock(checkMap)) {
        message = "在庫が足りません。購入数を変更してください";
        return new ModelAndView("KGO101", "error", message);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }
    try {
      List<CargoDTO> insertCargoList = orderService.findCargoListByCode(checkMap);
      ChargeDTO chargeDTO = cargoService.calculate(checkMap);
      session.setAttribute("charge", chargeDTO);
      session.setAttribute("sessionCargo", insertCargoList);
    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }

    return new ModelAndView("KGO102");
  }

  @PostMapping("/insertOrder")
  public ModelAndView insertOrder(HttpSession session) {
    MemberEntity member = (MemberEntity) session.getAttribute("member");

    List<CargoDTO> cargoList = (List<CargoDTO>) session.getAttribute("sessionCargo");
    ChargeDTO chargeDTO = (ChargeDTO) session.getAttribute("charge");

    HashMap<String, Integer> checkMap = toHashMap(cargoList);

    try {
      cargoService.insertOrder(cargoList, chargeDTO, member.getMEMBER_NO());
    } catch (FileNotFoundException e) {
      return new ModelAndView("KGO102", "error", e.getMessage());
    }
    catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }
    session.removeAttribute("sessionCargo");
    session.removeAttribute("charge");

    return new ModelAndView("KGO103");
  }

  private String deleteValidationCheck(List<CargoDTO> cargoList) {
    if (cargoList.stream().allMatch(e -> e.getProduct_code().isEmpty())) {
      return "取り消しする商品を選択してください";
    }

    return null;
  }

  private String insertValidationCheck(List<CargoDTO> cargoList) {
    try {

      if (cargoList.stream()
          .anyMatch(e -> e.getProduct_quantity() == null && e.getProduct_quantity() == 0)) {
        return "購入数は1～999の数値で入力してください。";
      }
      if (cargoList.stream()
          .anyMatch(e -> e.getProduct_quantity() < 1 || e.getProduct_quantity() > 999)) {
        return "購入数は1～999の数値で入力してください。";
      }
    } catch (NullPointerException e) {
      return "購入数は1～999の数値で入力してください。";
    } catch (NumberFormatException e) {
      return "購入数は1～999の数値で入力してください。";
    }
    return null;
  }

  private HashMap<String, Integer> toHashMap(List<CargoDTO> cargoList) {
    HashMap<String, Integer> checkMap = (HashMap<String, Integer>) cargoList.stream()
        .collect(Collectors.toMap(CargoDTO::getSeletedProduct_code, CargoDTO::getProduct_quantity));


    return checkMap;
  }
}
