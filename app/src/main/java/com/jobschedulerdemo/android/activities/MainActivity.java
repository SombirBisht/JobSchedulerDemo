package com.jobschedulerdemo.android.activities;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jobschedulerdemo.android.R;
import com.jobschedulerdemo.android.services.MyJobService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(12, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(jobInfo);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled!");
        } else {
            Log.d(TAG, "Job not scheduled");
        }
    }
}
