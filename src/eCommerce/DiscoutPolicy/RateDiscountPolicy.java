package eCommerce.DiscoutPolicy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RateDiscountPolicy implements DiscountPolicy {

  private int discountPercent = 10;

  @Override
  public Long discount(Long price) {
    return price * discountPercent / 100;
  }
}
