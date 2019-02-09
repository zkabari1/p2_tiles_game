package com.example.a2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class GameScreen extends AppCompatActivity {

        Button[] buttons = new Button[16];
        static Random r = new Random();
        int tiles[][] = new int[4][4];

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        addRandomNum();
        }

        // Generate random tile
        private void addRandomNum(){
            int i = r.nextInt((16 - 1) + 1) + 1;
            String b_ID = "button" + i;
            int ID = getResources().getIdentifier(b_ID, "id", getPackageName());
            buttons[i] = ((Button) findViewById(ID));
            if(buttons[i].getText() != null)
                addRandomNum();
            else
                buttons[i].setText("2");
                //buttons[i].setBackgroundColor(@#000);
        }



      /*switch (number) {
            case 0: lable.setBackgroundColor(0xffccc0b2); break;
            case 2: lable.setBackgroundColor(0xffeee4da); break;
            case 4: lable.setBackgroundColor(0xffede0c8); break;
            case 8: lable.setBackgroundColor(0xfff2b179); break;
            case 16: lable.setBackgroundColor(0xfff59563); break;
            case 32: lable.setBackgroundColor(0xfff67c5f); break;
            case 64: lable.setBackgroundColor(0xfff65e3b); break;
            case 128: lable.setBackgroundColor(0xffedcf72); break;
            case 256: lable.setBackgroundColor(0xffedc750); break;
            case 512: lable.setBackgroundColor(0xffedc850); break;
            case 1024: lable.setBackgroundColor(0xffecc640); break;
            default: lable.setBackgroundColor(0xffedc22d); break;
        } */
}