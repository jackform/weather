package com.weather.utils;

import java.util.ArrayList;
import com.weather.model.ProvinceInfo;
public class ProvinceHeadNoUtils {

	private static ArrayList<ProvinceInfo> mProvinceQuery;
	private static int mCurrentNo = 0;
	static {
		String province[] = new String [] {"1","北京", "22","上海", "37","天津", 
										"51","重庆", "88","黑龙江", "172","吉林", 
										"226","辽宁", "291","内蒙古", "423","河北", 
										"571","山西", "682","陕西", "781","山东", 
										"908","新疆", "1019","西藏", "1060","青海", 
										"1122","甘肃", "1208","宁夏", "1234","河南",
										"1358","江苏", "1439","湖北", "1521","浙江", 
										"1599","安徽", "1681","福建", "1754","江西", 
										"1846","湖南", "1948","贵州", "2037","四川", 
										"2204","广东", "2303","云南", "2439","南宁", 
										"2536","海南", "2561","香港", "2566","澳门", 
										"2567","台湾"};
		mProvinceQuery = new ArrayList<ProvinceInfo>();
		ProvinceInfo p;
		for(int i=0;i<province.length;i+=2){
			p = new ProvinceInfo();
			p.setHeaderCityNo(Integer.valueOf(province[i]));
			p.setName(province[i+1]);
			mProvinceQuery.add(p);
		}
	}
	
	public static void reset()
	{
		mCurrentNo = 0;
	}
	
	public static int getHeadCityNo(int position)
	{
		return mProvinceQuery.get(position).getHeaderCityNo();
	}
	
	public static String getProvinceName()
	{
		return mProvinceQuery.get(mCurrentNo).getName();
	}
	public static void next()
	{
		mCurrentNo++;
	}

	public static boolean isHeadCity(int position) {
		boolean res = false;
		mCurrentNo = 0;
		for(ProvinceInfo p : mProvinceQuery) {
			if( position == p.getHeaderCityNo()-1 ) {
				
				res = true;
				break;
			}
			mCurrentNo++;
		}
		return res;
	}
}
