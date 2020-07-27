package com.example.studikasus;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String STATE_RESULT = "state_result";
    LinearLayout linearLayout;
    AnimationDrawable animationDrawable;
    private EditText name, bornplace, born, place, number, hobby, wish;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayout);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);

        name = findViewById(R.id.name);
        bornplace = findViewById(R.id.bornplace);
        born = findViewById(R.id.born);
        place = findViewById(R.id.place);
        number = findViewById(R.id.number);
        hobby = findViewById(R.id.hobby);
        wish = findViewById(R.id.wish);
        btnCalculate = findViewById(R.id.print);
        tvResult = findViewById(R.id.tvresult);
        btnCalculate.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (animationDrawable != null && !animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.print) {
            String inputName = String.valueOf(name.getText());
            String inputBornplace = String.valueOf(bornplace.getText());
            String inputBorn = String.valueOf(born.getText());
            String inputPlace = String.valueOf(place.getText());
            String inputNumber = String.valueOf(number.getText());
            String inputHobby = String.valueOf(hobby.getText());
            String inputWish = String.valueOf(wish.getText());

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputName)) {
                isEmptyFields = true;
                name.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputBornplace)) {
                isEmptyFields = true;
                bornplace.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputBorn)) {
                isEmptyFields = true;
                born.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(inputPlace)) {
                isEmptyFields = true;
                place.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputNumber)) {
                isEmptyFields = true;
                number.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputHobby)) {
                isEmptyFields = true;
                hobby.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(inputWish)) {
                isEmptyFields = true;
                wish.setError("Field ini tidak boleh kosong");
            }

            Double length = toDouble(inputNumber);
            if (length == null) {
                isInvalidDouble = true;
                number.setError("Field ini harus berupa nomer yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                String value = "Nama: " + inputName + "\n" + "\n Tempat Lahir: " + inputBornplace + "\n" + "\n Tanggal Lahir: " + inputBorn + "\n" + "\n Alamat: " + inputPlace + "\n" + "\n No.Handphone: " + inputNumber + "\n" + "\n Hobby: " + inputHobby + "\n" + "\n Keinginan: " + inputWish;
                tvResult.setText(value);
            }

        }
    }

    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }
}
