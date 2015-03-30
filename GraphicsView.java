package com.example.hellocanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

public class GraphicsView extends View {
	
	
	private static int SCREEN_WIDTH = 480;
	private static int SCREEN_HEIGHT = 800;
	
	private Paint paint = new Paint();
	private Paint linePaint = new Paint();
	private Paint redPaint = new Paint();

	public GraphicsView(Context context) {
		super(context);
		init(context);
	}

	public GraphicsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public GraphicsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}
	
	public void init(Context context)
	{
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		
		SCREEN_WIDTH = wm.getDefaultDisplay().getWidth();
		SCREEN_HEIGHT = wm.getDefaultDisplay().getHeight();
		
		paint.setAntiAlias(true);
		//paint.setColor(Color.RED);
		paint.setColor(Color.rgb(87, 218, 213));
		
		linePaint.setAntiAlias(true);
		linePaint.setColor(Color.WHITE);
		linePaint.setStrokeWidth(4);
		
		redPaint.setAntiAlias(true);
		redPaint.setColor(Color.BLACK);
		redPaint.setStyle(Paint.Style.STROKE);
		redPaint.setStrokeWidth(2);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int width = getWidth();
		int height = getHeight();
		
		//part 1
		int x = width/2;
		int y = height*5/6;
		
		canvas.drawCircle(x, y , 50, paint);
		
		canvas.drawLine(x-40, y, x+40, y, linePaint);
		canvas.drawLine(x, y-40, x, y+40, linePaint);
		
		//part 2
		RectF rect = new RectF(2,0,height/4 + 2,height/4);
		canvas.drawArc(rect, 135, 270, true, paint);
		
		int inner_circle_radius = height / 16;
		int inner_circle_x = height/8 + 2;
		int inner_circle_y = height/8;
		float len =  (float) (inner_circle_radius * Math.pow(2, 0.5) /2);
		
		canvas.drawCircle(inner_circle_x, inner_circle_x, inner_circle_radius, linePaint);
		
		Path path = new Path();
		
		path.rMoveTo(inner_circle_x - len, inner_circle_y + len);
		path.lineTo(inner_circle_x - len*2, inner_circle_y + len *2);
		path.addArc(rect, 135, 90);
		path.lineTo(inner_circle_x - len, inner_circle_y -len);
		RectF innerRect = new RectF(height/16,height/16,height*3/16,height*3/16);
		path.addArc(innerRect, 135, 90);
		
		canvas.drawPath(path,redPaint);
		
		//part 3
		drawPanel(canvas);
		
		
	}
	
	private void drawPanel(Canvas canvas)
	{
		int width = getWidth();
		int height = getHeight();
		int rx = 20;
		int y = height / 3 ;

		Paint paint1 = new Paint();
		paint1.setAntiAlias(true);
		paint1.setStyle(Paint.Style.FILL);
		Shader mShader = new LinearGradient(0, y, 0, y+260,  
                new int[] { Color.rgb(250,233,222),Color.rgb(252,79,8) }, 
                null, Shader.TileMode.REPEAT);
		paint1.setShader(mShader);
		
		Paint paint2 = new Paint();
		paint2.setColor(Color.WHITE);
		paint2.setAntiAlias(true);
		paint2.setStyle(Paint.Style.STROKE);
		paint2.setStrokeWidth(3);
		
		Paint paint3 = new Paint();
		
		Shader shader = new LinearGradient(0,y+10,0,y+260,
				Color.argb(128, 255, 255, 255),
				Color.argb(0, 255, 255, 255),
				Shader.TileMode.REPEAT);
		paint3.setShader(shader);
		
	
		Path path = new Path();
		RectF oval = new RectF(0, y, width, y+260);
		path.addRoundRect(oval, rx,rx, Path.Direction.CCW);
		canvas.drawPath(path, paint1);
		
		Path path1 = new Path();
		path1.moveTo(30, y + 20);
		path1.lineTo(80, y + 60);
		path1.lineTo(130, y + 50);

		path1.lineTo(180, y + 40);
		path1.lineTo(230, y + 30);
		path1.lineTo(280, y + 70);
		path1.lineTo(330, y + 100);
		
		canvas.drawPath(path1,paint2);
		
		
		Path path3 = new Path();
		
		path3.moveTo(30, y + 20);
		path3.lineTo(80, y + 60);
		path3.lineTo(130, y + 50);

		path3.lineTo(180, y + 40);
		path3.lineTo(230, y + 30);
		path3.lineTo(280, y + 70);
		path3.lineTo(330, y + 100);
		path3.lineTo(330, y + 260);
		path3.lineTo(30, y + 260);
		path3.lineTo(30, y + 20);
		path3.close();
		
		canvas.drawPath(path3, paint3);
		
	}


}
