package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        Constraints constraints = new Constraints.Builder().setRequiresCharging(true).build();

        WorkRequest workRequest = new
                OneTimeWorkRequest
                        .Builder(DemoWorker.class)
                        .setConstraints(constraints)
                        .build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance(getApplicationContext()).enqueue(workRequest);
            }
        });

        //Display The Status of Work Manager
        WorkManager.getInstance(getApplicationContext())
                .getWorkInfoByIdLiveData(workRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null) {
                            Toast.makeText(getApplicationContext(),"Status: " + workInfo.getState().name(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Working with Constraints - Running the app under certain conditions
        // The app continues only if the device is connected to WIFI and not mobile data
        // The App continues only if the device is above 20% mobile battery


    }
}