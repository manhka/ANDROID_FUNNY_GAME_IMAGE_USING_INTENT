package manhnguyen.intent_game.com;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ImageView imageQuestion, imageAnswer;
    int REQUEST_CODE_IMAGE = 123;
    String nameEqual = "";
    TextView mark;
    int total=100;
    public static ArrayList<String> arrayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mark =(TextView) findViewById(R.id.mark);
        mark.setText(total+"");
        imageQuestion = (ImageView) findViewById(R.id.imageViewQuestion);
        imageAnswer = (ImageView) findViewById(R.id.imageViewAnswer);
        String[] arrName = getResources().getStringArray(R.array.list_name);
        arrayName = new ArrayList<>(Arrays.asList(arrName));
        // trộn mảng
        Collections.shuffle(arrayName);
        // get name
        nameEqual = arrayName.get(2);
        int idImage = getResources().getIdentifier(arrayName.get(2), "drawable", getPackageName());
        imageQuestion.setImageResource(idImage);
        imageAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, ImageActivity.class), REQUEST_CODE_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null) {
            String imageName = data.getStringExtra("imageName");
            int idImage = getResources().getIdentifier(imageName, "drawable", getPackageName());
            imageAnswer.setImageResource(idImage);
            if (nameEqual.equals(imageName)) {
                Toast.makeText(this, "Win", Toast.LENGTH_SHORT).show();

                total+=5;
                Reload();
            } else {
                Toast.makeText(this, "Lose", Toast.LENGTH_SHORT).show();
                total-=5;
            }
            mark.setText(total+"");
        }
        if (requestCode==REQUEST_CODE_IMAGE && resultCode==RESULT_CANCELED){
            Toast.makeText(this, "Please! choose image to play", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.reload) {
            Collections.shuffle(arrayName);
            // get name
            nameEqual = arrayName.get(2);
            int idImage = getResources().getIdentifier(arrayName.get(2), "drawable", getPackageName());
            imageQuestion.setImageResource(idImage);
        }
        return super.onOptionsItemSelected(item);
    }

    private void Reload() {
        new CountDownTimer(2000, 100) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                Collections.shuffle(arrayName);
                // get name
                nameEqual = arrayName.get(2);
                int idImage = getResources().getIdentifier(arrayName.get(2), "drawable", getPackageName());
                imageQuestion.setImageResource(idImage);
            }
        }.start();
    }

}