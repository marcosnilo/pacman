package com.mstudios.entities;

import java.awt.image.BufferedImage;

public class Fruta extends Entity{

	public Fruta(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		depth = 0;
	}

}
