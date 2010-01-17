package com.twobitdesign;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class TaikiTextRecipients extends ListActivity {
	
	SQLiteDatabase recipientsDB = null;
    ListAdapter adapter = null;
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		//setContentView(R.layout.recipients);
		try {
			
			Log.d("TEST", "IN CREATE ! ");
			
			recipientsDB = openOrCreateDatabase("RECIPIENTS", MODE_WORLD_READABLE, null);	
			//recipientsDB.execSQL("DROP TABLE IF EXISTS PEOPLE");
			//recipientsDB.execSQL("create table PEOPLE(_id integer primary key autoincrement," + "name text not null," + "number text not null," + "email text not null" + ");");		
			//recipientsDB.execSQL("INSERT INTO PEOPLE" + " (name, number, email)" + " VALUES ('me', '123456', 'me@me.com');");
			//recipientsDB.execSQL("INSERT INTO PEOPLE" + " (name, number, email)" + " VALUES ('you', '987654', 'you@you.com');");
			//recipientsDB.execSQL("INSERT INTO PEOPLE" + " (name, number, email)" + " VALUES ('them', '457373', 'them@them.com');");
			//recipientsDB.execSQL("INSERT INTO PEOPLE" + " (name, number, email)" + " VALUES ('us', '765544', 'us@us.com');");

			String[] projection = new String[]{"_id","name","number","email"};    
			Cursor mCursor = recipientsDB.query("PEOPLE", projection, null, null, null, null, null);
			 	
	        adapter = new SimpleCursorAdapter(this, R.layout.recipients_entry, mCursor, new String[] { "name","number","email" }, new int[] { R.id.name_entry, R.id.number_entry, R.id.email_entry }); 

			TaikiTextCursorAdapter taikitextCursorAdapter = new TaikiTextCursorAdapter(this, mCursor);
			
	        //this.setListAdapter(taikitextCursorAdapter); 
	        this.setListAdapter(adapter);
	        
		} catch(Exception e) {
		    Log.d("", "error with database " + e.getMessage());
		} finally { 
	        if (recipientsDB != null) { 
	        	recipientsDB.close(); 
	        } 
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.recipients_menu, menu);
		return true;
	}
	
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.add_recipient:	
            Intent contact = new Intent(Intent.ACTION_PICK, People.CONTENT_URI);
            startActivityForResult(contact, 3);
            return true;
		    // startActivity(new Intent(this, TaikiTextAddRecipient.class));
		case R.id.delete_recipients:
			startActivity(new Intent(this, TaikiTextDeleteRecipients.class));
			break;
		}
		return false;
	}
    
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        
        switch (reqCode) {
        case (3):
           if (resultCode == RESULT_OK) {
               Uri contactData = data.getData();
               
               Cursor c = managedQuery(contactData, null, null, null, null);
               if(c.moveToFirst()) {        //
                  String name = c.getString(c.getColumnIndexOrThrow(People.NAME));
                  String number = c.getString(c.getColumnIndexOrThrow(People.NUMBER));
                  String email = new String("");
      			  
                  recipientsDB = openOrCreateDatabase("RECIPIENTS", MODE_WORLD_WRITEABLE, null);
      			
      			  recipientsDB.execSQL("INSERT INTO PEOPLE" + " (name, number, email)" + " VALUES ( '" + name + "','" + number + "','" +  "email" + "');");
      			  Log.d("TEST", "Inserting new Contact");
      			  
                  // long email_id = c.getLong(c.getColumnIndexOrThrow(People.PRIMARY_EMAIL_ID));
                  
                  /*
                  String cols[] = c.getColumnNames();
                  
                  for(int i=0; i<cols.length; i++) {
                	  Log.d("TEST", cols[i]);
                  }
                  */
                  
                  Log.d("TEST", name + " " + number); 
                  
      	          if (recipientsDB != null) { 
    	              recipientsDB.close(); 
    	          } 
               }
               
               /*  
               Uri mContacts= data.getData(); //Contacts.ContactMethods.CONTENT_URI; 
                  
               String[] projection = new String[] { 
                         Contacts.ContactMethods.PERSON_ID, 
                         Contacts.ContactMethods.KIND, 
                         Contacts.ContactMethods.DATA 
               }; 
                      
               Cursor c = this.managedQuery( mContacts, projection, null,  null, null);
               String person_name = c.getString(c.getColumnIndex(People.NAME));
              
               Log.d("TEST ", person_name);
               */
           }
           break;       
        }
    }
}
