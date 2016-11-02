package com.aiyouwai.aphotoalbum.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.aiyouwai.aphotoalbum.entity.Album;

public class UserPreferences {
	
	private static UserPreferences sInstance;
	
	public static synchronized UserPreferences getInstance() {
		if (null == sInstance) {
			sInstance = new UserPreferences();
		}
		return sInstance;
	}

	public void setAlbumShowType(Context context, Album album, int showType) {
		final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
		editor.putInt("album_show_type" + album.getId(), showType);
		editor.commit();
	}

	public int getAlbumShowType(Context context, Album album) {
		final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		return  preferences.getInt("album_show_type" + album.getId(), 0);
	}

	public String getAuth(Context context) {
		final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		String auth = preferences.getString("user_auth", "");
		if (TextUtils.isEmpty(auth)) {
			return "";
		}
		return auth;
	}

	public void saveVpnCountry(Context context, String location) {
		final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
		editor.putString("location", location);
		editor.commit();
	}
	
	public String getVpnCountry(Context context) {
		final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		String location = preferences.getString("location", "CN");
		return location;
	}

	// 保存外网ip
	public void saveOutIp(Context context, String ip) {
		final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
		editor.putString("ip", ip);
		editor.commit();
	}

	public String getOutIp(Context context) {
		final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		String ip = preferences.getString("ip", "");
		return ip;
	}

	/*
	public void saveLocation(Context context, double[] location) {
		final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
		editor.putString("latitude", String.valueOf(location[0]));
		editor.putString("longitude", String.valueOf(location[1]));
		editor.commit();
	}

	public double[] getLocation(Context context) {
		double[] location = new double[2];
		final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		String latitude = preferences.getString("latitude", "0");
		String longitude = preferences.getString("longitude", "0");
		location[0] = Double.parseDouble(latitude);
		location[1] = Double.parseDouble(longitude);
		return location;
	}
	*/
}
