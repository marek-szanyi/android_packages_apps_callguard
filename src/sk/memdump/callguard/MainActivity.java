package sk.memdump.callguard;

import sk.memdump.callguard.fragments.PhoneNumbersFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.orangegangsters.lollipin.lib.PinFragmentActivity;
import com.github.orangegangsters.lollipin.lib.managers.AppLock;
import com.github.orangegangsters.lollipin.lib.managers.LockManager;


public class MainActivity extends PinFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if( !LockManager.getInstance().getAppLock().isPasscodeSet() )
		{
			Intent intent = new Intent(MainActivity.this, PinLock.class);
			intent.putExtra(AppLock.EXTRA_TYPE, AppLock.ENABLE_PINLOCK);
			startActivityForResult(intent, 100);
		}
		
		if (savedInstanceState == null) {

			getFragmentManager().beginTransaction()
					.add(R.id.container, new PhoneNumbersFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
