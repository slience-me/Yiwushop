package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.service.IAdminService;
import xyz.slienceme.project_shop.service.IComplaintStatusService;
import xyz.slienceme.project_shop.vo.LoginVO;

/**
 * <p>
 * 投诉状态类型表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Slf4j
@Api(tags = "投诉状态类型表")
@RestController
@RequestMapping("/complaint")
public class ComplaintStatusController {

    @Autowired
    private IComplaintStatusService complaintStatusService;

    @ApiOperation("查询投诉状态类型列表")
    @GetMapping("/type")
    public Result ComplaintStatusList() throws Exception {
        log.info("查询投诉状态类型列表接口调用--get---</complaint/type>");
        return complaintStatusService.complaintTypeList();
    }

}
