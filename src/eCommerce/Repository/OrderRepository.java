package eCommerce.Repository;

public interface OrderRepository {

  void insert(Integer MEMBER_NO, Long TOTAL_MONEY, Long TOTAL_TAX, Integer COLLECT_NO);

}
