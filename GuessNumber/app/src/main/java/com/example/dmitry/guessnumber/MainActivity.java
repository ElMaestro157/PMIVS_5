package com.example.dmitry.guessnumber;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;
    Button bEpilepsy;
    ConstraintLayout main;
    TextView tryNumberTV;

    int randomNumber;
    boolean isEnded;
    boolean isEpilepsy;
    int id = 0;
    int tries = 0;


    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView)findViewById(R.id.textView);
        etInput = (EditText)findViewById(R.id.editText);
        bControl = (Button)findViewById(R.id.button);
        bEpilepsy = (Button)findViewById(R.id.button3);
        main = (ConstraintLayout)findViewById(R.id.mainConstr);
        tryNumberTV = (TextView) findViewById(R.id.tryNumbTV);

        randomNumber = (int)(Math.random()*100);
        isEnded = false;
        isEpilepsy = false;

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        if (!sharedPref.contains(getResources().getString(R.string.idKey))) {
            editor.putInt(getResources().getString(R.string.idKey), ++id);
            //editor.apply();
        }

        int starts = sharedPref.getInt(getResources().getString(R.string.startKey), 0);
        editor.putInt(getResources().getString(R.string.startKey), ++starts);
        editor.apply();

        tries = sharedPref.getInt(getResources().getString(R.string.tryKey), 0);

        tryNumberTV.setText(getResources().getString(R.string.tryNumber) + Integer.toString(tries));
        ((TextView)findViewById(R.id.idTV)).setText(getResources().getString(R.string.idNumber) + Integer.toString(id));
        ((TextView)findViewById(R.id.startTV)).setText(getResources().getString(R.string.start) + Integer.toString(starts));
    }

    @SuppressLint("SetTextI18n")
    public void onClick(View v)
    {
        editor.putInt(getResources().getString(R.string.tryKey), ++tries);
        editor.apply();
        if (!isEnded)
        {
            if (etInput.getText().length() != 0) {
                int input = Integer.parseInt(etInput.getText().toString());
                if (input >= 1 && input <= 100) {
                    if (input > randomNumber)
                        tvInfo.setText(getResources().getString(R.string.ahead));
                    else if (input < randomNumber)
                        tvInfo.setText(getResources().getString(R.string.behind));
                    else {
                        tvInfo.setText(getResources().getString(R.string.hit));
                        bControl.setText(getResources().getString(R.string.play_more));
                        isEnded = true;
                    }
                }
                else
                    tvInfo.setText(getResources().getString(R.string.out_range));
            }
            else
                tvInfo.setText(getResources().getString(R.string.empty));
        }
        else
        {
            tvInfo.setText(getResources().getString(R.string.try_to_guess));
            bControl.setText(getResources().getString(R.string.input_value));

            randomNumber = (int)(Math.random()*100);
            isEnded = false;
        }
        etInput.setText("");
        tryNumberTV.setText(getResources().getString(R.string.tryNumber) + Integer.toString(tries));
    }

    public void onClickEpilepsy(View v)
    {
        if (!isEpilepsy)
        {
            tvInfo.setTextColor(getResources().getColor(R.color.epileptLabelText));
            bEpilepsy.setBackgroundColor(getResources().getColor(R.color.epileptButton));
            bControl.setBackgroundColor(getResources().getColor(R.color.epileptButton));
            bControl.setTextColor(getResources().getColor(R.color.epileptLabelText));
            main.setBackgroundColor(getResources().getColor(R.color.epileptBack));
            bEpilepsy.setText(R.string.preventEpilept);
            isEpilepsy = true;
        }
        else
        {
            tvInfo.setTextColor(getResources().getColor(R.color.normalText));
            bEpilepsy.setBackgroundColor(getResources().getColor(R.color.normalButton));
            bControl.setBackgroundColor(getResources().getColor(R.color.normalButton));
            bControl.setTextColor(getResources().getColor(R.color.normalText));
            main.setBackgroundColor(getResources().getColor(R.color.normalBack));
            bEpilepsy.setText(R.string.makeEpilept);
            isEpilepsy = false;
        }
    }
}
