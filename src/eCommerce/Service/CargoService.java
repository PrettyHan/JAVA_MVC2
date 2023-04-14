package eCommerce.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import eCommerce.DTO.CargoDTO;
import eCommerce.DTO.ChargeDTO;

public interface CargoService {

  List<CargoDTO> deleteCargo(List<CargoDTO> cargoList, List<CargoDTO> sessionCargo)
      throws Exception;

  void insertOrder(List<CargoDTO> cargoList, ChargeDTO chargeDTO, Integer MEMBER_NO)
      throws SQLException, Exception;

  ChargeDTO calculate(HashMap<String, Integer> checkMap) throws SQLException, Exception;

}
