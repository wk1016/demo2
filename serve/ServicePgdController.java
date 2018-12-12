package njhk.wisdom.datasys.web.api.controller.serve;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import njhk.wisdom.datasys.bean.common.constants.HttpConstants;
import njhk.wisdom.datasys.bean.common.persistence.Page;
import njhk.wisdom.datasys.bean.common.utils.HttpMessageUtils;
import njhk.wisdom.datasys.bean.entity.customer.Customer;
import njhk.wisdom.datasys.bean.entity.server.ServicePgd;
import njhk.wisdom.datasys.bean.vo.ServicePgdVo;
import njhk.wisdom.datasys.web.api.config.BaseController;
import njhk.wisdom.datasys.web.service.impl.ServicePgdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备接口
 *
 * @author chengsheng
 * @version 2017-11-11
 */
@Api(tags = "工单信息查询接口")
@Controller
@RequestMapping(value = "/servicePgd/info/*")
public class ServicePgdController extends BaseController {
    @Autowired
    private ServicePgdService servicePgdService;

    /**
     * 获取会员地址列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "工单信息列表", notes = "工单信息列表", produces = "application/json", response = ServicePgdVo.class)
    @RequestMapping(value = "/getServicePgdInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getServicePGDInfo(
            @ApiParam(required = true, name = "pageNo", value = "页号") @RequestParam(value = "pageNo") Integer pageNo,
            @ApiParam(required = true, name = "pageSize", value = "每页条数") @RequestParam(value = "pageSize") Integer pageSize,
            @ApiParam(required = false, name = "id", value = "用户id") @RequestParam(value = "id", required = false) String id)
    {
        Page<ServicePgd> objectPage = new Page<ServicePgd>(pageNo, pageSize);
        Page<ServicePgdVo> voObjectPage = new Page<ServicePgdVo>(pageNo, pageSize);
        ServicePgd object =new ServicePgd();
        objectPage = servicePgdService.findPage(objectPage, object);
        if (objectPage.getList().isEmpty()) {
            return HttpMessageUtils.getReturnMap(null, "无对应记录", HttpConstants.NotSupport);
        }
        List<ServicePgdVo> voObjectList = new ArrayList<ServicePgdVo>();
        for (ServicePgd forObject : objectPage.getList()) {
            ServicePgdVo voObject = new ServicePgdVo();
            voObject.setId(forObject.getId());
            voObject.setOrderNum(forObject.getOrderNum());
            voObject.setAppStates(forObject.getAppStates());
            voObject.setStates(forObject.getStates());
            voObject.setCustomerName(forObject.getCustomerName());
            voObject.setServeproductName(forObject.getServeproductName());
            voObject.setServestationName(forObject.getServestationName());
            voObject.setServiceType(forObject.getServiceType());
            voObject.setFromApp(forObject.getFromApp());
            voObject.setTimeStart(forObject.getTimeStart());
            voObject.setTimeEnd(forObject.getTimeEnd());
            voObject.setPgdtype(forObject.getPgdtype());
            voObject.setPicStart(forObject.getPicStart());
            voObject.setPicFinished(forObject.getPicFinished());
            voObject.setAmountPayable(forObject.getAmountPayable());
            voObject.setEstimateLevel(forObject.getEstimateLevel());
            voObject.setPeriod(forObject.getPeriod());
            voObject.setSertime(forObject.getSertime());
            voObject.setRealPeriod(forObject.getRealPeriod());
            voObject.setIsFree(forObject.getIsFree());
            voObject.setTotalPrice(forObject.getTotalPrice());
            voObject.setSertimeEnd(forObject.getSertimeEnd());
            voObject.setServePersonName(forObject.getServePersonName());
            voObject.setCommunity(forObject.getCommunity());


            voObjectList.add(voObject);
        }
        if (voObjectList.isEmpty()) {
            return HttpMessageUtils.getReturnMap(voObjectPage, "无对应记录", HttpConstants.NotSupport);
        }
        voObjectPage.setList(voObjectList);
        voObjectPage.setCount(objectPage.getCount());
        return HttpMessageUtils.getReturnMap(voObjectPage, "获取服务工单列表成功", HttpConstants.OK);
    }


    /**
     * 修改工单信息
     *
     * @param
     * @return
     */

    @ApiOperation(value = "修改工单信息列表", notes = "修改工单信息列表", produces = "application/json", response = ServicePgd.class)
    @RequestMapping(value = "/updateServicePgd", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> updateServicePgd(
            @ApiParam(required = false, name = "id", value = "用户id") @RequestParam(value = "id", required = false) String id)
    {
           ServicePgd objects=new ServicePgd();
           objects.setId(id);

        try {
            servicePgdService.save(objects);
            return  HttpMessageUtils.getReturnMap(null,"工单列表更新成功", HttpConstants.OK);
        } catch (Exception e) {
            return HttpMessageUtils.getReturnMap(e.getMessage(), "系统异常", HttpConstants.OK);

        }
    }


    /**
     * 增加工单信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "增加工单信息列表", notes = "增加工单信息列表", produces = "application/json", response = ServicePgd.class)
    @RequestMapping(value = "/insertServicePgd", method = RequestMethod.POST)
    @ResponseBody
     public Map<String,Object> insertServicePgd(
            @ApiParam(required = false, name = "askCloseName", value = "参数一") @RequestParam(value = "askCloseName", required = false) String askCloseName,
            @ApiParam(required = false, name = "askCloseSeatNo", value = "参数二") @RequestParam(value = "askCloseSeatNo", required = false) String askCloseSeatNo,
            @ApiParam(required = false, name = "callSeatName", value = "参数三") @RequestParam(value = "callSeatName", required = false) String callSeatName,
            @ApiParam(required = false, name = "orderNum", value = "参数四") @RequestParam(value = "orderNum", required = false) String orderNum,
            @ApiParam(required = false, name = "shiTime", value = "参数四") @RequestParam(value = "shiTime", required = false) Double shiTime)
     {
         ServicePgd objects=new ServicePgd();
         objects.setAskCloseName(askCloseName);
         objects.setAskCloseSeatNo(askCloseSeatNo);
         objects.setCallSeatName(callSeatName);
         objects.setOrderNum(orderNum);
         objects.setShiTime(shiTime);
         try {
             servicePgdService.save(objects);
             return  HttpMessageUtils.getReturnMap(null,"工单信息增加成功",HttpConstants.OK);
         } catch (Exception e) {
             return  HttpMessageUtils.getReturnMap(e.getMessage(),"系统异常",HttpConstants.OK);

         }


     }



    /**
     * 删除工单信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "删除工单信息列表", notes = "删除工单信息列表", produces = "application/json", response = ServicePgd.class)
    @RequestMapping(value = "/deleteServicePgd", method = RequestMethod.POST)
    @ResponseBody

    public Map<String,Object> deleteServicePgd( @ApiParam(
            required = false, name = "id", value = "用户id") @RequestParam(value = "id", required = false) String id)
    {
        ServicePgd objects=new ServicePgd();
        objects.setId(id);

        try {
            servicePgdService.delete(objects);
            return  HttpMessageUtils.getReturnMap(null,"工单信息删除成功",HttpConstants.OK);
        } catch (Exception e) {
            return  HttpMessageUtils.getReturnMap(e.getMessage(),"系统异常",HttpConstants.OK);

        }


    }



}
