package com.ssaw.GlobalManagement.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DateManageMapper {
 
	/**
	 * 将所有节假日保存到数据库中
	 * @param date 页面日期
	 * @return 受影响的行数（1：成功；0：失败）
	 */
	public int insertDate(List<String> list);
	
	/**
	 * 删除保存相重复的日期
	 * @param date 页面日期
	 * @return 受影响的行数（1：成功；0:失败）
	 */
	public int deleteDate(List<String> list);
	
	/**
	 * 查询所有的节假日期
	 * @param where 条件
	 * @return 结果集
	 */
	@Select("select to_char(datetime,'yyyy-MM-dd') from holiday where to_char(datetime,'yyyy-MM-dd') like #{date}")
	public List<String> selectDate(String date);
	
	/**
	 * 查询所有节假日数据
	 * @return 结果集
	 */
	@Select("select to_char(datetime,'yyyy-MM-dd') from holiday")
	public List<String> slectAllDate();
	
}
