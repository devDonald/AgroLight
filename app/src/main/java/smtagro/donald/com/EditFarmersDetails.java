package smtagro.donald.com;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Picasso;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.text.DateFormat;
import java.util.Date;

import smtagro.donald.com.Admin.ViewFarmersAdmin;
import smtagro.donald.com.models.FarmersModel;

public class EditFarmersDetails extends AppCompatActivity {
    private DatabaseReference farmersDatabase;
    private StorageReference farmersStorage,idStorage;
    private EditText mNames,mAge,mHouseHoldSize,mPhone,mFarmSize,mFarmLocation,mCooperativeLoc;
    private EditText mDistanceToMarket,mAvgIncomeFarming,mAvgIncomeNonFarming,mMajorCrop,mBVN;
    private EditText mMajorLivestock,mMembership,mEnteredBy,mModeOfIdent,mLga,mChairman_name;
    private EditText mFederalWard,mVillage,mFarmerType,mEducationalQual,mBank_name,mAccount_name,mAcct_no;
    private EditText mTrainingAttended,mTrainingType;
    private TextView mFin;
    private ImageView mfarmerImage;
    private EditText mMaritalStatus,mGender,mState;
    private Button btSubmitFarmer,btIdentificationImage;
    private static final int CAMERA_REQUEST_CODE1 = 2;
    private static final int CAMERA_REQUEST_CODE = 1;
    private Uri uri_displayID;
    private KProgressHUD hud;
    private Double longitude,latitude;
    private String registrationDate,position;
    private String names,gender, age,lga,marital_status, farm_size, household_size;
    private String phone_number, avg_income_non_farming, avg_income_farming, distance_to_market;
    private String cooperative_membership, major_crops;
    private String major_livestock,farm_location,cooperative_location,mode_of_identification;
    private String firstName,lastName,state,federal_ward,village,farmerType,educationalQual;
    private String agentName,fBvn,bank_name,account_name,account_no,chairman_name;
    private String FIN,id,agentID,training_attended,training_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_farmers_details);

        registrationDate = DateFormat.getDateTimeInstance().format(new Date());

        farmersDatabase = FirebaseDatabase.getInstance().getReference().child("Farmers");
        farmersStorage = FirebaseStorage.getInstance().getReference().child("Farmers");
        idStorage = FirebaseStorage.getInstance().getReference().child("IDcards");

        mFarmLocation = findViewById(R.id.edit_farmLocation1);
        mCooperativeLoc = findViewById(R.id.edit_cooperative_location1);

        mFederalWard = findViewById(R.id.edit_etWard1);
        mVillage = findViewById(R.id.edit_etVillage1);
        mFarmerType = findViewById(R.id.edit_new_or_existing1);
        mEducationalQual = findViewById(R.id.edit_etQualification1);
        mNames = findViewById(R.id.edit_names1);
        mAge =findViewById(R.id.edit_age1);
        mHouseHoldSize = findViewById(R.id.edit_household_size1);
        mPhone = findViewById(R.id.edit_phone1);
        mFarmSize = findViewById(R.id.edit_farmsize1);
        mDistanceToMarket = findViewById(R.id.edit_distance_to_market1);
        mAvgIncomeFarming = findViewById(R.id.edit_avg_income_farming1);
        mAvgIncomeNonFarming = findViewById(R.id.edit_avg_income_non_farm1);
        mMajorCrop = findViewById(R.id.edit_major_crops1);
        mBVN = findViewById(R.id.edit_bvn1);
        mMajorLivestock = findViewById(R.id.edit_major_livestock1);
        mMembership = findViewById(R.id.edit_cooperative_membership1);
        mEnteredBy = findViewById(R.id.edit_etEnteredBy1);
        mfarmerImage = findViewById(R.id.edit_farmer_image_snap);
        mMaritalStatus = findViewById(R.id.edit_marital_status1);
        mGender = findViewById(R.id.edit_gender1);
        mState = findViewById(R.id.edit_sp_lga);
        mLga = findViewById(R.id.edit_etLGA1);
        mBank_name = findViewById(R.id.edit_etBank_name1);
        mAccount_name = findViewById(R.id.edit_etAccount_name1);
        mAcct_no = findViewById(R.id.edit_etAccountNo1);
        mChairman_name = findViewById(R.id.edit_etChairmanName1);
        mTrainingAttended = findViewById(R.id.edit_et_any_training1);
        mTrainingType = findViewById(R.id.edit_et_training_type1);
        mFin = findViewById(R.id.edit_fin);

        btSubmitFarmer = findViewById(R.id.edit_btnRegister1);

        hud = KProgressHUD.create(EditFarmersDetails.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Updating Farmer...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setBackgroundColor(Color.BLACK)
                .setAutoDismiss(true);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            position = extras.getString("position");
            latitude = extras.getDouble("latitude");
            longitude = extras.getDouble("longitude");
            Log.d("latitude",""+latitude);
            if (position!=null){
                DatabaseReference userRef = farmersDatabase.child(position);

                Log.d("userref",""+userRef);
                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        FarmersModel member = dataSnapshot.getValue(FarmersModel.class);

                        mFarmerType.setText(member.getFarmer_existance());
                        mAge.setText(member.getAge()+" Years");
                        mAvgIncomeFarming.setText(member.getAvg_income_farming());
                        mNames.setText(member.getNames());
                        mPhone.setText(member.getPhone_number());
                        mBVN.setText(member.getBvn());
                        mCooperativeLoc.setText(member.getCooperative_location());
                        mDistanceToMarket.setText(member.getDistance_to_market()+" KM");
                        mEducationalQual.setText(member.getHighestQualification());
                        mEnteredBy.setText(member.getAgentName());
                        mFarmLocation.setText(member.getFarmLocation());
                        mFarmSize.setText(member.getFarm_size()+" Hectares");
                        mMajorCrop.setText(member.getMajor_crops());
                        mGender.setText(member.getGender());
                        mState.setText(member.getState());
                        mLga.setText(member.getLga());
                        mChairman_name.setText(member.getCooperative_chairman());
                        mMembership.setText(member.getCooperative_membership());
                        mVillage.setText(member.getVillage());
                        mFederalWard.setText(member.getFederal_ward());
                        mMaritalStatus.setText(member.getMarital_status());
                        mAvgIncomeNonFarming.setText(member.getAvg_income_non_farming());
                        mHouseHoldSize.setText(member.getHousehold_size());
                        mFin.setText(member.getFIN());
                        mBank_name.setText(member.getBank_name());
                        mAccount_name.setText(member.getAccount_name());
                        mAcct_no.setText(member.getAccount_number());
                        mMajorLivestock.setText(member.getMajor_livestock());
                        mTrainingAttended.setText(member.getTraining_attended());
                        mTrainingType.setText(member.getTraining_type());

                        String profile_image = member.getFarmerImage();
                        Picasso.with(EditFarmersDetails.this).load(profile_image).into(mfarmerImage);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                userRef.addListenerForSingleValueEvent(valueEventListener);


            }



        }


        btSubmitFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                names=mNames.getText().toString().trim();
                gender =mGender.getText().toString();
                state=mState.getText().toString();
                age = mAge.getText().toString().trim();
                marital_status = mMaritalStatus.getText().toString();
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
                }  else if (TextUtils.isEmpty(federal_ward)){
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
                    registerFamer();

                }

            }
        });

    }

    public void registerFamer(){

        hud.show();
        farmersDatabase.child(position).child("names").setValue(names);
        farmersDatabase.child(position).child("gender").setValue(gender);
        farmersDatabase.child(position).child("state").setValue(state);
        farmersDatabase.child(position).child("age").setValue(age);
        farmersDatabase.child(position).child("marital_status").setValue(marital_status);
        farmersDatabase.child(position).child("farm_size").setValue(farm_size);
        farmersDatabase.child(position).child("household_size").setValue(household_size);
        farmersDatabase.child(position).child("phone_number").setValue(phone_number);
        farmersDatabase.child(position).child("avg_income_non_farming").setValue(avg_income_non_farming);
        farmersDatabase.child(position).child("avg_income_farming").setValue(avg_income_farming);
        farmersDatabase.child(position).child("distance_to_market").setValue(distance_to_market);
        farmersDatabase.child(position).child("cooperative_membership").setValue(cooperative_membership);
        farmersDatabase.child(position).child("major_crops").setValue(major_crops);
        farmersDatabase.child(position).child("bvn").setValue(fBvn);
        farmersDatabase.child(position).child("agentName").setValue(agentName);
        farmersDatabase.child(position).child("major_livestock").setValue(major_livestock);
        farmersDatabase.child(position).child("lga").setValue(lga);
        farmersDatabase.child(position).child("farmLocation").setValue(farm_location);
        farmersDatabase.child(position).child("cooperative_location").setValue(cooperative_location);
        farmersDatabase.child(position).child("cooperative_chairman").setValue(chairman_name);
        farmersDatabase.child(position).child("federal_ward").setValue(federal_ward);
        farmersDatabase.child(position).child("village").setValue(village);
        farmersDatabase.child(position).child("farmer_existance").setValue(farmerType);
        farmersDatabase.child(position).child("highestQualification").setValue(educationalQual);

        farmersDatabase.child(position).child("bank_name").setValue(bank_name);
        farmersDatabase.child(position).child("account_name").setValue(account_name);
        farmersDatabase.child(position).child("account_number").setValue(account_no);
        farmersDatabase.child(position).child("training_attended").setValue(training_attended);
        farmersDatabase.child(position).child("training_type").setValue(training_type);

        hud.dismiss();
        MDToast.makeText(getApplication(),"Farmer Updated Successfully",
                            MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();

                    Intent intent = new Intent(EditFarmersDetails.this, ViewFarmersAdmin.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();


    }
}
