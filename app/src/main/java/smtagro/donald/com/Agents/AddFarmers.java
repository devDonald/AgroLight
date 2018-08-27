package smtagro.donald.com.Agents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kaopiz.kprogresshud.KProgressHUD;

import smtagro.donald.com.R;

public class AddFarmers extends AppCompatActivity {
    private DatabaseReference farmersDatabase;
    private EditText mNames,mAge,mHouseHoldSize,mPhone,mCenterNo,mCenterName,mFarmSize;
    private EditText mDistanceToMarket,mAvgIncomeFarming,mAvgIncomeNonFarming,mMajorCrop,mBVN;
    private EditText mMajorLivestock,mInputsAndQuant,mStorageCapacity,mMembership,mEnteredBy,bvn;
    private ImageView mfarmerImage,mfarmImage;
    private Spinner mMaritalStatus,mGender,mLga;
    private Button btSubmitFarmer;
    private static final int CAMERA_REQUEST_CODE1 = 1;
    private static final int CAMERA_REQUEST_CODE = 2;
    private KProgressHUD hud;
    private String names,gender, age,lga,marital_status, farm_size, household_size;
    private String phone_number, avg_income_non_farming, avg_income_farming, distance_to_market;
    private String produce_storage_capacity;
    private String cooperative_membership, center_number,center_name, major_crops;
    private String major_livestock;
    private String inputs_and_quantities;
    private String agentName,fBvn;
    private String FIN;
    private String farmerImage,farmImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farmers);

        farmersDatabase = FirebaseDatabase.getInstance().getReference().child("Farmers");
        mNames = findViewById(R.id.names1);
        mAge =findViewById(R.id.names1);
        mHouseHoldSize = findViewById(R.id.household_size1);
        mPhone = findViewById(R.id.phone1);
        mCenterName = findViewById(R.id.etCenterName1);
        mCenterNo = findViewById(R.id.etCenterNum1);
        mFarmSize = findViewById(R.id.farmsize1);
        mDistanceToMarket = findViewById(R.id.distance_to_market1);
        mAvgIncomeFarming = findViewById(R.id.avg_income_farming1);
        mAvgIncomeNonFarming = findViewById(R.id.avg_income_non_farm1);
        mMajorCrop = findViewById(R.id.major_crops1);
        mBVN = findViewById(R.id.bvn1);
        mMajorLivestock = findViewById(R.id.major_livestock1);
        mInputsAndQuant = findViewById(R.id.inputs_and_quantities1);
        mStorageCapacity = findViewById(R.id.produce_storage_capacity1);
        mMembership = findViewById(R.id.cooperative_membership1);
        mEnteredBy = findViewById(R.id.etEnteredBy1);
        mfarmerImage = findViewById(R.id.farmer_image_snap);
        mfarmImage = findViewById(R.id.farmer_farm_snap);
        mMaritalStatus = findViewById(R.id.marital_status1);
        mGender = findViewById(R.id.gender1);
        mLga = findViewById(R.id.sp_lga);

        btSubmitFarmer = findViewById(R.id.btnRegister1);

        mfarmImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
                }
            }
        });

        mfarmerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE1);
                }

            }
        });
        btSubmitFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            Bitmap bitmap= (Bitmap)data.getExtras().get("data");
            mfarmImage.setImageBitmap(bitmap);

        }

        if (requestCode == CAMERA_REQUEST_CODE1 && resultCode == RESULT_OK) {

            Bitmap bitmap= (Bitmap)data.getExtras().get("data");
            mfarmerImage.setImageBitmap(bitmap);

        }

    }


    public void registerFamer(){
        names=mNames.getText().toString().trim();
        gender =mGender.getItemAtPosition(mGender.getSelectedItemPosition()).toString();
        lga=mLga.getItemAtPosition(mLga.getSelectedItemPosition()).toString();

    }
}
