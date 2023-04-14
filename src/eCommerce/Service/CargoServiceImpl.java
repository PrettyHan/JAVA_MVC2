package eCommerce.Service;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import eCommerce.DTO.CargoDTO;
import eCommerce.DTO.ChargeDTO;
import eCommerce.DiscoutPolicy.DiscountPolicy;
import eCommerce.Entity.ProductEntity;
import eCommerce.Repository.CategoryRepository;
import eCommerce.Repository.MemberRepository;
import eCommerce.Repository.OrderListRepository;
import eCommerce.Repository.OrderRepository;
import eCommerce.Repository.ProductRepository;

@Service
public class CargoServiceImpl implements CargoService {

  private final MemberRepository memberRepository;
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final OrderListRepository orderListRepository;
  private final DiscountPolicy discountPolicy;

  @Autowired
  CargoServiceImpl(MemberRepository memberRepository, OrderRepository orderRepository,
      ProductRepository productRepository, CategoryRepository categoryRepository,
      OrderListRepository orderListRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
    this.orderListRepository = orderListRepository;
    this.discountPolicy = discountPolicy;
  }

  @Override
  @Transactional(readOnly = true)
  public List<CargoDTO> deleteCargo(List<CargoDTO> cargoList, List<CargoDTO> sessionCargo) {
    HashMap<String, Integer> cargoMap = (HashMap<String, Integer>) cargoList.stream()
        .collect(Collectors.toMap(CargoDTO::getProduct_code, CargoDTO::getProduct_quantity));
    HashMap<String, Integer> sessionMap = (HashMap<String, Integer>) sessionCargo.stream()
        .collect(Collectors.toMap(CargoDTO::getProduct_code, CargoDTO::getProduct_quantity));

    List<CargoDTO> deletedCargoList = new ArrayList<CargoDTO>();

    for (String key : cargoMap.keySet()) {
      sessionMap.remove(key);
    }
    for (String key : sessionMap.keySet()) {
      CargoDTO cargoListDTO = new CargoDTO();
      ProductEntity productInfo = productRepository.findByCode(key).get(0);
      cargoListDTO.setProduct_code(productInfo.getPRODUCT_CODE());
      cargoListDTO.setProduct_name(productInfo.getPRODUCT_NAME());
      cargoListDTO.setMaker(productInfo.getMAKER());
      cargoListDTO.setUnit_price(productInfo.getUNIT_PRICE());
      cargoListDTO.setProduct_quantity(sessionMap.get(key));
      cargoListDTO.setTotal_price(productInfo.getUNIT_PRICE() * sessionMap.get(key));

      deletedCargoList.add(cargoListDTO);
    }
    return deletedCargoList;
  }

  @Override
  @Transactional(rollbackForClassName = "Exception")
  public void insertOrder(List<CargoDTO> cargoList, ChargeDTO chargeDTO, Integer MEMBER_NO)
      throws SQLException, Exception {
    Integer COLLECT_NO = orderListRepository.createCollectNo();
    Long TOTAL_MONEY = chargeDTO.getTOTAL_SUB();
    Long TOTAL_TAX = chargeDTO.getTOTAL_TAX();
    for (CargoDTO cargo : cargoList) {
      String PRODUCT_CODE = cargo.getSeletedProduct_code();
      Integer ORDER_COUNT = cargo.getProduct_quantity();
      Long ORDER_PRICE = cargo.getUnit_price();
      Integer PRODUCT_STOCK = productRepository.findStock(PRODUCT_CODE);
      Integer UPDATE_STOCK =
          PRODUCT_STOCK - ORDER_COUNT >= 0 ? (PRODUCT_STOCK - ORDER_COUNT) : null;
      if (productRepository.findByCode(PRODUCT_CODE).get(0).getDELETE_FLG() == '1') {
        throw new FileNotFoundException(PRODUCT_CODE + "の取り扱いは終了されたため購入できません。");
      }
      orderListRepository.insert(COLLECT_NO, PRODUCT_CODE, ORDER_COUNT, ORDER_PRICE);
      productRepository.updateStock(PRODUCT_CODE, UPDATE_STOCK);
    }
    orderRepository.insert(MEMBER_NO, TOTAL_MONEY, TOTAL_TAX, COLLECT_NO);

  }

  @Override
  @Transactional(readOnly = true)
  public ChargeDTO calculate(HashMap<String, Integer> cargoList) throws SQLException, Exception {
    Long TOTAL_SUB = 0L;
    Long TOTAL_TAX = 0L;
    Long TOTAL_MONEY = 0L;
    for (String key : cargoList.keySet()) {
      ProductEntity productInfo = productRepository.findByCode(key).get(0);
      TOTAL_SUB += productInfo.getUNIT_PRICE() * cargoList.get(key);
    }

    TOTAL_TAX = discountPolicy.discount(TOTAL_SUB);
    TOTAL_MONEY = TOTAL_SUB + TOTAL_TAX;

    ChargeDTO chargeDTO = new ChargeDTO();
    chargeDTO.setTOTAL_SUB(TOTAL_SUB);
    chargeDTO.setTOTAL_TAX(TOTAL_TAX);
    chargeDTO.setTOTAL_MONEY(TOTAL_MONEY);

    return chargeDTO;
  }

}
