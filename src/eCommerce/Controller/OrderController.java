package eCommerce.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import eCommerce.DTO.CargoDTO;
import eCommerce.DTO.Sho101DTO;
import eCommerce.Entity.CategoryEntity;
import eCommerce.Entity.ProductEntity;
import eCommerce.Service.OrderService;

@Controller
public class OrderController {

  private static Integer maxNum;
  private static Integer endPage;
  private static Integer curretPage;

  private final OrderService orderService;

  @Autowired
  OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/searchGoods")
  public ModelAndView showSearchGoods(HttpSession session) {
    List<CategoryEntity> categorys = null;
    try {
      maxNum = orderService.findMaxNum();
      endPage = (maxNum / 10);
      if (endPage == 0) {
        endPage = 1;
      }
      if (endPage % 10 > 0) {
        endPage += 1;
      }
      curretPage = 1;

      categorys = orderService.getCategory();
      session.setAttribute("categorys", categorys);

    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }
    session.setAttribute("endPage", endPage);
    session.removeAttribute("searchQuery");

    ModelAndView amv = new ModelAndView("SHO101");

    amv.addObject("nextPage", 10);
    amv.addObject("returnPage", 0);
    amv.addObject("currentPage", curretPage);

    return amv;
  }

  @GetMapping("/resultGoods")
  public ModelAndView showResultGoods(@Valid Sho101DTO sho101DTO, BindingResult errors,
      HttpSession session) {

    if (errors.hasErrors()) {
      return new ModelAndView("SHO101");
    }

    try {

      Integer PAGE = 0;
      String CATEGORY_NAME = sho101DTO.getCategory_name();
      String PRODUCT_NAME = sho101DTO.getGoods_name();
      String MAKER = sho101DTO.getGoods_maker();
      String UPPER_LIMIT = sho101DTO.getUpper_limit();
      String LOWER_LIMIT = sho101DTO.getLower_limit();


      List<ProductEntity> productList = orderService.searchGoods(PAGE, CATEGORY_NAME, PRODUCT_NAME,
          MAKER, UPPER_LIMIT, LOWER_LIMIT);

      List<ProductEntity> productPage = orderService.searchPage(PAGE, CATEGORY_NAME, PRODUCT_NAME,
          MAKER, UPPER_LIMIT, LOWER_LIMIT);

      curretPage = 1;
      endPage = (productPage.size() / 10);
      if (endPage == 0) {
        endPage = 1;
      }
      if (endPage % 10 > 0 && productPage.size() > 10) {
        endPage += 1;
      }


      session.setAttribute("searchQuery", sho101DTO);
      session.setAttribute("endPage", endPage);

      ModelAndView amv = new ModelAndView("SHO101", "productList", productList);
      amv.addObject("nextPage", 10);
      amv.addObject("returnPage", 0);
      amv.addObject("currentPage", curretPage);

      return amv;

    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");

    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }
  }

  @GetMapping("/next")
  public ModelAndView showNextPage(@Valid Sho101DTO sho101DTO, BindingResult errors,
      @RequestParam(name = "nextPage") int PAGE, HttpSession session) {
    if (errors.hasErrors()) {
      return new ModelAndView("SHO101");
    }

    try {
      if ((@Valid Sho101DTO) session.getAttribute("searchQuery") != null) {
        sho101DTO = (@Valid Sho101DTO) session.getAttribute("searchQuery");
      }

      String CATEGORY_NAME = sho101DTO.getCategory_name();
      String PRODUCT_NAME = sho101DTO.getGoods_name();
      String MAKER = sho101DTO.getGoods_maker();
      String UPPER_LIMIT = sho101DTO.getUpper_limit();
      String LOWER_LIMIT = sho101DTO.getLower_limit();

      List<ProductEntity> productList = orderService.searchGoods(PAGE, CATEGORY_NAME, PRODUCT_NAME,
          MAKER, UPPER_LIMIT, LOWER_LIMIT);

      ModelAndView amv = new ModelAndView("SHO101", "productList", productList);

      curretPage += 1;
      amv.addObject("sho101DTO", sho101DTO);
      amv.addObject("nextPage", PAGE + 10);
      amv.addObject("returnPage", PAGE - 10);
      amv.addObject("currentPage", curretPage);
      return amv;
    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }
  }

