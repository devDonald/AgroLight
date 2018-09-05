package smtagro.donald.com.Agents;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.Calendar;

import smtagro.donald.com.R;
import smtagro.donald.com.models.FarmingModel;

public class Farming extends AppCompatActivity {
    private DatabaseReference reportFarming;
    private String firstName,lastName,agentID;
    private int mYear4, mMonth4, mDay4,mYear5, mMonth5, mDay5,mYear6, mMonth6, mDay6,mYear7, mMonth7, mDay7;
    private int mYear, mMonth, mDay,mYear1, mMonth1, mDay1,mYear2, mMonth2, mDay2,mYear3, mMonth3, mDay3;
    private Button mDateOfSowing, mDateOfFertilizer1,mDateOfFertilizer2,mEDateOfHervest;
    private EditText mAgentID,mFIN, mFarmerName,mCropType,mInputsAndQuantities,mDanger;
    private Button mPreEmergenceDate,mPostEmergenceDate,mCultivationDate,mClearingDate;
    private Button mSubmitButton;
    private String FIN,dateOfPlanting,expectedHarvestDate,dateOfFirstFertApplication;
    private String farmerName, typeOfCrop,dateOfNextFertApplication,inputAndQuantities;
    private String farmDanger,preEmergenceDate,postEmergenceDate,cultivationDate,clearingDate;
    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farming);

        mFIN = findViewById(R.id.report_farmerId);
        mFarmerName = findViewById(R.id.report_farmer_name);
        mCropType = findViewById(R.id.report_crop_type);
        mInputsAndQuantities = findViewById(R.id.report_inputs_quantities);
        mDanger =findViewById(R.id.report_danger_on_farm);
        mSubmitButton=findViewById(R.id.report_submit);
        mPreEmergenceDate = findViewById(R.id.report_date_of_preEm);
        mPostEmergenceDate = findViewById(R.id.report_date_of_postEm);
        mClearingDate = findViewById(R.id.report_date_of_clearing);
        mCultivationDate = findViewById(R.id.report_date_of_cultivation);


        mAgentID = findViewById(R.id.report_agentId);
        mDateOfSowing = findViewById(R.id.report_date_of_planting);
        mDateOfFertilizer1 = findViewById(R.id.report_date_of_fert);
        mDateOfFertilizer2 = findViewById(R.id.report_date_of_fert1);
        mEDateOfHervest = findViewById(R.id.report_expected_hervest_date);

        hud = KProgressHUD.create(Farming.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Reporting activities...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setBackgroundColor(Color.BLACK)
                .setAutoDismiss(true);

        reportFarming = FirebaseDatabase.getInstance().getReference().child("Farming Activities");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            firstName= bundle.getString("firstName");
            lastName = bundle.getString("lastName");
            agentID = bundle.getString("agentID");
            mAgentID.setText(agentID);


        }

        mCultivationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear4 = c.get(Calendar.YEAR);
                mMonth4 = c.get(Calendar.MONTH);
                mDay4 = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Farming.this,
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mCultivationDate.setText(dayOfMonth+ "-"+ (monthOfYear + 1)+ "-"+ year);
                    }
                }, mYear4, mMonth4, mDay4);
                datePickerDialog.show();

            }
        });
        mClearingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear5 = c.get(Calendar.YEAR);
                mMonth5 = c.get(Calendar.MONTH);
                mDay5 = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Farming.this,
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mClearingDate.setText(dayOfMonth+ "-"+ (monthOfYear + 1)+ "-"+ year);
                    }
                }, mYear5, mMonth5, mDay5);
                datePickerDialog.show();

            }
        });
        mPreEmergenceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear6 = c.get(Calendar.YEAR);
                mMonth6 = c.get(Calendar.MONTH);
                mDay6 = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Farming.this,
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mPreEmergenceDate.setText(dayOfMonth+ "-"+ (monthOfYear + 1)+ "-"+ year);
                    }
                }, mYear6, mMonth6, mDay6);
                datePickerDialog.show();

            }
        });
        mPostEmergenceDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear7 = c.get(Calendar.YEAR);
                mMonth7 = c.get(Calendar.MONTH);
                mDay7 = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Farming.this,
                        AlertDialog.THEME_DEVICE_DEFAULT_DARK, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        mPostEmergenceDate.setText(dayOfMonth+ "-"+ (monthOfYear + 1)+ "-"+ year);
                    }
                }, mYear7, mMonth7, mDay7);
                datePickerDialog.show();

            }
        });

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

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendReport();
            }
        });
    }


    public void sendReport(){
        FIN = mFIN.getText().toString().trim();
        dateOfPlanting = mDateOfSowing.getText().toString().trim();
        dateOfFirstFertApplication = mDateOfFertilizer1.getText().toString().trim();
        dateOfNextFertApplication =  mDateOfFertilizer2.getText().toString().trim();
        expectedHarvestDate = mEDateOfHervest.getText().toString().trim();
        typeOfCrop = mCropType.getText().toString().trim();
        farmerName = mFarmerName.getText().toString().trim();
        farmDanger = mDanger.getText().toString().trim();
        inputAndQuantities = mInputsAndQuantities.getText().toString().trim();
        preEmergenceDate = mPreEmergenceDate.getText().toString().trim();
        postEmergenceDate = mPostEmergenceDate.getText().toString().trim();
        cultivationDate = mCultivationDate.getText().toString().trim();
        clearingDate = mClearingDate.getText().toString().trim();

        if (TextUtils.isEmpty(FIN)){
            mFIN.setError("fill FIN");
        }else if (TextUtils.isEmpty(dateOfPlanting)){
            MDToast.makeText(getApplication(),"Pls select date of planting",
                    MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
        }else if (TextUtils.isEmpty(dateOfFirstFertApplication)){
            MDToast.makeText(getApplication(),"Select date of first fertilizer Application",
                    MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
        }else if (TextUtils.isEmpty(dateOfNextFertApplication)){
            MDToast.makeText(getApplication(),"Select date of next fertilizer Application",
                    MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
        }else if (TextUtils.isEmpty(expectedHarvestDate)){
            MDToast.makeText(getApplication(),"select expected date of hervest",
                    MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
        }else if (TextUtils.isEmpty(typeOfCrop)){
            mCropType.setError("fill type of crop");
        } else if (TextUtils.isEmpty(farmerName)){
            mFarmerName.setError("fill farmer name");
        }else if (TextUtils.isEmpty(farmDanger)){
            mDanger.setError("fill danger type");
        }else if (TextUtils.isEmpty(inputAndQuantities)){
            mInputsAndQuantities.setError("fill inputs and quantities");
        } else {
            hud.show();

            FarmingModel farming = new FarmingModel(FIN,agentID,dateOfPlanting,expectedHarvestDate,
                    dateOfFirstFertApplication,farmerName,typeOfCrop,firstName+" "+lastName,
                    dateOfNextFertApplication,inputAndQuantities,farmDanger,preEmergenceDate,
                    postEmergenceDate,cultivationDate,clearingDate);
           String id = reportFarming.push().getKey();
            reportFarming.child(id).setValue(farming).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    hud.dismiss();
                    if (task.isSuccessful()){
                        MDToast.makeText(getApplication(),"Farm Activity reported successfully",
                                MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();
                        Intent intent = new Intent(Farming.this, AgentMenu.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    } else {
                        MDToast.makeText(getApplication(),"Farm Activity report failed",
                                MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                    }
                }
            });


        }


    }
}
