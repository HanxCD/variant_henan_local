package com.boco.soap.variant.henan.local.nsnba.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.soap.check.standvalue.valueinvoke.impl.DataQueryUtils;
import com.boco.soap.check.standvalue.valueinvoke.impl.VariantValueInvoke;
import com.boco.soap.common.pojo.INeElement;
import com.boco.soap.common.pojo.solution.IInstructionParameter;
/**
 * 
 * @author zy
 * tree分析
 *
 */
public class DIG extends VariantValueInvoke{
	private Map<String,String> map = null;
	private Map<String,String> map1 = null;
	private Map<String,String> mapRC = null;
	Object[] arr=null;
	Object[] arr1=null;
	StringBuilder mobBigNum=null;
	StringBuilder telBigNum=null;
	StringBuilder uniBigNum=null;
	StringBuilder shortNum=null;
	StringBuilder roamP=null;
 	
	
	@Override
	public String[] getValues(INeElement arg0, IInstructionParameter arg1,
			Map<String, ?> data, String dbFile) {	
		Object o = data.get("PREFIX");
		String PREFIX=null;
		if(null == o){
			 PREFIX="";
		} else {
			PREFIX = o.toString();
		}
		Object o1=data.get("ELE_NAME");
		String ELE_NAME = null;
		if(null == o1){
			return new String[]{"[NULL]"};
		} else {
			ELE_NAME = o1.toString();
		}
		String o2=arg0.getCorverAddr();
		String[] sCity = null;
		if(null == o2){
			return new String[]{"[NULL]"};
		} else {
			sCity=o2.split(",");
		}
		Object o4 = data.get("START_GT");
		String DIG=null;
		if(null == o4){
			return new String[]{"[NULL]"};
		} else {
			DIG = o4.toString();
		}
		if(PREFIX.contains("本地区号(不带0)")&&map==null){
			this.getAreaCode( sCity, PREFIX,dbFile);
		}
		if(PREFIX.contains("本地区号(带0)")&&map1==null){
			this.getAreaCode( sCity, PREFIX,dbFile);
		}
		if(mapRC==null){
			mapRC=new HashMap<String,String>();
		}
		
		if(PREFIX.contains("外地区号(不带0)")){
			this.getAreaCode( sCity, PREFIX,dbFile);
		}
		if(PREFIX.contains("外地区号(带0)")){
			this.getAreaCode( sCity, PREFIX,dbFile);
		}
		if(DIG.equals("移动大号段")&&mobBigNum==null){
			this.getMobBigNum( sCity, PREFIX,dbFile);
		}
		if(DIG.equals("电信大号段")&&telBigNum==null){
			this.getTelBigNum( sCity, PREFIX,dbFile);
		}
		if(DIG.equals("联通大号段")&&uniBigNum==null){
			this.getUniBigNum( sCity, PREFIX,dbFile);
		}
		if(DIG.equals("短号码")&&shortNum==null){
			this.getShortNum( sCity, PREFIX,dbFile);
		}
		if(DIG.equals("本地")){
			StringBuilder s=new StringBuilder();
			int j=sCity.length;
			for(int i=0;i<j;i++){
				if(mapRC.get(sCity[i])==null){
					this.getRoamC(sCity[i].toString(),PREFIX,dbFile);
				}
				s.append(mapRC.get(sCity[i]));
				if(i<j-1){
					s.append(",");
				}
			}
			return s.toString().split(",");
		}
		if(DIG.equals("本省号段")&&roamP==null){
			this.getRoamP( sCity, PREFIX,dbFile);
		}
		if(PREFIX.contains("本地区号(带0)")&&DIG.contains("本地区号")){
			if(sCity.length>1){
				StringBuilder s=new StringBuilder();
				int j=sCity.length;
				for(int i=0;i<j;i++){
					s.append(map1.get(sCity[i].toString()));
					if(i<j-1){
						s.append(",");
					}
				}
				return s.toString().split(",");
			}
			return new String[]{map1.get(sCity[0].toString())};
		}
		if(PREFIX.contains("外地区号(不带0)")&&!DIG.contains("号")&&!DIG.contains("本")){
			return getParam(DIG);
		}
		if(PREFIX.contains("本地区号(不带0)")&&DIG.equals("移动大号段")){
			return getNative(DIG,sCity);
		}else if(PREFIX.equals("本地区号(带0)")&&DIG.equals("移动大号段")){
			return getNative1(DIG,sCity);
		}else if(PREFIX.equals("外地区号(不带0)")&&DIG.equals("移动大号段")){
			return getNonlocal(DIG);
		}else if(PREFIX.equals("外地区号(带0)")&&DIG.equals("移动大号段")){
			return getNonlocal1(DIG);
		}else if(PREFIX.contains("任意数字")&&DIG.equals("联通大号段")){
			return getUni(DIG);
		}else if(!PREFIX.contains("区号")&&!PREFIX.contains("任意")&&DIG.equals("电信大号段")){
			return getTel(sCity,PREFIX,dbFile);
		}else if(DIG.equals("短号码")){
			return getS(sCity,PREFIX,dbFile);
		}else if(DIG.equals("本地")){
			return getRC(sCity,PREFIX,dbFile);
		}else if(DIG.equals("本省号段")){
			return getRP(sCity,PREFIX,dbFile);
		}else if(!PREFIX.contains("区号")&&!PREFIX.contains("任意")&&!DIG.contains("号")&&!DIG.contains("本")
				&&!DIG.contains("%")&&!DIG.contains("&")){
			return new String[]{PREFIX+DIG};
		}
		
		return new String[]{"[NULL]"};
		
	}
	
