package com.jobschedulerdemo.android.services;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.WindowManager;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    private static final String TAG = MyJobService.class.getSimpleName();

    // Called by the Android system when it's time to run the job
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.e(TAG, "Job started!");

        AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(getApplicationContext(), jobParameters);
        asyncTaskRunner.execute();

        return true;
    }

    // Called if the job was cancelled before being finished
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        jobFinished(jobParameters, true);
        return false;
    }

    /** Async task for background task */
    @SuppressLint("StaticFieldLeak")
    private class AsyncTaskRunner extends AsyncTask<Integer, Integer, String> {
        ProgressDialog progressDialog;
        private Context mContext;
        private JobParameters mJobParameters;

        private AsyncTaskRunner(Context context, JobParameters jobParameters) {
            super();
            mContext = context;
            mJobParameters = jobParameters;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("Wait for 10 seconds");
            progressDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
        }

        @Override
        protected String doInBackground(Integer... params) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            jobFinished(mJobParameters, false);
            return "executed";
        }
    }
}
