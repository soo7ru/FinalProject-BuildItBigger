package com.example.jokesandroidlib;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class JokesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container,new JokesActivityFragment()).commit();
        }
    }
}