	private String[] getParam(String dIG) {
		// TODO Auto-generated method stub
		StringBuilder sbarr=new StringBuilder();
		for(int i=0;i<arr.length;i++){
				sbarr.append(arr[i].toString()).append(dIG);
				if(i<arr.length-1){
					sbarr.append(",");
		}}
		return sbarr.toString().split(",");
	}

	private String[] getRP(Object[] sCity, String pREFIX, String dbFile) {
		// TODO Auto-generated method stub
		String[] arr=roamP.toString().split(",");
		StringBuilder sbarr=new StringBuilder();
		for(int i=0;i<arr.length;i++){
			sbarr.append(arr[i]);
			if(i<arr.length-1){
				sbarr.append(",");
			}
		}
		return sbarr.toString().split(",");
		
	}

	private String[] getRC(Object[] sCity, String PREFIX, String dbFile) {
		// TODO Auto-generated method stub
		StringBuilder s=new StringBuilder();
		int j=sCity.length;
		for(int i=0;i<j;i++){
			if(mapRC.get(sCity[i])==null){
				this.getRoamC(sCity[i].toString(),PREFIX,dbFile);
			}
			s.append(mapRC.get(sCity[i]));
			if(i<j-1){
				s.append(",");
			}
		}
		return s.toString().split(",");
	}

	private String[] getS(Object[] sCity, String PREFIX, String dbFile) {
		// TODO Auto-generated method stub
		String[] arr=shortNum.toString().split(",");
		StringBuilder sbarr=new StringBuilder();
		for(int i=0;i<arr.length;i++){
			sbarr.append(PREFIX).append(arr[i]);
			if(i<arr.length-1){
				sbarr.append(",");
			}
		}
		return sbarr.toString().split(",");
	}

	private String[] getTel(Object[] sCity, String PREFIX, String dbFile) {
		// TODO Auto-generated method stub
		String[] arr=telBigNum.toString().split(",");
		StringBuilder sbarr=new StringBuilder();
		for(int i=0;i<arr.length;i++){
			sbarr.append(PREFIX).append(arr[i]);
			if(i<arr.length-1){
				sbarr.append(",");
			}
		}
		return sbarr.toString().split(",");
	}

	private String[] getUni(String dIG) {
		// TODO Auto-generated method stub
		String[] arr=uniBigNum.toString().split(",");
		StringBuilder sbarr=new StringBuilder();
		for(int i=0;i<arr.length;i++){
			sbarr.append("12593").append(arr[i]);
			if(i<arr.length-1){
				sbarr.append(",");
			}
		}
		return sbarr.toString().split(",");
	}

	private void getRoamP(Object[] sCity, String pREFIX, String dbFile) {
		// TODO Auto-generated method stub
		roamP=new StringBuilder();
		DataQueryUtils util = DataQueryUtils.getInstance();
		String sql ="SELECT START_GT FROM TCM_ROAM_NK_LIST WHERE PROVINCE='河南'" ;
		List<Map<String, ?>> resultList = util.getLocalData(sql, dbFile);
		if(resultList!=null && resultList.size()>0){
		int j=resultList.size();
		for(int i=0;i<j;i++){
			roamP.append(pREFIX).append(resultList.get(i).get("START_GT").toString());
			if(i<j-1){
				roamP.append(",");
				}
			}}
	}

