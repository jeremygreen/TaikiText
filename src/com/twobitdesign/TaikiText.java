package com.twobitdesign;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class TaikiText extends Activity {
	
	private static final float GRAVITY_EARTH = 9.8f;
	private static final int PICK_CONTACT = 0;
    private static final String TAG = "test";
	
	TextView directionText = null;
	TextView inclinationText = null;
	TextView aboveOrBelowText = null;	
	TextView joltText = null;
	
	private SensorManager sensorManager;
	
	boolean accelerometerAvailable = false;
	boolean isEnabled = false;
	
	float direction = (float) 0;
	float inclination;
	float aboveOrBelow = (float)0;
	
	float rollingZ = (float)0;
	float kFilteringFactor = (float)0.05;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 	    aboveOrBelowText = (TextView) findViewById(R.id.aboveOrBelowText);
 	    joltText = (TextView) findViewById(R.id.joltText);  
        init();    
    }
    
	/** 
	 * Start all the initialisation.
	 */
	private void init() {
		sensorManager = (SensorManager) this.getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(listener,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);
      // Nothing else we currently do
	}
	
	private SensorEventListener listener = new SensorEventListener(){

		   public void onAccuracyChanged(Sensor arg0, int arg1){}

		   public void onSensorChanged(SensorEvent evt)
		   {
		      float vals[] = evt.values;
		      
		      if(evt.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
		      {
		          aboveOrBelow =
		             (float) ((vals[2] * kFilteringFactor) + 
		             (aboveOrBelow * (1.0 - kFilteringFactor)));
	     	      
		          Float aboveOrBelowFloat = new Float(aboveOrBelow);
			      aboveOrBelowText.setText(aboveOrBelowFloat.toString());
			      
		          float accel_mag = (vals[0]*vals[0]+vals[1]*vals[1]+vals[2]*vals[2]);
		   
		          if(accel_mag > (2*GRAVITY_EARTH)*(2*GRAVITY_EARTH)) { 
		        	  Float accel_magFloat = new Float(accel_mag);
		        	  joltText.setText(accel_magFloat.toString());
		          }
		      }
		   }
		};
		
		/* (non-Javadoc)
		 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
		 */
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			super.onCreateOptionsMenu(menu);
			MenuInflater mi = getMenuInflater();
			mi.inflate(R.menu.menu, menu);
			return true;
		}

		/* (non-Javadoc)
		 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
		 
		*/
	    @Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch(item.getItemId()) {
			case R.id.recipients:
			    // Intent intent = new Intent(Intent.ACTION_PICK, People.CONTENT_URI);
			    // startActivityForResult(intent, PICK_CONTACT);
				startActivity(new Intent(this, TaikiTextRecipients.class));
				break;
			case R.id.settings:
				startActivity(new Intent(this, TaikiTextPrefs.class));
				//ShockTextUtils.notImplemented(this);
				break;
			case R.id.exit:
				finish();
				break;
			}
			return false;
	}
	
	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
	  super.onActivityResult(reqCode, resultCode, data);

	  switch (reqCode) {
	    case (PICK_CONTACT) :
	      if (resultCode == Activity.RESULT_OK) {
	        Uri contactData = data.getData();
	        Cursor c =  managedQuery(contactData, null, null, null, null);
	        if (c.moveToFirst()) {
	        	int i = 0;
	        	do {
	                String number = c.getString(c.getColumnIndexOrThrow(People.NUMBER));
	                Log.v(TAG, number);
	                i++;
	        	}
	        	while(c.move(i));
	          }
	      }
	      break;
	  }
	}
}