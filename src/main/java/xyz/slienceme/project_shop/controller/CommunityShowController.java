package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.CommunityShow;
import xyz.slienceme.project_shop.service.ICommunityShowService;
import xyz.slienceme.project_shop.vo.CommunityShowVO;

/**
 * <p>
 * 公益展示表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Slf4j
@Api(tags = "公益展示表")
@RestController
@RequestMapping("/community")
public class CommunityShowController {

    @Autowired
    private ICommunityShowService communityShowService;

    @ApiOperation("新增公益")
    @PostMapping("")
    public Result addCommunity(@RequestHeader("x-access-token") String accessToken,
                            @RequestBody CommunityShowVO communityShowVO) throws Exception {
        log.info("新增公益接口调用-------post---------</community>");
        return communityShowService.addCommunity(accessToken, communityShowVO);
    }

    @ApiOperation("删除公益")
    @DeleteMapping("")
    public Result deleteCommunity(@RequestHeader("x-access-token") String accessToken,
                               @ApiParam(value = "id", required = true) @RequestParam(value = "id") Integer id) throws Exception {
        log.info("删除公益接口调用-------Delete---------</community>:  id=" + id);
        return communityShowService.deleteCommunity(accessToken, id);
    }

    @ApiOperation("修改公益信息")
    @PutMapping("")
    public Result updateCommunity(@RequestHeader("x-access-token") String accessToken,
                               @RequestBody CommunityShow communityShow) throws Exception {
        log.info("修改公益信息接口调用-------Put---------</community>");
        return communityShowService.updateCommunity(accessToken, communityShow);
    }


    @ApiOperation("按页查询所有公益")
    @GetMapping("")
    public Result getCommunity(@RequestHeader("x-access-token") String accessToken,
                             @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                             @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                             @ApiParam(value = "名称、描述") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("按页查询所有公益接口调用-------get---------</community>:  ");
        return communityShowService.community(accessToken, pageNo, pageSize, keyword);
    }

}
