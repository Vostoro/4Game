package com.jewco.kosterman.game;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        gameView = new GameView(this, size.x, size.y);
        setContentView(gameView);
    }
@Override
protected void onPause(){
    super.onPause();
    gameView.pause();
}
    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }
}
