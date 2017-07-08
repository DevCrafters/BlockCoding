
package com.perasite.bot;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ASCII {

	public static String returnStrPos(double g)//takes the grayscale value as parameter
	{
		String str = " ";
		if (g >= 230) {
			str = " ";
		} else if (g >= 200) {
			str = ".";
		} else if (g >= 180) {
			str = "*";
		} else if (g >= 160) {
			str = ":";
		} else if (g >= 130) {
			str = "o";
		} else if (g >= 100) {
			str = "&";
		} else if (g >= 70) {
			str = "8";
		} else if (g >= 50) {
			str = "#";
		} else {
			str = "@";
		}

		return str;
	}

	public static String imageToText(BufferedImage image) {
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				Color pixelColor = new Color(image.getRGB(x, y));
				double gValue = (((pixelColor.getRed() * 0.2989) + (pixelColor.getBlue() * 0.5870)
						+ (pixelColor.getGreen() * 0.1140)));
				builder.append(returnStrPos(gValue));
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
