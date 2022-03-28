package xyz.slienceme.project_shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.slienceme.project_shop.common.Result;
import xyz.slienceme.project_shop.dto.Goods;
import xyz.slienceme.project_shop.dto.GoodsImage;
import xyz.slienceme.project_shop.mapper.GoodsImageMapper;
import xyz.slienceme.project_shop.mapper.GoodsMapper;
import xyz.slienceme.project_shop.service.IGoodsService;
import xyz.slienceme.project_shop.vo.GoodsVO;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsImageMapper goodsImageMapper;

    /**
     * 根据商品信息添加商品
     *
     * @param accessToken
     * @param goodsVO
     * @return
     * @throws Exception
     */
    @Override
    public Result goodsAdd(String accessToken, GoodsVO goodsVO) throws Exception {

        List<HashMap<String, Object>> data = goodsMapper.selectConditionList(goodsVO.getGoodsName(), null, null, null, goodsVO.getUserId());
        if (data.size() != 0) {
            return Result.createByErrorMessage("相同用户拥有的商品的名称不能相同");
        } else {
            Goods goods = new Goods();
            goods.setGoodsName(goodsVO.getGoodsName());
            goods.setGoodsPrice(goodsVO.getGoodsPrice());
            goods.setGoodsInfo(goodsVO.getGoodsInfo());
            if (Objects.isNull(goodsVO.getStateOn())) {
                goods.setStateOn(1);//默认不上架
            } else {
                goods.setStateOn(goodsVO.getStateOn());
            }
            goods.setCategoryId(goodsVO.getCategoryId());
            goods.setUserId(goodsVO.getUserId());
            int flag1 = goodsMapper.insertSelective(goods);
            if (flag1 > 0) {
                if (goodsVO.getImageIds().length != 0) {
                    List<HashMap<String, Object>> data1 = goodsMapper.selectConditionList(goodsVO.getGoodsName(), null, null, null, goodsVO.getUserId());
                    Integer goodsId = 0;
                    for (HashMap<String, Object> datum : data1) {
                        goodsId = (Integer) datum.get("goodsId");
                    }
                    for (int i = 0; i < goodsVO.getImageIds().length; i++) {
                        GoodsImage goodsImage = new GoodsImage();
                        goodsImage.setGoodsId(goodsId);
                        goodsImage.setImageId(goodsVO.getImageIds()[i]);
                        goodsImageMapper.insertSelective(goodsImage);
                    }
                }
                return Result.createBySuccessMessage("成功");
            } else {
                return Result.createByErrorMessage("操作失败请稍后重试");
            }
        }

    }

    /**
     * 根据id删除商品
     *
     * @param accessToken
     * @param goodsId
     * @return
     * @throws Exception
     */
    @Override
    public Result goodsDel(String accessToken, Integer goodsId) throws Exception {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        goods.setIsDelete(1);
        int flag = goodsMapper.updateByPrimaryKeySelective(goods);
        if (flag > 0) {
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    /**
     * 根据商品信息修改商品
     *
     * @param accessToken
     * @param goods
     * @return
     * @throws Exception
     */
    @Override
    public Result goodsPut(String accessToken, Goods goods) throws Exception {
        Goods goods1 = goodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (Objects.isNull(goods1)) {
            return Result.createByErrorMessage("商品不存在");
        }
        int flag = goodsMapper.updateByPrimaryKeySelective(goods);
        if (flag > 0) {
            return Result.createBySuccessMessage("成功");
        } else {
            return Result.createByErrorMessage("操作失败请稍后重试");
        }
    }

    @Override
    public Result selectByPrimaryKey(String accessToken, Integer goodsId) {
        Goods goods = goodsMapper.selectByPrimaryKey1(goodsId);
        if (Objects.isNull(goods)) {
            return Result.createByErrorMessage("id不正确");
        }
        return Result.createBySuccess(goods);
    }

    @Override
    public Result goods(String accessToken, Integer pageNo, Integer pageSize, String goodsName, String goodsInfo, Integer stateOn, Integer categoryId, Integer userId) {
        PageHelper.startPage(pageNo, pageSize);
        List<HashMap<String, Object>> list = goodsMapper.selectConditionList(goodsName, goodsInfo, stateOn, categoryId, userId);
        for (HashMap<String, Object> map : list) {
            Integer goodsId = (Integer) map.get("goodsId");
            List<String> images = goodsImageMapper.selectImageByGoodsId(goodsId);
            if (Objects.nonNull(images)) {
                map.put("images", images);
            }
        }
        return Result.createBySuccess(new PageInfo<>(list));
    }
}