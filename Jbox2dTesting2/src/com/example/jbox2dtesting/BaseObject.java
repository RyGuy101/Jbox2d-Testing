package com.example.jbox2dtesting;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.Vec3;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public abstract class BaseObject
{

	public boolean visible = true;

	private int id;
	protected Body body = null;

	protected Vec2 position = new Vec2(0.0f, 0.0f);
	protected float rotation = 0.0f;

	protected FloatBuffer vertBuffer;
	protected float[] vertices;

	// Saved for when body is recreated on a vert refresh
	protected float friction;
	protected float density;
	protected float restitution;

	public BaseObject()
	{
		this.id = Renderer.getNextId();
		Renderer.actors.add(this);
	}

	// public abstract void draw(GL10 gl);

	// Modify the actor or the body
	public void setPosition(Vec2 position)
	{
		if (body == null)
		{
			this.position = position;
		} else
		{
			body.setTransform(Renderer.screenToWorld(position), body.getAngle());
		}
	}

	// Modify the actor or the body
	public void setRotation(float rotation)
	{
		if (body == null)
		{
			this.rotation = rotation;
		} else
		{
			body.setTransform(body.getPosition(), rotation * 0.0174532925f); // Convert
																				// to
																				// radians
		}
	}

	// Get from the physics body if avaliable
	public Vec2 getPosition()
	{
		if (body == null)
		{
			return position;
		} else
		{
			return Renderer.worldToScreen(body.getPosition());
		}
	}

	public float getRotation()
	{
		if (body == null)
		{
			return rotation;
		} else
		{
			return body.getAngle() * 57.2957795786f;
		}
	}

	public int getId()
	{
		return id;
	}

	public void createPhysicsBody(float _density, float _friction, float _restitution)
	{

		// Cowardly refuse to continue if the body already exists
		if (body != null)
		{
			return;
		}

		// Save values
		friction = _friction;
		density = _density;
		restitution = _restitution;

		// Create the body
		BodyDef bd = new BodyDef();

		if (density > 0)
		{
			bd.type = BodyType.DYNAMIC;
		} else
		{
			bd.type = BodyType.STATIC;
		}

		// Oh jeez
		bd.position = Renderer.screenToWorld(position);

		// Add to physics world body creation queue, will be finalized when
		// possible
		Physics.requestBodyCreation(new BodyQueueDef(id, bd));
	}

	public abstract void onBodyCreation(Body _body);

	public void destroyPhysicsBody()
	{

		if (body == null)
		{
			return;
		}

		Physics.destroyBody(body);
		body = null;
	}

	public void setVertices(float[] _vertices)
	{

		this.vertices = _vertices;

		// Allocate a new byte buffer to move the vertices into a FloatBuffer
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertBuffer = byteBuffer.asFloatBuffer();
		vertBuffer.put(vertices);
		vertBuffer.position(0);
		if (body != null)
		{
			destroyPhysicsBody();
			createPhysicsBody(density, friction, restitution);
		}
	}
}
