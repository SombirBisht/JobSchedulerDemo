package com.jobschedulerdemo.android.utility;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.jobschedulerdemo.android.services.MyJobService;

public class Utils {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void ScheduleJob(JobScheduler jobScheduler, Context context) {
        // job scheduler
        Log.e("Job", "now started");

    }
}
