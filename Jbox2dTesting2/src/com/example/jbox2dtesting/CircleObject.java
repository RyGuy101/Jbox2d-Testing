package com.example.jbox2dtesting;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.Vec3;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.FixtureDef;

import android.graphics.drawable.shapes.OvalShape;

public class CircleObject extends BaseObject
{
	private float radius;

	public CircleObject(float _radius)
	{
		super();
		vertices = new float[(int) Math.max(360 * 3, _radius * 2 * Math.PI * 3)];
		this.radius = _radius;
		refreshVertices();
	}

	// @Override
	// public void onBodyCreation(Body _body) {
	// body = _body;
	//
	// // Body has been created, make fixture and finalize it
	// // Physics world waits for completion before continuing
	//
	// // Create fixture from vertices
	// CircleShape shape = new CircleShape();
	// // for (int a = 0; a < 360; a += 360 / 360) {
	// // double heading = a * 3.1415926535897932384626433832795 / 180;
	// // vertices.Add(new Vector3d(Math.Cos(heading) * this.radius, Math
	// // .sin(heading) * this.radius, position.Z));
	// // }
	//
	// shape.setRadius(radius);
	//
	// // Attach fixture
	// FixtureDef fd = new FixtureDef();
	// fd.shape = shape;
	// fd.density = density;
	// fd.friction = friction;
	// fd.restitution = restitution;
	//
	// body.createFixture(fd);
	//
	// }
	@Override
	public void onBodyCreation(Body _body)
	{

		body = _body;

		// Body has been created, make fixture and finalize it
		// Physics world waits for completion before continuing

		// Create fixture from vertices
		PolygonShape shape = new PolygonShape();
		Vec2[] verts = new Vec2[vertices.length / 3];

		int vertIndex = 0;
		for (int i = 0; i < vertices.length; i += 3)
		{
			verts[vertIndex] = new Vec2(vertices[i] / Renderer.getPPM(), vertices[i + 1] / Renderer.getPPM());
			vertIndex++;
		}

		shape.set(verts, verts.length);

		// Attach fixture
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = density;
		fd.friction = friction;
		fd.restitution = restitution;

		body.createFixture(fd);
	}

	// public void setRadius(float _radius) {
	// this.radius = _radius;
	// }

	public float getRadius()
	{
		return radius;
	}

	// @Override
	// public void draw(GL10 gl) {
	// if (!visible) {
	// return;
	// }
	// // Update local data from physics engine, if applicable
	// if (body != null) {
	// position = Renderer.worldToScreen(body.getPosition());
	// rotation = body.getAngle() * 57.2957795786f;
	// }
	//
	// // Save the current state of things
	// gl.glPushMatrix();
	//
	// // Enabling this allows us to give GL a pointer to an array containing
	// // our vertices
	// // This is instead of manually drawing every triangle ourselves using
	// // glVertex3f() statements
	// // which are missing from GL ES anyways. As far as I know (not that
	// // far), this is the only way
	// // to actually draw in 1.1
	// gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	//
	// // Move to where our object is positioned.
	// gl.glTranslatef(position.x, position.y, 1.0f);
	//
	// // Set the angle on each axis, 0 on x and y, our angle on z
	// gl.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
	// gl.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
	// gl.glRotatef(rotation, 0.0f, 0.0f, 1.0f);
	//
	// // Grab our color, convert it to the 0.0 - 1.0 range
	// Vec3 renderCol = color.toFloat();
	// gl.glColor4f(renderCol.x, renderCol.y, renderCol.z, 1.0f);
	// gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuffer);
	// gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
	// gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	// gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	// gl.glPopMatrix();
	// }

	private void refreshVertices()
	{
		int index = 0;
		for (double i = 0; i < 360; i += 360.0 / (double) (vertices.length / 3))
		{
			vertices[index] = (float) Math.cos(Math.toRadians(i)) * radius;
			index++;
			vertices[index] = (float) Math.sin(Math.toRadians(i)) * radius;
			index++;
			vertices[index] = 1.0f;
			index++;
		}
		// Update!
		setVertices(vertices);
	}

}