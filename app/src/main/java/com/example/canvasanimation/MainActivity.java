package com.example.canvasanimation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SpriteSheetView spriteSheetView;
    private Button btnIniciar, btnParar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spriteSheetView = findViewById(R.id.spriteSheetView);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnParar = findViewById(R.id.btnParar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spriteSheetView.iniciarAnimacao();
            }
        });

        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spriteSheetView.pararAnimacao();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        spriteSheetView.pararAnimacao();
    }
}