package com.DNI.andagain;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;


public class B_Main extends Activity {
	public SQLiteDatabase mydb;
	int screenHeight;
	int screenWidth;
	User u;
	private static String DBNAME = "USERS.db";    // THIS IS THE SQLITE DATABASE FILE NAME
	DatabaseManager dbm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		screenHeight = metrics.heightPixels;
		screenWidth = metrics.widthPixels;
		//System.out.println("checkItAll " + screenHeight);
		//setContentView(R.layout.activity_b__main);
		u = new User("Bob");
		mydb = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
		dbm = DatabaseManager.get(mydb);	//Initialize database manager
		
		dbm.establishUser(u);
		FourSquareView v = new FourSquareView(this,screenWidth,screenHeight);
		ZombieView zombieView = new ZombieView(this);
		setContentView(zombieView);
		
		
		//u.time = 60;
		//dbm.updateUser(u, 300, "TIME");
		//System.out.println("Say Call");
		dbm.sayValue("TIME", u.initials);
		
	}	
	public void closeActivity(){
		this.finish();
	}
    	
        // create database
        // create registerView
        // create testViews
        // create resultsView
        // functions to maneuver through views
        // ideas:
        	//speed math prompt at top 41+34 and numbers of varied type are floating around the screen bouncing off each other
        	//follow task: trail predicts where it will be next second when switch switch trail as well
        	//color sequences screen flashes different colors in order then you must match with colored squares.
			//four quadrants change colors 2 of the four colors are the same. touch one of the colors that are the same or different depending on a prompt that flashes between each trial
	
	

}