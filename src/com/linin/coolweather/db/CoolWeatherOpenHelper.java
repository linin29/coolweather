package com.linin.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

	/** 创建省表语句 */
	public static final String CREATE_PROVINCE = "CREATE TABLE Province (" + "id integer primary key autoincrement, " + "province_name text, "
			+ "province_code text)";
	/** 创建市表语句 */
	public static final String CREATE_CITY = "CREATE TABLE City (" + "id integer primary key autoincrement, " + "city_name text, "
			+ "city_code text, " + "province_id integer)";
	/** 创建县表语句 */
	public static final String CREATE_COUNTY = "CREATE TABLE County (" + "id integer primary key autoincrement, " + "county_name text, "
			+ "county_code text, " + "city_id integer)";

	public CoolWeatherOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建Province表
		db.execSQL(CREATE_PROVINCE);
		// 创建City表
		db.execSQL(CREATE_CITY);
		// 创建County表
		db.execSQL(CREATE_COUNTY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
