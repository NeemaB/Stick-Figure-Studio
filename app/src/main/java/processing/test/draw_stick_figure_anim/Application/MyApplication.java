package processing.test.draw_stick_figure_anim.Application;

import android.app.Application;
import android.view.Window;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by neema on 2016-08-18.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        //initialize Iconify module for use in app
        Iconify.
                with(new FontAwesomeModule());
    }
}
