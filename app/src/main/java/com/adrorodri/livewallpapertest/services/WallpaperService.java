package com.adrorodri.livewallpapertest.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.adrorodri.livewallpapertest.model.DisplayParams;
import com.adrorodri.livewallpapertest.model.Point;
import com.adrorodri.livewallpapertest.utils.DisplayUtils;
import com.adrorodri.livewallpapertest.utils.PointUtils;

import java.util.LinkedList;
import java.util.List;

public class WallpaperService extends android.service.wallpaper.WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new MyWallpaperEngine(WallpaperService.this);
    }

    class MyWallpaperEngine extends android.service.wallpaper.WallpaperService.Engine {
        private final Handler handler = new Handler();
        private final Runnable drawRunner = new Runnable() {
            @Override
            public void run() {
                draw();
            }

        };
        private Paint paint = new Paint();
        private Paint strokePaint = new Paint();
        private boolean visible = true;
        private List<Point> points;

        // Prefs values
        private SharedPreferences prefs;
        private int maxNumber;
        private float minSpeed;
        private float maxSpeed;
        private float maxDrawingDistance;

        private MyWallpaperEngine(Context context) {
            prefs = PreferenceManager
                    .getDefaultSharedPreferences(context);

            updatePrefs();

            paint.setAntiAlias(true);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(10f);

            strokePaint.setAntiAlias(true);
            strokePaint.setColor(Color.GRAY);
            strokePaint.setStyle(Paint.Style.STROKE);
            strokePaint.setStrokeJoin(Paint.Join.ROUND);
            strokePaint.setStrokeWidth(2f);
            strokePaint.setAlpha(100);

            points = new LinkedList<>();
            points = PointUtils.getInitialRandomPoints(new DisplayParams(DisplayUtils.getHeight(context), DisplayUtils.getWidth(context)), maxNumber, minSpeed, maxSpeed);
            handler.post(drawRunner);
        }

        private void updatePrefs() {
            maxNumber = Integer
                    .valueOf(prefs.getString("numberOfDots", "30"));
            minSpeed = Float
                    .valueOf(prefs.getString("minSpeed", "0.1"));
            maxSpeed = Float
                    .valueOf(prefs.getString("maxSpeed", "1"));
            maxDrawingDistance = Float
                    .valueOf(prefs.getString("maxDrawingDistance", "500"));
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;
            if (visible) {
                updatePrefs();
                handler.post(drawRunner);
            } else {
                handler.removeCallbacks(drawRunner);
            }
            Log.d("LIVEWALLPAPERLOG", "Visibility Changed");
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            this.visible = false;
            handler.removeCallbacks(drawRunner);
            Log.d("LIVEWALLPAPERLOG", "Surface Destroyed");
        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            Log.d("LIVEWALLPAPERLOG", "Surface Created");
        }

        @Override
        public void onSurfaceRedrawNeeded(SurfaceHolder holder) {
            super.onSurfaceRedrawNeeded(holder);
            Log.d("LIVEWALLPAPERLOG", "Redraw Needed");
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format,
                                     int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);
            Log.d("LIVEWALLPAPERLOG", "Surface Changed");
        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            // TODO: Implement Touch Events
        }

        private void draw() {
            SurfaceHolder holder = getSurfaceHolder();
            Canvas canvas = null;
            List<Point> shownPoints = new LinkedList<>();
            try {
                canvas = holder.lockCanvas();
                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                for (Point point : points) {
                    point.updatePosition(canvas);
                    for (Point shownPoint : shownPoints) {
                        strokePaint.setAlpha(PointUtils.getLineAlpha(maxDrawingDistance, point, shownPoint));
                        if (strokePaint.getAlpha() > 0) {
                            canvas.drawLine(point.getX(), point.getY(), shownPoint.getX(), shownPoint.getY(), strokePaint);
                        }
                    }
                    canvas.drawCircle(point.getX(), point.getY(), point.getSize(), paint);
                    shownPoints.add(point);
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
            handler.removeCallbacks(drawRunner);
            if (visible) {
                handler.postDelayed(drawRunner, 5);
            }
        }
    }
}