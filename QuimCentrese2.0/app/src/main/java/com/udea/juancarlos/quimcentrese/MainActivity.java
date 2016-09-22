package com.udea.juancarlos.quimcentrese;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity implements View.OnClickListener {

    Tablero t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        t = new Tablero(MainActivity.this, 6,8,true);
        super.setContentView(t.layout);
    }


    @Override
    public void onClick(View button) {
        t.clic(button);
    }
}
