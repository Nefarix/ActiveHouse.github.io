package io.github.activehouse;
//AHStudio
/**
 * Created by patng2007 on 2017-12-08.
 */
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Timer;
import java.util.TimerTask;

public class TempService /*extends Service*/ {
//
//    public TempService(){
//    }
//
//    private static Timer time = new Timer();
//    private Context ctx;
//    int houseID;
//
//    public IBinder onBind(Intent arg0)
//    {
//        return null;
//
//    }
//
//    public int onStartCommand(Intent intent, int flags, int startID)
//    {
//        houseID = intent.getIntExtra("HouseID", 0);
//        return START_STICKY;
//    }
//
//    public void onCreate()
//    {
//        super.onCreate();
//        ctx = this;
//        startService();
//    }
//
//    public void startService()
//    {
//        time.scheduleAtFixedRate(new io.github.activehouse.TempService.mainTask(), 0, 60000);
//    }
//
//    private class mainTask extends TimerTask
//    {
//        public void run()
//        {
//            Log.e(this.getClass().getSimpleName(), "Service is called");
//            new getTemp().execute();
//        }
//    }
//
//    public void onDestroy()
//    {
//        super.onDestroy();
//    }
//
//    private class getTemp extends AsyncTask<Void, Void, Void>
//    {
//        protected void onPreExecute()
//        {
//            super.onPreExecute();
//        }
//
//        protected Void doInBackground(Void... arg0)
//        {
//            HttpHandler ts = new HttpHandler();
//        }
//    }
//
//    protected void onPostExecute(Void result)
//    {
//        super.onPostExecute(result);
//    }
}
