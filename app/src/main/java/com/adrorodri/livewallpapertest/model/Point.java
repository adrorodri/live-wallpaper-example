package com.adrorodri.livewallpapertest.model;

import android.graphics.Canvas;

public class Point {
    private float speed;
    private double angle; // rad
    private float x;
    private float y;
    private float size;

    public Point(float speed, float angle, float x, float y, float size) {
        this.speed = speed;
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void updatePosition(Canvas canvas) {
        this.x = this.x + (float) (Math.cos(angle) * this.speed);
        this.y = this.y + (float) (Math.sin(angle) * this.speed);
        if (this.x > canvas.getWidth()) {
            this.x = 0;
        } else {
            if (this.x < 0) {
                this.x = canvas.getWidth();
            }
        }
        if (this.y > canvas.getHeight()) {
            this.y = 0;
        } else {
            if (this.y < 0) {
                this.y = canvas.getHeight();
            }
        }
    }
}