	private void getRoamC(String sCity, String pREFIX, String dbFile) {
		// TODO Auto-generated method stub
		StringBuilder roamC=new StringBuilder();
		DataQueryUtils util = DataQueryUtils.getInstance();
		String sql = "SELECT START_GT,BUSI_CITY FROM TCM_ROAM_NK_LIST WHERE BUSI_CITY='"+sCity+"'";
		List<Map<String, ?>> resultList = util.getLocalData(sql, dbFile);
		if(resultList!=null && resultList.size()>0){
			int j=resultList.size();
			for(int i=0;i<j;i++){
				roamC.append(pREFIX).append(resultList.get(i).get("START_GT").toString());
				if(i<j-1){
					roamC.append(",");
					}
				if(i==j-1){
					mapRC.put(resultList.get(i).get("BUSI_CITY").toString(), roamC.toString());
				}
				} 
		}
	}

	private void getShortNum(Object[] sCity, String pREFIX, String dbFile) {
		// TODO Auto-generated method stub
		shortNum=new StringBuilder();
		DataQueryUtils util = DataQueryUtils.getInstance();
		String sql = "SELECT START_GT FROM TCM_LOCAL_SHORTNUMBER_LIST WHERE PROVINCE='河南' ";
		List<Map<String, ?>> resultList = util.getLocalData(sql, dbFile);
		if(resultList!=null && resultList.size()>0){
		int j=resultList.size();
		for(int i=0;i<j;i++){
			shortNum.append(resultList.get(i).get("START_GT").toString());
			if(i<j-1){
				shortNum.append(",");
				}
			}}
	}

	private void getUniBigNum(Object[] sCity, String pREFIX, String dbFile) {
		// TODO Auto-generated method stub
		uniBigNum=new StringBuilder();
		DataQueryUtils util = DataQueryUtils.getInstance();
		String sql = "SELECT START_GT FROM TCM_LOCAL_BIGNUM_LIST WHERE COMP_NAME='联通'";
		List<Map<String, ?>> resultList = util.getLocalData(sql, dbFile);
		if(resultList!=null && resultList.size()>0){
		int j=resultList.size();
		for(int i=0;i<j;i++){
			uniBigNum.append(resultList.get(i).get("START_GT").toString());
			if(i<j-1){
				uniBigNum.append(",");
				}
			}}
	}

	private void getTelBigNum(Object[] sCity, String pREFIX, String dbFile) {
		// TODO Auto-generated method stub
		telBigNum=new StringBuilder();
		DataQueryUtils util = DataQueryUtils.getInstance();
		String sql = "SELECT START_GT FROM TCM_LOCAL_BIGNUM_LIST WHERE COMP_NAME='电信'";
		List<Map<String, ?>> resultList = util.getLocalData(sql, dbFile);
		if(resultList!=null && resultList.size()>0){
		int j=resultList.size();
		for(int i=0;i<j;i++){
			telBigNum.append(resultList.get(i).get("START_GT").toString());
			if(i<j-1){
				telBigNum.append(",");
				}
			} }}
	

	private void getMobBigNum(Object[] sCity, String pREFIX, String dbFile) {
		mobBigNum=new StringBuilder();
		DataQueryUtils util = DataQueryUtils.getInstance();
		String sql = "SELECT START_GT FROM TCM_LOCAL_BIGNUM_LIST WHERE COMP_NAME='移动'";
		List<Map<String, ?>> resultList = util.getLocalData(sql, dbFile);
		if(resultList!=null && resultList.size()>0){
		int j=resultList.size();
		for(int i=0;i<j;i++){
			mobBigNum.append(resultList.get(i).get("START_GT").toString());
			if(i<j-1){
				mobBigNum.append(",");
				}
			} 
		}
		}
		

