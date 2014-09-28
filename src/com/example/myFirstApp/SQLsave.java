package com.example.myFirstApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class SQLsave {
	public static final String KEY_ROWID = "_id" ;
	public static final String KEY_NAME = "persons_name" ;
	
	private static final String DATABASE_NAME = "Firstdb" ;
	private static final String DATABASE_TABLE = "peopleTable" ;
	private static final int	DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE "+DATABASE_TABLE+ " (" +
					KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
					KEY_NAME+" TEXT NOT NULL);"					
					);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public SQLsave(Context c){
		ourContext = c;
	}
	
	public SQLsave open() throws SQLException{
		ourHelper = new DbHelper(ourContext, DATABASE_NAME, null, DATABASE_VERSION);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
		
	}
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String name) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			result = result + c.getString(iRow) + "  " + c.getString(iName)+ "  \n";
		}
		return result;
	}
	
}

