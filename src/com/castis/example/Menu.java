package com.castis.example;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.castis.winter.navigator.NavigatorModelData;
import com.castis.winter.window.WComposite;

public class Menu {
	public static void main(String[] args) throws Exception{
		MenuModel menuModel = new MenuModel();
		menuView menuView = new menuView();
		menuModel.registerObserver(menuView);
		MenuController menuController = new MenuController(menuView, menuModel);
		menuView.setVisible(true);
	}
}
