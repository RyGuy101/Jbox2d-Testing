package com.example.jbox2dtesting;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		View view = new View(this);
		// GLSurfaceView view = new GLSurfaceView(this);
		Renderer renderer = new Renderer();
		// view.setRenderer(renderer);

		setContentView(view);
	}
}
