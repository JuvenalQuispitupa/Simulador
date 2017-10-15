package com.juvenalquisptitupayupa.simulador;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import Dao.PatronDao;
import Model.MPatron;

public class Ver extends AppCompatActivity {

    private MPatron patron;
    private PatronDao patronDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        patronDao = new PatronDao(this);
        setContentView(new MiPropiaVista(this));

    }
    public static void start(Context context) {
        Intent starter = new Intent(context, Ver.class);
        //starter.putExtra("");
        context.startActivity(starter);
    }

    public class MiPropiaVista extends View {
        private CountDownTimer countDownTimer;
        private Paint miPincel;
        private List<MPatron> patronesDraw;

        int px0 =300;
        int py0 =100;
        int pxf =0;
        int pyf =0;
        int avance=50;

        int ARpx0 =300;
        int ARpy0 =280;
        int ARpxf =0;
        int ARpyf =0;
        int ARavance=50;
        int AR_i=0;

        int DEpx0 =300;
        int DEpy0 =460;
        int DEpxf =0;
        int DEpyf =0;
        int DEavance=50;
        int DE_i=0;

        int CLpx0 =300;
        int CLpy0 =640;
        int CLpxf =0;
        int CLpyf =0;
        int CLavance=50;
        int CL_i=0;

        int IZpx0 =300;
        int IZpy0 =820;
        int IZpxf =0;
        int IZpyf =0;
        int IZavance=50;
        int IZ_i=0;

        public MiPropiaVista(Context context)
        {
            super(context);
            this.setBackgroundColor(Color.BLACK);
        }

        protected void onDraw(Canvas canvas){

            miPincel = new Paint();
            miPincel.setColor(Color.WHITE);
            miPincel.setStrokeWidth(3);
            miPincel.setStyle(Paint.Style.FILL);
           //canvas.drawCircle(175,175,100,miPincel);
            canvas.drawLine(300,100,1100,100,miPincel);//Abajo
            canvas.drawLine(300,280,1100,280,miPincel);//Arriba
            canvas.drawLine(300,460,1100,460,miPincel);//Derecha
            canvas.drawLine(300,640,1100,640,miPincel);//Click
            canvas.drawLine(300,820,1100,820,miPincel);//Izquierda

            miPincel.setTextSize(60);
            canvas.drawText("AB.",1000,100,miPincel);
            canvas.drawText("AR.",1000,280,miPincel);
            canvas.drawText("DE.",1000,460,miPincel);
            canvas.drawText("CL.",1000,640,miPincel);
            canvas.drawText("IZ.",1000,820,miPincel);

            Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.fotosim);
            canvas.drawBitmap(b,0,0,  miPincel);


            try{
                patronesDraw =patronDao.getPatrones("AB");
                miPincel.setColor(Color.RED);
                miPincel.setStrokeWidth(5);

                for (int i = 0; i < patronesDraw.size(); i++) {
                    pxf = px0 + avance;
                    pyf = 100 + (-1*(patronesDraw.get(i).getValor()));
                    canvas.drawLine(px0,py0,pxf,pyf,miPincel);//Abajo
                    px0=pxf;
                    py0=pyf;
                }

                patronesDraw =patronDao.getPatrones("AR");
                miPincel.setColor(Color.YELLOW);
                miPincel.setStrokeWidth(5);
                for (int i = 0; i < patronesDraw.size(); i++) {
                    ARpxf = ARpx0 + ARavance;
                    ARpyf = 280 + (-1 * (patronesDraw.get(AR_i).getValor()));
                    canvas.drawLine(ARpx0, ARpy0, ARpxf, ARpyf, miPincel);//ARriba
                    ARpx0 = ARpxf;
                    ARpy0 = ARpyf;
                    AR_i += 1;
                }


                patronesDraw =patronDao.getPatrones("CL");
                miPincel.setColor(Color.GRAY);
                miPincel.setStrokeWidth(5);
                for (int i = 0; i < patronesDraw.size(); i++) {
                    CLpxf = CLpx0 + CLavance;
                    CLpyf = 640 + (-1 * (patronesDraw.get(CL_i).getValor()));
                    canvas.drawLine(CLpx0, CLpy0, CLpxf, CLpyf, miPincel);//
                    CLpx0 = CLpxf;
                    CLpy0 = CLpyf;
                    CL_i += 1;
                }
                patronesDraw =patronDao.getPatrones("DE");
                miPincel.setColor(Color.GREEN);
                miPincel.setStrokeWidth(5);
                for (int i = 0; i < patronesDraw.size(); i++) {
                    DEpxf = DEpx0 + DEavance;
                    DEpyf = 460 + (-1 * (patronesDraw.get(DE_i).getValor()));
                    canvas.drawLine(DEpx0, DEpy0, DEpxf, DEpyf, miPincel);//ARriba
                    DEpx0 = DEpxf;
                    DEpy0 = DEpyf;
                    DE_i += 1;
                }
                patronesDraw =patronDao.getPatrones("IZ");
                miPincel.setColor(Color.BLUE);
                miPincel.setStrokeWidth(5);
                for (int i = 0; i < patronesDraw.size(); i++) {
                    IZpxf = IZpx0 + IZavance;
                    IZpyf = 820 + (-1 * (patronesDraw.get(IZ_i).getValor()));
                    canvas.drawLine(IZpx0, IZpy0, IZpxf, IZpyf, miPincel);//ARriba
                    IZpx0 = IZpxf;
                    IZpy0 = IZpyf;
                    IZ_i += 1;
                }

            }
            catch (Exception e){};
            /*countDownTimer = new CountDownTimer(10* 1000,600) {
                @Override
                public void onTick(long millisUntilFinished) {
                    try{
                        miPincel.setColor(Color.YELLOW);
                        miPincel.setStrokeWidth(5);

                        ARpxf = ARpx0 + ARavance;
                        ARpyf = 280 + (-1*(patronesDraw.get(AR_i).getValor()));
                        canvas.drawLine(ARpx0,ARpy0,ARpxf,ARpyf,miPincel);//ARriba
                        ARpx0=ARpxf;
                        ARpy0=ARpyf;
                        AR_i+=1;

                    }
                    catch (Exception e){};
                }

                @Override
                public void onFinish() {
                    Toast.makeText(Ver.this, "fin", Toast.LENGTH_SHORT).show();
                }
            }.start();*/
        }


    }
}
