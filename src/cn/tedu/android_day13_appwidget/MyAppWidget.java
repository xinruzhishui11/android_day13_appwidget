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
 * ����appwidget�Ŀ�����
 */
public class MyAppWidget extends AppWidgetProvider{

	@Override
	public void onReceive(Context context, Intent intent) {
		//super.onReceive(context, intent);����ɾ
		super.onReceive(context, intent);
		//����button2�ĵ���㲥
		String action = intent.getAction();
		if(action.equals("ACTION_BUTTON2_CLICKED")){
			//����AppWidget��TextView  �漴��ɫ
			//1. ��ȡAppWidgetManager
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			//2. ����RemoteViews
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
			int[] colors = {Color.RED, Color.BLUE, Color.BLACK, Color.CYAN, Color.GRAY, Color.GREEN, Color.WHITE, Color.YELLOW};
			views.setTextColor(R.id.textView1, colors[new Random().nextInt(colors.length)]);
			//3. ����AppWidget
			ComponentName name = new ComponentName(context, MyAppWidget.class);
			manager.updateAppWidget(name, views);
		}
	}
	
	public void onUpdate(Context context, 
			AppWidgetManager manager,
			int[] appWidgetIds) {
		Log.i("info", "onUpdate..."+Arrays.toString(appWidgetIds));
		//����AppWidget��UI����
		//1. RemoteViews
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
		//2. ����views�ķ�������UI
		views.setTextColor(R.id.textView1, Color.RED);
		views.setTextViewText(R.id.textView1, "Hello Android");
		//��button1��ť��ӵ����ͼ
		Intent i1 = new Intent(context, MainActivity.class);
		PendingIntent pi1 = PendingIntent.getActivity(
				context, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.button1, pi1);
		//��button2��ť��ӵ����ͼ
		Intent i2 = new Intent("ACTION_BUTTON2_CLICKED");
		PendingIntent pi2 = PendingIntent.getBroadcast(context, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.button2, pi2);
		//3. ����manager.updateAppWidget
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







