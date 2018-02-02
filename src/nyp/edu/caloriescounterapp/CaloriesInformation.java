package nyp.edu.caloriescounterapp;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CaloriesInformation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//set layout activity3.xml
		setContentView(R.layout.activity3);
		
		//find label_more TextView
		TextView label_more = (TextView)findViewById(R.id.label_more);

		label_more.setOnClickListener(new TextView. OnClickListener() {
			public void onClick(View v) {
				//link to browser
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.addCategory(Intent.CATEGORY_BROWSABLE);
				intent.setData(Uri.parse("http://www.hpb.gov.sg/HOPPortal/health-article/HPBSUEXTAPP1_4021885")); //redirect to website
				startActivity(intent);
			}
		});
	}
	public void backToMainPage(View v)
	{
		finish();
		//redirect to MainActivity.java
		Intent backToMainPageIntent = new Intent(CaloriesInformation.this, MainActivity.class);
		startActivity(backToMainPageIntent);
	}


}
