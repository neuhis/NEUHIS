package neu.his.controller;
/**
 *
 * 排班规则及信息管理
 */

import neu.his.bean.ScheduleInfo;
import neu.his.bean.ScheduleRule;
import neu.his.dto.ScheduleInfoDTO;
import neu.his.dto.ScheduleRuleDTO;
import neu.his.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @RequestMapping("scheduleManagement")
    public String toScheduleManagement(){
        return "schedule/scheduleManagement";
    }

    @RequestMapping("scheduleRulesList")
    public @ResponseBody
    List scheduleRulesList(){
        List<ScheduleRule> scheduleRuleList = scheduleService.findAll();
        return scheduleRuleList;
    }


    /**
     * scheduleInfosList
     * @return: List
     */
    @RequestMapping("scheduleInfosList")
    public @ResponseBody List scheduleInfosList(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<ScheduleInfo> scheduleInfoList = scheduleService.findAllInfo();
        for (ScheduleInfo scheduleInfo : scheduleInfoList) {
            scheduleInfo.setScheduleDateString(simpleDateFormat.format(scheduleInfo.getScheduleDate()));
        }
        return scheduleInfoList;
    }



    @RequestMapping("deleteScheduleRulesByID")
    public @ResponseBody
    void deleteScheduleRuleByID(String idString){
        String[] IDs =idString.split(",");
        for(String id:IDs){
            scheduleService.deleteByID(Integer.parseInt(id));
        }
    }

    @RequestMapping("updateScheduleRules")
    public @ResponseBody
    void updateScheduleRules(ScheduleRuleDTO scheduleRules){
        List<ScheduleRule> scheduleRules1 =scheduleRules.getScheduleRules();
        for(ScheduleRule scheduleRule: scheduleRules1){
            scheduleService.updateScheduleRule(scheduleRule);
        }
    }
//    @RequestMapping("addScheduleInfo")
//    public @ResponseBody
//    void addScheduleInfo(ScheduleInfo scheduleInfo){
//        scheduleService.
//    }


    @RequestMapping("addScheduleRule")
    public @ResponseBody
    void addScheduleRule(ScheduleRule scheduleRule){
        scheduleService.insertScheduleRule(scheduleRule);
    }


    @RequestMapping("deleteScheduleInfosByID")
    public @ResponseBody
    void deleteScheduleInfosByID(String idString){
        String ids[] = idString.split(",");
        for(String id:ids){
            scheduleService.deleteInfo(Integer.parseInt(id));
        }
    }

    @RequestMapping("updateScheduleInfos")
    public @ResponseBody
    void updateScheduleInfos(ScheduleInfoDTO scheduleInfoList){
        List<ScheduleInfo> scheduleInfoList1 = scheduleInfoList.getScheduleInfos();
        for(ScheduleInfo scheduleInfo:scheduleInfoList1){
            scheduleService.updateInfo(scheduleInfo);
        }
    }

    /**
     *
     * createScheduleInfos
     * @param startTime
     * @param endTime
     */
    @RequestMapping("createScheduleInfos")
    public @ResponseBody
    void createScheduleInfos(String startTime,String endTime){
        scheduleService.creatInfo(startTime,endTime);
    }

}
