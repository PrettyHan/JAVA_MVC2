package eCommerce.Controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import eCommerce.DTO.Log101DTO;
import eCommerce.DTO.Mem101DTO;
import eCommerce.DTO.Mem202DTO;
import eCommerce.DTO.Mem301DTO;
import eCommerce.Entity.MemberEntity;
import eCommerce.Service.MemberService;

@Controller
public class MemberController {
  private final MemberService memberService;

  @Autowired
  MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/menu")
  public ModelAndView showMenu(HttpSession session) {

    return new ModelAndView("MEN101");
  }

  @GetMapping("/login")
  public ModelAndView loginForm(HttpSession session) {
    return new ModelAndView("LOG101");
  }

  @PostMapping("/login")
  public ModelAndView loginMember(@Valid Log101DTO log101DTO, BindingResult error,
      HttpSession session, HttpServletRequest request) {
    String backPage = (String) session.getAttribute("backPage");
    if (error.hasErrors()) {
      return new ModelAndView("LOG101");
    }

    try {
      Integer MEMBER_NO = Integer.valueOf(log101DTO.getMember_no());
      String PASSWORD = log101DTO.getPassword();

      MemberEntity member = memberService.loginMember(MEMBER_NO, PASSWORD).get(0);

      if (member == null || member.getDELETE_FLG() == '1') {
        String message = "ログインできませんでした。";
        return new ModelAndView("LOG101", "error", message);
      }

      if (MEMBER_NO != member.getMEMBER_NO() || !PASSWORD.equals(member.getPASSWORD())) {
        String message = "ログインできませんでした。";

        return new ModelAndView("LOG101", "error", message);
      }

      session.setAttribute("member", member);

      if (backPage != null && backPage.contains("updateMember")) {
        session.removeAttribute("backPage");
        return new ModelAndView("redirect:/updateMember.html");
      }
      if (backPage != null && backPage.contains("insertOrder")) {
        session.removeAttribute("backPage");
        return new ModelAndView("forward:/confirmOrder.html");
      }


    } catch (IndexOutOfBoundsException e) {
      String message = "入力した会員NOがございません。";
      return new ModelAndView("LOG101", "error", message);
    } catch (SQLException e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("ERR101");
    }

    return new ModelAndView("redirect:/menu.html");
  }

  @GetMapping("/logout")
  public ModelAndView logout(HttpSession session) {
    session.invalidate();
    return new ModelAndView("redirect:/login.html");
  }

  @GetMapping("/join")
  public ModelAndView joinForm() {
    return new ModelAndView("MEM101");
  }


  @GetMapping("/confirmJoin")
  public ModelAndView confirmMember(@Valid Mem101DTO mem101DTO, BindingResult e,
      HttpServletRequest request, HttpSession session) {
    if (e.hasErrors()) {
      return new ModelAndView("MEM101");
    }
    return new ModelAndView("MEM102");

  }

  @PostMapping("/confirmJoin")
  public ModelAndView joinMember(@Valid Mem101DTO mem101DTO, BindingResult e,
      HttpServletRequest request, HttpSession session) {
    if (e.hasErrors()) {
      System.out.println(e.getAllErrors());
      return new ModelAndView("MEM101");
    }
    try {

      String NAME = mem101DTO.getName();
      String PASSWORD = mem101DTO.getPassword();
      Integer AGE = Integer.valueOf(mem101DTO.getAge());
      char SEX = mem101DTO.getSex();
      String ZIP = mem101DTO.getZip();
      String ADDRESS = mem101DTO.getAddress();
      String TEL = mem101DTO.getTel();

      Integer member_no = memberService.insertMember(PASSWORD, NAME, AGE, SEX, ZIP, ADDRESS, TEL);

      return new ModelAndView("MEM103", "MEMBER_NO", member_no);
    } catch (SQLException error) {
      error.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception error) {
      error.printStackTrace();
      return new ModelAndView("ERR101");
    }

  }

  @GetMapping("/updateMember")
  public ModelAndView needLoginforUpdate(HttpSession session) {
    MemberEntity member = (MemberEntity) session.getAttribute("member");
    session.setAttribute("currentPage", "updateMember");
    if (member == null) {
      return new ModelAndView("redirect:/login.html");
    }
    return new ModelAndView("MEM201");
  }

  @GetMapping("/changeMember")
  public ModelAndView changeMemberForm(HttpSession session) {
    MemberEntity member = (MemberEntity) session.getAttribute("member");
    if (member == null) {
      return new ModelAndView("redirect:/login.html");
    }
    return new ModelAndView("MEM202");
  }

  @GetMapping("/confirmChange")
  public ModelAndView confirmChangeMember(@Valid Mem202DTO mem202DTO, BindingResult e,
      HttpServletRequest request, HttpSession session) {
    if (e.hasErrors()) {
      return new ModelAndView("MEM202_2");
    }
    MemberEntity member = (MemberEntity) session.getAttribute("member");

    if (mem202DTO.getName() == null || mem202DTO.getName() == "") {
      mem202DTO.setName(member.getNAME());
    }

    if (member.getPASSWORD().equals(mem202DTO.getPassword())) {
      String message = "変更前と同じパスワードには変更はできません。";

      ModelAndView amv = new ModelAndView("MEM202_2", "error", message);
      amv.addObject("mem202DTO", mem202DTO);
      return amv;
    }

    return new ModelAndView("MEM203");

  }

  @PostMapping("/confirmChange")
  public ModelAndView changeMember(@Valid Mem202DTO mem202DTO, BindingResult e,
      HttpServletRequest request, HttpSession session) {
    if (e.hasErrors()) {
      return new ModelAndView("MEM202");
    }

    try {

      Integer MEMBER_NO = mem202DTO.getMember_no();
      String NAME = mem202DTO.getName();
      String PASSWORD = mem202DTO.getPassword();
      Integer AGE = Integer.valueOf(mem202DTO.getAge());
      char SEX = mem202DTO.getSex();
      String ZIP = mem202DTO.getZip();
      String ADDRESS = mem202DTO.getAddress();
      String TEL = mem202DTO.getTel();


      if (!PASSWORD.isEmpty()) {
        memberService.changePassword(MEMBER_NO, PASSWORD);
      }
      memberService.changeMember(MEMBER_NO, NAME, AGE, SEX, ZIP, ADDRESS, TEL);

    } catch (SQLException error) {
      error.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception error) {
      error.printStackTrace();
      return new ModelAndView("ERR101");
    }
    return new ModelAndView("MEM204");

  }

  @GetMapping("/deleteMember")
  public ModelAndView deleteMemberForm(HttpSession session) {
    MemberEntity member = (MemberEntity) session.getAttribute("member");
    if (member == null) {
      return new ModelAndView("redirect:/login.html");
    }
    return new ModelAndView("MEM301");
  }

  @PostMapping("/deleteMember")
  public ModelAndView deleteMember(@Valid Mem301DTO mem301DTO, BindingResult e,
      HttpSession session) {
    if (e.hasErrors()) {
      return new ModelAndView("MEM301");
    }
    try {
      Integer MEMBER_NO = mem301DTO.getMember_no();

      memberService.deleteMember(MEMBER_NO);

    } catch (SQLException error) {
      error.printStackTrace();
      return new ModelAndView("ERR101");
    } catch (Exception error) {
      error.printStackTrace();
      return new ModelAndView("ERR101");
    }
    session.removeAttribute("member");
    return new ModelAndView("MEM302");

  }

}
