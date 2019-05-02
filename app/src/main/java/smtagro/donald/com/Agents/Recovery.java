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
import smtagro.donald.com.models.RecoveryModel;

public class Recovery extends AppCompatActivity {
    private DatabaseReference reportRecovery;

    private String firstName,lastName,agentID;
    private int mYear, mMonth, mDay,mYear1, mMonth1, mDay1;
    private Button mDateOfHervest, mDateOfOffTake;
    private EditText mAgentId,mFIN,mFarmerName,mNoOfHectares,mCropType,mOfftakeCenter,mTractorSize;
    private Button mSubmit;
    private KProgressHUD hud;
    private String FIN,dateOfHervest,noOfHectares,offTakeDate,tractor_size;
    private String offTakeCenter,farmerName,typeOfCrop;



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


        reportRecovery = FirebaseDatabase.getInstance().getReference().child("Report Recovery");
        hud = KProgressHUD.create(Recovery.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Reporting Recovery...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setBackgroundColor(Color.BLACK)
                .setAutoDismiss(true);
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
                reportRecoveryMethod();

            }
        });
    }
    public void reportRecoveryMethod(){
        FIN = mFIN.getText().toString().trim();
        dateOfHervest = mDateOfHervest.getText().toString().trim();
        noOfHectares = mNoOfHectares.getText().toString().trim();
        offTakeCenter =  mOfftakeCenter.getText().toString().trim();
        offTakeDate = mDateOfOffTake.getText().toString().trim();
        typeOfCrop = mCropType.getText().toString().trim();
        farmerName = mFarmerName.getText().toString().trim();
        tractor_size= mTractorSize.getText().toString().trim();


        if (TextUtils.isEmpty(FIN)){
            mFIN.setError("fill FIN");
        }else if (TextUtils.isEmpty(dateOfHervest)){
            MDToast.makeText(getApplication(),"select expected date of hervest",
                    MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
        }else if (TextUtils.isEmpty(offTakeDate)){
            MDToast.makeText(getApplication(),"select date of offtaking",
                    MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
        }else if (TextUtils.isEmpty(noOfHectares)){
            mNoOfHectares.setError("fill no of Hectares");
        }else if (TextUtils.isEmpty(offTakeCenter)){
            mOfftakeCenter.setError("fill offtake center");
        }else if (TextUtils.isEmpty(typeOfCrop)){
            mCropType.setError("fill type of crop");
        } else if (TextUtils.isEmpty(farmerName)){
            mFarmerName.setError("fill farmer name");
        }else if (TextUtils.isEmpty(tractor_size)){
            mTractorSize.setError("fill size of Tractor");
        } else{
            hud.show();
            RecoveryModel model = new RecoveryModel(FIN,agentID,dateOfHervest,noOfHectares,
                    offTakeDate,tractor_size,offTakeCenter,farmerName,typeOfCrop,firstName+" "+lastName);
            String id = reportRecovery.push().getKey();
            reportRecovery.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    hud.dismiss();
                    if (task.isSuccessful()){
                        MDToast.makeText(getApplication(),"Farm Recovery reported successfully",
                                MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();
                        Intent intent = new Intent(Recovery.this, AgentMenu.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    } else {
                        MDToast.makeText(getApplication(),"Farm Recovery report failed",
                                MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                    }
                }
            });

        }
    }
}
