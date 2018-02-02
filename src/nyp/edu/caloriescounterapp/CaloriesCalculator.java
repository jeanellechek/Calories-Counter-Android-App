package nyp.edu.caloriescounterapp;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CaloriesCalculator extends Activity {
	
	//declare elements
	Spinner breakfastSpinner = null;
	Spinner lunchSpinner = null;
	Spinner dinnerSpinner = null;
	EditText currentWeight = null;
	int breakfastCalories;
	int lunchCalories;
	int dinnerCalories;
	int calories=0;
	TextView Date =null;
	protected ArrayAdapter<CharSequence> foodAdapter;

	static final int DATE_DIALOG_ID = 999;
	int day, month, year;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);

		//date
		setCurrentDateOnView();
		addListenerOnButton();

		//find views by id

		Date = (TextView) findViewById(R.id.dateTB);
		breakfastSpinner = (Spinner) findViewById(R.id.breakfast_spinner);
		lunchSpinner = (Spinner) findViewById(R.id.lunch_spinner);
		dinnerSpinner = (Spinner) findViewById(R.id.dinner_spinner);
		currentWeight = (EditText) findViewById(R.id.currentWeightTB);
		this.foodAdapter = ArrayAdapter.createFromResource(this,R.array.food, android.R.layout.simple_spinner_item);


		//print values on spinners
		breakfastSpinner.setAdapter(this.foodAdapter);
		lunchSpinner.setAdapter(this.foodAdapter);
		dinnerSpinner.setAdapter(this.foodAdapter);

		breakfastSpinner
		.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {

				//search for calorie value based on selected option
				if (position == 0)
				{
					breakfastCalories=694;
				}
				else if (position == 1)
				{
					breakfastCalories=411;
				}

				else if (position == 2)
				{
					breakfastCalories=324;
				}
				else if (position == 3)
				{
					breakfastCalories=700;
				}

				else if (position == 4)
				{
					breakfastCalories=494;
				}
				else if (position == 5)
				{
					breakfastCalories=0;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		lunchSpinner
		.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				// stuff here to handle item selection
				if (position == 0)
				{
					lunchCalories=694;
				}
				else if (position == 1)
				{
					lunchCalories=411;
				}

				else if (position == 2)
				{
					lunchCalories=324;
				}
				else if (position == 3)
				{
					lunchCalories=700;
				}

				else if (position == 4)
				{
					lunchCalories=494;
				}
				else if (position == 5)
				{
					lunchCalories=0;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		dinnerSpinner
		.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int position, long id) {
				// stuff here to handle item selection
				if (position == 0)
				{
					dinnerCalories=694;
				}
				else if (position == 1)
				{
					dinnerCalories=411;
				}

				else if (position == 2)
				{
					dinnerCalories=324;
				}
				else if (position == 3)
				{
					dinnerCalories=700;
				}

				else if (position == 4)
				{
					dinnerCalories=494;
				}
				else if (position == 5)
				{
					dinnerCalories=0;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});


		//Calculate button
		Button calculateBtn = (Button) findViewById(R.id.btn_calculate);
		calculateBtn.setOnClickListener(new View.OnClickListener() {


			public void onClick(View v) {
				//check if currentWeight is empty
				if(currentWeight.getText().toString().equals(""))
				{
					//if empty, display toast
					Toast.makeText(getApplicationContext(),"Please fill up current weight", Toast.LENGTH_SHORT).show();
				}
				else
				{
					finish();
					//redirect to ResultActivity.java
					Intent intent = new Intent ("nyp.edu.caloriescounterapp.ResultActivity");
					Bundle extras = new Bundle();

					//set values on intent
					extras.putString("Date", Date.getText().toString());
					extras.putString("breakfast", breakfastSpinner.getSelectedItem().toString());
					extras.putString("lunch", lunchSpinner.getSelectedItem().toString());
					extras.putString("dinner", dinnerSpinner.getSelectedItem().toString());
					extras.putInt("breakfastCalories", breakfastCalories);
					extras.putInt("lunchCalories", lunchCalories);
					extras.putInt("dinnerCalories", dinnerCalories);
					extras.putFloat("currentWeight", Float.parseFloat(currentWeight.getText().toString()));
					intent.putExtras(extras);

					startActivity(intent);
				}
			}


		});
	}

	public void backToMainPage(View v)
	{
		finish();
		
		//redirect to MainActivity.java
		Intent backToMainPageIntent = new Intent(CaloriesCalculator.this, MainActivity.class);
		startActivity(backToMainPageIntent);
	}

	// display current date
	public void setCurrentDateOnView() {

		TextView tvDisplayDate = (TextView) findViewById(R.id.dateTB);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// set current date into TextView
		tvDisplayDate.setText(new StringBuilder()
		// Month is 0 based, just add 1
		.append(month + 1).append("-").append(day).append("-")
		.append(year).append(" "));

	}

	public void addListenerOnButton() {

		Button btnChangeDate = (Button) findViewById(R.id.btn_change);

		btnChangeDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				DatePickerDialog dpd = new DatePickerDialog(CaloriesCalculator.this,
						new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int selectedYear,
							int selectedMonth, int selectedDay) {
						TextView tvDisplayDatePeriod = (TextView) findViewById(R.id.dateTB);

						//format to MM-DD-YYYY
						String periodDate = String.valueOf(""+(selectedMonth + 1)+"-"+selectedDay+"-"+selectedYear);

						// get last period date
						Calendar period = Calendar.getInstance();
						period.set(Calendar.YEAR, selectedYear);
						period.set(Calendar.MONTH, selectedMonth);
						period.set(Calendar.DAY_OF_MONTH, selectedDay);

						// set selected date into TextView
						tvDisplayDatePeriod.setText(periodDate);

					}
				}, year, month, day);
				dpd.show();

			}

		});

	}



}

