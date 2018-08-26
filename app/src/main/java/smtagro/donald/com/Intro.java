package smtagro.donald.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
//
//        Dexter.withActivity(this)
//                .withPermissions(
//                        android.Manifest.permission.CAMERA,
//                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
//                        android.Manifest.permission.ACCESS_FINE_LOCATION
//                )
//                .withListener(new MultiplePermissionsListener() {
//                    @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        if (report.areAllPermissionsGranted()) {
//                            Intent intent = new Intent(Intro.this, LandingPage.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
//                        }
//                        for (int i=0;i<report.getDeniedPermissionResponses().size();i++) {
//                            Log.d("dennial permision res", report.getDeniedPermissionResponses().get(i).getPermissionName());
//                        }
//                        // check for permanent denial of any permission
//                        if (report.isAnyPermissionPermanentlyDenied()) {
//                            // permission is denied permenantly, navigate user to app settings
//                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//                                    Uri.fromParts("package", getPackageName(), null));
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                        }
//                    }
//                    @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token)
//                    {
//                        token.continuePermissionRequest();
//                    }
//                }).check();

    }
}
