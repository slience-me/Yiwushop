package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.Complaint;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 投诉表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
@Mapper
public interface ComplaintMapper {
    int deleteByPrimaryKey(Integer complaintId);

    int insert(Complaint record);

    int insertSelective(Complaint record);

    Complaint selectByPrimaryKey(Integer complaintId);

    int updateByPrimaryKeySelective(Complaint record);

    int updateByPrimaryKey(Complaint record);

    /**
     * 查询投诉表
     *
     */
    List<HashMap<String, Object>> selectList(@Param("keyword") String keyword);

    /**
     * 查询投诉表根据用户（卖家）
     *
     */
//    List<HashMap<String, Object>> selectListBySeller(@Param("userId") Integer userId);

    /**
     * 查询投诉表根据用户（买家）
     *
     */
//    List<HashMap<String, Object>> selectListByBuyer(@Param("ordersId") Integer ordersId);
}