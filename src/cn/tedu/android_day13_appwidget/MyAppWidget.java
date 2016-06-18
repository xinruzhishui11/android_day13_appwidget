package cn.tedu.android_day13_appwidget;

import java.util.Arrays;
import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;
/**
 * 控制appwidget的控制器
 */
public class MyAppWidget extends AppWidgetProvider{

	@Override
	public void onReceive(Context context, Intent intent) {
		//super.onReceive(context, intent);不能删
		super.onReceive(context, intent);
		//处理button2的点击广播
		String action = intent.getAction();
		if(action.equals("ACTION_BUTTON2_CLICKED")){
			//更新AppWidget的TextView  随即颜色
			//1. 获取AppWidgetManager
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			//2. 构建RemoteViews
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
			int[] colors = {Color.RED, Color.BLUE, Color.BLACK, Color.CYAN, Color.GRAY, Color.GREEN, Color.WHITE, Color.YELLOW};
			views.setTextColor(R.id.textView1, colors[new Random().nextInt(colors.length)]);
			//3. 更新AppWidget
			ComponentName name = new ComponentName(context, MyAppWidget.class);
			manager.updateAppWidget(name, views);
		}
	}
	
	public void onUpdate(Context context, 
			AppWidgetManager manager,
			int[] appWidgetIds) {
		Log.i("info", "onUpdate..."+Arrays.toString(appWidgetIds));
		//更新AppWidget的UI界面
		//1. RemoteViews
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
		//2. 调用views的方法更新UI
		views.setTextColor(R.id.textView1, Color.RED);
		views.setTextViewText(R.id.textView1, "Hello Android");
		//给button1按钮添加点击意图
		Intent i1 = new Intent(context, MainActivity.class);
		PendingIntent pi1 = PendingIntent.getActivity(
				context, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.button1, pi1);
		//给button2按钮添加点击意图
		Intent i2 = new Intent("ACTION_BUTTON2_CLICKED");
		PendingIntent pi2 = PendingIntent.getBroadcast(context, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.button2, pi2);
		//3. 调用manager.updateAppWidget
		manager.updateAppWidget(appWidgetIds, views);
	}

	@Override
	public void onDeleted(Context context, 
			int[] appWidgetIds) {
		Log.i("info", "onDeleted..."+Arrays.toString(appWidgetIds));
	}

	@Override
	public void onEnabled(Context context) {
		Log.i("info", "onEnabled...");
	}

	@Override
	public void onDisabled(Context context) {
		Log.i("info", "onDisabled...");
	}
	
}







