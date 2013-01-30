package com.example.ddshb.control;

import java.util.ArrayList;
import java.util.List;

import com.example.ddshb.MainActivity;
import com.example.ddshb.R;
import com.example.ddshb.adapter.NavgatorAdapter;
import com.example.ddshb.html.Navigator;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;

public class NavDialog extends Dialog {
	
	List<Navigator> listNav;
	
	ListView listView;
	NavgatorAdapter navgatorAdapter;
	Context context;
	Handler handler;
	public NavDialog(Context context, 
			List<Navigator> list,
			Handler handler) {
		super(context);
		this.listNav = list;
		this.handler = handler;
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setLayout( 
				LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT); 
		getWindow().setGravity(Gravity.CENTER);
		
		this.setContentView(R.layout.nav_ist);
		listView = (ListView) findViewById(R.id.lvNav);
		navgatorAdapter = new NavgatorAdapter(context, listNav, handler);
		listView.setAdapter(navgatorAdapter);
	}

	public  void show() {
		if (navgatorAdapter != null) {
			navgatorAdapter.notifyDataSetChanged();
		}
		super.show();
	}

	
	
}
