package com.et.model;

import java.util.List;
import java.util.Map;

import com.et.tool.OracleTool;
import com.et.tool.PageTool;

public class Food {
	public static Integer getFoodCount(String name) throws Exception{
		if(name==null){
			name="";
		}
		String sql = "select count(rowid) as CR from  FOOD where FOODNAME  like '%"+name+"%'";
		System.out.println(sql);
		List<Map>list = OracleTool.query(sql);
		//����list�еȵ�������map ���� ����֪�� ��ȡ map�е�ֵ ��ͨ����  ��ȡlist�е�Ԫ����ͨ���±� ����
		return Integer.parseInt(list.get(0).get("CR").toString()) ;
	}
	
	
	public static PageTool getFoodPager(String name,Integer curPage) throws Exception{
		if(name==null){
			name="";
		}
		Integer count = Food.getFoodCount(name);
		System.out.println(count);
		//������� ��ǰҳ�� �ܸ�����ÿҳ��ʾ����
		PageTool pt = new PageTool(curPage, count,null);
		String sql="select * from(select rownum rn,t.* from FOOD t where FOODNAME like '%"+name+"%')" +
				"where rn >="+ pt.getStartIndex() +" and  rn <="+pt.getEndIndex();
		
		List<Map> list =OracleTool.query(sql);
		pt.setData(list);
		return pt;
		
		
	}
	
	public static void saveFood(String FoodNAME,String price,String TYPEID,String imgPath) throws Exception{
		String sql="insert into FOOD values((select max(FOODID)+1 from FOOD),'"+TYPEID+"','"+FoodNAME+"','"+price+"','"+imgPath+"')";
		System.out.println(sql);
		int count =OracleTool.execute(sql);
		
	}
	public static void deleteFoodType(String FOODID) throws Exception{
		String sql="delete from FOOD where FOODID = "+FOODID ;
		
		int count =OracleTool.execute(sql);
		
	}
	public static void updateFood(String FOODID,String FOODNAME,String TYPEID,String PRICE,String IMAGEPATH) throws Exception{
	
		String sql="update Food SET FOODNAME ='"+FOODNAME+"',TYPEID='"+TYPEID+"',PRICE='"+PRICE+"',IMGPATH='"+IMAGEPATH+"' WHERE FOODID = "+FOODID ;
		System.out.println("�޸Ĳ�"+sql);
		int count =OracleTool.execute(sql);
		
	}
	public List<Map> getFoodList() throws Exception{
		String sql = "select * from foodtype";
		return OracleTool.query(sql);
	}
	 
}
