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
		int x = width / 2;
		Paint paint1 = new Paint();
		paint1.setColor(Color.BLUE);
		paint1.setAntiAlias(true);
		paint1.setStyle(Paint.Style.FILL);
		Shader mShader = new LinearGradient(0, y, 0, y+260,  
                new int[] { Color.rgb(250,233,222),Color.rgb(252,79,8) }, null, Shader.TileMode.REPEAT);
		paint1.setShader(mShader);
	
		Path path = new Path();
		RectF oval = new RectF(0, y, width, y+260);

		path.addRoundRect(oval, rx,rx, Path.Direction.CCW);
/*
		//a round rect width:200 height:100
		RectF oval = new RectF(0,y,rx*2,y+rx*2);
		path.addArc(oval, 180, 90);
		path.moveTo(0,y+rx);
		path.lineTo(0, y+rect_height-rx);
		
		oval.set(0, rect_height+y-rx*2, rx*2, rect_height+y);
		path.addArc(oval, 90, 90);
		path.moveTo(rx, rect_height+y);
		path.lineTo(rect_width-rx, rect_height+y);
		
		oval.set(rect_width-rx*2,rect_height+y-rx*2,rect_width,rect_height+y);
		path.addArc(oval, 0, 90);
		path.moveTo(rect_width, y+rect_height-rx);
		path.lineTo(rect_width, rx+y);
		
		oval.set(rect_width-rx*2,y,rect_width,rx*2+y);
		path.addArc(oval,270,90);
		path.moveTo(rect_width-rx, y);
		path.lineTo(rx,y);
		//path.lineTo(0, y+rx);
		path.close();
		
		
		Path path2 = new Path();
		path2.moveTo(0,y+rx);
		path2.lineTo(0, y+rect_height-rx);
		path2.lineTo(rx, y+rect_height);
		path2.lineTo(rect_width-rx, y+rect_height);
		
		
		path2.lineTo(0, y+rx);
*/

		canvas.drawPath(path, paint1);
		
		//canvas.drawPath(path2, paint);
	}


}
