package nyp.edu.caloriescounterapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends Activity{
	
	//declare elements
	EditText name = null;
	EditText age = null;
	Spinner gender = null;
	EditText cWeight = null;
	EditText iWeight = null;
	
	String nameString;
	int ageString;
	String genderString;
	Float cWeightString;
	Float iWeightString;

	protected ArrayAdapter<CharSequence> irAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//set to register.xml
		setContentView(R.layout.register);
		
		//find views
		name = (EditText) findViewById(R.id.nameTB);
		age = (EditText) findViewById(R.id.ageTB);
		gender = (Spinner) findViewById(R.id.gender_spinner);
		cWeight = (EditText) findViewById(R.id.currentWeightTB);
		iWeight = (EditText) findViewById(R.id.idealWeightTB);

		
		//register the user
		Button registerBtn = (Button) findViewById(R.id.btn_Register);
		
		//use arrayAdapter to hold array
		this.irAdapter = ArrayAdapter
				.createFromResource(this, R.array.gender,
						android.R.layout.simple_spinner_item);

		//associate spinner to the adapter
		gender.setAdapter(this.irAdapter);

		registerBtn.setOnClickListener(new View.OnClickListener() {


			public void onClick(View v) {
				
				//check for empty fields
				if((name.getText().toString().equals("")) || (age.getText().toString().equals("")) || (cWeight.getText().toString().equals("")) || (iWeight.getText().toString().equals("")))
				{
					//if empty, display toast
					Toast.makeText(getApplicationContext(),"Ensure all fields are filled", Toast.LENGTH_SHORT).show();
				}
				else
				{
					try {
						
						//assign attributes
						nameString = name.getText().toString();
						ageString = Integer.parseInt(age.getText().toString());
						genderString = gender.getSelectedItem().toString();
						cWeightString = Float.parseFloat(cWeight.getText().toString());
						iWeightString = Float.parseFloat(iWeight.getText().toString());

						//save info
						saveAsPreferences();
						
						//Registration successful
						Toast.makeText(getApplicationContext(),"Profile created successfully!", Toast.LENGTH_SHORT).show();
						finish();
						//redirect back to MainActivity.class
						Intent registeredIntent = new Intent(RegisterActivity.this, MainActivity.class);
						startActivity(registeredIntent);
					}catch(NumberFormatException e) {
						
						//fail to insert
						Toast.makeText(getApplicationContext(),"Invalid input", Toast.LENGTH_SHORT).show();
					} 

				}
			}

		});
	}



	public void saveAsPreferences() {

		//save info into shared preference
		SharedPreferences prefs = getSharedPreferences("profile",MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();

		editor.putString("Name", nameString);
		editor.putInt("Age", ageString);
		editor.putString("Gender", genderString);
		editor.putFloat("currentWeight", cWeightString);
		editor.putFloat("idealWeight", iWeightString);
		editor.commit();
	}


}
