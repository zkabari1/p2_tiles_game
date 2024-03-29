package com.example.a2048;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import java.util.Random;

public class GameScreen extends AppCompatActivity {

        TextView SwipeArea;
        Button[] buttons = new Button[16];
        static Random r = new Random();
        int[][] tiles = new int[4][4];
        int moves;
        TextView countmoves;
        String count;
        boolean gameover=false;

     @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game_screen);
            newgame();
            initializeSwipeArea();

            //Media Player
            final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music3);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();

            //Switch to on/off music
            Switch musicbtn = (Switch) findViewById(R.id.switch_music);
            musicbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                 if (!bChecked) {
                    mediaPlayer.pause();
                 } else {
                     mediaPlayer.setLooping(true);
                     mediaPlayer.start();
                 }
             }
         });

        //New game
         Button newbtn = (Button) findViewById(R.id.button_newgame);
         newbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newgame();
                }
            });

         //End game
         ImageButton endgame = (ImageButton) findViewById(R.id.imageButtonendgame);
         endgame.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 gameover();
             }
         });

         //Set SwipeArea Left, Top, Bottom and Right logic
         SwipeArea.setOnTouchListener(new OnSwipeTouchListener(GameScreen.this) {
                public void onSwipeTop() {
                    boolean flg = false;
                    for (int k = 0; k < 4; k++) {
                        for (int j = 0; j < 3; j++) {
                            if ((tiles[j][k] == tiles[j+1][k]) && tiles[j][k]!=0) {
                                tiles[j][k] = tiles[j][k] + tiles[j+1][k];
                                tiles[j+1][k] = 0;
                                flg=true;
                                break;
                            }
                        }
                        for (int j = 3; j > 0; j--) {
                            if (tiles[j][k] > 0 && tiles[j - 1][k] == 0) {
                                tiles[j - 1][k] = tiles[j][k];
                                tiles[j][k] = 0;
                                flg = true;
                            }
                        }
                    }
                    if(flg)
                    addRandomNum();
                    else
                        gameover=true;
                }
                public void onSwipeRight() {
                    boolean flg = false;
                    for (int j = 0; j < 4; j++) {
                        for(int k=2;k>=0;k--){
                            if ((tiles[j][k] == tiles[j][k+1]) && tiles[j][k]!=0) {
                                tiles[j][k+1] = tiles[j][k] + tiles[j][k+1];
                                tiles[j][k] = 0;
                                flg=true;
                                break;
                            }
                        }
                        for (int k = 0; k < 3; k++) {
                            if (tiles[j][k] > 0 && tiles[j][k + 1] == 0) {
                                tiles[j][k + 1] = tiles[j][k];
                                tiles[j][k] = 0;
                                flg = true;
                            }
                        }
                    }
                    if(flg)
                        addRandomNum();
                    else
                        gameover=true;
                }
                public void onSwipeLeft() {
                    boolean flg = false;
                    for (int j = 0; j < 4; j++) {
                        for(int k=1;k<4;k++){
                        if ((tiles[j][k] == tiles[j][k-1]) && tiles[j][k]!=0) {
                                tiles[j][k-1] = tiles[j][k] + tiles[j][k-1];
                                tiles[j][k] = 0;
                                flg=true;
                                break;
                            }
                        }
                        for (int k = 3; k > 0; k--) {
                            if (tiles[j][k] > 0 && tiles[j][k - 1] == 0) {
                                tiles[j][k - 1] = tiles[j][k];
                                tiles[j][k] = 0;
                                flg = true;
                            }
                        }
                    }
                    if(flg)
                        addRandomNum();
                    else
                        gameover=true;
                }
                public void onSwipeBottom() {
                    boolean flg=false;
                    for (int k = 0; k < 4; k++) {
                        for (int j = 2; j >= 0 ; j--) {
                            if((tiles[j][k] == tiles[j+1][k]) && tiles[j][k]!=0) {
                                tiles[j+1][k] = tiles[j][k] + tiles[j+1][k];
                                tiles[j][k] = 0;
                                flg=true;
                                break;
                            }
                        }
                        for (int j = 0; j < 3; j++) {
                            if (tiles[j][k] > 0 && tiles[j + 1][k] == 0) {
                                tiles[j + 1][k] = tiles[j][k];
                                tiles[j][k] = 0;
                                flg = true;
                            }
                        }
                    }
                    if(flg)
                    addRandomNum();
                    else
                        gameover=true;
                }
            });

         //No moves available then game over
        if(gameover)
            gameover();
        }

        //Set full screen swipe area
        private void initializeSwipeArea() {
        SwipeArea=(TextView) findViewById(R.id.SwipeArea);
        }

        //New Game
        private void  newgame() {
            moves=-1;
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    tiles[i][j]=0;
                }
            }
            displaydata();
            addRandomNum();
        }

        //display tiles
        void displaydata(){
            for (int i = 0; i < 16; i++) {
                int j=i/4;
                int k=i%4;
                String b_ID = "button" + i;
                int ID = getResources().getIdentifier(b_ID, "id", getPackageName());
                buttons[i] = ((Button) findViewById(ID));
                setvalue(i,tiles[j][k]);
                switch (getvalue(i)) {
                    case 2: buttons[i].setBackgroundColor(Color.parseColor("#0067b1"));
                        buttons[i].setTextColor(Color.WHITE); break;
                    case 4: buttons[i].setBackgroundColor(Color.parseColor("#35bfc0"));
                        buttons[i].setTextColor(Color.WHITE);break;
                    case 8: buttons[i].setBackgroundColor(Color.parseColor("#6c8cb8"));
                        buttons[i].setTextColor(Color.WHITE);break;
                    case 16: buttons[i].setBackgroundColor(Color.parseColor("#307e80"));
                        buttons[i].setTextColor(Color.WHITE);break;
                    case 32: buttons[i].setBackgroundColor(Color.parseColor("#cc7770"));
                        buttons[i].setTextColor(Color.WHITE);break;
                    case 64: buttons[i].setBackgroundColor(Color.parseColor("#3f512b"));
                        buttons[i].setTextColor(Color.WHITE);break;
                    case 128: buttons[i].setBackgroundColor(Color.parseColor("#c21d5f"));
                        buttons[i].setTextColor(Color.WHITE);break;
                    case 256: buttons[i].setBackgroundColor(Color.parseColor("#feb302"));
                        buttons[i].setTextColor(Color.WHITE);break;
                    case 512: buttons[i].setBackgroundColor(Color.parseColor("#312545"));
                        buttons[i].setTextColor(Color.WHITE);break;
                    case 1024: buttons[i].setBackgroundColor(Color.parseColor("#99a751"));
                        buttons[i].setTextColor(Color.WHITE);break;
                    case 2048: buttons[i].setBackgroundColor(Color.parseColor("#a28cff"));
                        buttons[i].setTextColor(Color.WHITE);break;
                    default: buttons[i].setTextColor(Color.parseColor("#dee0e0"));
                        buttons[i].setBackgroundColor(Color.parseColor("#dee0e0"));break;
                }
            }
        }

        // Generate random tile
        public void addRandomNum(){

            int i = r.nextInt(16);
            int j = i/4;
            int k = i % 4;
            String b_ID = "button" + i;
            int ID = getResources().getIdentifier(b_ID, "id", getPackageName());
            buttons[i] = ((Button) findViewById(ID));
            if(getvalue(i) == 0) {
                tiles[j][k] = 2;
                setvalue(i,tiles[j][k]);
                displaydata();
                moves++;
                moveschanges(moves);
            }
            else{
                addRandomNum();
            }
        }

        //get value from button
        int getvalue(int i){
            return Integer.parseInt(buttons[i].getText().toString());
        }

        //set value to button
        void setvalue(int i, int value){
            String val = String.valueOf(value);
            buttons[i].setText(val);
     }

        //display no of moves
        void moveschanges(int moves) {
            countmoves = (TextView) findViewById(R.id.movesbtn);
            count = String.valueOf(moves);
            countmoves.setText(count);
        }

        //gameover
        void gameover(){
            Intent gameover = new Intent(GameScreen.this, GameOver.class);
            startActivity(gameover);
            finish();
            }
    }