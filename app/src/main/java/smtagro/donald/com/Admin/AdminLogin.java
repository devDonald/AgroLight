package smtagro.donald.com.Admin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.valdesekamdem.library.mdtoast.MDToast;

import smtagro.donald.com.R;

public class AdminLogin extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btAdminLogin;
    private KProgressHUD hud;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        mAuth = FirebaseAuth.getInstance();

        hud = KProgressHUD.create(AdminLogin.this);
        hud = KProgressHUD.create(AdminLogin.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setDetailsLabel("Authenticating Admin...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setBackgroundColor(Color.BLACK)
                .setAutoDismiss(true);

        etUsername = findViewById(R.id.et_admin_email);
        etPassword = findViewById(R.id.et_admin_password);
        btAdminLogin = findViewById(R.id.admin_login);

        btAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();


                if (TextUtils.isEmpty(username)){
                    etUsername.setError("Username empty");
                } else if (TextUtils.isEmpty(password)||password.length()!=8){
                    etPassword.setError("password error");
                } else {
                    hud.show();
                    mAuth.signInWithEmailAndPassword(username,password)
                            .addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    hud.dismiss();
                                    if (task.isSuccessful()){
                                        MDToast.makeText(getApplicationContext(),"Login Successful",
                                                MDToast.LENGTH_LONG,MDToast.TYPE_SUCCESS).show();
                                        Intent adminMenu = new Intent(AdminLogin.this,AdminMenu.class);
                                        adminMenu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        adminMenu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(adminMenu);
                                        finish();

                                    } else{
                                        MDToast.makeText(getApplicationContext(),"Login unsuccessful",
                                                MDToast.LENGTH_LONG,MDToast.TYPE_SUCCESS).show();

                                    }

                                }
                            });
                }
            }
        });

    }
}
