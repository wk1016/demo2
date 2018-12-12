package njhk.wisdom.datasys.web.api.controller.serve;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import njhk.wisdom.datasys.bean.common.constants.HttpConstants;
import njhk.wisdom.datasys.bean.common.persistence.Page;
import njhk.wisdom.datasys.bean.common.utils.HttpMessageUtils;

import njhk.wisdom.datasys.bean.entity.server.ServeType;
import njhk.wisdom.datasys.bean.entity.server.ServicePgd;
import njhk.wisdom.datasys.bean.entity.system.Address;
import njhk.wisdom.datasys.bean.vo.ServicePgdVo;
import njhk.wisdom.datasys.web.api.config.BaseController;
import njhk.wisdom.datasys.web.service.impl.AddressService;
import njhk.wisdom.datasys.web.service.impl.ServeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 设备接口
 *
 * @author chengsheng
 * @version 2017-11-11
 */
@Api(tags = "服务内容查询接口")
@Controller
@RequestMapping(value = "/Servetype/info/*")
public class ServetypeController extends BaseController {
    @Autowired
    private ServeTypeService serveTypeService;

    /**
     * 获取会员服务内容列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "服务内容列表", notes = "服务内容列表", produces = "application/json", response = Address.class)
    @RequestMapping(value = "/getServetype", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getServicePGDInfo(
            @ApiParam(required = true, name = "pageNo", value = "页号") @RequestParam(value = "pageNo") Integer pageNo,
            @ApiParam(required = true, name = "pageSize", value = "每页条数") @RequestParam(value = "pageSize") Integer pageSize,
            @ApiParam(required = false, name = "id", value = "用户id") @RequestParam(value = "id", required = false) String id)
    {
        Page<ServeType> objectPage = new Page<ServeType>(pageNo, pageSize);
        Page<ServeType> voObjectPage = new Page<ServeType>(pageNo, pageSize);
        ServeType object =new ServeType();
        objectPage = serveTypeService.findPage(objectPage, object);
        if (objectPage.getList().isEmpty()) {
            return HttpMessageUtils.getReturnMap(null, "无对应记录", HttpConstants.NotSupport);
        }
        List<ServeType> voObjectList = new ArrayList<ServeType>();
        for (ServeType forObject : objectPage.getList()) {
            ServeType voObject = new ServeType();
            voObject.setId(forObject.getId());
            voObject.setBeizhu(forObject.getBeizhu());
            voObject.setChildTypesNum(forObject.getChildTypesNum());
            voObject.setPid(forObject.getPid());
            voObject.setTypeLevel(forObject.getTypeLevel());
            voObject.setTypeName(forObject.getTypeName());
            voObject.setServeType(forObject.getServeType());
            voObjectList.add(voObject);
        }
        if (voObjectList.isEmpty()) {
            return HttpMessageUtils.getReturnMap(voObjectPage, "无对应记录", HttpConstants.NotSupport);
        }
        voObjectPage.setList(voObjectList);
        voObjectPage.setCount(objectPage.getCount());
        return HttpMessageUtils.getReturnMap(voObjectPage, "获取服务内容成功", HttpConstants.OK);
    }


    /**
     * 修改服务内容信息
     *
     * @param
     * @return
     */

    @ApiOperation(value = "修改服务内容信息列表", notes = "修改服务内容信息列表", produces = "application/json", response = Address.class)
    @RequestMapping(value = "/updateServetype", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateServicePgd(
            @ApiParam(required = false, name = "beizhu", value = "参数一") @RequestParam(value = "beizhu", required = false) String beizhu,
            @ApiParam(required = false, name = "typeName", value = "参数一") @RequestParam(value = "typeName", required = false) String typeName)
    {
        ServeType objects=new ServeType();
        objects.setBeizhu(beizhu);
        objects.setTypeName(typeName);

        try {
            serveTypeService.save(objects);
            return  HttpMessageUtils.getReturnMap(null,"服务内容更新成功", HttpConstants.OK);
        } catch (Exception e) {
            return HttpMessageUtils.getReturnMap(e.getMessage(), "系统异常", HttpConstants.OK);

        }
    }


    /**
     * 增加服务内容信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "增加服务内容", notes = "增加服务内容", produces = "application/json", response = ServicePgd.class)
    @RequestMapping(value = "/insertServetype", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insertServicePgd(
            @ApiParam(required = false, name = "beizhu", value = "参数一") @RequestParam(value = "beizhu", required = false) String beizhu,
            @ApiParam(required = false, name = "typeName", value = "参数二") @RequestParam(value = "typeName", required = false) String typeNam)
    {
        ServeType objects=new ServeType();
        objects.setBeizhu(beizhu);
        objects.setTypeName(typeNam);
        try {
            serveTypeService.save(objects);
            return  HttpMessageUtils.getReturnMap(null,"服务内容增加成功",HttpConstants.OK);
        } catch (Exception e) {
            return  HttpMessageUtils.getReturnMap(e.getMessage(),"系统异常",HttpConstants.OK);

        }
    }



    /**
     * 删除服务内容
     *
     * @param
     * @return
     */
    @ApiOperation(value = "删除服务内容", notes = "删除服务内容", produces = "application/json", response = ServicePgd.class)
    @RequestMapping(value = "/deleteServetype", method = RequestMethod.POST)
    @ResponseBody

    public Map<String,Object> deleteAddres( @ApiParam(
            required = false, name = "id", value = "用户id") @RequestParam(value = "id", required = false) String id)
    {
        ServeType objects=new ServeType();
        objects.setId(id);

        try {
            serveTypeService.delete(objects);
            return  HttpMessageUtils.getReturnMap(null,"服务内容删除成功",HttpConstants.OK);
        } catch (Exception e) {
            return  HttpMessageUtils.getReturnMap(e.getMessage(),"系统异常",HttpConstants.OK);

        }
    }



}
