package com.example.assignment2.database;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.assignment2.R;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Exercises.class}, version = 2, exportSchema = false)
public abstract class ExerciseRoomDatabase extends RoomDatabase {

    public abstract ExerciseDAO exerciseDAO();

    private static volatile ExerciseRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ExerciseRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ExerciseRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ExerciseRoomDatabase.class, "exercise_database")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ExerciseDAO dao = INSTANCE.exerciseDAO();
                dao.deleteAll();

                Exercises exercise = new Exercises("Push Up Burpees", null, "High Intensity");
                dao.insert(exercise);
                exercise = new Exercises("Something", null, "Light Cardio");
                dao.insert(exercise);
                exercise = new Exercises("Push Up Burpees", null, "Plyometric Jumps");
                dao.insert(exercise);
                exercise = new Exercises("Push Up Burpees", null, "Joint Friendly");
                dao.insert(exercise);
                Log.e("databaseCreate", "not here");
            });
        }

        @Override
        public void onOpen(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            ExerciseDAO dao = INSTANCE.exerciseDAO();

            Log.e("databaseOpen", "not here");
        }
    };
}
