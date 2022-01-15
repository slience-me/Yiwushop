package xyz.slienceme.project_shop.mapper;

import xyz.slienceme.project_shop.dto.ComplaintStatus;
/**
 * <p>
 * 投诉状态类型表 Mapper 接口
 * </p>
 *
 * @author slience_me
 * @since 2022-01-15
 */
public interface ComplaintStatusMapper {
    int deleteByPrimaryKey(Integer complaintStatusId);

    int insert(ComplaintStatus record);

    int insertSelective(ComplaintStatus record);

    ComplaintStatus selectByPrimaryKey(Integer complaintStatusId);

    int updateByPrimaryKeySelective(ComplaintStatus record);

    int updateByPrimaryKey(ComplaintStatus record);
}