package com.et.model;

import java.util.List;
import java.util.Map;

import com.et.tool.OracleTool;
import com.et.tool.PageTool;

public class FoodTypeModel {
	public static Integer getFoodTypeCount(String name) throws Exception{
		if(name==null){
			name="";
		}
		String sql = "select count(rowid) as CR from  foodtype where TYPENAME  like '%"+name+"%'";
		System.out.println(sql);
		List<Map>list = OracleTool.query(sql);
		//����list�еȵ�������map ���� ����֪�� ��ȡ map�е�ֵ ��ͨ����  ��ȡlist�е�Ԫ����ͨ���±� ����
		return Integer.parseInt(list.get(0).get("CR").toString()) ;
	}
	
	
	public static PageTool getFoodTypePager(String name,Integer curPage) throws Exception{
		if(name==null){
			name="";
		}
		Integer count = FoodTypeModel.getFoodTypeCount(name);
		System.out.println(count);
		//������� ��ǰҳ�� �ܸ�����ÿҳ��ʾ����
		PageTool pt = new PageTool(curPage, count,null);
		String sql="select * from(select rownum rn,t.* from FOODTYPE t where TYPENAME like '%"+name+"%')" +
				"where rn >="+ pt.getStartIndex() +" and  rn <="+pt.getEndIndex();
		System.out.println(sql);
		List<Map> list =OracleTool.query(sql);
		System.out.println(list);
		pt.setData(list);
		return pt;
		
		
	}
	
	public static void saveFoodType(String TYPENAME) throws Exception{
		String sql="insert into FOODTYPE values((select max(typeid)+1 from FOODTYPE),'"+TYPENAME+"')";
		System.out.println(sql);
		int count =OracleTool.execute(sql);
		
	}
	public static void deleteFoodType(String TYPEID) throws Exception{
		String sql="delete from FOODTYPE  where TYPEID = "+TYPEID ;
		System.out.println("ɾ����Ʒ"+sql);
		int count1 =OracleTool.execute(sql);
		
	}
	public static void updateFoodType(String TYPEID,String TYPENAME) throws Exception{
	
		String sql="	update Foodtype SET TYPENAME ='"+TYPENAME+"' WHERE TYPEid = "+TYPEID ;
		System.out.println("�޸Ĳ�Ʒ"+sql);
		int count1 =OracleTool.execute(sql);
		
	}
	public static List<Map> getFoodType() throws Exception{
		
		String sql = "select *  from  FOODTYPE ";
		System.out.println(sql);
		List<Map>list = OracleTool.query(sql);
		return list;
	}
	
}
