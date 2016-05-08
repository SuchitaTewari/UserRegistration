package project.suchita.com.projectactivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
    EditText tvUserName,tvpassword,etEmail;
    public Button btnLogin,btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        tvUserName = (EditText)findViewById(R.id.tvUserName);
        tvpassword = (EditText)findViewById(R.id.tvPassword);
        btnLogin = (Button) findViewById(R.id.btLogout);
        btnRegister =(Button)findViewById(R.id.btnRegister);
        etEmail=(EditText) findViewById(R.id.etEmail);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnRegister){

            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            return;
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String saveUserName = sharedPreferences.getString("username", "");
        String savePassword = sharedPreferences.getString("password","");
        if (tvUserName.getText()!= null && tvUserName.getText().toString().equals(saveUserName) && tvpassword.getText()!=null && tvpassword.getText().toString().equals(savePassword)){
                Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);

       }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Please fill valid details")
                    .setTitle("Error");

            // 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
            dialog.show();
           // isEmailValid(etEmail);
        }
    }
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
