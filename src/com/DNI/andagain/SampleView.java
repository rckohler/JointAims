package com.DNI.andagain;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class SampleView extends View {
	Vector<DrawnObject> drawnObjects = new Vector<DrawnObject>();
	public SampleView(Context context) {
		super(context);
		createDrawnObject();
		// TODO Auto-generated constructor stub
	}
	private void createDrawnObject(){
		for (int i =0; i < 10; i ++){
			drawnObjects.add(new DrawnObject(i*50,i*50,50,50,Shape.OVAL));
		}
	}
	public void onDraw(Canvas canvas){
		int length = drawnObjects.size();
		
		for (int i = 0; i < length; i ++){
			DrawnObject d = drawnObjects.elementAt(i);
			d.draw(canvas);
			System.out.println("here " + d.x);
		}
	}
}
