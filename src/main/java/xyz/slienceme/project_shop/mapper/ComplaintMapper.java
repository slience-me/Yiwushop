package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.slienceme.project_shop.dto.Complaint;

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
}