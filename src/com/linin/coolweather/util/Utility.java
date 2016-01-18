package com.linin.coolweather.util;

import android.text.TextUtils;

import com.linin.coolweather.db.CoolWeatherDB;
import com.linin.coolweather.model.City;
import com.linin.coolweather.model.County;
import com.linin.coolweather.model.Province;

public class Utility {

	/**
	 * 处理服务器返回的省级数据
	 * 
	 * @param coolWeatherDB
	 * @param response
	 * @return
	 */
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String provinceInfo : allProvinces) {
					String[] array = provinceInfo.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					// 保存到数据库
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取服务器返回的市级数据
	 * 
	 * @param coolWeatherDB
	 * @param response
	 * @param provinceId
	 * @return
	 */
	public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String cityInfo : allCities) {
					String[] array = cityInfo.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					// 保存到数据库
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}

	public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String countyInfo : allCounties) {
					String[] array = countyInfo.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					// 保存到数据库
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
