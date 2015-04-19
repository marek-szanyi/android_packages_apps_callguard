package sk.memdump.callguard;

import com.github.orangegangsters.lollipin.lib.managers.LockManager;

import android.app.Application;

public class CallguardApplication extends Application 
{
		
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		@SuppressWarnings("unchecked")
		LockManager<PinLock> lockManager = LockManager.getInstance();
		lockManager.enableAppLock(this, PinLock.class);
		lockManager.getAppLock().setLogoId(R.drawable.ic_launcher);
	}
}
