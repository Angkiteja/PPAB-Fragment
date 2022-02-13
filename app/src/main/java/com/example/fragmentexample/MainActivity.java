package com.example.fragmentexample;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SimpleFragment.onFragmentInteractionListener {


    private Button openButton;
    //fragmentnya dah muncul belom? default belom
    private Boolean isFragmentDisplayed = false;

    private final String FRAGMENT_STATE = "fragment-state";
    private int mCurrentChoice = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //    isi button open
        openButton = findViewById(R.id.open_btn);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFragmentDisplayed){
                    openFragment();
                }
                else{
                    closeFragment();
                }

            }
        });
        if(savedInstanceState != null){
            isFragmentDisplayed = savedInstanceState.getBoolean(FRAGMENT_STATE);
            if(isFragmentDisplayed){
                openButton.setText(R.string.close);

            }
        }
    }
    //buat methodnya
    private void openFragment(){
        //untuk attach suatu fragment ke activity butuh fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//        initialize fragment, fragment apa yg harus ditempel
        SimpleFragment simpleFragment = SimpleFragment.newInstance(mCurrentChoice); //ngasi tau yg lg kepilih yes/no (mCurrentChoice)
        //nempel baris atas ke main activity fragment container
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit();

        isFragmentDisplayed = true;
        openButton.setText(R.string.close);

    }
    private void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //klo mau ngilangin harus tau fragment nya
        //(SimpleFragment) untuk parse
        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        fragmentTransaction.remove(simpleFragment).commit();

        isFragmentDisplayed = false;
        openButton.setText(R.string.open);

    }

    //menyimpan state apakah fragment sedang didisplay di mainActivity atau tidak
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
//        isFragmentDisplayed yg disimpan
        outState.putBoolean(FRAGMENT_STATE, isFragmentDisplayed);
    }

    @Override
    public void onRadioButtonChoiceChecked(int choice) {
        mCurrentChoice = choice;
        Toast.makeText(this,"choice is " + String.valueOf(choice), Toast.LENGTH_SHORT).show();
    }
}