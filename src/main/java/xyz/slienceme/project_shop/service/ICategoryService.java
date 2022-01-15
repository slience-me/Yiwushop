package xyz.slienceme.project_shop.service;


import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Category;

/**
 * <p>
 * 物品类型表 服务类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface ICategoryService {

    /**
     * 物品类型表列表
     *
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     */
    Result categoryList(String accessToken,
                        Integer page,
                        Integer limit,
                        String keyword) throws Exception;

    /**
     * 根据物品类型信息添加
     */
    Result categoryAdd(String accessToken, String categoryName) throws Exception;

    /**
     * 根据id删除物品类型
     */
    Result categoryDel(String accessToken, Integer categoryId) throws Exception;

    /**
     * 根据物品类型信息修改物品类型
     */
    Result categoryPut(String accessToken, Category category) throws Exception;
}
