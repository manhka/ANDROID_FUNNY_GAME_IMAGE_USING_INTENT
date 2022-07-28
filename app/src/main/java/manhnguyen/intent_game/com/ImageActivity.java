package manhnguyen.intent_game.com;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.android.material.tabs.TabLayout;

import java.util.Collections;

public class ImageActivity extends Activity {
    TableLayout myTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        myTb = (TableLayout) findViewById(R.id.tbImage);
        int row = 4;
        int column = 3;
        Collections.shuffle(MainActivity.arrayName);
        for (int i = 1; i <= row; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 1; j <= column; j++) {
                ImageView imageView = new ImageView(this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(270, 400);
                imageView.setLayoutParams(layoutParams);
                int position=column*(i-1)+j-1;
                int idImage = getResources().getIdentifier(MainActivity.arrayName.get(position), "drawable", getPackageName());
                imageView.setImageResource(idImage);
                //add imageView into tableRow
                tableRow.addView(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra("imageName",MainActivity.arrayName.get(position));
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
            }
            //add tableRow into table
            myTb.addView(tableRow);
        }
    }
}