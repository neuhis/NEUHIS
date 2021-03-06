package neu.his.controller;
/**
 * 结算类型
 * @version 1.0
 * @since 1.8
 */

import neu.his.bean.SettlementType;
import neu.his.dto.SettlementTypeDTO;
import neu.his.service.SettlementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("settlementType")
public class SettlementTypeController {
    @Autowired
    SettlementTypeService settlementTypeService;

    @RequestMapping("settlementTypeManagement")
    public String toSettlementTypeManagement(){
        return "settlementType/settlementTypeManagement";
    }

    @RequestMapping("list")
    public @ResponseBody
    List settlementTypeList(){
        List<SettlementType> settlementtypelist = settlementTypeService.findAll();
        return settlementtypelist;
    }

    @RequestMapping("deleteSettlementTypesByID")
    public @ResponseBody
    void deleteSettlementTypesByID(String idString ){
        String[] IDs =idString.split(",");
        for(String id:IDs){
            settlementTypeService.deleteByID(Integer.parseInt(id));
        }
    }

    @RequestMapping("updateSettlementTypes")
    public @ResponseBody
    void updateSettlementTypes(SettlementTypeDTO settlementtypes){
        List<SettlementType> settlementTypes1=settlementtypes.getSettlementTypes();
        for(SettlementType settlementType:settlementTypes1){
            settlementTypeService.updateSettlementType(settlementType);
        }
    }
    @RequestMapping("addSettlementType")
    public @ResponseBody
    void addSettlementType(SettlementType settlementType){
        settlementTypeService.insertSettlementType(settlementType);
    }


}
