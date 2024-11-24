package com.example.l_feladat_4;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    RatingBar ratingBar1, ratingBar2, ratingBar3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ratingBar1 = findViewById(R.id.ratingBar1);
        ratingBar2 = findViewById(R.id.ratingBar2);
        ratingBar3 = findViewById(R.id.ratingBar3);
    }

    @Override
    public void onBackPressed(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Float[] ratings = new Float[]{ratingBar1.getRating(), ratingBar2.getRating(), ratingBar3.getRating()};
                float maxRatingValue = ratings[0];
                int maxRatingValueIndex = 0;
                for (int i=1; i<ratings.length; i++){
                    if (ratings[i]>maxRatingValue){
                        maxRatingValue = ratings[i];
                        maxRatingValueIndex = i;
                    }
                }
                String message = "A nyertes kép a(z): " + (maxRatingValueIndex+1) + ". kép - " + maxRatingValue + " pont";
                Toast.makeText(MainActivity2.this, message, Toast.LENGTH_LONG).show();
            }
        }, 1000);

        super.onBackPressed();
    }
}