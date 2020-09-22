package com.ssaw.GlobalManagement.controller;

import com.ssaw.GlobalManagement.service.DateManageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dateManageController")
public class DateManageController {

	@Resource
	DateManageService dateManagerBiz;

	/**
	 * 节假日设置 删除重复数据和添加
	 * @param strList
	 * @return 受影响的行数（1：成功；0：失败）
	 */
	@RequestMapping("/insertDate")
	public int insertDate(String strList) {
		return dateManagerBiz.insertDate(strList);
	}
	
	/**
	 * 查询已有的节假日数据 
	 * @param date 条件
	 * @return 结果集
	 */
	@RequestMapping("/selectDate")
	public List<String> selectDate(String date){
		List<String> selectDate = dateManagerBiz.selectDate(date);
		return selectDate;
	}
}
