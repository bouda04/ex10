package com.example.ex10;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;


public class FragA extends Fragment implements OnClickListener{
	ClickHandler listener;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.frag_a, container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		view.findViewById(R.id.button1).setOnClickListener(this);
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		try{
			this.listener = (ClickHandler)getActivity();
		}catch(ClassCastException e){
			throw new ClassCastException("the class " +
					getActivity().getClass().getName() +
					" must implements the interface 'ClickHandler'");
		}

		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onClick(View v) {
//		Toast.makeText(getActivity(), "hello!!!", Toast.LENGTH_LONG).show();		
		listener.OnClickEvent();
	}

	public interface ClickHandler{
		public void OnClickEvent();
	}
}
