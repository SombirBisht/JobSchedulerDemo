package com.jobschedulerdemo.android.services;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.jobschedulerdemo.android.utility.Util;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class TestJobService extends JobService {
    private static final String TAG = "SyncService";

    @Override
    public boolean onStartJob(JobParameters params) {

        Toast.makeText(getApplicationContext(), "check", Toast.LENGTH_SHORT).show();

        Intent service = new Intent(getApplicationContext(), LocalWordService.class);
        getApplicationContext().startService(service);
        Util.scheduleJob(getApplicationContext()); // reschedule the job
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        return true;
    }

    private class DoItTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d("DoItTask", "Clean up the task here and call jobFinished...");
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d("DoItTask", "Working here...");
            return null;
        }
    }

}
