package com.example.ddshb.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class HtmlDDSHB {

	public static final int PatternFlags = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;
	
    /**
     * 根据页面链接地址获取页面html流
     * @param url 页面链接地址
     * @return 页面的html流
     */
	public static String getHtmlCode(String url) {
		try {
			URL u = new URL(url);
			URLConnection urlConnection = u.openConnection();
			urlConnection.setAllowUserInteraction(false);
			// 使用openStream得到一输入流并由此构造一个BufferedReader对象
			BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream(), "utf-8"));
			String inputLine;
			StringBuffer tempHtml = new StringBuffer();
			while ((inputLine = in.readLine()) != null){ // 从输入流不断的读数据，直到读完为止
				tempHtml.append(inputLine).append("\n");
			}
			return tempHtml.toString();
		}catch (IOException e) {
			return "";
		}
	}
	
	public static List<Navigator> getNavgators(String url) {

		String html = getHtmlCode(url);
		Pattern patternNav = Pattern
				.compile("<li><a id=pageLink href=(.*?)>(.*?)</a> </li>",
						PatternFlags);
		Matcher matcher = patternNav.matcher(html);
		List<Navigator> list = new ArrayList<Navigator>();
		String titleContent = null;
		String titleUrl = null;
		while (matcher.find()) {
			titleContent = matcher.group(2);
			titleUrl = matcher.group(1);
			Log.v("log nav", titleContent + "   " + titleUrl);
			list.add(new Navigator(titleContent, titleUrl));
		}
		return list;
	}
	
}