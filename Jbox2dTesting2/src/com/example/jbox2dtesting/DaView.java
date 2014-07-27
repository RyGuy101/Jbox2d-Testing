package com.example.jbox2dtesting;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DaView extends View
{

	Circle ball;
	Rectangle floor;
	Paint ballPaint = new Paint();

	public DaView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		setup();
	}

	public DaView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		setup();
	}

	public DaView(Context context)
	{
		super(context);
		setup();
	}

	private void setup()
	{
		WorldManager.setupWorld();
		ball = new Circle(1f, 0f, 0.25f, 0.5f, 0.5f, 1f);
		floor = new Rectangle(0.1f, 3f, 1f, 0.1f, 0f, 0f, 0f);
		ballPaint.setColor(Color.RED);
	}

	//	private static int screenW;
	//	private static int screenH;

	private static float PPM = 128.0f;

	public static float toMeters(float pixels)
	{
		return pixels / PPM;
	}

	public static float toPixels(float meters)
	{
		return meters * PPM;
	}

	//	public static float getPPM()
	//	{
	//		return PPM;
	//	}
	//
	//	public static float getMPP()
	//	{
	//		return 1.0f / PPM;
	//	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		long startTime = System.currentTimeMillis();
		super.onDraw(canvas);
		WorldManager.step();
		canvas.drawCircle(toPixels(ball.getX()), toPixels(ball.getY()), toPixels(ball.getRadius()), ballPaint);
		canvas.drawRect(toPixels(floor.getX()), toPixels(floor.getY() - floor.getHeight()), toPixels(floor.getX() + floor.getWidth()), toPixels(floor.getY()), ballPaint);

		long timeTook = System.currentTimeMillis() - startTime;
		if (timeTook < 1000.0 / 60.0)
		{
			try
			{
				Thread.sleep((long) (1000.0 / 60.0 - timeTook));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		invalidate();
	}

}
