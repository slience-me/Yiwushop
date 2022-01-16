package xyz.slienceme.project_shop.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Category;
import xyz.slienceme.project_shop.service.ICategoryService;

/**
 * <p>
 * 物品类型表 前端控制器
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Api(tags = "物品类型表")
@RestController
@RequestMapping("/category")
public class CategoryController {

    public static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private ICategoryService categoryService;

    @ApiOperation("查询物品类型")
    @GetMapping("/categoryList")
    public Result categoryList(@RequestHeader("x-access-token") String accessToken,
                               @ApiParam(value = "第几页", required = true) @RequestParam(value = "pageNo") Integer pageNo,
                               @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize") Integer pageSize,
                               @ApiParam(value = "名称、描述") @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        log.info("查询物品类型列表接口调用--get---</categoryList>:  pageNo=" + pageNo + ",pageSize=" + pageSize + ",keyword=" + keyword);
        return categoryService.categoryList(accessToken, pageNo, pageSize, keyword);
    }

    @ApiOperation("添加物品类型")
    @PostMapping("/categoryList")
    public Result categoryAdd(@RequestHeader("x-access-token") String accessToken,
                              @ApiParam(value = "分类名称") @RequestParam String categoryName) throws Exception {
        log.info("添加管理员接口调用---post--</categoryList>:  categoryName=" + categoryName);
        return categoryService.categoryAdd(accessToken, categoryName);
    }

    @ApiOperation("通过id删除物品类型")
    @DeleteMapping("/categoryList")
    public Result categoryDel(@RequestHeader("x-access-token") String accessToken,
                              @ApiParam(value = "物品类型id") @RequestParam(value = "categoryId") Integer categoryId) throws Exception {
        log.info("通过id删除物品类型接口调用---delete--</categoryList>:  categoryId=" + categoryId);
        return categoryService.categoryDel(accessToken, categoryId);
    }


    @ApiOperation("修改物品类型")
    @PutMapping("/categoryList")
    public Result categoryPut(@RequestHeader("x-access-token") String accessToken,
                              @RequestBody Category category) throws Exception {
        log.info("修改物品类型接口调用---put--</categoryList>:  Category=" + category);
        return categoryService.categoryPut(accessToken, category);
    }

}
