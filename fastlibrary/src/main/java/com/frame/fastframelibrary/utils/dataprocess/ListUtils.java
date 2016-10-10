package com.frame.fastframelibrary.utils.dataprocess;

import com.frame.fastframelibrary.utils.LogUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUtils {
	public static boolean isEmpty(List<?> list){
		if (list!=null&&list.size()>0) {
			return false;
		}
		return true;
	}
	public static boolean isNotEmpty(List<?> list){
		return !isEmpty(list);
	}

	public static <T> T getData(List<T> list,int index){
		if (!isEmpty(list)) {
			if (list.size()>index) {
				return list.get(index);
			}
		}
		return null;
	}

	public static <T> ArrayList<T> set2ArrayList(Set<T> set){
		if (set!=null&&set.size()>0) {
			try {
				ArrayList<T> list = new ArrayList<T>(set);
				return list;
			} catch (Exception e) {
				LogUtils.e(e);
			}
		}
		return null;
	}
	public static boolean isEmptyArray(Object[] objs){
		if(objs == null ||objs.length == 0){
			return true ;
		}
		return false;
	}
	/**
	 * 判断是否为空
	 * @param objList
	 * @return
	 */
	public static boolean isEmpty(Object... objList){
		if(objList == null ||objList.length == 0){
			return true ;
		}
		return false;
	}
	public static boolean isNotEmpty(Object... objList){
		return !isEmpty(objList);
	}

	public static boolean isEmptySet(HashSet target){
		if( target == null ||
				target.size() == 0 ){
			return true ;
		}
		return false ;
	}
	public static  <T> ArrayList<T> buildList(T... objList){
		if (objList!=null&&objList.length>0) {
				ArrayList<T> list = new ArrayList<T>();
				for (int i = 0; i <objList.length; i++) {
					list.add(objList[i]);
				}
				return list;
		}
		return null;
	}

	/**
	 * 泛型ArrayList转数组
	 */
	public static <T> T[] toArray(Class<?> cls, ArrayList<T> items) {
		if (items == null || items.size() == 0) {
			return (T[]) Array.newInstance(cls, 0);
		}
		return items.toArray((T[]) Array.newInstance(cls, items.size()));
	}

	/**
	 * 泛型数组转ArrayList
	 */
	public static  <T> ArrayList<T> toList(Class<?> cls,T[] array){
		if(isEmptyArray(array)){
			return new ArrayList<T>();
		}
		return new ArrayList<>(Arrays.asList(array));
	}
}
