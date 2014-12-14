package com.DNI.andagain;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
enum MoveDirection{north, northEast, east, southEast, south, southWest, west, northWest};

public class AnimatedObject {
	
	MoveDirection direction = MoveDirection.north;
	
	float radius=50; // radius of object
	float rx=50,ry=50; //position of object on x and y axis
	RectF size = new RectF(); //used for drawing object
	Rect frame = new Rect(); // used for drawing object
	
	int speed =15; // speed of object not speed of animation
	int destinationX, destinationY;
	
	int animationCount = 0;
	int animationStart = 0;
	int animationFrames = 0;
	
	public AnimatedObject(float rx, float ry){
		this.rx = rx;
		this.ry = ry;
		destinationX = (int)rx;
		destinationY = (int)ry;
		size.set(rx-radius, ry-radius, rx+radius, ry+radius);

	}
	public void setDestination(int newDestinationX, int newDestinationY){
		destinationX = newDestinationX;
		destinationY = newDestinationY;
		int dx = (int) (newDestinationX-rx);
		int dy = (int) (newDestinationY-ry);
		double d = Math.pow(dx*dx+dy*dy, .5);
		int ax,ay;
		ax = Math.abs(dx);
		ay = Math.abs(dy);
		double px = ax/(d);
		double py = ay/(d);
		if (dx>0 && dy >0)
		{
			direction = MoveDirection.southEast;
			if (px>.7)direction = MoveDirection.east;
			if (px<.3)direction = MoveDirection.south;
		}
		if (dx<0 && dy <0){
			direction = MoveDirection.northWest;
			if (px>.7)direction = MoveDirection.west;
			if (px<.3)direction = MoveDirection.north;
		}

		if (dx>0 && dy <0){
			direction = MoveDirection.northEast;
			if (px>.7)direction = MoveDirection.east;
			if (px<.3)direction = MoveDirection.north;
		}
		if(dx <0 && dy >0){
			direction = MoveDirection.southWest;
			if (px>.7)direction = MoveDirection.west;
			if (px<.3)direction = MoveDirection.south;
		}
	}
	
	public void move(){
		int dx=0,dy=0,d=0;
		int speedX=0, speedY=0;
		dx = (int) (destinationX-rx);
		dy = (int) (destinationY-ry);
		d  = (int) Math.pow(dx*dx+dy*dy, .5);

		if(d<speed) {
			rx = destinationX;
			ry = destinationY;
			speedX = 0;
			speedY = 0;
			//System.out.println("at destination");
		}
		else{
			if (d>0)
			{
				speedX = speed*dx/d;
				speedY = speed*dy/d;
				//System.out.println ("moving");
			}
		}
		rx+=speedX;
		ry+=speedY;
		size.set(rx-radius, ry-radius, rx+radius, ry+radius);
		//System.out.println(rx + " " + ry);
	}
	
	public void drawSelf(Canvas canvas, Animation animation){
		int animationFrame=0;
		switch(direction){
		case east: animationFrame = animation.east*animation.columns + animationCount + animationStart;
			break;
		case north: animationFrame = animation.north*animation.columns + animationCount + animationStart;
			break;
		case northEast:animationFrame = animation.northeast*animation.columns + animationCount + animationStart;
			break;
		case northWest:animationFrame = animation.northwest*animation.columns + animationCount + animationStart;
			break;
		case south:animationFrame = animation.south*animation.columns + animationCount + animationStart;
			break;
		case southEast:animationFrame = animation.southeast*animation.columns + animationCount + animationStart;
			break;
		case southWest:animationFrame = animation.southwest*animation.columns + animationCount + animationStart;
			break;
		case west:animationFrame = animation.west*animation.columns + animationCount + animationStart;
			break;
		default:
			break;
		
		}
		Bitmap b = animation.animationFrames.elementAt(animationFrame);
		canvas.drawBitmap(b, rx-radius, ry-radius, null);
		animationCount++;
		if(animationCount>=animationFrames)animationCount = animationStart;
	}
	
}