package com.example.ex10;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity implements FragA.ClickHandler, FragB.DataReporter{

	private int counter=0;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState != null ) {
			this.counter = savedInstanceState.getInt("counter");
		}

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
				FragmentManager mFragmentManager = getFragmentManager();
	          // However if we are being restored from a previous state, then we don't
	          // need to do anything and should return or we could end up with overlapping Fragments
	          if (savedInstanceState != null && mFragmentManager.findFragmentByTag("AAA")!=null){
	              return;
	          }
	          FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
	          fragmentTransaction.add(R.id.fragContainer, new FragA(),"AAA").commit();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("counter", this.counter);
		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	protected void onPause() {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
//		if (fragA != null)
//			ft.remove(fragA);
//		if (fragB != null)
//			ft.remove(fragB);
		ft.commit();
		fm.executePendingTransactions();
		super.onPause();
	}

	@Override
	public void OnClickEvent() {
		FragB fragB;
		this.counter++;
		
	    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
	    	fragB = (FragB) getFragmentManager().findFragmentById(R.id.fragB);
		else
		{
			fragB = new FragB();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			  
			ft.add(R.id.fragContainer, fragB);//add on top of the static fragment
			ft.addToBackStack("webfrag");//cause the back button scrolling through the loaded fragments
			ft.commit();
			fm.executePendingTransactions();
		 }
		 fragB.countChanged(counter);			
	}

	@Override
	public int getData() {
		return this.counter;
	}
}
