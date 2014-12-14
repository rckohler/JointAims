package com.DNI.andagain;

import java.io.InputStream;
import java.util.Vector;
import android.graphics.BitmapRegionDecoder;
import android.R.anim;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;

enum AnimationModel{ninja,runningMan,captain,mario,cowboy,zombie,dyingZombie,zombieFull};//add names to this in or
enum AnimationType{directional,single, directionalLR};
public class Animation {
	int columns, rows;
	int bitmapName;
	int animationCount=1; // number of frames in a single animation, if a NSEW 3x4 animation count = 3 because three images make the animation in each of the 4 directions. 
	int north,northeast,east,southeast, south, southwest, west, northwest;
	AnimationType animationType;
	int frames;
	int startFrame;
	Vector<Bitmap> animationFrames = new Vector<Bitmap>();

	public Animation(AnimationModel am,AnimationType at){
		animationType = at;
		defineAnimationAttributes(am);
	}
	public Animation (Bitmap b, AnimationType animationType, AnimationModel animationModel){
		this.animationType = animationType;

		int width = b.getWidth();
		int height = b.getHeight();
		defineAnimationAttributes(animationModel);

		int frameWidth = (int)(width/columns)-1;
		int frameHeight = (int)(height/rows)-1;

		Bitmap croppedBitmap;
		int left,right,top,bottom;
		Rect rect; 
		
		//cycle through each frame
		for (int row = 0; row < rows; row++)
			for(int col = 0; col < columns; col++)
			{
				//measure and cut
				left = col*frameWidth;
				top = row*frameHeight;
				right = left + frameWidth;
				bottom = top + frameHeight;
				rect = new Rect(left,right,top,bottom);
				
				croppedBitmap = Bitmap.createBitmap(b, left, top, frameWidth, frameHeight);
				//append to vector
				//croppedBitmap =  
				animationFrames.add(croppedBitmap);
			}
	}




	private void defineAnimationAttributes(AnimationModel animationModel){
		switch(animationModel){
		case ninja:
			bitmapName = R.drawable.ninja2;
			frames = 6;
			columns = 6;
			rows = 1;

			break;
		case runningMan:
			bitmapName = R.drawable.runningman;
			frames = 30;
			columns = 6;
			rows = 5;
			break;

		case captain:
			bitmapName = R.drawable.captain;
			frames = 4;
			columns = 8;
			rows = 7;
			south = 0;
			southwest = 1;
			west = 2;
			northwest =3;
			north = 4;
			northeast = 5;
			east = 6;
			southeast=7;
			break;
		case cowboy:
			bitmapName = R.drawable.cowboy;
			frames = 9;
			columns = 14;
			rows = 10;
			startFrame = 1;
			east = 3;
			west = 7;
			southeast = 2;
			northeast = 4;
			north = 5;
			northwest = 6;
			southwest = 8;
			south = 9;
			break;
		case zombieFull:
			bitmapName = R.drawable.zombie;  // zombie3 works...
			frames = 9; //9
			columns = 36; //36
			rows = 8; //1
			startFrame = 1;
			west = 0;
			northwest = 1;
			north = 2;
			northeast = 3;
			east = 4;
			southeast = 5;
			south = 6;
			southwest = 7;
			break;
		case zombie:
			bitmapName = R.drawable.zombie3;  // zombie3 works...
			frames = 8; //9
			columns = 8; //36
			rows = 8; //1
			startFrame = 1;
			west = 0;
			northwest = 1;
			north = 2;
			northeast = 3;
			east = 4;
			southeast = 5;
			south = 6;
			southwest = 7;
			break;
		case dyingZombie:
			bitmapName = R.drawable.zombie3;  // zombie3 works...
			frames = 8; //9
			columns = 8; //36
			rows = 8; //1
			startFrame = 1;
			west = 0;
			northwest = 1;
			north = 2;
			northeast = 3;
			east = 4;
			southeast = 5;
			south = 6;
			southwest = 7;
			break;
		default:
			break;
		}
	}
}