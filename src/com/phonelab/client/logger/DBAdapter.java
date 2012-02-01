package com.phonelab.client.logger;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	private SQLiteDatabase mDb;
    private static final String DATABASE_NAME = "db";
    private static final String UPLOADTIME = "uploadTime";
    private static final String PROCESSID = "pid";

    public DBAdapter(Context context) {
        mDb = context.openOrCreateDatabase(DATABASE_NAME, 0, null);
                
        // Last updated Time
        mDb.execSQL("CREATE TABLE IF NOT EXISTS " +
					UPLOADTIME +
					" (_id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp BIGINT);"
					);
        // Process ID of Logcat
        mDb.execSQL("CREATE TABLE IF NOT EXISTS " +
				PROCESSID +
				" (_id INTEGER PRIMARY KEY AUTOINCREMENT, pid INTEGER);"
				);
        
    }
    
    public int getLastPID() {
    	String command = "SELECT pid FROM " + PROCESSID + " ORDER BY pid DESC LIMIT 0,1";
    	Cursor c = mDb.rawQuery(command, null);
    	int res = 0;
    	if (c != null ) {
			if  (c.moveToFirst()) {
				res = c.getInt(c.getColumnIndex("pid"));
			}
    	}
    	return res;
    }
    
    public void setLastPID(int pid) {
    	String command = "INSERT OR REPLACE INTO " + PROCESSID + " (_id,pid) VALUES (1,"+ pid +")";
    	mDb.execSQL(command);
    }
    
    
    public long getLastUpdateTime() {
    	String command = "SELECT time FROM " + UPLOADTIME + " ORDER BY time DESC LIMIT 0,1";
    	Cursor c = mDb.rawQuery(command, null);
    	long res = 0L;
    	if (c != null ) {
			if  (c.moveToFirst()) {
				res = c.getLong(c.getColumnIndex("timestamp"));
			}
    	}
    	return res;
    }
    
    public void setLastUpdateTime(long timestamp) {
    	String command = "INSERT OR REPLACE INTO " + UPLOADTIME + " (_id,timestamp) VALUES (1,"+ timestamp +")";
    	mDb.execSQL(command);
    }
    
    public void closeDB(){
    	mDb.close();
    }
    
//    public LogFile getLastUpdateTime(String filename) {
//    	String command = "SELECT * FROM " + STATS + " WHERE file_name = '" + filename + "'";
//    	Cursor c = mDb.rawQuery(command, null);
//    	LogFile logfile = null;
//    	if (c != null ) {
//			if  (c.moveToFirst()) {
//				logfile = new LogFile(c.getString(c.getColumnIndex("file_name")), c.getLong(c.getColumnIndex("file_size")), c.getLong(c.getColumnIndex("timestamp")));
//			}
//    	}
//    	return logfile;
//    }
//    
//    public void setLastUpdateTime(LogFile logfile) {
//    	String command = "INSERT OR REPLACE INTO " + STATS + " (file_name, file_size, timestamp) VALUES ('" + logfile.getFileName() + "', " + logfile.getFileSize() + ", " + logfile.getTimestamp() + ")";
//    	mDb.execSQL(command);
//    }    
}
