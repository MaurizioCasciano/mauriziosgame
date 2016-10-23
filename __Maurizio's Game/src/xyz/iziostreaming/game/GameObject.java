package xyz.iziostreaming.game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	/**
	 * Moves this GameObject in x and y direction of a random value.
	 */
	public abstract void tick();
	public abstract void render(Graphics g);
	
	/**
	 * Gets a Rectangle representing the same GameObject info.
	 * @return A Rectangle representing the same GameObject info.
	 */
	public abstract Rectangle getBounds();
	
	/**
	 * Returns the top left X coordinate of the GameObject.
	 * @return The top left X coordinate of the GameObject.
	 */
	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns the top left Y coordinate of the GameObject.
	 * @return The top left Y coordinate of the GameObject.
	 */
	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	/**
	 * Gets the ID of the GameObject.
	 * @return The ID of the GameObject.
	 */
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	protected float x, y, velocityX, velocityY;
	protected ID id;
}
