package com.example.jbox2dtesting;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class WorldManager
{
	static Vec2 gravity;
	static World world;
	static float timeStep = 1.0f / 50.f;
	static int velocityIterations = 6;
	static int positionIterations = 3;

	public static void setupWorld()
	{
		gravity = new Vec2(0.0f, 10.0f);
		world = new World(gravity);
		world.setAllowSleep(true);
	}
	public static void step()
	{
		world.step(timeStep, velocityIterations, positionIterations);
	}

}
