package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Admin;
import xyz.slienceme.project_shop.dto.AdminLogs;
import xyz.slienceme.project_shop.dto.Category;
import xyz.slienceme.project_shop.mapper.AdminLogsMapper;
import xyz.slienceme.project_shop.mapper.AdminMapper;
import xyz.slienceme.project_shop.mapper.CategoryMapper;
import xyz.slienceme.project_shop.service.ICategoryService;
import xyz.slienceme.project_shop.utils.JWT;
import xyz.slienceme.project_shop.vo.TokenVO;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 物品类型表 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private AdminLogsMapper adminLogsMapper;
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 物品类型表列表
     * @param accessToken 请求token
     * @param page        页码
     * @param limit       每页个数
     * @param keyword     关键词
     * @return
     * @throws Exception
     */
    @Override
    public Result category(String accessToken, Integer page, Integer limit, String keyword) throws Exception {
        PageHelper.startPage(page,limit);
        List<HashMap<String, Object>> list = categoryMapper.selectList(keyword);
        return Result.createBySuccess(new PageInfo<>(list));
    }

    /**
     * 根据物品类型信息添加
     * @param accessToken
     * @param categoryName
     * @return
     * @throws Exception
     */
    @Override
    public Result categoryAdd(String accessToken, String categoryName) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Category category1 = categoryMapper.selectByName(categoryName);
        if (Objects.nonNull(category1)) {
            return Result.createByErrorMessage("类型已存在");
        }
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCreatedBy(unsign.getUserId());
        int flag = categoryMapper.insertSelective(category);
        if(flag > 0){
            adminLogsMapper.insertSelective(new AdminLogs(unsign.getUserId(), "新增了商品类型名称 " + category.getCategoryName()));
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    /**
     * 根据id删除物品类型
     * @param accessToken
     * @param categoryId
     * @return
     * @throws Exception
     */
    @Override
    public Result categoryDel(String accessToken, Integer categoryId) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        category.setIsDelete(1);
        int flag = categoryMapper.updateByPrimaryKeySelective(category);
        if(flag > 0){
            adminLogsMapper.insertSelective(new AdminLogs(unsign.getUserId(), "删除了商品类型名称 " + category.getCategoryName()));
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    /**
     * 根据物品类型信息修改物品类型
     * @param accessToken
     * @param category
     * @return
     * @throws Exception
     */
    @Override
    public Result categoryPut(String accessToken, Category category) throws Exception {
        TokenVO unsign = JWT.unsign(accessToken, TokenVO.class);
        Category category1 = categoryMapper.selectByPrimaryKey(category.getCategoryId());
        if (Objects.isNull(category1)) {
            return Result.createByErrorMessage("类型不存在");
        }
        int flag = categoryMapper.updateByPrimaryKeySelective(category);
        if(flag > 0){
            adminLogsMapper.insertSelective(new AdminLogs(unsign.getUserId(), "修改了商品类型名称 " + category.getCategoryName()));
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }
}
