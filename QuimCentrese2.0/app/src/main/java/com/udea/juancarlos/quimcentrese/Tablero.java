package com.udea.juancarlos.quimcentrese;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Juan Carlos on 17/09/2016.
 */
public class Tablero {
    private final int fil;
    private final int col;
    public ArrayList<Integer> tab;
    public int k = 0;
    public ImageView d1;
    GridLayout layout;
    final int h;
    final int w;
    int len;
    Activity vs;
    boolean bonus;
    PopupWindow pop,pi;





    public Tablero(Activity vv,int ff, int cc, boolean sw) {
        //new Tablero(Activity vv, int ff, int cc, boolean Bonus)

        vs=vv;
        fil=ff;
        col=cc;
        bonus=sw;
        int k=0;


        h = (int) (vs.getResources().getDisplayMetrics().heightPixels / (fil + 0.5));
        w = (int) (vs.getResources().getDisplayMetrics().widthPixels / (col));
        final TypedArray pics = vs.getResources().obtainTypedArray(bonus?R.array.panelpics:R.array.panelBonus);
        GridLayout.LayoutParams param;

        layout = new GridLayout(vs.getApplicationContext());
        layout.setColumnCount(col);
        layout.setRowCount(fil);
        layout.setBackgroundColor(bonus ? Color.BLACK : Color.RED);
        viewUtil idgenerator=new viewUtil();

        tab = new ArrayList<Integer>();
        random();

        for (int i = 0; i < fil * col; i++) {

            ImageView b = new ImageView(vs);
            b.setOnClickListener((View.OnClickListener) vs);
            b.setId(idgenerator.generateViewId());

            param = new GridLayout.LayoutParams();
            param.height = h;
            param.width = w;
            b.setLayoutParams(param);
            b.setPadding(0, 0, 0, 0);

            b.setTag(tab.get(i));

            //todas las tarjetas estaran abiertas si es el bonus
            b.setSelected(!bonus);


            Drawable disp = pics.getDrawable(tab.get(i));

            b.setImageDrawable(disp);

            layout.addView(b, param);
        }
    }


    public void random() {

            for (int i = 0; i <fil*col ; i++) {//fil*col
                tab.add(i);
            }

        Collections.shuffle(tab);
    }

    public void clic(View vw) {
        final View bb=vw;
        final ImageView button = (ImageView) vs.findViewById(bb.getId());

        Animation anim = AnimationUtils.loadAnimation(vs.getApplicationContext(), R.anim.round);
        button.startAnimation(anim);

        button.setClickable(false);
        button.postDelayed(new Runnable() {
            @Override
            public void run() {

                button.setClickable(true);
                if(button.isSelected()==false)
                {
                    if(Integer.valueOf(button.getTag().toString())>41)
                    {
                        popupwindowBonus(bb);
                    }
                    else
                    {
                        popupwindow(bb);
                    }

                }
                button.setSelected((!button.isSelected()));

            }
        }, 500);
        //Toast.makeText(vs.getApplicationContext(),bb.getTag().toString(),Toast.LENGTH_SHORT).show();


    }

    public void popupwindow(View s)
    {
        ImageView image;
        ImageView image2=(ImageView) s;


        LayoutInflater inflater = (LayoutInflater) vs.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup, (ViewGroup) vs.findViewById(R.id.popupLayout));
        layout.setBackgroundColor(Color.WHITE);

        pop=new PopupWindow(layout,4*h,3*w,true);

