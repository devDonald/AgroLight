package smtagro.donald.com.Agents;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import smtagro.donald.com.R;

public class Farming extends AppCompatActivity {
    private String firstName,lastName,agentID;
    private int mYear, mMonth, mDay,mYear1, mMonth1, mDay1,mYear2, mMonth2, mDay2,mYear3, mMonth3, mDay3;
    private EditText mDateOfSowing, mDateOfFertilizer1,mDateOfFertilizer2,mEDateOfHervest;
    private EditText mAgentID,mFIN, mFarmerName,mCropType,mInputsAndQuantities,mDanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farming);

        mFIN = findViewById(R.id.report_farmerId);
        mFarmerName = findViewById(R.id.report_farmer_name);

        mAgentID = findViewById(R.id.report_agentId);
        mDateOfSowing = findViewById(R.id.report_date_of_planting);
        mDateOfFertilizer1 = findViewById(R.id.report_date_of_fert);
        mDateOfFertilizer2 = findViewById(R.id.report_date_of_fert1);
        mEDateOfHervest = findViewById(R.id.report_expected_hervest_date);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            firstName= bundle.getString("firstName");
            lastName = bundle.getString("lastName");
            agentID = bundle.getString("agentID");
            mAgentID.setText(agentID);


        }

        mDateOfSowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Farming.this,
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mDateOfSowing.setText(dayOfMonth+ "-"+ (monthOfYear + 1)+ "-"+ year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        mDateOfFertilizer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear1 = c.get(Calendar.YEAR);
                mMonth1 = c.get(Calendar.MONTH);
                mDay1 = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Farming.this,
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mDateOfFertilizer1.setText(dayOfMonth+ "-"+ (monthOfYear + 1)+ "-"+ year);
                    }
                }, mYear1, mMonth1, mDay1);
                datePickerDialog.show();

            }
        });

        mDateOfFertilizer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear2 = c.get(Calendar.YEAR);
                mMonth2 = c.get(Calendar.MONTH);
                mDay2 = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Farming.this,
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mDateOfFertilizer2.setText(dayOfMonth+ "-"+ (monthOfYear + 1)+ "-"+ year);
                    }
                }, mYear2, mMonth2, mDay2);
                datePickerDialog.show();

            }
        });

        mEDateOfHervest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear3 = c.get(Calendar.YEAR);
                mMonth3 = c.get(Calendar.MONTH);
                mDay3 = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Farming.this,
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mEDateOfHervest.setText(dayOfMonth+ "-"+ (monthOfYear + 1)+ "-"+ year);
                    }
                }, mYear3, mMonth3, mDay3);
                datePickerDialog.show();

            }
        });
    }
}
