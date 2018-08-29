package smtagro.donald.com.Admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.io.ByteArrayOutputStream;

import smtagro.donald.com.R;
import smtagro.donald.com.models.AgentModel;

public class AddAgents extends AppCompatActivity {
    private ImageView addAgentImage;
    private EditText etFirst,etLast,etPhone,etEmail,etAddress,mLga;
    private Button btSubmit;
    private Spinner mState;
    private DatabaseReference agentsDatabase;
    private FirebaseAuth mAuth;
    private StorageReference agentsStorage;
    private FirebaseUser mUser;
    private String uID,id,firstName,lastName,phone,email,address,lgas,state;
    private static final int CAMERA_REQUEST_CODE = 1;
    private int CAMERA_PERMISSION_CODE = 24;
    private KProgressHUD hud;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agents);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        uID = mUser.getUid();

        hud = KProgressHUD.create(AddAgents.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Adding Agent...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setBackgroundColor(Color.BLACK)
                .setAutoDismiss(true);


        agentsDatabase = FirebaseDatabase.getInstance().getReference().child("Agents");
        agentsStorage = FirebaseStorage.getInstance().getReference();

        addAgentImage = findViewById(R.id.agent_image_snap);
        etFirst = findViewById(R.id.etFname);
        etLast = findViewById(R.id.etLname);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        btSubmit = findViewById(R.id.btnSubmitAgent);
        mState = findViewById(R.id.sp_lgaa);
        mLga = findViewById(R.id.et_agent_lga);


        addAgentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
                }

            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName= etFirst.getText().toString().trim();
                lastName = etLast.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                address = etAddress.getText().toString().trim();
                lgas = mLga.getText().toString().trim();
                state=mState.getItemAtPosition(mState.getSelectedItemPosition()).toString();

                if (TextUtils.isEmpty(firstName)){
                    etFirst.setError("input first name");
                } else if (TextUtils.isEmpty(lastName)){
                    etLast.setError("input last name");
                }else if (TextUtils.isEmpty(phone)){
                    etPhone.setError("input phone");
                }else if (TextUtils.isEmpty(email)){
                    etEmail.setError("input email");
                }else if (TextUtils.isEmpty(lgas)){
                    mLga.setError("input LGA");
                } else if (TextUtils.isEmpty(address)){
                    etAddress.setError("input address");
                }else if (state.matches("Select State")){
                    MDToast.makeText(getApplication(),"Select a valid State",
                            MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
                }else{
                    addAgent();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            Bitmap bitmap= (Bitmap)data.getExtras().get("data");
            addAgentImage.setImageBitmap(bitmap);

        }

    }

    public void addAgent(){
        hud.show();
        StorageReference mountainsRef = agentsStorage.child(email).child("image.jpg");
        if (addAgentImage!=null) {

            addAgentImage.setDrawingCacheEnabled(true);
            addAgentImage.buildDrawingCache();
            Bitmap bitmap = addAgentImage.getDrawingCache();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            UploadTask uploadTask = mountainsRef.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadURI = taskSnapshot.getDownloadUrl();

                    id = agentsDatabase.push().getKey();
                    AgentModel model = new AgentModel(id,firstName,lastName,phone,email,
                            address,state,lgas,downloadURI.toString());
                    agentsDatabase.child(id).setValue(model);
                    hud.dismiss();
                    MDToast.makeText(getApplication(),"Agent Added Successfully",
                            MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS).show();
                    mAuth.createUserWithEmailAndPassword(email,"secret");

                    Intent intent = new Intent(AddAgents.this, AdminMenu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });
        } else{
            MDToast.makeText(getApplication(),"Failed to add Agent",
                    MDToast.LENGTH_LONG, MDToast.TYPE_ERROR).show();
        }

    }


}