        pop.showAtLocation(layout, Gravity.CENTER, 0, 0);
        Button btnClosePopup = (Button) layout.findViewById(R.id.buttonClosePopup);
        btnClosePopup.setOnClickListener(cancel_button);
        image=(ImageView) layout.findViewById(R.id.popupImage);
        image.setImageDrawable(image2.getDrawable());

    }

    public void popupwindowBonus(View s)
    {
        ImageView image;
        ImageView image2=(ImageView) s;
        TextView textbonus;
        d1=image2;


        LayoutInflater inflater = (LayoutInflater) vs.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popupbonus, (ViewGroup) vs.findViewById(R.id.popupLayoutBonus1));
        layout.setBackgroundColor(Color.WHITE);

        pop=new PopupWindow(layout,4*h,4*w,true);
        pop.showAtLocation(layout, Gravity.CENTER, 0, 0);

        Button btnClosePopup = (Button) layout.findViewById(R.id.buttonClosePopupBonus);
        Button btnOpenActivity=(Button) layout.findViewById(R.id.buttonOpenActivityBonus);

        Button btnDescription = (Button) layout.findViewById(R.id.buttonDescription);
        Button btnPrize = (Button) layout.findViewById(R.id.buttonPrize);
        btnDescription.setOnClickListener(description_button);
        btnPrize.setOnClickListener(prize_button);

        textbonus=(TextView) layout.findViewById(R.id.textReto);

        btnClosePopup.setOnClickListener(cancel_button);
        btnOpenActivity.setOnClickListener(open_button);


        switch (Integer.valueOf(s.getTag().toString()))
        {
            case 42:
                textbonus.setText("Apareamiento");

                break;
            case 43:
                textbonus.setText("Reto 1");
                break;
            case 44:
                textbonus.setText("Reto 2");

                break;
            default:
                textbonus.setText("");

                break;
        }

        image=(ImageView) layout.findViewById(R.id.popupImageBonus);
        image.setImageDrawable(image2.getDrawable());

    }
    public void popupDescription(View s)
    {
        LayoutInflater inflater = (LayoutInflater) vs.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_info, (ViewGroup) vs.findViewById(R.id.popupInfoLayout));
        layout.setBackgroundColor(Color.WHITE);
        pi=new PopupWindow(layout,4*h,3*w,true);
        pi.showAtLocation(layout, Gravity.CENTER, 0, 0);
        Button btnClosePopup = (Button) layout.findViewById(R.id.buttonCloseInfo);
        btnClosePopup.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                pi.dismiss();
            }
        });

        TextView infoText=(TextView) layout.findViewById(R.id.popupTextInfo);
        infoText.setText(vs.getString(R.string.sB1Desc));
    }
    public void popupPrize(View s)
    {
        final PopupWindow pi;
        LayoutInflater inflater = (LayoutInflater) vs.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup_info, (ViewGroup) vs.findViewById(R.id.popupInfoLayout));
        layout.setBackgroundColor(Color.WHITE);
        pi=new PopupWindow(layout,4*h,3*w,true);
        pi.showAtLocation(layout, Gravity.CENTER, 0, 0);
        Button btnClosePopup = (Button) layout.findViewById(R.id.buttonCloseInfo);
        btnClosePopup.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                pi.dismiss();
            }
        });

        TextView infoText=(TextView) layout.findViewById(R.id.popupTextInfo);
        infoText.setText(vs.getString(R.string.sB1Prize));
    }

    private View.OnClickListener description_button=new View.OnClickListener()
{
    public void onClick(View v)
    {
        popupDescription(v);
    }
};
    private View.OnClickListener prize_button=new View.OnClickListener()
    {
        public void onClick(View v)
        {
            popupPrize(v);
        }
    };
    private View.OnClickListener cancel_button = new View.OnClickListener() {
        public void onClick(View v) {
            pop.dismiss();
        }
    };
    private View.OnClickListener open_button=new View.OnClickListener()
    {
        public void onClick(View v)
        {
            //llamar a la segunda activity
            int name=Integer.valueOf(d1.getTag().toString());
            name=name-42;
            Intent ssIntent;

            switch (name)
            {
                case 0:
                    ssIntent = new Intent(vs, BonusActivity1.class);
                    vs.startActivity(ssIntent);
                    break;
                case 1:
                    ssIntent = new Intent(vs, BonusActivity2.class);
                    vs.startActivity(ssIntent);
                    break;
                case 2:
                    ssIntent = new Intent(vs, BonusActivity3.class);
                    vs.startActivity(ssIntent);
                    break;
                case 3:
                    ssIntent = new Intent(vs, BonusActivity4.class);
                    vs.startActivity(ssIntent);
                    break;
                case 4:
                    ssIntent = new Intent(vs, BonusActivity5.class);
                    vs.startActivity(ssIntent);
                    break;
                case 5:
                    ssIntent = new Intent(vs, BonusActivity6.class);
                    vs.startActivity(ssIntent);
                    break;
            }



            //TODO put extra needs here
        }


    }






}

