package com.ssaw.GlobalManagement.service;

import java.util.List;


public interface DateManageService {

	/**
	 * 将所有节假日保存到数据库中
	 * @return 受影响的行数（1：成功；0：失败）
	 */
	public int insertDate(String strList);
	
	
	/**
	 * 查询所有的节假日期
	 * @return 结果集
	 */
	public List<String> selectDate(String date);
	
}
