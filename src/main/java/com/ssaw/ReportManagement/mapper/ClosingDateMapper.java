package com.ssaw.ReportManagement.mapper;

import com.ssaw.ReportManagement.entity.ClosingDate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: TescComment
 * @Description:成交清算日报表Dao类
 * @author: 瞿平
 * @version:1.0
 * @create: 2020-09-16
 */
@Mapper
public interface ClosingDateMapper {
    /**
     * 查询成交清算日报表
     * @param dateTime
     * @return
     */
    public List<ClosingDate> selectClosingDate(String  dateTime);
}
