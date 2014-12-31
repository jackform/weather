package com.weather.utils;

import java.util.ArrayList;
import com.weather.model.ProvinceInfo;
public class ProvinceHeadNoUtils {

	private static ArrayList<ProvinceInfo> mProvinceQuery;
	private static int mCurrentNo = 0;
	static {
		String province[] = new String [] {"0","北京", "21","上海", "36","天津", 
										"50","重庆", "87","黑龙江", "171","吉林", 
										"225","辽宁", "290","内蒙古", "422","河北", 
										"570","山西", "681","陕西", "780","山东", 
										"907","新疆", "1018","西藏", "1059","青海", 
										"1121","甘肃", "1207","宁夏", "1233","河南",
										"1357","江苏", "1438","湖北", "1520","浙江", 
										"1598","安徽", "1680","福建", "1753","江西", 
										"1845","湖南", "1947","贵州", "2036","四川", 
										"2203","广东", "2302","云南", "2438","南宁", 
										"2537","海南", "2560","香港", "2565","澳门", 
										"2566","台湾"};
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
			if( position == p.getHeaderCityNo() ) {
				
				res = true;
				break;
			}
			mCurrentNo++;
		}
		return res;
	}
}
