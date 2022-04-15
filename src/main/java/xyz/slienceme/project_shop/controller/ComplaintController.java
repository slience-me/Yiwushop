package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Complaint;
import xyz.slienceme.project_shop.service.IComplaintService;
import xyz.slienceme.project_shop.service.IComplaintStatusService;
import xyz.slienceme.project_shop.vo.ComplaintVO;

/**
 * <p>
 * 投诉表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Slf4j
@Api(tags = "投诉表")
@RestController
@RequestMapping("/admin")
public class ComplaintController {

    @Autowired
    private IComplaintService complaintService;
    @Autowired
    private IComplaintStatusService complaintStatusService;

    @ApiOperation("条件查询投诉表")
    @GetMapping("/complaint")
    public Result complaint(@RequestHeader("x-access-token") String accessToken,
                            @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                            @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                            @ApiParam(value = "订单id") @RequestParam(value = "ordersId", required = false) String ordersId,
                            @ApiParam(value = "申请人") @RequestParam(value = "userId", required = false) Integer userId,
                            @ApiParam(value = "投诉状态") @RequestParam(value = "complaintStatus", required = false) Integer complaintStatus) throws Exception {
        //log.info("查询投诉表接口调用--get---</complaintList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",ordersId=" + ordersId + ",userId=" + userId + ",complaintStatus=" + complaintStatus);
        return complaintService.complaint(accessToken, pageNo, pageSize, ordersId, userId, complaintStatus);
    }

    @ApiOperation("查询投诉状态类型列表")
    @GetMapping("/complaint/type")
    public Result ComplaintStatusList(@RequestHeader("x-access-token") String accessToken) throws Exception {
        //log.info("查询投诉状态类型列表接口调用--get---</complaint/type>");
        return complaintStatusService.complaintTypeList();
    }

    @ApiOperation("查询单个投诉表")
    @GetMapping("/complaint/{complaintId}")
    public Result complaintOne(@RequestHeader("x-access-token") String accessToken,
                              @PathVariable("complaintId") @ApiParam(value = "投诉id", required = true) Integer complaintId) throws Exception {
        //log.info("查询单个投诉表接口调用--get---</categoryList/{complaintId}>: complaintId=" + complaintId);
        return complaintService.selectByPrimaryKey(accessToken, complaintId);
    }


    @ApiOperation("添加投诉")
    @PostMapping("/complaint")
    public Result complaintAdd(@RequestHeader("x-access-token") String accessToken,
                               @RequestBody ComplaintVO complaintVO) throws Exception {
        log.info("添加投诉接口调用---post--</complaintList>:  ComplaintVO=" + complaintVO);
        return complaintService.complaintAdd(accessToken, complaintVO);
    }

    @ApiOperation("修改投诉")
    @PutMapping("/complaint")
    public Result complaintPut(@RequestHeader("x-access-token") String accessToken,
                               @RequestBody Complaint complaint) throws Exception {
        log.info("修改投诉接口调用---put--</complaintList>:  complaint=" + complaint);
        return complaintService.complaintPut(accessToken, complaint);
    }

    @ApiOperation("通过id删除投诉")
    @DeleteMapping("/complaint")
    public Result complaintDel(@RequestHeader("x-access-token") String accessToken,
                               @ApiParam(value = "物品类型id") @RequestParam(value = "complaintId") Integer complaintId) throws Exception {
        log.info("通过id删除投诉接口调用---delete--</complaintList>:  complaintId=" + complaintId);
        return complaintService.complaintDel(accessToken, complaintId);
    }
}
