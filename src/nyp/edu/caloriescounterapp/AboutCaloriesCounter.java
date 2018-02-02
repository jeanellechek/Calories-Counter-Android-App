package nyp.edu.caloriescounterapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AboutCaloriesCounter extends Activity {
	
	//declare elements
	Button callBTN = null;
	Button emailBTN = null;
	String num="+6564515115";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//set layout to activity41.xml
		setContentView(R.layout.activity41);;

		//call button
		callBTN = (Button) findViewById(R.id.callBTN);
		callBTN.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					String number = "tel:" + num.trim();
					Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number)); //make a call based on the number 
					startActivity(callIntent);
				} catch (Exception e) {
					//fail to make a call
					Toast.makeText(getApplicationContext(),"Fail to make a call", Toast.LENGTH_SHORT).show();
				}
			}
		});

		//email button
		emailBTN = (Button) findViewById(R.id.emailBTN);
		emailBTN.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					Intent intent = new Intent(Intent.ACTION_SENDTO);
					intent.setType("text/plain");
					intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
					intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
					intent.setData(Uri.parse("mailto:info@cybertools.com"));
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Ensure user returns to the about page, instead of the email application
					startActivity(intent);
				} catch (Exception e) {
					//fail to send an email
					Toast.makeText(getApplicationContext(),"Fail to send an email", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}


	public void backToMainPage(View v)
	{
		finish();
		Intent backToMainPageIntent = new Intent(AboutCaloriesCounter.this, MainActivity.class);
		startActivity(backToMainPageIntent);
	}
}
