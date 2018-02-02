package nyp.edu.caloriescounterapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//declare elements
	EditText Name = null;
	Spinner greetingSpinner = null;
	float currentWeight;
	float idealWeight;
	String nameString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//set layout to activity_main.xml
		setContentView(R.layout.activity_main);
		
		//find name EditText * assign to Name
		Name = (EditText) findViewById(R.id.nameTB);

	}

	public void onResume() {
		super.onResume();
		retrievePreferences();
	}

	//check if user opens the application for the first time
	public void retrievePreferences() {
		SharedPreferences prefs = getSharedPreferences("profile",
				MODE_PRIVATE);
		if (prefs.contains("Name")) {
			nameString = prefs.getString("Name", "");
			idealWeight = prefs.getFloat("idealWeight", 0);

			//greet the user
			TextView nameLabel = (TextView)findViewById(R.id.nameLabel);
			nameLabel.setText("Hello, " + nameString);
			
			TextView idealWeightLabel = (TextView)findViewById(R.id.idealWeightLabel);
			idealWeightLabel.setText("Ideal weight - " + idealWeight+"kg");
		}

		else
		{
			//if user enters application for the first time, redirect to welcome.java
			startActivity(new Intent("nyp.edu.caloriescounterapp.welcome"));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Method for main page's navigation buttons

	public void onNextActivityClicked1(View v)
	{
		startActivity(new Intent("nyp.edu.caloriescounterapp.CaloriesCalculator"));
	}
	public void onNextActivityClicked2(View v)
	{
		startActivity(new Intent("nyp.edu.caloriescounterapp.CaloriesIntakeHistory"));
	}
	public void onNextActivityClicked3(View v)
	{
		startActivity(new Intent("nyp.edu.caloriescounterapp.CaloriesInformation"));
	}
	public void onNextActivityClicked4(View v)
	{
		startActivity(new Intent("nyp.edu.caloriescounterapp.AboutCaloriesCounter"));
	}
}
