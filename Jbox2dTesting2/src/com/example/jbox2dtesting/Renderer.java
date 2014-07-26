package com.example.jbox2dtesting;

import java.util.ArrayList;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.jbox2d.common.Vec2;

public class Renderer
{

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

	// @Override
	// public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
	// gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	// bob = new BoxObject(800, 50);
	// bob.color = new Color3(255, 0, 0);
	// Physics.setGravity(new Vec2(0, -10));
	// }
	//
	// @Override
	// public void onSurfaceChanged(GL10 gl, int width, int height) {
	// gl.glViewport(0, 0, width, height);
	// gl.glMatrixMode(GL10.GL_PROJECTION);
	// gl.glOrthof(0, width, 0, height, -10, 10);
	// screenW = width;
	// screenH = height;
	// bob.setPosition(new Vec2(screenW / 2, 100));
	// bob.createPhysicsBody(0, 0.5f, 0.8f);
	// }
	//
	// @Override
	// public void onDrawFrame(GL10 gl) {
	// // Note when we begin
	// long startTime = System.currentTimeMillis();
	// if (System.currentTimeMillis() - spawnDelay > 1000) {
	//
	// // BoxObject obj = new BoxObject((float) Math.random() * 50,
	// // (float) Math.random() * 50);
	// CircleObject obj = new CircleObject(20);
	// obj.color = new Color3();
	// obj.color.r = (255);
	// obj.color.g = (255);
	// obj.color.b = (255);
	//
	// obj.setPosition(new Vec2(screenW / 2, screenH / 2));
	// obj.createPhysicsBody(1.0f, 0.2f, 0.5f);
	//
	// spawnDelay = System.currentTimeMillis();
	// }
	//
	// gl.glMatrixMode(GL10.GL_MODELVIEW);
	// gl.glLoadIdentity();
	// gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
	//
	// // Draw everything
	// for (BaseObject obj : actors) {
	// obj.draw(gl);
	// }
	//
	// // Calculate how much time rendering took
	// long drawTime = System.currentTimeMillis() - startTime;
	//
	// // If we didn't take enough time, sleep for the difference
	// // 1.0f / 60.0f ~= 0.016666666f -> 0.016666666f * 1000 = 16.6666666f
	// // Since currentTimeMillis() returns a ms value, we convert our elapsed
	// // to ms
	// //
	// // It's also 1000.0f / 60.0f, but meh
	// if (drawTime < 16) {
	// try {
	// Thread.sleep(16 - drawTime);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// }

	public static int getNextId()
	{
		return nextId++;
	}
}