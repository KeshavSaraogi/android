package com.example.learningmanagementapplication.Model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database
        (entities = {Category.class, Course.class},
        version = 1)
public abstract class CourseDB extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();
    public abstract CourseDAO courseDAO();

    //Singleton Design Pattern
    private static CourseDB courseDBInstance;

    public static synchronized CourseDB getInstance(Context context) {
        if (courseDBInstance == null) {
            courseDBInstance = Room.databaseBuilder(context.getApplicationContext(),
                    CourseDB.class,
                    "course_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return courseDBInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //Insert New Data When DB is created
        }
    };
}
