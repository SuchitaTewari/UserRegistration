package project.suchita.com.projectactivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {

    Button btnRegister;
    EditText etFirstName,etLastName,etAdrress1,etAddress2,dateEditText,etEmail;
    EditText etCountry,etState,etCity,etPassword,etCPassword,tvMobile;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        etFirstName =  (EditText) findViewById(R.id.tvFirstName);
        etLastName = (EditText) findViewById( R.id.tvLastName);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        rgGender = (RadioGroup) findViewById(R.id.gender);
        dateEditText = (EditText)findViewById(R.id.tvDate);
        etPassword = (EditText) findViewById(R.id.tvPassword);
        etCPassword = (EditText) findViewById(R.id.tvRePassword);
        etAdrress1 = (EditText)findViewById(R.id.etAddress1);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etAddress2=(EditText)findViewById(R.id.etAddress2);
        tvMobile = (EditText) findViewById(R.id.tvMobile);

        btnRegister.setOnClickListener(this);
        // Country Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.country, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // Set default value select state for state spinner
        setDefaultState();
        setDefaultCity();

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public  void setDefaultState() {
        Spinner stateSpinner = (Spinner) findViewById(R.id.state_spinner);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Select State");
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);

    }

    public  void setDefaultCity() {
        Spinner stateSpinner = (Spinner) findViewById(R.id.city_spinner);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Select City");

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(cityAdapter);

    }

    // Date picker
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateLabel();
        }

    };


    private void updateDateLabel() {

        String myFormat = "mm/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateEditText.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case (R.id.spinner):
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                String countryName = spinner.getItemAtPosition(position).toString();
                Log.i("", "country spinner is selected");
                // set spinner adaper for state
                setStateSpinnerAdapter(countryName);
                break;

            case (R.id.state_spinner):
                Log.i("","state spinner is selected");
                Spinner cityspinner = (Spinner) findViewById(R.id.state_spinner);
                String stateName = cityspinner.getItemAtPosition(position).toString();
                Log.i("", "country spinner is selected");
                // set spinner adaper for state
                setCitySpinnerAdapter(stateName);
                break;

            case (R.id.city_spinner):
                Log.i("", "city spinner is selected");

                break;


        }
    }


    public void  setStateSpinnerAdapter(String countryName) {
        // State spinner
        Spinner state_spinner = (Spinner) findViewById(R.id.state_spinner);
        if(countryName.equalsIgnoreCase("india")) {
            ArrayAdapter<CharSequence> states = ArrayAdapter.createFromResource(this,
                    R.array.india_state, android.R.layout.simple_spinner_item);
            states.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            state_spinner.setAdapter(states);
            state_spinner.setOnItemSelectedListener(this);
        } else {

            setDefaultState();

        }

    }

    public void  setCitySpinnerAdapter(String countryName) {
        // State spinner
        Spinner state_spinner = (Spinner) findViewById(R.id.city_spinner);
        if(countryName.equalsIgnoreCase("uttarakhand")) {
            ArrayAdapter<CharSequence> states = ArrayAdapter.createFromResource(this,
                    R.array.uttarakhand_city, android.R.layout.simple_spinner_item);
            states.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            state_spinner.setAdapter(states);
            state_spinner.setOnItemSelectedListener(this);
        } else {
            setDefaultCity();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        int radioButtonID = rgGender.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)rgGender.findViewById(radioButtonID);
        if(etFirstName.getText()!=null && etLastName.getText()!=null && (radioButton!= null && radioButton.getText()!=null) && (etPassword.getText()!=null && etCPassword.getText()!= null )&& etEmail.getText()!=null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            String message = "";
            if (!(etPassword.getText().toString() .equals(etCPassword.getText().toString())))
                message = "Password Does not match";
            else
                message = "Register succesfully";
            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage(message)
                    .setTitle("Done");

            // 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
            dialog.show();
            showLoginActivity();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Please fill all the details")
                    .setTitle("Error");

        // 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
            dialog.show();


        }



    }
        public static boolean isEmailValid(String etEmail) {
            boolean isValid = false;

            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            CharSequence inputStr = etEmail;

            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches()) {
                isValid = true;
            }
            return isValid;
        }


    public void showLoginActivity() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",etFirstName.getText().toString());
        editor.putString("password",etPassword.getText().toString());
        editor.commit();
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);

    }
}
