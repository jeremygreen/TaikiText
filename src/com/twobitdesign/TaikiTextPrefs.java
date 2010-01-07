package com.twobitdesign;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TaikiTextPrefs extends Activity {

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prefs);  
        
        final EditText editText = (EditText)findViewById(R.id.joltSettingText);

		try {
			File root = Environment.getExternalStorageDirectory();
			if(root.canRead()) {
				File f = new File(root, "test1.txt");
				FileReader fr = new FileReader(f);
				BufferedReader in = new BufferedReader(fr);
				String s = in.readLine();
				in.close();
				
				editText.setText(s);
			}
		} catch(IOException e) {
			// AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
			// dlgAlert.setMessage("File not Found");
			// dlgAlert.setPositiveButton("OK", null);
		    // dlgAlert.setCancelable(true);
		    // dlgAlert.create().show();
		}

        final Button saveButton = (Button)findViewById(R.id.prefsSaveButton);
        saveButton.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v) {
        		
        		Editable s = editText.getText();
        		try {
        			File root = Environment.getExternalStorageDirectory();
        			if(root.canWrite()) {
        				File f = new File(root, "test.txt");
        				FileWriter fw = new FileWriter(f);
        				BufferedWriter out = new BufferedWriter(fw);
        				out.write(s.toString());
        				out.close();
        			}
        		} catch(IOException e) {
        		}
        	}
        });
    }
}