	private void getAreaCode(Object[] sCity,String PREFIX,String dbFile){
		if(PREFIX.contains("外地区号(不带0)")&&map==null){
			map = new HashMap<String,String>();
			DataQueryUtils util = DataQueryUtils.getInstance();
			String sql = "SELECT BUSI_CITY,CMCC_AREA FROM AREA_CODE_LIST WHERE PROVINCE='河南'";
			List<Map<String, ?>> resultList = util.getLocalData(sql, dbFile);
			if(resultList!=null && resultList.size()>0){
			int j=resultList.size();
			for(int i=0;i<j;i++){	
				map.put(resultList.get(i).get("BUSI_CITY").toString(), 
						resultList.get(i).get("CMCC_AREA").toString());
					}		
				}
			}
		if(PREFIX.contains("外地区号(不带0)")&&map!=null){
			Map<String,String> mapb=map;
			int j=sCity.length;
			for(int i=0;i<j;i++){
				mapb.remove(sCity[i]);
			}
			arr=mapb.values().toArray();
		}
		
		if(PREFIX.contains("外地区号(带0)")&&map1==null){
			map1 = new HashMap<String,String>();
			DataQueryUtils util = DataQueryUtils.getInstance();
			String sql = "SELECT BUSI_CITY,CMCC_AREA FROM AREA_CODE_LIST WHERE PROVINCE='河南'";
			List<Map<String, ?>> resultList = util.getLocalData(sql, dbFile);
			if(resultList!=null && resultList.size()>0){
			int j=resultList.size();
			for(int i=0;i<j;i++){
				map1.put(resultList.get(i).get("BUSI_CITY").toString(), 
						"0"+resultList.get(i).get("CMCC_AREA").toString());
			}}}
		
		if(PREFIX.contains("外地区号(带0)")&&map1!=null){
			Map<String,String> mapw=map1;
			int j=sCity.length;
			for(int i=0;i<j;i++){
				mapw.remove(sCity[i]);
			}
			arr1=mapw.values().toArray();	
		}
		
		if(PREFIX.contains("本地区号(不带0)")&&map==null){
			map = new HashMap<String,String>();
			DataQueryUtils util = DataQueryUtils.getInstance();
			String sql = "SELECT BUSI_CITY,CMCC_AREA FROM AREA_CODE_LIST WHERE PROVINCE='河南'";
			List<Map<String, ?>> resultList = util.getLocalData(sql, dbFile);
			if(resultList!=null && resultList.size()>0){
			int j=resultList.size();
			for(int i=0;i<j;i++){
				map.put(resultList.get(i).get("BUSI_CITY").toString(), 
						resultList.get(i).get("CMCC_AREA").toString());
			}}
			return;
		}
		if(PREFIX.contains("本地区号(带0)")&&map1==null){
			map1 = new HashMap<String,String>();
			DataQueryUtils util = DataQueryUtils.getInstance();
			String sql = "SELECT BUSI_CITY,CMCC_AREA FROM AREA_CODE_LIST WHERE PROVINCE='河南'";
			List<Map<String, ?>> resultList = util.getLocalData(sql, dbFile);
			int j=resultList.size();
			for(int i=0;i<j;i++){
				map1.put(resultList.get(i).get("BUSI_CITY").toString(), 
						"0"+resultList.get(i).get("CMCC_AREA").toString());
			}
			return;
		}
	}
	
	private String[] getNative(String dig,Object[] sCity){
		String[] arr=mobBigNum.toString().split(",");
		StringBuilder sbarr=new StringBuilder();
		for(int j=0;j<sCity.length;j++){
			String area_code=map.get(sCity[j].toString());
			for(int i=0;i<arr.length;i++){
				sbarr.append(area_code).append(arr[i]);
				if(i<arr.length-1){
					sbarr.append(",");
				}
				if(i==arr.length-1&&j<sCity.length-1){
					sbarr.append(",");
				}
			}
		}
		return sbarr.toString().split(",");
	}
	
	private String[] getNative1(String dig,Object[] sCity){
		String[] arr=mobBigNum.toString().split(",");
		StringBuilder sbarr=new StringBuilder();
		for(int j=0;j<sCity.length;j++){
			String area_code=map1.get(sCity[j].toString());
			for(int i=0;i<arr.length;i++){
				sbarr.append(area_code).append(arr[i]);
				if(i<arr.length-1){
					sbarr.append(",");
				}
				if(i==arr.length-1&&j<sCity.length-1){
					sbarr.append(",");
				}
			}
		}
		return sbarr.toString().split(",");
	}
	
	
	private String[] getNonlocal(String dig){
		String[] arrNum=mobBigNum.toString().split(",");
		StringBuilder sbarr=new StringBuilder();
		for(int i=0;i<arr.length;i++){
			String s=arr[i].toString();
			for(int j=0;j<arrNum.length;j++){
				sbarr.append(s).append(arrNum[j]);
				if(i<arr.length-1){
					sbarr.append(",");
				}
				if(i==arr.length-1&&j<arrNum.length-1){
					sbarr.append(",");
				}
			}
		}
		return sbarr.toString().split(",");
	}
	
	private String[] getNonlocal1(String dig){
		String[] arrNum=mobBigNum.toString().split(",");
		StringBuilder sbarr=new StringBuilder();
		for(int i=0;i<arr1.length;i++){
			String s=arr1[i].toString();
			for(int j=0;j<arrNum.length;j++){
				sbarr.append(s).append(arrNum[j]);
				if(i<arr1.length-1){
					sbarr.append(",");
				}
				if(i==arr1.length-1&&j<arrNum.length-1){
					sbarr.append(",");
				}
			}
		}
		return sbarr.toString().split(",");
	}
	
	
	
	
	


}
