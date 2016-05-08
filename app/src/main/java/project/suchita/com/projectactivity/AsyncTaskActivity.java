package project.suchita.com.projectactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.lang.ref.WeakReference;

public class AsyncTaskActivity extends Activity {

    public AsyncTaskActivity () {

    };
    Button btn;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                new LongOperation(AsyncTaskActivity.this).execute("10", "Hello");
            }
        });
    }



    private class LongOperation extends AsyncTask<String, Void, String> {
        public AsyncTaskActivity parentActivity;
        public LongOperation(Activity activity){
            parentActivity = (AsyncTaskActivity)parentActivity;
        }
        @Override
        protected String doInBackground(String... params) {
            int count = Integer.parseInt(params[0]);
            final String title = params[1];

            for (int i = 0; i < count; i++) {
                try {
                    Thread.sleep(1000);
                    Log.i("BackGround Task","Task excuting count" + i);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


            parentActivity.runOnUiThread(new Runnable() {
                public void run() {
                    TextView txt = (TextView)findViewById(R.id.output);
                    txt.setText(title);
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("BackGround Task","BackGround Task finished");

        }

        @Override
        protected void onPreExecute() {
            Log.i("BackGround Task"," Before BackGround Task");

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.i("BackGround Task"," onProgressUpdate" + values);

        }
    }

    }