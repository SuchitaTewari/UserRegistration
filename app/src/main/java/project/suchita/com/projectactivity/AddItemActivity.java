package project.suchita.com.projectactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etItemName,etDescription,etPrice;
    Spinner spinner;
    Button btnAdd;
    Feed feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        etItemName = (EditText) findViewById(R.id.etItemName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etPrice = (EditText)findViewById(R.id.etPrice);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        feed = new Feed();
        spinner =(Spinner)findViewById(R.id.sUnit);


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        spinner.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(etItemName.getText() != null && etItemName.getText().toString().length()>0){
            feed.setItem_name(etItemName.getText().toString());
        }else{
            etItemName.setError("Please enter item name");
            return;
        }if(etPrice.getText() != null && etPrice.getText().toString().length()>0){
            feed.setPrice(Double.parseDouble(etPrice.getText().toString()));
        }else{
            etPrice.setError("Pleasde enter price");
            return;
        }
        feed.setDescription(etDescription.getText().toString());
        feed.setUnit((Integer) spinner.getSelectedItem());
        FeedOrm.insertFeed(this, feed);
        Toast.makeText(AddItemActivity.this, "Item saved successfully!", Toast.LENGTH_SHORT).show();
        finish();

    }
}
