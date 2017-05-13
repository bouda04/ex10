package com.example.ex10;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragB extends Fragment {
	TextView tvValue;
	DataReporter listner;

	public static FragB newInstance(int pushCount) {
		FragB myFragment = new FragB();

		Bundle args = new Bundle();
		args.putInt("pushCount", pushCount);
		myFragment.setArguments(args);
		return myFragment;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.frag_b, container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		this.tvValue= (TextView) view.findViewById(R.id.textView1);
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		try{
			this.listner = (DataReporter)getActivity();
		}catch(ClassCastException e){
			throw new ClassCastException("the class " +
					getActivity().getClass().getName() +
					" must implements the interface 'DataReporter'");
		}
		this.tvValue.setText(Integer.toString(listner.getData()));

		super.onActivityCreated(savedInstanceState);
	}
	
	public void countChanged(int newValue){
//		getArguments().putInt("pushCount", newValue);
		this.tvValue.setText(Integer.toString(newValue));
	}

	public interface DataReporter{
		public int getData();
	}

}
