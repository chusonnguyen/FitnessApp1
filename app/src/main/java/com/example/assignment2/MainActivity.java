package com.example.assignment2;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment2.database.DataSample;
import com.example.assignment2.database.Exercises;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment2.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar customToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(customToolbar);
        

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
         if (!sharedPreferences.contains("restTime")){
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putInt("restTime", 6);
            myEdit.apply();
        }
/*
         for (int pos = 0; pos <= 4; pos++) {
             long time = System.currentTimeMillis();
             Log.e("currentTime", ""+time);
             Bitmap bitmap = BitmapFactory.decodeResource(getResources(), DataSample.imageArr[pos]);
             ContextWrapper cw = new ContextWrapper(getApplicationContext());
             File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
             File file = new File(directory, time + ".jpg");
             if (!file.exists()) {
                 Log.d("path", file.toString());
                 FileOutputStream fos = null;
                 try {
                     fos = new FileOutputStream(file);
                     bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                     fos.flush();
                     fos.close();
                 } catch (java.io.IOException e) {
                     e.printStackTrace();
                 }
             }
         }

 */
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    

}