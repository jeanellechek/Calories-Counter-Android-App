package nyp.edu.caloriescounterapp;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class CaloriesIntakeHistory extends Activity {
	TableLayout tableLayout_History = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);

		tableLayout_History = (TableLayout) findViewById(R.id.tableLayout_History);

		tableLayout_History.setStretchAllColumns(true);
		tableLayout_History.setShrinkAllColumns(true);
		retrieveRecords();
	}


	//View all records
	public void retrieveRecords() {
		tableLayout_History.removeAllViews();

		TableRow rowTitle = new TableRow(this);

		rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

		//Main title of table
		TextView title = new TextView(this);
		title.setText("Calories Intake History");
		title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
		title.setGravity(Gravity.CENTER);
		title.setTextColor(Color.parseColor("#FA8072"));
		title.setTypeface(Typeface.SERIF, Typeface.BOLD);

		TableRow.LayoutParams params = new TableRow.LayoutParams();
		params.span = 6;

		rowTitle.addView(title, params);

		tableLayout_History.addView(rowTitle);

		TableRow rowTitle1 = new TableRow(this);

		//Set, style and print column name
		TextView subTitle1 = new TextView(this);
		subTitle1.setGravity(Gravity.CENTER);
		subTitle1.setTextColor(Color.parseColor("#FFFFFF"));
		subTitle1.setBackgroundColor(Color.parseColor("#FA8072"));
		subTitle1.setText("Date");

		TextView subTitle2 = new TextView(this);
		subTitle2.setGravity(Gravity.CENTER);
		subTitle2.setTextColor(Color.parseColor("#FFFFFF"));
		subTitle2.setBackgroundColor(Color.parseColor("#FA8072"));
		subTitle2.setText("Breakfast");

		TextView subTitle3 = new TextView(this);
		subTitle3.setGravity(Gravity.CENTER);
		subTitle3.setTextColor(Color.parseColor("#FFFFFF"));
		subTitle3.setBackgroundColor(Color.parseColor("#FA8072"));
		subTitle3.setText("Lunch");

		TextView subTitle4 = new TextView(this);
		subTitle4.setGravity(Gravity.CENTER);
		subTitle4.setTextColor(Color.parseColor("#FFFFFF"));
		subTitle4.setBackgroundColor(Color.parseColor("#FA8072"));
		subTitle4.setText("Dinner");

		rowTitle1.addView(subTitle1);
		rowTitle1.addView(subTitle2);
		rowTitle1.addView(subTitle3);
		rowTitle1.addView(subTitle4);

		tableLayout_History.setPadding(30, 10, 30, 10); //left top right bottom
		tableLayout_History.addView(rowTitle1);

		final DBAdapter dbAdaptor = new DBAdapter(getApplicationContext());

		Cursor cursor = null;

		try {
			dbAdaptor.open();

			cursor = dbAdaptor.getAllRecords();
			cursor.moveToFirst();

			do {

				//retrieve records from database
				String date = cursor.getString(0); //1st column
				String breakfast = cursor.getString(1); //2nd column
				String lunch = cursor.getString(2); //3rd column
				String dinner = cursor.getString(3); //4th column
				String breakfastCalories = cursor.getString(4); //5th column
				String lunchCalories = cursor.getString(5); //6th column
				String dinnerCalories = cursor.getString(6); //7th column


				//Print values on TextViews
				TextView nameView = new TextView(getApplicationContext());
				nameView.setGravity(Gravity.CENTER);
				nameView.setText(date);

				TextView breakfastView = new TextView(getApplicationContext());
				breakfastView.setGravity(Gravity.CENTER);
				breakfastView.setText(""+breakfast + "\n(" + breakfastCalories+")" );

				TextView lunchView = new TextView(getApplicationContext());
				lunchView.setGravity(Gravity.CENTER);
				lunchView.setText(""+lunch + "\n(" + lunchCalories+")" );

				TextView dinnerView = new TextView(getApplicationContext());
				dinnerView.setGravity(Gravity.CENTER);
				dinnerView.setText(""+dinner + "\n(" + dinnerCalories+")" );


				//Add Views
				TableRow row2 = new TableRow(this);
				row2.setGravity(Gravity.CENTER_HORIZONTAL);
				row2.addView(nameView);
				row2.addView(breakfastView);
				row2.addView(lunchView);
				row2.addView(dinnerView);

				//Row to history table
				tableLayout_History.addView(row2);
			} while (cursor.moveToNext());

		} catch (Exception e) {
			//fail to retrieve records
			Toast.makeText(getApplicationContext(),"Fail to retrieve records", Toast.LENGTH_SHORT).show();
		} finally {
			if (cursor != null)
				cursor.close();

			if (dbAdaptor != null)
				dbAdaptor.close();
		}
	}
	public void backToMainPage(View v)
	{
		finish();
		
		//redirect back to MainActivity.java
		Intent backToMainPageIntent = new Intent(CaloriesIntakeHistory.this, MainActivity.class);
		startActivity(backToMainPageIntent);
	}
}
