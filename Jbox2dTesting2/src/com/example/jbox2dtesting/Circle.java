package com.example.jbox2dtesting;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Circle
{
	private float x;
	private float y;
	private float radius;
	private float density;
	private float friction;
	private float restitution;
	Body body;

	public Circle(float x, float y, float radius, float density, float friction, float restitution)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.density = density;
		this.friction = friction;
		this.restitution = restitution;

		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = BodyType.DYNAMIC;
		CircleShape cs = new CircleShape();
		cs.setRadius(radius);
		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = density;
		fd.friction = friction;
		fd.restitution = restitution;
		body = WorldManager.world.createBody(bd);
		body.createFixture(fd);
	}

	public float getX()
	{
		return body.getPosition().x;
	}

	public float getY()
	{
		return body.getPosition().y;
	}

	public float getRadius()
	{
		return radius;
	}
}
