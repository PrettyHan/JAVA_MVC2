package eCommerce.Intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import eCommerce.Entity.MemberEntity;

@Component
public class LoginIntercepter implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    MemberEntity member = (MemberEntity) request.getSession().getAttribute("member");
    if (member != null) {
      return true;
    }

    else {
      String destUri = request.getRequestURI();
      String destQuery = request.getQueryString();
      String backPage = (destQuery == null) ? destUri : destUri + "?" + destQuery;
      request.getSession().setAttribute("backPage", backPage);

      response.sendRedirect("./login.html");
      return false;
    }
  }
}
