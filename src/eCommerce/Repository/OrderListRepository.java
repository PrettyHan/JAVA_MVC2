package eCommerce.Repository;

public interface OrderListRepository {

  Integer createCollectNo();

  void insert(Integer cOLLECT_NO, String pRODUCT_CODE, Integer oRDER_COUNT, Long oRDER_PRICE);

}
