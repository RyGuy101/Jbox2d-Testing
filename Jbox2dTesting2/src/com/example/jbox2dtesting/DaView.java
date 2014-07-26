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
import android.util.AttributeSet;
import android.view.View;

public class DaView extends View
{

	Vec2 gravity;
	World world;
	BodyDef bd;
	CircleShape cs;
	FixtureDef fd;
	Body body;

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
		gravity = new Vec2(0.0f, -10.0f);
		world = new World(gravity);
		bd = new BodyDef();
		world.setAllowSleep(true);
		bd.position.set(50, 50);
		bd.type = BodyType.DYNAMIC;
		cs = new CircleShape();
		cs.m_radius = 0.5f;
		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = 0.5f;
		fd.friction = 0.3f;
		fd.restitution = 0.5f;
		body = world.createBody(bd);
		body.createFixture(fd);
	}

	private static int nextId = 0;
	public static ArrayList<BaseObject> actors = new ArrayList<BaseObject>();

	private BoxObject bob;

	private static long spawnDelay = 0;
	private static int screenW;
	private static int screenH;

	private static float PPM = 128.0f;

	public static Vec2 screenToWorld(Vec2 cords)
	{
		return new Vec2(cords.x / PPM, cords.y / PPM);
	}

	public static Vec2 worldToScreen(Vec2 cords)
	{
		return new Vec2(cords.x * PPM, cords.y * PPM);
	}

	public static float getPPM()
	{
		return PPM;
	}

	public static float getMPP()
	{
		return 1.0f / PPM;
	}

	public static int getNextId()
	{
		return nextId++;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		long startTime = System.currentTimeMillis();
		super.onDraw(canvas);
		long timeTook = System.currentTimeMillis() - startTime;
		if (timeTook < 20)
		{
			try
			{
				Thread.sleep(20 - timeTook);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}

}
