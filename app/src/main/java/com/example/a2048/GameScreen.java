package com.example.a2048;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameScreen extends AppCompatActivity {

        TextView SwipeArea;
        Button[] buttons = new Button[16];
        static Random r = new Random();
        int[][] tiles = new int[4][4];
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game_screen);
            cleardata();
            addRandomNum();
            initializeSwipeArea();

            SwipeArea.setOnTouchListener(new OnSwipeTouchListener(GameScreen.this) {
                public void onSwipeTop() {
                    addRandomNum();
                }
                public void onSwipeRight() {
                    addRandomNum();
                }
                public void onSwipeLeft() {
                    for (int j = 0; j < 4; j++) {
                            for (int k = 3; k >=0 ; k--) {
                                if(tiles[j][k] == 1 && tiles[j][k-1] == 0){
                                    tiles[j][k-1] = tiles[j][k];
                                    buttons[j*4 + k-1].setText(buttons[j*4 + k].getText());
                                    tiles[j][k] = 0;
                                    buttons[j*4 + k].setText("");
                                }
                            }
                        }
                    addRandomNum();
                }
                public void onSwipeBottom() {
                    addRandomNum();
                }
            });
        }
    private void initializeSwipeArea() {
        SwipeArea=(TextView) findViewById(R.id.SwipeArea);
        }
        //clear data
        private void  cleardata() {
            for (int i = 0; i < 16; i++) {
                String b_ID = "button" + i;
                int ID = getResources().getIdentifier(b_ID, "id", getPackageName());
                buttons[i] = ((Button) findViewById(ID));
                buttons[i].setText("");
            }
        }
        // Generate random tile
        private void addRandomNum(){
            int i = r.nextInt(16);
            int j = i/4;
            int k = i % 4;
            String b_ID = "button" + i;
            int ID = getResources().getIdentifier(b_ID, "id", getPackageName());
            buttons[i] = ((Button) findViewById(ID));
            if(buttons[i].getText() == "") {
                buttons[i].setText("2");
                tiles[j][k] = 1;
                switch (Integer.parseInt(buttons[i].getText().toString())) {
                    case 2: buttons[i].setBackgroundColor(0xffeee4da); break;
                    case 4: buttons[i].setBackgroundColor(0xffede0c8); break;
                    case 8: buttons[i].setBackgroundColor(0xfff2b179); break;
                    case 16: buttons[i].setBackgroundColor(0xfff59563); break;
                    case 32: buttons[i].setBackgroundColor(0xfff67c5f); break;
                    case 64: buttons[i].setBackgroundColor(0xfff65e3b); break;
                    case 128: buttons[i].setBackgroundColor(0xffedcf72); break;
                    case 256: buttons[i].setBackgroundColor(0xffedc750); break;
                    case 512: buttons[i].setBackgroundColor(0xffedc850); break;
                    case 1024: buttons[i].setBackgroundColor(0xffecc640); break;
                   // default: lable.setBackgroundColor(0xffedc22d); break;
                }

            }
            else{
                addRandomNum();
            }
        }
}