package processing.test.draw_stick_figure_anim.Activities;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.view.ViewGroup.LayoutParams;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.DialogInterface;

import processing.core.PApplet;
import processing.test.draw_stick_figure_anim.Animation_Fragments.Draw_Stick_Figure_Anim;
import processing.test.draw_stick_figure_anim.Animation_Fragments.Launch_Animation;
import processing.test.draw_stick_figure_anim.R;

public class MainActivity extends Activity {
    PApplet fragment;
    private static final String MAIN_FRAGMENT_TAG = "main_fragment";
    private static final int REQUEST_PERMISSIONS = 1;
    int viewId = 0x1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make window full screen (remove notifications bar)
        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {

            //pass width and height of screen as arguments to launch animation
            Bundle arguments = new Bundle();


            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            double dpHeight = displayMetrics.heightPixels;
            double dpWidth = displayMetrics.widthPixels;

            arguments.putDouble("width", dpWidth);
            arguments.putDouble("height", dpHeight);

            //attach animation fragment
            fragment = new Launch_Animation();
            fragment.setArguments(arguments);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(findViewById(R.id.main_activity_frame).getId(), fragment, MAIN_FRAGMENT_TAG).commit();
        } else {
            fragment = (PApplet) getFragmentManager().findFragmentByTag(MAIN_FRAGMENT_TAG);
        }
    }
    @Override
    public void onBackPressed() {
        fragment.onBackPressed();
        super.onBackPressed();
    }
    @Override
    public void onStart() {
        super.onStart();
        ArrayList<String> needed = new ArrayList<String>();
        int check;
        boolean danger = false;
        if (!needed.isEmpty()) {
          ActivityCompat.requestPermissions(this, needed.toArray(new String[needed.size()]), REQUEST_PERMISSIONS);
        } else if (danger) {
          fragment.onPermissionsGranted();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
      if (requestCode == REQUEST_PERMISSIONS) {
        if (grantResults.length > 0) {
          for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
              AlertDialog.Builder builder = new AlertDialog.Builder(this);
              builder.setMessage("The app cannot run without these permissions, will quit now.")
                     .setCancelable(false)
                     .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int id) {
                              finish();
                          }
                     });
              AlertDialog alert = builder.create();
              alert.show();
            }
          }
          fragment.onPermissionsGranted();
        }
      }
    }
}
