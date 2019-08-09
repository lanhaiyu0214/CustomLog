/*
 * Copyright (C) Appotronics
 * Author name:
 *		Lanhaiyu
 * Author Email:
 *      306740439@qq.com
 * Create Time:
 * 		2018年6月21日 下午2:25:47 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package com.appo.commonlib;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.content.Context;
import android.util.Log;

public class CustomLog {
	/*
	 * When you user this log class, Must do the below init things:
	 * 
	 *    CustomLog.setSearchKey(String);
	 *    CustomLog.getAppDataPath(Context);
	 */
	private static boolean isOpen=true;
	private static boolean isSave=false;

	private static String searchKey="NotInit";

	private static String LogPath = null;
	private static Object mObject = new Object();
	private static String preKey = "----";
	
	/*******************************************************************
	 * Called when you want to change the switch state.
	 * 
	 * The default state is open.
	 * 
	 * @param open Control the global switch
	 */
	public static void setOpen(boolean open) {
		isOpen = open;
	}
	
	/*******************************************************************
	 * Must call this for the init.
	 * Just need init for one time.
	 * Tell the jar lib what is the search key. 
	 * 
	 * @param key The global key ,by filter which you can see all the global logs
	 */
	
	public static void setSearchKey(String key) {
		CustomLog.searchKey = key;
	}
	/*******************************************************************
	 * Call this when model want to save log to Disk.
	 * Just need init for one time.
	 * lib will not save logs for default
	 * 
	 * @param c The context
	 * 
	 * Log saving path:/storage/emulated/0/Android/data/"package name"/files/logs
	 */
	public static void enableDiskSaved(Context c){
		if(c !=null){
			LogPath = c.getExternalFilesDir(null).getPath() + "/logs"; 
			isSave = true;
			Log.d("CustomLog", "LogPath =" + LogPath + preKey + searchKey);
		}
		
	}
	/*******************************************************************
	 * The same use with Log.d
	 * 
	 * @param TAG The TAG
	 * @param s The message need to print
	 */
	public static void d(String TAG,String s){
		if (isOpen) {
			Log.d(TAG, s + preKey +searchKey);
			writeToFileForAppend(LogPath, getTime() + ": TAG " +TAG + ": "+ s, TAG);
		}
	}
	/*******************************************************************
	 * The same use with Log.e
	 * 
	 * @param TAG The TAG
	 * @param s The message need to print
	 */
	public static void e(String TAG,String s){
		if (isOpen) {
			Log.e(TAG, s + preKey + searchKey);
			writeToFileForAppend(LogPath, getTime() + ": TAG " +TAG + ": "+ s, TAG);
		}
	}
	
	/*******************************************************************
	 * Called when you want to print bytes array
	 * 
	 * @param TAG The TAG
	 * @param bytes The bytes array.
	 * 
	 * @return the final generate Strings
	 */
	public static String printByteArray(String TAG,byte[] bytes){
		if (isOpen) {
			String result ="byte[]:";
			for (int i =0 ;i < bytes.length; i++){
				if(i == bytes.length-1){
					result = result + UtilFuc.byteToHexString(bytes[i]) + ".";
				}else{
					result = result + UtilFuc.byteToHexString(bytes[i]) + " ";
				}
			}
			Log.d(TAG, result + preKey + searchKey);
			writeToFileForAppend(LogPath, getTime() + ": TAG " +TAG + ": printByteArray:"+ result, TAG);
			return result;
		}
		return "";
	}
	/*******************************************************************
	 * Called when you want to print function called stack
	 * 
	 * @param TAG The TAG
	 * @param where Where the function is called.
	 */
	public static void printStack(String TAG, String where) {
		Log.w(TAG, "======= Print StackTrace from: " + where + preKey +searchKey + " =======");
    	StackTraceElement[] ele = Thread.currentThread().getStackTrace();
    	for (int i = 0; i < ele.length; i++) {
    	  if((i != 0) && (i != 1)){
    		  Log.i(TAG, ele[i].getClassName() + "." + ele[i].getMethodName() + preKey +searchKey);
    	  }
    	}
    	Log.w(TAG, "======= Finish print StackTrace" + preKey +searchKey + " =======");
	}
	private static String getTime(){
		long begin = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		String hms = formatter.format(begin);
		return hms;
	}
	
	private static void writeToFileForAppend(String fileName,String value, String TAG) {
		if(fileName == null){
			//Log.e(TAG, "Do not init the log path!");
			return;
		}
		if(!isSave){
			return;
		}
		synchronized(mObject){
			try{
				File mFile = new File(fileName);
				/*
				 * Create it if does not exist
				 */
				if(!mFile.exists()){
					//Log.d(TAG, "Mark1");
					boolean rest = mFile.createNewFile();
					//Log.d(TAG, "Mark2");
					if(!rest){
						Log.e(TAG, "Failed to create file!!!" + preKey +searchKey);
						return;
					}
				}
				FileOutputStream out = new FileOutputStream(mFile,true);
				
				String str_final = value + "\r\n";
		        out.write(str_final.getBytes());
		        out.close();

			}catch(Exception e){
				Log.e(TAG, "Error:" + e.toString() + preKey +searchKey);
			}
			
		}
	}
    
}
