package com.adrorodri.livewallpapertest.utils;

import com.adrorodri.livewallpapertest.model.DisplayParams;
import com.adrorodri.livewallpapertest.model.Point;

import java.util.LinkedList;
import java.util.List;

public class PointUtils {
    public static List<Point> getInitialRandomPoints(DisplayParams displayParams, int quantity, float minSpeed, float maxSpeed) {
        List<Point> randomPoints = new LinkedList<>();
        if (quantity < 0) {
            throw new NumberFormatException("Quantity can not be lower than 0");
        }
        if (minSpeed < 0) {
            throw new NumberFormatException("Min Speed must be greater than 0");
        }
        if (maxSpeed < 0) {
            throw new NumberFormatException("Max Speed must be greater than 0");
        }
        for (int i = 0; i < quantity; i++) {
            randomPoints.add(new Point(
                    MathUtils.getRandomFloat(maxSpeed, minSpeed),
                    MathUtils.getRandomFloat((float) (2 * Math.PI), 0),
                    MathUtils.getRandomInt(displayParams.getWidthPx(), 0),
                    MathUtils.getRandomInt(displayParams.getHeigntPx(), 0),
                    MathUtils.getRandomFloat(6, 2)));
        }
        return randomPoints;
    }

    public static int getLineAlpha(float maxDrawingDistance, Point point1, Point point2) {
        float distancePercentage = ((float) Math.sqrt(
                Math.pow(point2.getX() - point1.getX(), 2) +
                        Math.pow(point2.getY() - point1.getY(), 2)) /
                maxDrawingDistance);
        distancePercentage = distancePercentage > 1 ? 1 : distancePercentage < 0 ? 0 : distancePercentage;
        return 255 - (int) (255 * distancePercentage);
    }
}
