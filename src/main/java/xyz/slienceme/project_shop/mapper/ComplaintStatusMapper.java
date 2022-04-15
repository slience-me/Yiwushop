package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.ComplaintStatus;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 投诉状态类型表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-03-15
 */
@Mapper
public interface ComplaintStatusMapper {
    int deleteByPrimaryKey(Integer complaintStatusId);

    int insert(ComplaintStatus record);

    int insertSelective(ComplaintStatus record);

    ComplaintStatus selectByPrimaryKey(Integer complaintStatusId);

    int updateByPrimaryKeySelective(ComplaintStatus record);

    int updateByPrimaryKey(ComplaintStatus record);

    /**
     * 查询投诉状态类型列表
     */
    List<HashMap<String, Object>> selectList();
}