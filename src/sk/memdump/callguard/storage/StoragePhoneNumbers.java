package sk.memdump.callguard.storage;

import sk.memdump.callguard.dto.BlockingPhoneNumber;
import sk.memdump.callguard.dto.BlockingPhoneNumbersList;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StoragePhoneNumbers extends AsyncTaskLoader<BlockingPhoneNumbersList> implements IStorage<BlockingPhoneNumber> {

	long[] mKeysToLoad;
	
	public StoragePhoneNumbers(Context context) {
		super(context);
		
		mKeysToLoad = null;
	}

	@Override
	public long store(BlockingPhoneNumber toStore) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(long key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void request()
	{
		mKeysToLoad = null;		
	}

	@Override
	public void request(long[] keys) 
	{
		mKeysToLoad = keys;
	}

	@Override
	public BlockingPhoneNumbersList loadInBackground() 
	{
		BlockingPhoneNumbersList numbersList = new BlockingPhoneNumbersList();
		
		loadInternal(numbersList);
		
		return numbersList;
	}

	private void loadInternal(BlockingPhoneNumbersList numbersList) 
	{
		final SQLiteDatabase db = StorageHelper.getInstance(getContext()).getReadableDatabase();
		final Cursor cursor;
		
		if( mKeysToLoad == null )
		{
			cursor = db.query(
					StorageHelper.Tables.PHONE_NUMBERS,
					null, 
					null, 
					null, 
					null, 
					null,
					null);

		}
		else
		{
			String[] selectionArgs = new String[mKeysToLoad.length];
			StringBuilder bs = new StringBuilder();
			bs.append("(");
			for(int i=0; i < mKeysToLoad.length; ++i)
			{
				selectionArgs[i] = String.valueOf(mKeysToLoad[i]);
				bs.append("?,");
			}
			bs.deleteCharAt(bs.length()-1);
			bs.append(")");
			
			cursor = db.query(StorageHelper.Tables.PHONE_NUMBERS,
					null, 
					StorageHelper.PhoneNumbersTableColumns.ID+" IN " + bs.toString(), 
					selectionArgs, 
					null, 
					null, 
					null);
		}
		
		if( cursor.moveToFirst() )
		{
			final int idColumnIdx = cursor.getColumnIndexOrThrow(StorageHelper.PhoneNumbersTableColumns.ID);
			final int numberIdx = cursor.getColumnIndexOrThrow(StorageHelper.PhoneNumbersTableColumns.PHONE_NUMBER);
			final int blockIncomingIdx = cursor.getColumnIndexOrThrow(StorageHelper.PhoneNumbersTableColumns.BLOCK_INCOMING);
			final int blockOutgoingIdx = cursor.getColumnIndexOrThrow(StorageHelper.PhoneNumbersTableColumns.BLOCK_OUTGOING);
			
			do
			{
				numbersList.add( new BlockingPhoneNumber(cursor.getLong(idColumnIdx), 
						cursor.getString(numberIdx), 
						cursor.getInt(blockIncomingIdx), 
						cursor.getInt(blockOutgoingIdx))
				);
			}while( cursor.moveToNext() );
		}
		
		cursor.close();
	}

}
