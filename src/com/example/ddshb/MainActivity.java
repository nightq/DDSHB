package com.example.ddshb;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.JsResult;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Message;

public class MainActivity extends Activity {

	private WebView webView;
	private ProgressBar pgb;
	private ImageButton btnRefresh;
	private String mUrl = "";

	private Calendar cal = Calendar.getInstance();

	private Button btnDatePicker;
	private DatePickerDialog datePickerDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

	}

	private void refresh(int year, int monthOfYear, int dayOfMonth) {
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, monthOfYear);
		cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

		String month = (cal.get(Calendar.MONTH) >= 9 ? "" : "0")
				+ String.valueOf(cal.get(Calendar.MONTH) + 1);
		String day = (cal.get(Calendar.DATE) >= 10 ? "" : "0")
				+ String.valueOf(cal.get(Calendar.DATE));
		mUrl = getResources().getString(R.string.url_content,
				String.valueOf(cal.get(Calendar.YEAR)), month, day);
		btnDatePicker.setText(String.valueOf(cal.get(Calendar.YEAR)) + "-"
				+ month + "-" + day);
		webView.loadUrl(mUrl);
	}

	private void init() {
		webView = (WebView) findViewById(R.id.webView);
		pgb = (ProgressBar) findViewById(R.id.pgb);
		btnRefresh = (ImageButton) findViewById(R.id.btnRefresh);

		btnDatePicker = (Button) findViewById(R.id.btnDatePicker);

		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		// webSettings.setSupportZoom(true);
		webSettings.setBuiltInZoomControls(true);
		WebViewClient wvc = new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				mUrl = url;
				webView.loadUrl(mUrl);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}

		};
		btnRefresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				webView.loadUrl(mUrl);
			}
		});
		webView.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				if (progress == 100) {
					pgb.setVisibility(View.INVISIBLE);
					btnRefresh.setVisibility(View.VISIBLE);
				} else {
					pgb.setVisibility(View.VISIBLE);
					btnRefresh.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					JsResult result) {
				Log.v("´íÎó±¨¸æ~", "this is error!!!");
				result.confirm();
				return true;
			}
			
		});
		webView.setWebViewClient(wvc);

		btnDatePicker.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (datePickerDialog == null) {
					datePickerDialog = new DatePickerDialog(MainActivity.this,
							new OnDateSetListener() {

								@Override
								public void onDateSet(DatePicker view,
										int year, int monthOfYear,
										int dayOfMonth) {
									refresh(year, monthOfYear, dayOfMonth);
								}
							}, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
							cal.get(Calendar.DATE));
				}
				datePickerDialog.show();
			}
		});

		refresh(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE) - 1);

	}

}
