package nyp.edu.caloriescounterapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class welcome extends Activity {
	//declare elements
	Button startBtn = null;
	Spinner greetingSpinner = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//set layout to welcomeuser.xml
		setContentView(R.layout.welcomeuser);
	}

	public void registerEvent(View v)
	{
		//direct to registerActivity.java
		startActivity(new Intent("nyp.edu.caloriescounterapp.RegisterActivity"));
	}

}
