package com.example.fragmentexample_git;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    private Button openButton;
    private Boolean isFragmentDisplayed = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        openButton = findViewById(R.id.open_btn);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    openFragment();
                }
                else{
                    closeFragment();
                }
            }
        });
    }
    private void openFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SimpleFragment_GIT simpleFragmentGit = SimpleFragment_GIT.newInstance();
        fragmentTransaction.add(R.id.fragment_container, simpleFragmentGit).addToBackStack(null).commit();

        isFragmentDisplayed = true;
        openButton.setText(R.string.close);
    }
    private void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SimpleFragment_GIT simpleFragmentGit = (SimpleFragment_GIT) fragmentManager.findFragmentById(R.id.fragment_container);

        fragmentTransaction.remove(simpleFragmentGit).commit();

        isFragmentDisplayed = false;
        openButton.setText(R.string.open);

    }
}