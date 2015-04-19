package sk.memdump.callguard.fragments;

import sk.memdump.callguard.R;
import sk.memdump.callguard.dto.BlockingPhoneNumber;
import sk.memdump.callguard.dto.BlockingPhoneNumbersList;
import sk.memdump.callguard.storage.IStorage;
import sk.memdump.callguard.storage.StoragePhoneNumbers;
import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PhoneNumbersFragment extends Fragment implements LoaderCallbacks<BlockingPhoneNumbersList>{

	private static final int PHONE_LOADER_ID = 0x1;
	
	private LayoutInflater mLayoutInflater;
	private LoaderManager mLoaderManager;
	private Context mContext;
	private IStorage mPhoneStorage;
	
	private LinearLayout mPhonesContainer;
	
	public PhoneNumbersFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.phone_numbers_fragment, container,
				false);
		mPhonesContainer = (LinearLayout) rootView.findViewById(R.id.linearLayoutPhonesContainer);
		return rootView;
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		getLoaderManager().initLoader(PHONE_LOADER_ID, null, this);
	}
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setContext(activity);
        setLoaderManager(super.getLoaderManager());
        mLayoutInflater = LayoutInflater.from(getContext());
        mPhoneStorage = new StoragePhoneNumbers(getContext());
    }
	
	 /**
     * Sets a context for the fragment in the unit test environment.
     */
    public void setContext(Context context) {
        mContext = context;
        
    }
    
    /**
     * Overrides a loader manager for use in unit tests.
     */
    public void setLoaderManager(LoaderManager loaderManager) {
        mLoaderManager = loaderManager;
    }
    
    public LoaderManager getLoaderManager()
    {
    	return mLoaderManager;
    }

    public Context getContext() {
        return mContext;
    }

	@SuppressWarnings("unchecked")
	@Override
	public Loader<BlockingPhoneNumbersList> onCreateLoader(int id, Bundle args) 
	{
		if( PHONE_LOADER_ID == id )
		{
			mPhoneStorage.request();
			return (Loader<BlockingPhoneNumbersList>) mPhoneStorage;
		}
		return null;
	}

	@Override
	public void onLoadFinished(Loader<BlockingPhoneNumbersList> loader, BlockingPhoneNumbersList data) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaderReset(Loader<BlockingPhoneNumbersList> loader) {
		// TODO Auto-generated method stub
		
	}
	
	private View phoneViewFactory(BlockingPhoneNumber number)
	{
		throw new UnsupportedOperationException();
	}
}