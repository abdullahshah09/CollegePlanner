package com.example.shahkhan.collegeplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.NumberPicker;
import android.graphics.Color;
import android.widget.Toast;

public class addModule extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextMessage;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modules);


        final TextView tv = (TextView) findViewById(R.id.tv);
        NumberPicker np = (NumberPicker) findViewById(R.id.np);

        tv.setTextColor(Color.parseColor("#000000"));


        np.setMinValue(0);
        np.setMaxValue(50);

        np.setWrapSelectorWheel(true);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                tv.setText("Select number CA % total : " + newVal);
            }
        });

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:

                EditText et = (EditText) findViewById(R.id.editText1);
                String v_module_name = et.getEditableText().toString();

                NumberPicker np = (NumberPicker) findViewById(R.id.np);
                Integer v_module_ca = np.getValue();

                myDatabase myDB = new myDatabase(this);

                if(et.length() != 0 && np.getValue() != 0)
                {
                    myDB.insertData(v_module_name, v_module_ca);

                    Intent saved = new Intent(addModule.this, MainActivity.class);
                    startActivity(saved);

                    Toast.makeText(addModule.this, "Module successfully added", Toast.LENGTH_LONG).show();

                } else Toast.makeText(addModule.this, "Enter name and CA", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
