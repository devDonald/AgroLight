package smtagro.donald.com.Agents;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import smtagro.donald.com.R;

public class Recovery extends AppCompatActivity {
    private String firstName,lastName,agentID;
    private int mYear, mMonth, mDay,mYear1, mMonth1, mDay1;
    private EditText mDateOfHervest, mDateOfOffTake,mTractorSize;
    private EditText mAgentId,mFIN,mFarmerName,mNoOfHectares,mCropType,mOfftakeCenter;
    private Button mSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        mAgentId = findViewById(R.id.recovery_agentId);
        mDateOfHervest = findViewById(R.id.recovery_date_of_hervest);
        mDateOfOffTake = findViewById(R.id.recovery_date_of_offtake);
        mFIN = findViewById(R.id.recovery_farmerId);
        mFarmerName = findViewById(R.id.recovery_farmer_name);
        mNoOfHectares = findViewById(R.id.recovery_no_hectares);
        mCropType = findViewById(R.id.recovery_croptype);
        mOfftakeCenter = findViewById(R.id.recovery_offtake_center);
        mTractorSize = findViewById(R.id.recovery_tractor_size);
        mSubmit = findViewById(R.id.recovery_submit);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            firstName= bundle.getString("firstName");
            lastName = bundle.getString("lastName");
            agentID = bundle.getString("agentID");
            mAgentId.setText(agentID);

        }
        mDateOfHervest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Recovery.this,
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mDateOfHervest.setText(dayOfMonth+ "-"+ (monthOfYear + 1)+ "-"+ year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        mDateOfOffTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear1 = c.get(Calendar.YEAR);
                mMonth1 = c.get(Calendar.MONTH);
                mDay1 = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Recovery.this,
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mDateOfOffTake.setText(dayOfMonth+ "-"+ (monthOfYear + 1)+ "-"+ year);
                    }
                }, mYear1, mMonth1, mDay1);
                datePickerDialog.show();

            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
