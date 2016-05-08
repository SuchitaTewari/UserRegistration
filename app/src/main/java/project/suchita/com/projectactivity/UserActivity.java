package project.suchita.com.projectactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class UserActivity extends AppCompatActivity {

    Button btnBrowser, btnMap,btnItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        btnBrowser = (Button) findViewById(R.id.btnBrowser);
        btnMap= (Button) findViewById(R.id.btnMap);
        btnItem=(Button)findViewById(R.id.btnItem);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WebActivity.class);
                startActivity(intent);
            }
        });



        btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),MapActivity.class);
//                startActivity(intent);
            }
        });

        btnItem.setOnClickListener(new View.OnClickListener(){
                                      @Override
                                  public void onClick(View v){
                                          Intent intent = new Intent(getApplicationContext(),ListActivity.class);
                                          startActivity(intent);
                                      }
                                   }
        );
    }

}
