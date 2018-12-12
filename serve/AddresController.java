package njhk.wisdom.datasys.web.api.controller.serve;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import njhk.wisdom.datasys.bean.common.constants.HttpConstants;
import njhk.wisdom.datasys.bean.common.persistence.Page;
import njhk.wisdom.datasys.bean.common.utils.HttpMessageUtils;

import njhk.wisdom.datasys.bean.entity.server.ServicePgd;
import njhk.wisdom.datasys.bean.entity.system.Address;
import njhk.wisdom.datasys.bean.vo.ServicePgdVo;
import njhk.wisdom.datasys.web.api.config.BaseController;
import njhk.wisdom.datasys.web.service.impl.AddressService;
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
@Api(tags = "地址查询接口")
@Controller
@RequestMapping(value = "/Address/info/*")
public class AddresController extends BaseController {
    @Autowired
    private AddressService addressService;

    /**
     * 获取会员地址列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "地址列表", notes = "地址列表", produces = "application/json", response = Address.class)
    @RequestMapping(value = "/getAddressInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getAddressInfo(
            @ApiParam(required = true, name = "pageNo", value = "页号") @RequestParam(value = "pageNo") Integer pageNo,
            @ApiParam(required = true, name = "pageSize", value = "每页条数") @RequestParam(value = "pageSize") Integer pageSize,
            @ApiParam(required = false, name = "id", value = "用户id") @RequestParam(value = "id", required = false) String id)
    {
        Page<Address> objectPage = new Page<Address>(pageNo, pageSize);
        Page<Address> voObjectPage = new Page<Address>(pageNo, pageSize);
        Address object =new Address();
        objectPage = addressService.findPage(objectPage, object);
        if (objectPage.getList().isEmpty()) {
            return HttpMessageUtils.getReturnMap(null, "无对应记录", HttpConstants.NotSupport);
        }
        List<Address> voObjectList = new ArrayList<Address>();
        for (Address forObject : objectPage.getList()) {
            Address voObject = new Address();
            voObject.setId(forObject.getId());
            voObject.setAddress(forObject.getAddress());
            voObject.setAddressName(forObject.getAddressName());
            voObject.setAddressType(forObject.getAddressType());
            voObject.setAno(forObject.getAno());
            voObject.setChnum(forObject.getChnum());
            voObject.setDel(forObject.getDel());
            voObject.setOrganizationCode(forObject.getOrganizationCode());
            voObject.setParentId(forObject.getParentId());
            voObject.setSysEndCood(forObject.getSysEndCood());
            voObject.setPid(forObject.getPid());
            voObjectList.add(voObject);
        }
        if (voObjectList.isEmpty()) {
            return HttpMessageUtils.getReturnMap(voObjectPage, "无对应记录", HttpConstants.NotSupport);
        }
        voObjectPage.setList(voObjectList);
        voObjectPage.setCount(objectPage.getCount());
        return HttpMessageUtils.getReturnMap(voObjectPage, "获取地址成功", HttpConstants.OK);
    }


    /**
     * 修改地址信息
     *
     * @param
     * @return
     */

    @ApiOperation(value = "修改地址信息列表", notes = "修改地址信息列表", produces = "application/json", response = Address.class)
    @RequestMapping(value = "/updateAddres", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateAddres(
            @ApiParam(required = false, name = "addressName", value = "参数一") @RequestParam(value = "addressName", required = false) String addressName)
    {
        Address objects=new Address();
        objects.setAddressName(addressName);

        try {
            addressService.save(objects);
            return  HttpMessageUtils.getReturnMap(null,"地址更新成功", HttpConstants.OK);
        } catch (Exception e) {
            return HttpMessageUtils.getReturnMap(e.getMessage(), "系统异常", HttpConstants.OK);

        }
    }


    /**
     * 增加地址信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "增加地址", notes = "增加地址", produces = "application/json", response = ServicePgd.class)
    @RequestMapping(value = "/insertAddress", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insertAddress(
            @ApiParam(required = false, name = "addressName", value = "参数一") @RequestParam(value = "addressName", required = false) String addressName,
            @ApiParam(required = false, name = "addressType", value = "参数二") @RequestParam(value = "addressType", required = false) int addressType,
            @ApiParam(required = false, name = "ano", value = "参数三") @RequestParam(value = "ano", required = false) String ano)
    {
        Address objects=new Address();
        objects.setAddressName(addressName);
        objects.setAddressType(addressType);
        objects.setAno(ano);
        try {
            addressService.save(objects);
            return  HttpMessageUtils.getReturnMap(null,"地址增加成功",HttpConstants.OK);
        } catch (Exception e) {
            return  HttpMessageUtils.getReturnMap(e.getMessage(),"系统异常",HttpConstants.OK);

        }
    }



    /**
     * 删除地址
     *
     * @param
     * @return
     */
    @ApiOperation(value = "删除地址", notes = "删除地址", produces = "application/json", response = ServicePgd.class)
    @RequestMapping(value = "/deleteAddres", method = RequestMethod.POST)
    @ResponseBody

    public Map<String,Object> deleteAddres( @ApiParam(
            required = false, name = "id", value = "用户id") @RequestParam(value = "id", required = false) String id)
    {
        Address objects=new Address();
        objects.setId(id);

        try {
            addressService.delete(objects);
            return  HttpMessageUtils.getReturnMap(null,"地址删除成功",HttpConstants.OK);
        } catch (Exception e) {
            return  HttpMessageUtils.getReturnMap(e.getMessage(),"系统异常",HttpConstants.OK);

        }
    }



}
