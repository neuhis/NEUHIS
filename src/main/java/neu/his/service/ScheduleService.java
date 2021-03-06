package neu.his.service;

import com.mysql.cj.exceptions.DataReadException;
import neu.his.bean.RegistrationInfo;
import neu.his.bean.ScheduleInfo;
import neu.his.bean.ScheduleRule;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * 处理排班信息
 * @author lsy
 * @version 1.8
 * @since 1.0
 */
public interface ScheduleService {
    /**
     * 0，1与无效有效转化
     * @param type 代码
     * @return 名称
     */
    String translate(String type);



    /**
     * 列出所有排班规则
     * @return 排班规则列表
     */
    List<ScheduleRule> findAll();

    /**
     * 删除排班规则信息
     * @param id 主键id
     */
    void deleteByID(int id);

    /**
     * 插入排班规则信息
     * @param scheduleRule 新的排班规则
     */
    void insertScheduleRule(ScheduleRule scheduleRule);

    /**
     * 更新排班规则信息
     * @param scheduleRule 内容更新过的排班规则
     */
    void updateScheduleRule(ScheduleRule scheduleRule);

    /**
     * 查询所有排班信息
     * @return 所有排班信息列表
     */
    List<ScheduleInfo> findAllInfo();

    /**
     * 生成排班计划
     */
    void creatInfo(String start, String end);

    /**
     * 删除排班信息
     * @param id 主键
     */
    void deleteInfo(int id);

    /**
     * 修改排班信息
     * @param scheduleInfo
     */
    void updateInfo(ScheduleInfo scheduleInfo);

    /**
     * 根据内容查询
     * @param attribute_name 属性名称
     * @param attribute 属性内容
     * @return 排班信息列表
     */
    List<ScheduleInfo> query(String attribute_name, String attribute);

    /**
     * 根据挂号时输入信息查询符合条件的医生
     * @param registrationInfo 要查询的条件
     * @return 返回符合条件的医生姓名
     */
     List<String> selectDoctor(RegistrationInfo registrationInfo);

}
