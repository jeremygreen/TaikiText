package com.twobitdesign;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDbAdaptor // extends SQLiteOpenHelper 
{
	/*
	private static String DB_PATH = "/data/data/ShockText/databases/";
	private static String DB_NAME = "contacts";
	private SQLiteDatabase myDataBase;
	private final Context myContext;
	
	public ContactDbAdaptor(Context context)
	{
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}
	
	public void createDataBase() throws IOException
	{	
		boolean dbExist = checkDataBase();	
		if(dbExist) 
		{
		}
		else
		{
			this.getReadableDatabase();
			try
			{
				copyDataBase();
			}
			catch(IOException e)
			{	
				throw new Error("Error copying database");
			}
		}
	}
	
	private boolean checkDataBase()
	{
		SQLiteDatabase checkDB = null;	
		try
		{
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}
		catch(SQLiteException e)
		{
		}
		
		if(checkDB != null)
		{
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}
	
	private void copyDataBase() throws IOException
	{
		InputStream myInput = myContext.getAssets().open(DB_NAME);		
		String outFileName = DB_PATH + DB_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);
		byte[] buffer = new byte[1024];
		int length;
		while((length = myInput.read(buffer))>0)
		{
			myOutput.write(buffer,0,length);
		}		
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
	
	public void openDataBase() throws SQLException
	{
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	}
	
	@Override
	public synchronized void close()
	{
		if(myDataBase != null)
		{
			myDataBase.close();
		}
		super.close();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	*/
	/*
	private static final String DATABASE_NAME = "contacts";
	private String DATABASE_TABLE;
    private static final int DATABASE_VERSION = 1;
    
    public static final String KEY_ID = "_id";
    
    public ArrayList<String> TABLE_KEYS = new ArrayList<String>();
    public ArrayList<String> TABLE_OPTIONS = new ArrayList<String>();
    
    public final String KEY_TIMESTAMP = "timestamp";
    public final int TIMESTAMP_COLUMN = 1;
    
    private String DATABASE_CREATE;
    private SQLiteDatabase db;
    private myDBHelper dbHelper;
    
    public ContactDbAdaptor(Context context, String table, ArrayList<String> keys. ArrayList<String> options)
    {
    	DATABASE_TABLE = table;
    	TABLE_KEYS = (ArrayList<String>)keys.clone();
    	TABLE_OPTIONS = options;
    	
    	String keyString = "";
    	for(int i = 0; TABLE_KEYS.size() >i; i++)
    	{
    		if(i + 1 !=TABLE_OPTIONS.size() && TABLE_OPTIONS.get(i) !=null)
    		{
    			TABLE_OPTIONS.set(i, TABLE_KEYS.get(i) + ",");
    		}
    		else if(i + 1 != TABLE_KEYS.size())
    		{
    			TABLE_KEYS.set(i, TABLE_KEYS.get(i) + ",");
    		}
    		else
    		{
    			TABLE_KEYS.set(i, TABLE_KEYS.get(i) + ",");
    		}
    	}
    	if(TABLE_OPTIONS.get(i) != null)
    	{
    		keyString = keyString + " " + TABLE_KEYS.get(i) + " " + TABLE_OPTIONS.get(i);
    	}
    	else if(TABLE_OPTIONS.get(i) == null)
    	{
    		keyString = keyString + " " + TABLE_KEYS.get(i);
    	}
    	
    	DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "("
    }
    */
}
