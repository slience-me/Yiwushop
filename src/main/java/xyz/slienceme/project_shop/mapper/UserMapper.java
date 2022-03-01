package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.User;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByOpenId(@Param("openid") String openid);

    User selectByUserId(@Param("userId") Integer userId);

    List<HashMap<String, Object>> selectList(@Param("keyword") String keyword,
                                             @Param("openid") String openid);

    List<HashMap<String, Object>> selectConditionList(@Param("openid") String openid,
                                                      @Param("idCard") String idCard,
                                                      @Param("userNumber") String userNumber,
                                                      @Param("userName") String userName,
                                                      @Param("userPhone") String userPhone,
                                                      @Param("userAddress") String userAddress);
}