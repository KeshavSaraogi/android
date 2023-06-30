package com.example.learningmanagementapplication.Model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            initializeDatabase();
        }
    };

    private static void initializeDatabase() {
        CourseDAO courseDAO = courseDBInstance.courseDAO();
        CategoryDAO categoryDAO = courseDBInstance.categoryDAO();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Category categoryOne = new Category();
                categoryOne.setCategoryName("Front-End");
                categoryOne.setCategoryDescription("Web Development Interface");

                Category categoryTwo = new Category();
                categoryTwo.setCategoryName("Back-End");
                categoryTwo.setCategoryDescription("Web Development Database");

                categoryDAO.insert(categoryOne);
                categoryDAO.insert(categoryTwo);

                Course courseOne = new Course();
                courseOne.setCourseName("HTML");
                courseOne.setCourseDescription("Provides Content and Structure to the Website");
                courseOne.setCourseId(1);

                Course courseTwo = new Course();
                courseTwo.setCourseName("CSS");
                courseTwo.setCourseDescription("Styles The Website");
                courseTwo.setCourseId(2);

                Course courseThree = new Course();
                courseTwo.setCourseName("PHP");
                courseTwo.setCourseDescription("General Purpose Programming Language");
                courseTwo.setCourseId(3);

                Course courseFour = new Course();
                courseTwo.setCourseName("AJAX");
                courseTwo.setCourseDescription("Asynchronous Javascript and XML");
                courseTwo.setCourseId(4);

                courseDAO.insert(courseOne);
                courseDAO.insert(courseTwo);
                courseDAO.insert(courseThree);
                courseDAO.insert(courseFour);
            }
        });
    }
}
