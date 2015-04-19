package sk.memdump.callguard.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class StorageHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_NAME = "callguard.db";
	
	public interface Tables
	{
		String PHONE_NUMBERS = "phone_numbers";
	}
	
	public interface PhoneNumbersTableColumns
	{
		String ID = BaseColumns._ID;
		String PHONE_NUMBER  = "number";
		String BLOCK_INCOMING = "bin";
		String BLOCK_OUTGOING = "bout";
	}
	
	private static StorageHelper sStorageHelper;
	
	
	public static StorageHelper getInstance(Context context)
	{
		if( sStorageHelper == null )
		{
			sStorageHelper = new StorageHelper(context.getApplicationContext());
		}
		
		return sStorageHelper;
	}
	
	private StorageHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL("CREATE TABLE " + Tables.PHONE_NUMBERS 
				+ " (" + PhoneNumbersTableColumns.ID + " PRIMARY KEY AUTOINCREMENT," 
				+ PhoneNumbersTableColumns.PHONE_NUMBER + " TEXT NOT NULL, "
				+ PhoneNumbersTableColumns.BLOCK_INCOMING + " INTEGER NOT NULL, "
				+ PhoneNumbersTableColumns.BLOCK_OUTGOING + " INTEGER NOT NULL);");
			
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		throw new UnsupportedOperationException("onUpgrade not implemented");
		
	}

}
