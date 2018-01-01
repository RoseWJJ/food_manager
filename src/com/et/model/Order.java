package com.et.model;

import java.util.List;
import java.util.Map;

import com.et.tool.OracleTool;
import com.et.tool.PageTool;

public class Order {
	public static Integer getFoodCount() throws Exception{
		
		String sql = "select count(rowid) as CR from  FOODORDER";
		System.out.println(sql);
		List<Map>list = OracleTool.query(sql);
		//由于list中等的类型是map 所以 我们知道 获取 map中的值 是通过键  获取list中的元素是通过下标 所以
		return Integer.parseInt(list.get(0).get("CR").toString()) ;
	}
	
	
	public static PageTool getFoodPager(Integer curPage) throws Exception{
		
		Integer count = Order.getFoodCount();
		System.out.println(count);
		//传入参数 当前页， 总个数，每页显示个数
		PageTool pt = new PageTool(curPage, count,null);
		String sql="select * from(select rownum rn,t.* from FOODORDER where rn >="+ pt.getStartIndex() +" and  rn <="+pt.getEndIndex();
		
		List<Map> list =OracleTool.query(sql);
		pt.setData(list);
		return pt;
		
		
	}
}
