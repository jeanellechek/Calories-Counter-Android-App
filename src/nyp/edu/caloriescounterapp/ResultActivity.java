package nyp.edu.caloriescounterapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity {

	//declare elements
	String nameString;
	String gender;
	int minCalorieRequired;
	int maxCalorieRequired;
	int age;
	String Date;
	String breakfast;
	String lunch;
	String dinner;
	int breakfastCalories;
	int lunchCalories;
	int dinnerCalories;
	int totalCalories ;

	Float currentWeight;
	Float weightLost;
	Float idealWeight;

	
	
	String progressStatus = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		retrievePreferences();
	}

	public void onResume() {
		super.onResume();
		retrievePreferences();
	}

	public void retrievePreferences() {
		//check if record exist in shared preference
		SharedPreferences prefs = getSharedPreferences("profile",
				MODE_PRIVATE);
		if (prefs.contains("Name")) {
			nameString = prefs.getString("Name", "");
			gender = prefs.getString("Gender", "");
			age = prefs.getInt("Age", 0);
			idealWeight = prefs.getFloat("idealWeight", 0);

			setContentView(R.layout.results);

			//get info from bundle
			Bundle bundle = getIntent().getExtras();
			Date = bundle.getString("Date");
			breakfast = bundle.getString("breakfast");
			lunch = bundle.getString("lunch");
			dinner = bundle.getString("dinner");
			breakfastCalories = bundle.getInt("breakfastCalories");
			lunchCalories = bundle.getInt("lunchCalories");
			dinnerCalories = bundle.getInt("dinnerCalories");
			currentWeight = bundle.getFloat("currentWeight");
			
			//calculate total calories consumed by the user
			totalCalories = breakfastCalories + lunchCalories + dinnerCalories;
			
			//calculate how far the user is to his/her ideal weight (lose weight)
			weightLost = currentWeight - idealWeight;
			
			//calculate how far the user is to his/her ideal weight (gaining weight)
			if(weightLost < 0)
			{
				weightLost = idealWeight- currentWeight;
			}


			//set date on TextView
			TextView date = (TextView)findViewById(R.id.dateLabel);
			date.setText("Calories Report\n(" + Date + ")");
			
			//set meal & calories on TextViews
			TextView breakfastFoodLabel = (TextView)findViewById(R.id.breakfastFoodLabel);
			breakfastFoodLabel.setText(""+ breakfast);
			
			TextView breakfastCaloriesLabel = (TextView)findViewById(R.id.breakfastCaloriesLabel);
			breakfastCaloriesLabel.setText(""+ breakfastCalories);

			TextView lunchFoodLabel = (TextView)findViewById(R.id.lunchFoodLabel);
			lunchFoodLabel.setText(""+ lunch);
			
			TextView lunchCaloriesLabel = (TextView)findViewById(R.id.lunchCaloriesLabel);
			lunchCaloriesLabel.setText(""+ lunchCalories);


			TextView dinnerFoodLabel = (TextView)findViewById(R.id.dinnerFoodLabel);
			dinnerFoodLabel.setText(""+ dinner);
			
			TextView dinnerCaloriesLabel = (TextView)findViewById(R.id.dinnerCaloriesLabel);
			dinnerCaloriesLabel.setText(""+ dinnerCalories);
			
			//set total calories on TextView
			TextView totalCaloriesLabel = (TextView)findViewById(R.id.totalCaloriesLabel);
			totalCaloriesLabel.setText(""+ totalCalories);
			
			//display user's progress
			TextView progressLabel = (TextView)findViewById(R.id.progressLabel);

			//check for the range of calories required based on gender & age
			if(age >=2 && age <=3){
				minCalorieRequired = 1000;
				maxCalorieRequired = 1400;
			}
			else if(age >=4 && age <=8 && gender.equals("female"))
			{
				minCalorieRequired = 1400;
				maxCalorieRequired = 1600;
			}
			else if(age >=9 && age <=13 && gender.equals("female"))
			{
				minCalorieRequired = 1600;
				maxCalorieRequired = 2000;
			}
			else if(age >=14 && age <=18 && gender.equals("female"))
			{
				minCalorieRequired = 2000;
				maxCalorieRequired = 2000;
			}
			else if(age >=19 && age <=30 && gender.equals("female"))
			{
				minCalorieRequired = 2000;
				maxCalorieRequired = 2200;
			}
			else if(age >=31 && age <=50 && gender.equals("female"))
			{
				minCalorieRequired = 2000;
				maxCalorieRequired = 2000;
			}
			else if(age >=51 && gender.equals("female"))
			{
				minCalorieRequired = 1800;
				maxCalorieRequired = 1800;
			}
			else if(age >=4 && age <=8 && gender.equals("male"))
			{
				minCalorieRequired = 1400;
				maxCalorieRequired = 1600;
			}
			else if(age >=9 && age <=13 && gender.equals("male"))
			{
				minCalorieRequired = 1800;
				maxCalorieRequired = 2200;
			}
			else if(age >=14 && age <=18 && gender.equals("male"))
			{
				minCalorieRequired = 2400;
				maxCalorieRequired = 2800;
			}
			else if(age >=19 && age <=30 && gender.equals("male"))
			{
				minCalorieRequired = 2600;
				maxCalorieRequired = 2800;
			}
			else if(age >=31 && age <=50 && gender.equals("male"))
			{
				minCalorieRequired = 2400;
				maxCalorieRequired = 2600;
			}
			else if(age >=51 && gender.equals("male"))
			{
				minCalorieRequired = 2200;
				maxCalorieRequired = 2400;
			}
			
			//if user's calories intakes is less than minimum calories required
			if(totalCalories < minCalorieRequired) 
			{
				progressStatus = "Underconsumed";
				progressLabel.setTextColor(Color.parseColor("#0000FF"));
			}
			
			//if user's calories intakes is more than maximum calories required
			else if(totalCalories > maxCalorieRequired) 
			{
				progressStatus = "Overconsumed";
				progressLabel.setTextColor(Color.parseColor("#FF0000"));
			}
			
			//if user's calories intakes is within the range
			else if((totalCalories >= minCalorieRequired) || (totalCalories <= maxCalorieRequired))
			{
				progressStatus = "Just right!";
				progressLabel.setTextColor(Color.parseColor("#00FF00"));
			}
			String caloriesRequired = minCalorieRequired + "-" + maxCalorieRequired;
			
			//display calories required on the TextView
			TextView calorieRequiredLabel = (TextView)findViewById(R.id.calorieRequiredLabel);
			calorieRequiredLabel.setText(""+ caloriesRequired);

			
			//display progress status
			progressLabel.setText(""+ progressStatus);
			
			TextView weightStatusLabel = (TextView)findViewById(R.id.weightStatusLabel);
			weightStatusLabel.setText(""+ weightLost + "kg to your ideal weight!");
			
			//recalculate button to calculate the intake again
			Button recalculateBtn = (Button) findViewById(R.id.btn_recalculate);
			recalculateBtn.setOnClickListener(new View.OnClickListener() {


				public void onClick(View v) 
				{
					Intent intent = new Intent("nyp.edu.caloriescounterapp.CaloriesCalculator");
					startActivity(intent);
				}
			});
			
			//return back to main page
			Button homeBtn = (Button) findViewById(R.id.btn_home);
			homeBtn.setOnClickListener(new View.OnClickListener() {


				public void onClick(View v) 
				{
					finish();
					Intent myActivity = new Intent(ResultActivity.this, MainActivity.class);
					startActivity(myActivity);

				}
			});


			// save calorie intakes into the database
			Button saveBtn = (Button) findViewById(R.id.btn_insert);
			saveBtn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					
					//declare adapter
					DBAdapter dbAdaptor = new DBAdapter(getApplicationContext());


					try {
						//open adapter
						dbAdaptor.open();

						//assign attributes
						String date = Date;
						String Breakfast = breakfast;
						String Lunch = lunch;
						String Dinner = dinner;
						String BreakfastCalories = String.valueOf(breakfastCalories);
						String LunchCalories = String.valueOf(lunchCalories);
						String DinnerCalories = String.valueOf(dinnerCalories);

						//insert record into the database
						dbAdaptor.insertRecord(date,Breakfast,Lunch,Dinner,BreakfastCalories,LunchCalories,DinnerCalories,String.valueOf(currentWeight));
						finish();

						//record successfully added, redirect to caloriesIntakeHistory
						Toast.makeText(getApplicationContext(),"Record successfully added.", Toast.LENGTH_SHORT).show();
						Intent backToMainPageIntent = new Intent(ResultActivity.this, CaloriesIntakeHistory.class);
						startActivity(backToMainPageIntent);
					} catch (Exception e) {

						//Fail to insert record
						Toast.makeText(getApplicationContext(),"Fail to enter record.", Toast.LENGTH_SHORT).show();
					} finally {
						if (dbAdaptor != null)
							dbAdaptor.close();
					}
				}
			});
		}
	}
}
