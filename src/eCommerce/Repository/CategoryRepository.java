package eCommerce.Repository;

import java.util.List;
import eCommerce.Entity.CategoryEntity;

public interface CategoryRepository {

  Integer findIdByName(String CATEGORY_NAME);

  List<CategoryEntity> findAll();

}
