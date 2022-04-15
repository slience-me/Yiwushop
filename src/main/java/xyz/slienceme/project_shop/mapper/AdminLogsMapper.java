package xyz.slienceme.project_shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xyz.slienceme.project_shop.dto.AdminLogs;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AdminLogsMapper {
    int deleteByPrimaryKey(Integer adminLogsId);

    int insert(AdminLogs record);

    int insertSelective(AdminLogs record);

    AdminLogs selectByPrimaryKey(Integer adminLogsId);

    int updateByPrimaryKeySelective(AdminLogs record);

    int updateByPrimaryKey(AdminLogs record);

    /**
     * 查询所有日志
     *
     * @param adminId 管理员ID
     */
    List<HashMap<String, Object>> selectList(@Param("adminId") Integer adminId);
}