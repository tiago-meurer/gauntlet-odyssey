package com.gilvanstudios.gauntletodyssey;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class FistView extends View
{
    private Bitmap fist[] = new Bitmap[2];
    private int fistX = 10;
    private int fistY;
    private int fistSpeed;

    private boolean touch = false;

    private int canvasWidth, canvasHeight;

    private int yellowX, yellowY, yellowSpeed = 40;
    private Paint yellowPaint = new Paint();

    private int greenX, greenY, greenSpeed = 33;
    private Paint greenPaint = new Paint();

    private int redX, redY, redSpeed = 28;
    private Paint redPaint = new Paint();

    private int blueX, blueY, blueSpeed = 22;
    private Paint bluePaint = new Paint();

    private int purpleX, purpleY, purpleSpeed = 17;
    private Paint purplePaint = new Paint();

    private int orangeX, orangeY, orangeSpeed = 25;
    private Paint orangePaint = new Paint();


    private int grayX, grayY, graySpeed = 33;
    private Paint grayPaint = new Paint();

    private int score, highScore, energyCounterOfFist;

    private Bitmap backgroundImage;

    private Paint scorePaint = new Paint();

    private Bitmap energy[] = new Bitmap[2];

    private SoundPlayer sound;



    public FistView (Context context) {
        super(context);

        sound = new SoundPlayer(getContext());

        // Fist sprites
        fist[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fist1);
        fist[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fist2);
        // game screen background
        backgroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        // yellow ball color properties
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);
        // green ball color properties
        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);
        // red ball color
        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);
        // blue ball properties
        bluePaint.setColor(Color.BLUE);
        bluePaint.setAntiAlias(false);
        // purple ball properties
        purplePaint.setColor(Color.rgb(210, 91, 247));
        purplePaint.setAntiAlias(false);
        // orange ball properties
        orangePaint.setColor(Color.rgb(249, 141, 24));
        orangePaint.setAntiAlias(false);
        // gray ball color properties
        grayPaint.setColor(Color.GRAY);
        grayPaint.setAntiAlias(false);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);
        //energy counter sprites
        energy[0] = BitmapFactory.decodeResource(getResources(), R.drawable.rays);
        energy[1] = BitmapFactory.decodeResource(getResources(),R.drawable.no_ray);

        fistY = 550;
        score = 0;
        energyCounterOfFist = 3;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();


        canvas.drawBitmap(backgroundImage, 0, 0,null);

        int minFistY = fist[0].getHeight();
        int maxFistY = canvasHeight - fist[0].getHeight() * 3;
        fistY = fistY + fistSpeed;

        if(fistY < minFistY)
        {
            fistY = minFistY;
        }
        if(fistY > maxFistY)
        {
            fistY = maxFistY;
        }
        fistSpeed = fistSpeed + 2;

        if (touch)
        {
            canvas.drawBitmap(fist[1], fistX, fistY,null);
            touch = false;
        }
        else
        {
            canvas.drawBitmap(fist[0], fistX, fistY,null);
        }

        // yellow ball property

        yellowX = yellowX - yellowSpeed;

        if(hitBallChecker(yellowX, yellowY))
        {
            score = score + 10;
            yellowX = -100;
            sound.playOver2Sound();
        }

        if(yellowX < 0)
        {
            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random()* (maxFistY - minFistY)) + minFistY;
        }
        canvas.drawCircle(yellowX, yellowY,13, yellowPaint);

        //green ball property

        greenX = greenX - greenSpeed;

        if(hitBallChecker(greenX, greenY))
        {
            score = score + 8;
            greenX = -100;
            sound.playOverSound();
        }

        if(greenX < 0)
        {
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random()* (maxFistY - minFistY)) + minFistY;
        }
        canvas.drawCircle(greenX, greenY,15, greenPaint);

        // red ball properties

        redX = redX - redSpeed;

        if(hitBallChecker(redX, redY))
        {
            score = score + 6;
            redX = -100;
            sound.playOverSound();
        }

        if(redX < 0)
        {
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random()* (maxFistY - minFistY)) + minFistY;
        }
        canvas.drawCircle(redX, redY,15, redPaint);

        // blue ball properties

        blueX = blueX - blueSpeed;

        if(hitBallChecker(blueX, blueY))
        {
            score = score + 3;
            blueX = -100;
            sound.playOverSound();
        }

        if(blueX < 0)
        {
            blueX = canvasWidth + 21;
            blueY = (int) Math.floor(Math.random()* (maxFistY - minFistY)) + minFistY;
        }
        canvas.drawCircle(blueX, blueY,17, bluePaint);

        //purple ball properties

        purpleX = purpleX - purpleSpeed;

        if(hitBallChecker(purpleX, purpleY))
        {
            score = score + 1;
            purpleX = -100;
            sound.playOverSound();
        }

        if(purpleX < 0)
        {
            purpleX = canvasWidth + 21;
            purpleY = (int) Math.floor(Math.random()* (maxFistY - minFistY)) + minFistY;
        }
        canvas.drawCircle(purpleX, purpleY,19, purplePaint);

        //Orange ball properties

        orangeX = orangeX - orangeSpeed;

        if(hitBallChecker(orangeX, orangeY))
        {
            score = score + 5;
            orangeX = -100;
            sound.playOverSound();
        }

        if(orangeX < 0)
        {
            orangeX = canvasWidth + 21;
            orangeY = (int) Math.floor(Math.random()* (maxFistY - minFistY)) + minFistY;
        }
        canvas.drawCircle(orangeX, orangeY,17, orangePaint);

        // grey (dangerous) ball properties

        grayX = grayX - graySpeed;

        if(hitBallChecker(grayX, grayY))
        {
            grayX = -100;
            energyCounterOfFist--;
            sound.playHitSound();

            if (energyCounterOfFist ==0)
            {
                sound.dieSound();
                Toast.makeText(getContext(), "'ENDGAME' FOR YOU!", Toast.LENGTH_SHORT).show();
                Intent endGameIntent = new Intent(getContext(), EndGameActivity.class);
                endGameIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                endGameIntent.putExtra("SCORE", score);
                endGameIntent.putExtra("HIGH_SCORE", highScore);
                getContext().startActivity(endGameIntent);


            }
        }

        if(grayX < 0)
        {
            grayX = canvasWidth + 21;
            grayY = (int) Math.floor(Math.random()* (maxFistY - minFistY)) + minFistY;
        }
        canvas.drawCircle(grayX, grayY,33, grayPaint);

        //Energy bar
        canvas.drawText("Gauntlet's Energy: " + score, 20,60, scorePaint);

        for (int i=0; i<3; i++)
        {
            int x = (int) (740 + energy[0].getWidth() * 0.8 * i);
            int y = 30;

            if (i < energyCounterOfFist)
            {
                canvas.drawBitmap(energy[0], x, y,null);
            }
            else
            {
                canvas.drawBitmap(energy[1], x, y,null);
            }
        }

    }
    // hit ball checker
    public boolean hitBallChecker(int x, int y)
    {

        if (fistX < x && x < (fistX + fist[0].getWidth()) && fistY < y && y < (fistY + fist[0].getHeight()))
        {
            return true;
        }

        return false;

    }

    //Touch action class
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;

            fistSpeed = -22;
        }
        return true;
    }
}
