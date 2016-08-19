package processing.test.draw_stick_figure_anim.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import java.util.zip.Inflater;

import processing.test.draw_stick_figure_anim.R;

/**
 * Created by neema on 2016-08-18.
 */
public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_activity);

        LayoutInflater inflater = getLayoutInflater();
        View actionBarView = inflater.inflate(R.layout.custom_action_bar, null);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        actionBar.setCustomView(actionBarView);

    }

    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

}