  @GetMapping("/return")
  public ModelAndView showReturnPage(@Valid Sho101DTO sho101DTO, BindingResult errors,
      @RequestParam(name = "returnPage") int PAGE, HttpSession session) {
    if (errors.hasErrors()) {
      return new ModelAndView("SHO101");
    }

    try {
      if ((@Valid Sho101DTO) session.getAttribute("searchQuery") != null) {
        sho101DTO = (@Valid Sho101DTO) session.getAttribute("searchQuery");
      }
      String CATEGORY_NAME = sho101DTO.getCategory_name();
      String PRODUCT_NAME = sho101DTO.getGoods_name();
      String MAKER = sho101DTO.getGoods_maker();
      String UPPER_LIMIT = sho101DTO.getUpper_limit();
      String LOWER_LIMIT = sho101DTO.getLower_limit();

      List<ProductEntity> productList = orderService.searchGoods(PAGE, CATEGORY_NAME, PRODUCT_NAME,
          MAKER, UPPER_LIMIT, LOWER_LIMIT);

      ModelAndView amv = new ModelAndView("SHO101", "productList", productList);

      curretPage -= 1;
      if (PAGE != 0) {
        amv.addObject("nextPage", PAGE + 10);
        amv.addObject("returnPage", PAGE - 10);
        amv.addObject("currentPage", curretPage);
      } else {
        amv.addObject("nextPage", 10);
        amv.addObject("returnPage", 0);
        amv.addObject("currentPage", 1);
      }
      amv.addObject("sho101DTO", sho101DTO);
      return amv;
    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }
  }

  @GetMapping("/frontPage")
  public ModelAndView showFirstPage(@Valid Sho101DTO sho101DTO, BindingResult errors,
      @RequestParam(name = "returnPage") int PAGE, HttpSession session) {
    curretPage = 1;
    if (errors.hasErrors()) {
      return new ModelAndView("SHO101");
    }

    try {
      if ((@Valid Sho101DTO) session.getAttribute("searchQuery") != null) {
        sho101DTO = (@Valid Sho101DTO) session.getAttribute("searchQuery");
      }
      String CATEGORY_NAME = sho101DTO.getCategory_name();
      String PRODUCT_NAME = sho101DTO.getGoods_name();
      String MAKER = sho101DTO.getGoods_maker();
      String UPPER_LIMIT = sho101DTO.getUpper_limit();
      String LOWER_LIMIT = sho101DTO.getLower_limit();

      PAGE = 0;

      List<ProductEntity> productList = orderService.searchGoods(PAGE, CATEGORY_NAME, PRODUCT_NAME,
          MAKER, UPPER_LIMIT, LOWER_LIMIT);

      ModelAndView amv = new ModelAndView("SHO101", "productList", productList);

      amv.addObject("nextPage", 10);
      amv.addObject("returnPage", 0);
      amv.addObject("currentPage", curretPage);
      amv.addObject("sho101DTO", sho101DTO);
      return amv;
    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }
  }

  @GetMapping("/backPage")
  public ModelAndView showBackPage(@Valid Sho101DTO sho101DTO, BindingResult errors,
      @RequestParam(name = "returnPage") int PAGE, HttpSession session) {
    curretPage = endPage;
    if (errors.hasErrors()) {
      return new ModelAndView("SHO101");
    }

    try {
      if ((@Valid Sho101DTO) session.getAttribute("searchQuery") != null) {
        sho101DTO = (@Valid Sho101DTO) session.getAttribute("searchQuery");
      }
      String CATEGORY_NAME = sho101DTO.getCategory_name();
      String PRODUCT_NAME = sho101DTO.getGoods_name();
      String MAKER = sho101DTO.getGoods_maker();
      String UPPER_LIMIT = sho101DTO.getUpper_limit();
      String LOWER_LIMIT = sho101DTO.getLower_limit();

      List<ProductEntity> productPage = orderService.searchPage(PAGE, CATEGORY_NAME, PRODUCT_NAME,
          MAKER, UPPER_LIMIT, LOWER_LIMIT);

      PAGE = productPage.size() - (productPage.size() % 10);

      List<ProductEntity> productList = orderService.searchGoods(PAGE, CATEGORY_NAME, PRODUCT_NAME,
          MAKER, UPPER_LIMIT, LOWER_LIMIT);

      ModelAndView amv = new ModelAndView("SHO101", "productList", productList);

      if (PAGE != 0) {
        amv.addObject("nextPage", PAGE + 10);
        amv.addObject("returnPage", PAGE - 10);
        amv.addObject("currentPage", curretPage);
      } else {
        amv.addObject("nextPage", 10);
        amv.addObject("returnPage", 0);
        amv.addObject("currentPage", 1);
      }
      amv.addObject("sho101DTO", sho101DTO);
      return amv;
    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }

  }

  @GetMapping("/retrieveGoods")
  public ModelAndView showGoodsPage(@Valid Sho101DTO sho101DTO, BindingResult errors,
      @RequestParam(name = "product_code") String PRODUCT_CODE, HttpSession session) {
    if (errors.hasErrors()) {
      return new ModelAndView("SHO101");
    }
    try {
      ProductEntity product = orderService.findGoodsByCode(PRODUCT_CODE);
      session.setAttribute("product", product);
      return new ModelAndView("SHO102", "product", product);

    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }
  }

