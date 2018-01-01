package com.et.model;

import java.util.List;
import java.util.Map;

import com.et.tool.OracleTool;
import com.et.tool.PageTool;

public class DeskModel {
	/**
	 * ��ȡ���� 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static Integer getTableListCount(String name) throws Exception{
		if(name==null){
			name="";
		}
		String sql = "select count(rowid) as CR from  DESK where DNAME  like '%"+name+"%'";
		System.out.println(sql);
		List<Map>list = OracleTool.query(sql);
		//����list�еȵ�������map ���� ����֪�� ��ȡ map�е�ֵ ��ͨ����  ��ȡlist�е�Ԫ����ͨ���±� ����
		return Integer.parseInt(list.get(0).get("CR").toString()) ;
	}
	
	
	public static PageTool getTableLsitPager(String name,Integer curPage) throws Exception{
		if(name==null){
			name="";
		}
		Integer count = DeskModel.getTableListCount(name);
		System.out.println(count);
		//������� ��ǰҳ�� �ܸ�����ÿҳ��ʾ����
		PageTool pt = new PageTool(curPage, count,null);
		String sql="select * from(select rownum rn,t.* from DESK t where DNAME like '%"+name+"%')" +
				"where rn >="+ pt.getStartIndex() +" and  rn <="+pt.getEndIndex();
		System.out.println(sql);
		List<Map> list =OracleTool.query(sql);
		pt.setData(list);
		return pt;
		
		
	}
	
	public static void saveDesk(String deskName) throws Exception{
		String sql="insert into desk values((select max(deskid)+1 from desk),'"+deskName+"',0,'')";
		System.out.println(sql);
		int count =OracleTool.execute(sql);
		
	}
	public static void deleteDesk(String deskid) throws Exception{
		String sql="delete from desk  where deskid = "+deskid ;
		System.out.println(sql);
		int count =OracleTool.execute(sql);
		
	}
}
