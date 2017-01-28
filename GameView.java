package com.jewco.kosterman.game;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable{

    private boolean playing;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private ArrayList<Star> stars = new ArrayList<>();
    private Friend friend;

   public GameView(Context context, int screenX, int screenY){
       super(context);
       surfaceHolder = getHolder();

       int starNums = 90;
       for(int i =0; i<starNums; i++){
           Star s = new Star(screenX, screenY);
           stars.add(s);
       }
   }
    @Override
    public void run() {
        while(playing) {
            update();
            draw();
        }
    }
    private void draw(){
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.WHITE);
            paint.setTextSize(20);

            for(Star s: stars) {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(), s.getY(), paint);

            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
    public void update(){
        for(Star s: stars){
            s.update(1);
            friend.update(1);
        }
    }
    public void pause(){
        playing = false;
        try{
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


}