  @GetMapping("/insertCargoByRetrieve")
  public ModelAndView insertCargoByRetrieve(
      @ModelAttribute(value = "product_quantity") String product_quantity,
      @ModelAttribute(value = "product_code") String product_code, HttpSession session) {
    HashMap<String, Integer> mergeMap;
    session.getAttribute("product");

    try {
      List<CargoDTO> sessionCargo = (List<CargoDTO>) session.getAttribute("sessionCargo");
      List<CargoDTO> cargoList = new ArrayList<CargoDTO>();
      CargoDTO cargoDTO = new CargoDTO();
      cargoDTO.setProduct_code(product_code);
      cargoDTO.setProduct_quantity(Integer.valueOf(product_quantity));
      cargoList.add(cargoDTO);

      cargoList.removeIf(el -> el.getProduct_code() == null);
      String message = cargoValidationCheck(cargoList);
      if (message != null) {
        ModelAndView amv = new ModelAndView("SHO102", "error", message);
        amv.addObject("product_quantity", product_quantity);
        return amv;
      }

      mergeMap = mergeStock(cargoList, sessionCargo);
      if (!orderService.checkStock(mergeMap)) {
        message = "在庫が足りません。購入数を変更してください";
        ModelAndView amv = new ModelAndView("SHO102", "error", message);
        amv.addObject("product_quantity", product_quantity);
        return amv;
      }
    } catch (NumberFormatException e) {
      String message = "購入数は1～999の数値で入力してください";
      return new ModelAndView("SHO102", "error", message);
    }

    catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }

    try {
      List<CargoDTO> insertCargoList = orderService.findCargoListByCode(mergeMap);

      session.setAttribute("sessionCargo", insertCargoList);
    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }

    return new ModelAndView("KGO101");
  }

  @GetMapping("/insertCargo")
  public ModelAndView insertCargo(@ModelAttribute(value = "CargoDTO") CargoDTO cargoDTO,
      BindingResult errors, HttpSession session, HttpServletRequest request) {
    List<CargoDTO> sessionCargo = (List<CargoDTO>) session.getAttribute("sessionCargo");
    List<CargoDTO> cargoList = cargoDTO.getCargoList();
    cargoList.removeIf(el -> el.getProduct_code() == null);
    String message = cargoValidationCheck(cargoList);
    if (message != null) {
      return new ModelAndView("SHO101", "error", message);
    }

    HashMap<String, Integer> mergeMap = mergeStock(cargoList, sessionCargo);

    try {
      if (!orderService.checkStock(mergeMap)) {
        message = "在庫が足りません。購入数を変更してください";
        return new ModelAndView("SHO101", "error", message);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }

    try {
      List<CargoDTO> insertCargoList = orderService.findCargoListByCode(mergeMap);

      session.setAttribute("sessionCargo", insertCargoList);
    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }

    return new ModelAndView("SHO103");
  }

  private String cargoValidationCheck(List<CargoDTO> cargoList) {
    if (cargoList.stream().allMatch(e -> e.getProduct_code().isEmpty())) {
      return "商品を選択してください";
    }
    if (cargoList.stream()
        .anyMatch(e -> e.getProduct_quantity() == null && e.getProduct_code() != null)) {
      return "購入数は1～999の数値で入力してください。";
    }
    if (cargoList.stream()
        .anyMatch(e -> e.getProduct_quantity() < 1 || e.getProduct_quantity() > 999)) {
      return "購入数は1～999の数値で入力してください。";
    }
    if (cargoList.stream().anyMatch(e -> e.getProduct_quantity().getClass() != Integer.class)) {
      return "購入数は1～999の数値で入力してください。";
    }

    return null;

  }

  private HashMap<String, Integer> mergeStock(List<CargoDTO> cargoList,
      List<CargoDTO> sessionCargo) {
    HashMap<String, Integer> checkMap = null;
    if (sessionCargo != null) {
      HashMap<String, Integer> cargoMap = (HashMap<String, Integer>) cargoList.stream()
          .collect(Collectors.toMap(CargoDTO::getProduct_code, CargoDTO::getProduct_quantity));
      HashMap<String, Integer> sessionMap = (HashMap<String, Integer>) sessionCargo.stream()
          .collect(Collectors.toMap(CargoDTO::getProduct_code, CargoDTO::getProduct_quantity));

      sessionMap.forEach((k, v) -> cargoMap.merge(k, v, (v1, v2) -> v1 + v2));
      checkMap = cargoMap;

    } else {
      checkMap = (HashMap<String, Integer>) cargoList.stream()
          .collect(Collectors.toMap(CargoDTO::getProduct_code, CargoDTO::getProduct_quantity));
    }

    return checkMap;
  }

}
