package com.frame.fastframelibrary.utils.view;

import android.content.Context;
import android.widget.Toast;

import com.frame.fastframelibrary.utils.dataprocess.StringUtils;

public class ToastUtils {

	public static void showToast(Context context, String msg){
		showToast(context, msg,false);
	}
	public static void showToast(Context context, int strgResouceId){
		showToast(context, strgResouceId,false);
	}
	public static void showToast(Context context, String msg, boolean isLongTime){
		if(context!=null&& StringUtils.isNotEmpty(msg)){
			Toast.makeText(context, msg, isLongTime? Toast.LENGTH_LONG: Toast.LENGTH_SHORT).show();
		}
	}
	public static void showToast(Context context, int strgResouceId, boolean isLongTime){
		if(context!=null&&strgResouceId>0){
			String msg =null;
			try {
				msg = context.getString(strgResouceId);
			} catch (Exception e) {
			}
			if(StringUtils.isNotEmpty(msg)){
				Toast.makeText(context, context.getString(strgResouceId), isLongTime? Toast.LENGTH_LONG: Toast.LENGTH_SHORT).show();
			}
		}
	}
}
