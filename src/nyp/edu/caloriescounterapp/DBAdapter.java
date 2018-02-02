package nyp.edu.caloriescounterapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	private static final String TAG = "DBAdapter";
	
	//declare columns
	public static final String KEY_DATE= "date";
	public static final String KEY_BREAKFASTFOOD = "breakfastFood";
	public static final String KEY_LUNCHFOOD = "lunchFood";
	public static final String KEY_DINNERFOOD = "dinnerFood";
	public static final String KEY_BREAKFASTCALORIES = "breakfastCalories";
	public static final String KEY_LUNCHCALORIES = "lunchCalories";
	public static final String KEY_DINNERCALORIES = "dinnerCalories";

	
	//declare name of database & version
	private static final String DATABASE_NAME = "MyDB";
	private static final String DATABASE_TABLE = "History";
	private static final int DATABASE_VERSION = 1;

	//create table
	private static final String DATABASE_CREATE =
			"create table History (date datetime primary key not null, breakfastFood text not null,lunchFood text not null, dinnerFood text not null," +
					"breakfastCalories text not null, lunchCalories text not null, dinnerCalories text not null);";

	private final Context context;    

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//if history table exist, drop the table
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS History");
			onCreate(db);
		}

	}
	//open the database
	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	//close the database
	public void close() {
		DBHelper.close();
	}

	//Insert record into database
	public void insertRecord(String date,String Breakfast,String Lunch,String Dinner,String BreakfastCalories,String LunchCalories,String DinnerCalories, String currentWeight)
	{
		String sqlStmt = "Insert into " + DATABASE_TABLE +
				" (" + KEY_DATE + "," +KEY_BREAKFASTFOOD + "," + KEY_LUNCHFOOD + "," + KEY_DINNERFOOD + "," + KEY_BREAKFASTCALORIES + "," + KEY_LUNCHCALORIES + "," + KEY_DINNERCALORIES+ ")"
				+" VALUES ('" + date + "','" + Breakfast + "','" + Lunch + "','" + Dinner + "','" + BreakfastCalories + "','" + LunchCalories + "','" + DinnerCalories + "')";

		//execute the SQL Statement
		db.execSQL(sqlStmt);
	}

	//Retrieve all records order by date
	public Cursor getAllRecords() {
		Cursor mCursor = db.rawQuery("Select * from " + DATABASE_TABLE +" order by " + KEY_DATE, null);
		return mCursor;
	}
}
