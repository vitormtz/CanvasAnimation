package com.example.canvasanimation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class SpriteSheetView extends View {

    private Bitmap spriteSheet;
    private int totalFrames = 25;
    private int frameLargura;
    private int frameAltura;
    private int frameAtual = 0;
    private boolean rodando = false;

    public SpriteSheetView(Context context) {
        super(context);
        inicializar();
    }

    public SpriteSheetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicializar();
    }

    private void inicializar() {
        spriteSheet = BitmapFactory.decodeResource(getResources(), R.drawable.mario_sprite_sheet);
        frameLargura = (int) (spriteSheet.getWidth() / 5.19);
        frameAltura = (int) (spriteSheet.getHeight() / 5.20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (spriteSheet != null) {
            int linha = 1;
            int coluna = frameAtual % 5;

            Rect origem = new Rect(
                    coluna * frameLargura,
                    linha * frameAltura,
                    (coluna + 1) * frameLargura,
                    (linha + 1) * frameAltura
            );

            Rect destino = new Rect(
                    getWidth()/2 - frameLargura/2,
                    getHeight()/2 - frameAltura/2,
                    getWidth()/2 + frameLargura/2,
                    getHeight()/2 + frameAltura/2
            );

            canvas.drawBitmap(spriteSheet, origem, destino, null);
        }
    }

    public void iniciarAnimacao() {
        rodando = true;
        new Thread(new AnimacaoThread()).start();
    }

    public void pararAnimacao() {
        rodando = false;
    }

    private class AnimacaoThread implements Runnable {
        @Override
        public void run() {
            while (rodando) {
                frameAtual = (frameAtual + 1) % totalFrames;

                postInvalidate();
                try {
                    Thread.sleep(91);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}