package eCommerce.Repository;

import java.util.List;
import eCommerce.Entity.ProductEntity;

public interface ProductRepository {

  List<ProductEntity> findByInfo(Integer PAGE, Integer CATEGORY_ID, String PRODUCT_NAME,
      String MAKER, String UPPER_LIMIT, String LOWER_LIMIT);

  Integer findMax();

  List<ProductEntity> findByCode(String PRODUCT_CODE);

  Integer findStock(String order_code);

  void updateStock(String PRODUCT_CODE, Integer UPDATE_STOCK);

  List<ProductEntity> findPage(Integer PAGE, Integer CATEGORY_ID, String PRODUCT_NAME, String MAKER,
      String UPPER_LIMIT, String LOWER_LIMIT);

}
