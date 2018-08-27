package smtagro.donald.com.Agents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.valdesekamdem.library.mdtoast.MDToast;

import smtagro.donald.com.R;

public class ResetPassword extends AppCompatActivity {
    private EditText mNewPassword;
    private Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mNewPassword = findViewById(R.id.update_password);
        btSubmit = findViewById(R.id.bt_update_password);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_password=mNewPassword.getText().toString().trim();
                if (TextUtils.isEmpty(new_password)|new_password.length()!=6){
                    mNewPassword.setError("password error");
                } else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                    user.updatePassword(new_password)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        MDToast.makeText(getApplicationContext(),"Password update Successful",
                                                MDToast.LENGTH_LONG,MDToast.TYPE_SUCCESS).show();

                                        Intent reset = new Intent(ResetPassword.this,AgentMenu.class);
                                        reset.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        reset.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        reset.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(reset);
                                        finish();
                                    } else{
                                        MDToast.makeText(getApplicationContext(),"Password update failed",
                                                MDToast.LENGTH_LONG,MDToast.TYPE_ERROR).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
