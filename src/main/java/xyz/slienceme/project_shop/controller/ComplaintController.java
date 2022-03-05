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
import xyz.slienceme.project_shop.dto.Category;
import xyz.slienceme.project_shop.dto.Complaint;
import xyz.slienceme.project_shop.dto.Goods;
import xyz.slienceme.project_shop.service.ICategoryService;
import xyz.slienceme.project_shop.service.IComplaintService;
import xyz.slienceme.project_shop.service.IComplaintStatusService;
import xyz.slienceme.project_shop.vo.ComplaintVO;

/**
 * <p>
 * 投诉表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Slf4j
@Api(tags = "投诉表")
@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private IComplaintService complaintService;

    @ApiOperation("查询投诉表")
    @GetMapping("/complaintList")
    public Result complaintList(@RequestHeader("x-access-token") String accessToken,
                                @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                                @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                                @ApiParam(value = "流水号、用户名、状态码") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        //log.info("查询投诉表接口调用--get---</complaintList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return complaintService.complaintList(accessToken, pageNo, pageSize, keyword);
    }

    @ApiOperation("条件查询投诉表")
    @GetMapping("/complaintList/data")
    public Result getComplaintData(@RequestHeader("x-access-token") String accessToken,
                                   @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                                   @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                                   @ApiParam(value = "订单id", required = true) @RequestParam(value = "ordersId") Integer ordersId,
                                   @ApiParam(value = "申请人", required = true) @RequestParam(value = "userId") Integer userId,
                                   @ApiParam(value = "投诉状态", required = true) @RequestParam(value = "complaintStatus") Integer complaintStatus) throws Exception {
        //log.info("查询投诉表接口调用--get---</complaintList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",ordersId=" + ordersId + ",userId=" + userId + ",complaintStatus=" + complaintStatus);
        return complaintService.complaintData(accessToken, pageNo, pageSize, ordersId, userId, complaintStatus);
    }

    @ApiOperation("查询单个投诉表")
    @GetMapping("/complaintList/{complaintId}")
    public Result getGoodsOne(@RequestHeader("x-access-token") String accessToken,
                              @PathVariable("complaintId") @ApiParam(value = "投诉id", required = true) Integer complaintId) throws Exception {
        //log.info("查询单个投诉表接口调用--get---</categoryList/{complaintId}>: complaintId=" + complaintId);
        return complaintService.selectByPrimaryKey(accessToken, complaintId);
    }


    @ApiOperation("添加投诉")
    @PostMapping("/complaintList")
    public Result complaintAdd(@RequestHeader("x-access-token") String accessToken,
                               @RequestBody ComplaintVO complaintVO) throws Exception {
        log.info("添加投诉接口调用---post--</complaintList>:  ComplaintVO=" + complaintVO);
        return complaintService.complaintAdd(accessToken, complaintVO);
    }

    @ApiOperation("修改投诉")
    @PutMapping("/complaintList")
    public Result complaintPut(@RequestHeader("x-access-token") String accessToken,
                           @RequestBody Complaint complaint) throws Exception {
        log.info("修改投诉接口调用---put--</complaintList>:  complaint=" + complaint);
        return complaintService.complaintPut(accessToken, complaint);
    }

    @ApiOperation("通过id删除投诉")
    @DeleteMapping("/complaintList")
    public Result complaintDel(@RequestHeader("x-access-token") String accessToken,
                               @ApiParam(value = "物品类型id") @RequestParam(value = "complaintId") Integer complaintId) throws Exception {
        log.info("通过id删除投诉接口调用---delete--</complaintList>:  complaintId=" + complaintId);
        return complaintService.complaintDel(accessToken, complaintId);
    }
}
