//AH Studios
//ActiveHouseV2 Project
package io.github.activehouse;

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

public class TempService{} /*extends Service {

   public TempService(){
    }

    private static Timer time = new Timer();
   private Context ctx;
    int houseID;

    public IBinder onBind(Intent arg0)
   {
       return null;

   }

  public int onStartCommand(Intent intent, int flags, int startID)
   {
       houseID = intent.getIntExtra("HouseID", 0);
        return START_STICKY;
   }

    public void onCreate()
    {

            super.onCreate();
        ctx = this;
       startService();
   }

    public void startService()
    {
        time.scheduleAtFixedRate(new io.github.activehouse.TempService.mainTask(), 0, 60000);
    }

    private class mainTask extends TimerTask
   {
        public void run()
        {
            Log.e(this.getClass().getSimpleName(), "Service is called");
            new getTemp().execute();
        }
    }

    public void onDestroy()
    {
        super.onDestroy();
    }

    private class getTemp extends AsyncTask<Void, Void, Void>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        protected Void doInBackground(Void... arg0)
        {
            HttpHandler ts = new HttpHandler();// Making a request to url and getting response
            String url = "192.168.0.21/ActiveHouse/get_gas.php?houseid=" + HouseID;
            String jsonStr = sh.makeServiceCall(url);

            Log.e(this.getClass().getSimpleName(), "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    int success = jsonObj.getInt("success");
                    if (success == 1) {
                        if (jsonObj.getInt("alert") == 1) {

                            // Getting JSON Array node
                            JSONArray rooms = jsonObj.getJSONArray("rooms");

                            // looping through All rooms
                            for (int i = 0; i < rooms.length(); i++) {
                                JSONObject c = rooms.getJSONObject(i);


                                String RoomID = c.getString("ROOM_ID");
                                String RoomName = c.getString("ROOM_NAME");
                                String UpdateTime = c.getString("UPDATE_TIME");

                                NotificationCompat.Builder builder =
                                        new NotificationCompat.Builder(getBaseContext())
                                                .setSmallIcon(R.drawable.fire)
                                                .setContentTitle("Gas Alert in " + RoomName)
                                                .setContentText("Updated at " + UpdateTime).setAutoCancel(true);

                                Intent notificationIntent = new Intent(getBaseContext(), RoomActivity.class);
                                notificationIntent.putExtra("ROOMID", Integer.valueOf(RoomID));
                                PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(), 0, notificationIntent,
                                        PendingIntent.FLAG_UPDATE_CURRENT);
                                builder.setContentIntent(contentIntent);

                                // Add as notification
                                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                manager.notify(0, builder.build());
                                HomeActivity.myhouse.getRoom(Integer.valueOf(RoomID)).setGas(true);

                            }


                        }

                    }
                    else {
                        /*runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Error Logging in, check Username and Password",Toast.LENGTH_LONG).show();
                            }
                        });*/
/*
                    }

                } catch (final JSONException e) {
                    Log.e(this.getClass().getSimpleName(), "Json parsing error: " + e.getMessage());
                    /*runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });*/
/*
                }

            } else {
                Log.e(this.getClass().getSimpleName(), "Couldn't get json from server.");
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });*/
/*
            }

            return null;
        }
    }

    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
    }
}

*/
