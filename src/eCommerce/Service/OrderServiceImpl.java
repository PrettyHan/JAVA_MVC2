package eCommerce.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import eCommerce.DTO.CargoDTO;
import eCommerce.Entity.CategoryEntity;
import eCommerce.Entity.ProductEntity;
import eCommerce.Repository.CategoryRepository;
import eCommerce.Repository.MemberRepository;
import eCommerce.Repository.OrderRepository;
import eCommerce.Repository.ProductRepository;


@Service
public class OrderServiceImpl implements OrderService {


  private final MemberRepository memberRepository;
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  @Autowired
  OrderServiceImpl(MemberRepository memberRepository, OrderRepository orderRepository,
      ProductRepository productRepository, CategoryRepository categoryRepository) {
    this.memberRepository = memberRepository;
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public List<ProductEntity> searchGoods(Integer PAGE, String CATEGORY_NAME, String PRODUCT_NAME,
      String MAKER, String UPPER_LIMIT, String LOWER_LIMIT) throws SQLException, Exception {
    Integer CATEGORY_ID = null;
    if (CATEGORY_NAME != null && !CATEGORY_NAME.isEmpty()) {
      CATEGORY_ID = categoryRepository.findIdByName(CATEGORY_NAME);
    }
    return productRepository.findByInfo(PAGE, CATEGORY_ID, PRODUCT_NAME, MAKER, UPPER_LIMIT,
        LOWER_LIMIT);
  }

  @Override
  @Transactional(readOnly = true)
  public Integer findMaxNum() throws SQLException, Exception {

    return productRepository.findMax();
  }

  @Override
  @Transactional(readOnly = true)
  public ProductEntity findGoodsByCode(String PRODUCT_CODE) throws SQLException, Exception {

    return productRepository.findByCode(PRODUCT_CODE).get(0);
  }

  @Override
  @Transactional(readOnly = true)
  public boolean checkStock(HashMap<String, Integer> mergeMap) throws SQLException, Exception {
    for (String key : mergeMap.keySet()) {
      String order_code = key;
      Integer order_stock = mergeMap.get(key);
      Integer DB_stock = productRepository.findStock(order_code);
      if (order_stock > DB_stock) {
        return false;
      }
    }
    return true;
  }

  @Override
  @Transactional(readOnly = true)
  public List<CargoDTO> findCargoListByCode(HashMap<String, Integer> mergeMap)
      throws SQLException, Exception {

    List<CargoDTO> insertCargoList = new ArrayList<CargoDTO>();

    for (String key : mergeMap.keySet()) {
      CargoDTO cargoListDTO = new CargoDTO();
      ProductEntity productInfo = productRepository.findByCode(key).get(0);
      cargoListDTO.setProduct_code(productInfo.getPRODUCT_CODE());
      cargoListDTO.setSeletedProduct_code(productInfo.getPRODUCT_CODE());
      cargoListDTO.setProduct_name(productInfo.getPRODUCT_NAME());
      cargoListDTO.setMaker(productInfo.getMAKER());
      cargoListDTO.setUnit_price(productInfo.getUNIT_PRICE());
      cargoListDTO.setProduct_quantity(mergeMap.get(key));
      cargoListDTO.setTotal_price(productInfo.getUNIT_PRICE() * mergeMap.get(key));
      insertCargoList.add(cargoListDTO);

    }
    return insertCargoList;
  }

  @Override
  public List<CategoryEntity> getCategory() throws SQLException, Exception {

    return categoryRepository.findAll();
  }

  @Override
  public List<ProductEntity> searchPage(Integer PAGE, String CATEGORY_NAME, String PRODUCT_NAME,
      String MAKER, String UPPER_LIMIT, String LOWER_LIMIT) throws SQLException, Exception {
    Integer CATEGORY_ID = null;
    if (CATEGORY_NAME != null && !CATEGORY_NAME.isEmpty()) {
      CATEGORY_ID = categoryRepository.findIdByName(CATEGORY_NAME);
    }
    return productRepository.findPage(PAGE, CATEGORY_ID, PRODUCT_NAME, MAKER, UPPER_LIMIT,
        LOWER_LIMIT);
  }

}
