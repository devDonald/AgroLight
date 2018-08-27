package smtagro.donald.com.Agents;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;

import smtagro.donald.com.R;
import smtagro.donald.com.models.FarmersModel;

public class AddFarmers extends AppCompatActivity {
    private DatabaseReference farmersDatabase;
    private StorageReference farmersStorage;
    private EditText mNames,mAge,mHouseHoldSize,mPhone,mCenterNo,mCenterName,mFarmSize;
    private EditText mDistanceToMarket,mAvgIncomeFarming,mAvgIncomeNonFarming,mMajorCrop,mBVN;
    private EditText mMajorLivestock,mInputsAndQuant,mStorageCapacity,mMembership,mEnteredBy,bvn;
    private ImageView mfarmerImage;
    private Spinner mMaritalStatus,mGender,mLga;
    private Button btSubmitFarmer;
    private static final int CAMERA_REQUEST_CODE1 = 1;
    private KProgressHUD hud;
    private String names,gender, age,lga,marital_status, farm_size, household_size;
    private String phone_number, avg_income_non_farming, avg_income_farming, distance_to_market;
    private String produce_storage_capacity;
    private String cooperative_membership, center_number,center_name, major_crops;
    private String major_livestock;
    private String inputs_and_quantities;
    private String agentName,fBvn;
    private String FIN,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farmers);

        farmersDatabase = FirebaseDatabase.getInstance().getReference().child("Farmers");
        farmersStorage = FirebaseStorage.getInstance().getReference().child("Farmers");

        mNames = findViewById(R.id.names1);
        mAge =findViewById(R.id.age1);
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
        mMaritalStatus = findViewById(R.id.marital_status1);
        mGender = findViewById(R.id.gender1);
        mLga = findViewById(R.id.sp_lga);

        btSubmitFarmer = findViewById(R.id.btnRegister1);

        hud = KProgressHUD.create(AddFarmers.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Adding Farmer...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setBackgroundColor(Color.BLACK)
                .setAutoDismiss(true);
        FIN = getFIN(new SecureRandom(),15);
        Log.d("fin",""+FIN);


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

                names=mNames.getText().toString().trim();
                gender =mGender.getItemAtPosition(mGender.getSelectedItemPosition()).toString();
                lga=mLga.getItemAtPosition(mLga.getSelectedItemPosition()).toString();
                age = mAge.getText().toString().trim();
                marital_status = mMaritalStatus.getItemAtPosition(mMaritalStatus.getSelectedItemPosition())
                        .toString();
                farm_size = mFarmSize.getText().toString().trim();
                household_size =mHouseHoldSize.getText().toString().trim();
                phone_number=mPhone.getText().toString().trim();
                avg_income_farming = mAvgIncomeFarming.getText().toString().trim();
                avg_income_non_farming = mAvgIncomeNonFarming.getText().toString().trim();
                distance_to_market = mDistanceToMarket.getText().toString().trim();
                produce_storage_capacity = mStorageCapacity.getText().toString().trim();
                cooperative_membership=mMembership.getText().toString().trim();
                center_name = mCenterName.getText().toString().trim();
                center_number = mCenterNo.getText().toString().trim();
                major_crops = mMajorCrop.getText().toString().trim();
                fBvn = mBVN.getText().toString().trim();
                agentName = mEnteredBy.getText().toString().trim();
                inputs_and_quantities = mInputsAndQuant.getText().toString().trim();
                major_livestock = mMajorLivestock.getText().toString().trim();

                if (TextUtils.isEmpty(names)){
                    mNames.setError("fill names");
                }else if (TextUtils.isEmpty(age)){
                    mAge.setError("fill age");
                }else if (TextUtils.isEmpty(farm_size)){
                    mFarmSize.setError("fill farm size");
                }else if (TextUtils.isEmpty(household_size)){
                    mHouseHoldSize.setError("fill household size");
                }else if (TextUtils.isEmpty(phone_number)){
                    mPhone.setError("fill phone");
                } else if (TextUtils.isEmpty(avg_income_farming)){
                    mAvgIncomeFarming.setError("fill avg income");
                }else if (TextUtils.isEmpty(avg_income_non_farming)){
                    mAvgIncomeNonFarming.setError("fill avg income");
                }else if (TextUtils.isEmpty(center_number)){
                    mCenterNo.setError("fill center no");
                } else if (TextUtils.isEmpty(center_name)){
                    mCenterName.setError("fill center name");
                }else if (TextUtils.isEmpty(major_crops)){
                    mMajorCrop.setError("fill major crops");
                } else if (TextUtils.isEmpty(fBvn)){
                    mBVN.setError("fill BVN");
                } else if (TextUtils.isEmpty(agentName)){
                    mEnteredBy.setError("fill Agent name");
                } else if (TextUtils.isEmpty(inputs_and_quantities)){
                    mInputsAndQuant.setError("enter inputs and quantities");
                } else if (TextUtils.isEmpty(major_livestock)){
                    mMajorLivestock.setError("enter major lifestock");
                } else if (gender.matches("Gender")){
                    MDToast.makeText(getApplication(),"Select valid gender",
                            MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                } else if (lga.matches("Select LGA")){
                    MDToast.makeText(getApplication(),"Select valid LGA",
                            MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                }else if (marital_status.matches("Marital Status")){
                    MDToast.makeText(getApplication(),"Select valid marital status",
                            MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                }else{
                    registerFamer();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE1 && resultCode == RESULT_OK) {

            Bitmap bitmap= (Bitmap)data.getExtras().get("data");
            mfarmerImage.setImageBitmap(bitmap);

        }

    }


    public void registerFamer(){
        hud.show();
        StorageReference mountainsRef = farmersStorage.child("image.jpg");
        if (mfarmerImage!=null) {

            mfarmerImage.setDrawingCacheEnabled(true);
            mfarmerImage.buildDrawingCache();
            Bitmap bitmap = mfarmerImage.getDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            UploadTask uploadTask = mountainsRef.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadURI = taskSnapshot.getDownloadUrl();

                    id = farmersDatabase.push().getKey();
                    FarmersModel model = new FarmersModel(names,gender,age,lga,marital_status,farm_size,household_size,
                            phone_number,avg_income_non_farming,avg_income_farming,distance_to_market,produce_storage_capacity,
                            cooperative_membership,center_number,center_name,major_crops,major_livestock,inputs_and_quantities,
                            fBvn,agentName,FIN,downloadURI.toString());


                    farmersDatabase.child(id).setValue(model);
                    hud.dismiss();
                    MDToast.makeText(getApplication(),"Farmer Added Successfully",
                            MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();

                    Intent intent = new Intent(AddFarmers.this, AgentMenu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });
        } else{
            MDToast.makeText(getApplication(),"Failed to add Farmer",
                    MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
        }


    }


    public static String getFIN(SecureRandom random, int length) {
        StringBuilder sb = new StringBuilder(length);
        sb.append(System.currentTimeMillis());

        while (sb.length() < length) {
            sb.append((char) ((Math.random() * 10) + '0'));
        }

        return sb.toString();
    }
}
