package eCommerce.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import eCommerce.DTO.CargoDTO;
import eCommerce.Entity.CategoryEntity;
import eCommerce.Entity.ProductEntity;

public interface OrderService {

  List<ProductEntity> searchGoods(Integer PAGE, String CATEGORY_NAME, String PRODUCT_NAME,
      String MAKER, String UPPER_LIMIT, String LOWER_LIMIT) throws SQLException, Exception;

  Integer findMaxNum() throws SQLException, Exception;

  ProductEntity findGoodsByCode(String PRODUCT_CODE) throws SQLException, Exception;

  boolean checkStock(HashMap<String, Integer> mergeMap) throws SQLException, Exception;

  List<CargoDTO> findCargoListByCode(HashMap<String, Integer> mergeMap)
      throws SQLException, Exception;

  List<CategoryEntity> getCategory() throws SQLException, Exception;

  List<ProductEntity> searchPage(Integer pAGE, String cATEGORY_NAME, String pRODUCT_NAME,
      String mAKER, String uPPER_LIMIT, String lOWER_LIMIT) throws SQLException, Exception;

}
