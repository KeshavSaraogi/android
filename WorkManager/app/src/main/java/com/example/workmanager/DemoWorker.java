package com.example.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DemoWorker extends Worker {

    public DemoWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context,workerParameters);
    }

    /**
     * The doWork() method runs asynchronously
     * in the background thread provided by the WorkManager
     *
     * The Result returned from doWork() informs the WorkManager service
     * whether the work succeeded and, in the case of a failure, whether or not
     * the work should be retired.
     *
     * - Result.success() - The work finished successfully
     * - Result.failure() - The work failed
     * - Result.retry()   - The work failed and should be retried at another time according to its retry policy.
     */

    @NonNull
    @Override
    public Result doWork() {
        for (int i = 0; i < 100; i++) {
            Log.i("TAG", "The Count Is: " + i);
        }
        return Result.success();
    }
}
