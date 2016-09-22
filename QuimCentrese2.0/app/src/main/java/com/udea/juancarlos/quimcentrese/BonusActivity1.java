package com.udea.juancarlos.quimcentrese;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class BonusActivity1 extends Activity implements View.OnClickListener {
    Tablero Bonus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Bonus= new Tablero(this, 4,4,false);
        super.setContentView(Bonus.layout);
    }

    @Override
    public void onClick(View boton) {
        Bonus.clic(boton);
    }
}
