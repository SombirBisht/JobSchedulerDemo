package com.jobschedulerdemo.android.activities;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jobschedulerdemo.android.R;
import com.jobschedulerdemo.android.databinding.ActivityMainBinding;
import com.jobschedulerdemo.android.services.MyJobService;

public class MainActivity extends AppCompatActivity {
    private JobScheduler jobScheduler;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // start job button click
        activityMainBinding.btnStartJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start your scheduled job
                startJob();
            }
        });

        //stop job button click
        activityMainBinding.btnCancelJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // stop your scheduled job
                stopJob();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void startJob() {

        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(this, MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(12, componentName)
                .setRequiresCharging(true)
                //.setPeriodic(5000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .build();

        if (jobScheduler != null) {
            jobScheduler.schedule(jobInfo);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void stopJob() {
        if (jobScheduler != null) {
            jobScheduler.cancelAll();
            jobScheduler = null;
            Toast.makeText(this, getResources().getString(R.string.jobCancelled), Toast.LENGTH_SHORT).show();
        }
    }
}
