package com.udea.juancarlos.quimcentrese;

import android.content.Context;
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
import android.widget.TextView;

public class BonusActivity2 extends AppCompatActivity implements View.OnClickListener {
    PopupWindow pp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus2);
        Button b=(Button) findViewById(R.id.infoButtonB2);
        b.setOnClickListener(this);
    }



    public void popupinfo(View s)
    {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_info, (ViewGroup) findViewById(R.id.popupInfoLayout));
        layout.setBackgroundColor(Color.WHITE);
        pp=new PopupWindow(layout,300,300,true);
        pp.showAtLocation(layout, Gravity.CENTER, 0, 0);
        Button btnClosePopup = (Button) layout.findViewById(R.id.buttonCloseInfo);
        btnClosePopup.setOnClickListener(cancel_button);

        TextView infoText=(TextView) layout.findViewById(R.id.popupTextInfo);
        infoText.setText(getString(R.string.sB2Points));
    }
    private View.OnClickListener cancel_button = new View.OnClickListener() {
        public void onClick(View v) {
            pp.dismiss();
        }
    };

    @Override
    public void onClick(View view) {
        popupinfo(view);
    }
}
