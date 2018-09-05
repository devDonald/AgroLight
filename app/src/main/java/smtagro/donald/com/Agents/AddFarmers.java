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
    private StorageReference farmersStorage,idStorage;
    private EditText mNames,mAge,mHouseHoldSize,mPhone,mFarmSize,mFarmLocation,mCooperativeLoc;
    private EditText mDistanceToMarket,mAvgIncomeFarming,mAvgIncomeNonFarming,mMajorCrop,mBVN;
    private EditText mMajorLivestock,mMembership,mEnteredBy,mModeOfIdent,mLga,mChairman_name;
    private EditText mFederalWard,mVillage,mFarmerType,mEducationalQual,mBank_name,mAccount_name,mAcct_no;
    private EditText mTrainingAttended,mTrainingType;
    private ImageView mfarmerImage,displayIDCard;
    private Spinner mMaritalStatus,mGender,mState;
    private Button btSubmitFarmer,btIdentificationImage;
    private static final int CAMERA_REQUEST_CODE1 = 2;
    private static final int CAMERA_REQUEST_CODE = 1;
    private Uri uri_displayID;
    private KProgressHUD hud;
    private String names,gender, age,lga,marital_status, farm_size, household_size;
    private String phone_number, avg_income_non_farming, avg_income_farming, distance_to_market;
    private String cooperative_membership, major_crops;
    private String major_livestock,farm_location,cooperative_location,mode_of_identification;
    private String firstName,lastName,state,federal_ward,village,farmerType,educationalQual;
    private String agentName,fBvn,bank_name,account_name,account_no,chairman_name;
    private String FIN,id,agentID,training_attended,training_type;
    private Double longitude,latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farmers);

        farmersDatabase = FirebaseDatabase.getInstance().getReference().child("Farmers");
        farmersStorage = FirebaseStorage.getInstance().getReference().child("Farmers");
        idStorage = FirebaseStorage.getInstance().getReference().child("IDcards");

        mFarmLocation = findViewById(R.id.farmLocation1);
        mCooperativeLoc = findViewById(R.id.cooperative_location1);
        mModeOfIdent = findViewById(R.id.etModeOfIdent1);
        btIdentificationImage = findViewById(R.id.bt_mode_of_ident1);
        mFederalWard = findViewById(R.id.etWard1);
        mVillage = findViewById(R.id.etVillage1);
        mFarmerType = findViewById(R.id.new_or_existing1);
        mEducationalQual = findViewById(R.id.etQualification1);
        mNames = findViewById(R.id.names1);
        mAge =findViewById(R.id.age1);
        mHouseHoldSize = findViewById(R.id.household_size1);
        mPhone = findViewById(R.id.phone1);
        mFarmSize = findViewById(R.id.farmsize1);
        mDistanceToMarket = findViewById(R.id.distance_to_market1);
        mAvgIncomeFarming = findViewById(R.id.avg_income_farming1);
        mAvgIncomeNonFarming = findViewById(R.id.avg_income_non_farm1);
        mMajorCrop = findViewById(R.id.major_crops1);
        mBVN = findViewById(R.id.bvn1);
        mMajorLivestock = findViewById(R.id.major_livestock1);
        mMembership = findViewById(R.id.cooperative_membership1);
        mEnteredBy = findViewById(R.id.etEnteredBy1);
        mfarmerImage = findViewById(R.id.farmer_image_snap);
        mMaritalStatus = findViewById(R.id.marital_status1);
        mGender = findViewById(R.id.gender1);
        mState = findViewById(R.id.sp_lga);
        mLga = findViewById(R.id.etLGA1);
        displayIDCard =findViewById(R.id.display_idcard);
        mBank_name = findViewById(R.id.etBank_name1);
        mAccount_name = findViewById(R.id.etAccount_name1);
        mAcct_no = findViewById(R.id.etAccountNo1);
        mChairman_name = findViewById(R.id.etChairmanName1);
        mTrainingAttended = findViewById(R.id.et_any_training1);
        mTrainingType = findViewById(R.id.et_training_type1);

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
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
             firstName= bundle.getString("firstName");
            lastName = bundle.getString("lastName");
            latitude = bundle.getDouble("latitude");
            longitude = bundle.getDouble("longitude");
            agentID = bundle.getString("agentID");
            mEnteredBy.setText(firstName+" "+lastName);
        }



        mfarmerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE1);
                }

            }
        });
        btIdentificationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
                }
            }
        });
        btSubmitFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                names=mNames.getText().toString().trim();
                gender =mGender.getItemAtPosition(mGender.getSelectedItemPosition()).toString();
                state=mState.getItemAtPosition(mState.getSelectedItemPosition()).toString();
                age = mAge.getText().toString().trim();
                marital_status = mMaritalStatus.getItemAtPosition(mMaritalStatus.getSelectedItemPosition())
                        .toString();
                farm_size = mFarmSize.getText().toString().trim();
                household_size =mHouseHoldSize.getText().toString().trim();
                phone_number=mPhone.getText().toString().trim();
                avg_income_farming = mAvgIncomeFarming.getText().toString().trim();
                avg_income_non_farming = mAvgIncomeNonFarming.getText().toString().trim();
                distance_to_market = mDistanceToMarket.getText().toString().trim();
                cooperative_membership=mMembership.getText().toString().trim();
                major_crops = mMajorCrop.getText().toString().trim();
                fBvn = mBVN.getText().toString().trim();
                agentName = mEnteredBy.getText().toString().trim();
                major_livestock = mMajorLivestock.getText().toString().trim();
                lga = mLga.getText().toString().trim();
                farm_location = mFarmLocation.getText().toString().trim();
                cooperative_location = mCooperativeLoc.getText().toString().trim();
                mode_of_identification = mModeOfIdent.getText().toString().trim();
                federal_ward = mFederalWard.getText().toString().trim();
                village = mVillage.getText().toString().trim();
                farmerType = mFarmerType.getText().toString().trim();
                educationalQual = mEducationalQual.getText().toString().trim();
                bank_name = mBank_name.getText().toString().trim();
                account_name = mAccount_name.getText().toString().trim();
                account_no = mAcct_no.getText().toString().trim();
                chairman_name = mChairman_name.getText().toString().trim();
                training_attended = mTrainingAttended.getText().toString().trim();
                training_type = mTrainingType.getText().toString().trim();

                if (TextUtils.isEmpty(names)){
                    mNames.setError("fill names");
                }else if (TextUtils.isEmpty(age)){
                    mAge.setError("fill age");
                }else if (TextUtils.isEmpty(educationalQual)){
                    mEducationalQual.setError("fill Educational Qualification");
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
                }else if (TextUtils.isEmpty(major_crops)){
                    mMajorCrop.setError("fill major crops");
                } else if (TextUtils.isEmpty(fBvn)){
                    mBVN.setError("fill BVN");
                } else if (TextUtils.isEmpty(agentName)){
                    mEnteredBy.setError("fill Agent name");
                } else if (TextUtils.isEmpty(lga)){
                    mLga.setError("fill Local govenment");
                } else if (TextUtils.isEmpty(major_livestock)){
                    mMajorLivestock.setError("enter major lifestock");
                } else if (TextUtils.isEmpty(farm_location)){
                    mFarmLocation.setError("fill farm location");
                }else if (TextUtils.isEmpty(cooperative_location)){
                    mCooperativeLoc.setError("fill Cooperative location");
                } else if (TextUtils.isEmpty(mode_of_identification)){
                    mModeOfIdent.setError("fill mode of identification");
                } else if (TextUtils.isEmpty(federal_ward)){
                    mFederalWard.setError("fill federal ward");
                } else if (TextUtils.isEmpty(village)){
                    mVillage.setError("fill Village");
                } else if (TextUtils.isEmpty(farmerType)){
                    mFarmerType.setError("fill Existent or new");
                } else if (TextUtils.isEmpty(bank_name)){
                    mBank_name.setError("fill in Bank_Name");
                } else if (TextUtils.isEmpty(account_name)){
                    mAccount_name.setError("fill in Account Name");
                } else if (TextUtils.isEmpty(account_no)){
                    mAcct_no.setError("fill in Account No");
                }else if (gender.matches("Gender")){
                    MDToast.makeText(getApplication(),"Select valid gender",
                            MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                } else if (state.matches("Select State")){
                    MDToast.makeText(getApplication(),"Select valid State",
                            MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                }else if (marital_status.matches("Marital Status")){
                    MDToast.makeText(getApplication(),"Select valid marital status",
                            MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                }else{
                    //uploadIDCard();
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

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            Bitmap bitmap= (Bitmap)data.getExtras().get("data");
            displayIDCard.setImageBitmap(bitmap);

            StorageReference mountainsRef2 = idStorage.child(FIN).child("image.jpg");
            if (displayIDCard!=null) {

                displayIDCard.setDrawingCacheEnabled(true);
                displayIDCard.buildDrawingCache();
                Bitmap bitmap2 = displayIDCard.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data1 = baos.toByteArray();

                UploadTask uploadTask = mountainsRef2.putBytes(data1);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        uri_displayID = taskSnapshot.getDownloadUrl();
                    }
                });
            }

        }
    }


    public void registerFamer(){
        hud.show();
        StorageReference mountainsRef = farmersStorage.child(FIN).child("image.jpg");
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
                    FarmersModel model = new FarmersModel(names,gender,age,lga,state,marital_status,farm_size,household_size,
                            phone_number,avg_income_non_farming,avg_income_farming,distance_to_market,bank_name,
                            account_name,account_no,training_attended,training_type,cooperative_membership,
                            cooperative_location,chairman_name, federal_ward,village,educationalQual,
                            mode_of_identification, uri_displayID.toString(),farm_location,
                            farmerType,major_crops,major_livestock,fBvn,agentName,FIN,downloadURI.toString());


                    farmersDatabase.child(id).setValue(model);
                    farmersDatabase.child(id).child("agentID").setValue(agentID);
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
