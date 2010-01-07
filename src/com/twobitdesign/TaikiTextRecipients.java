package com.twobitdesign;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TaikiTextRecipients extends ListActivity {
	TextView selection;
	String[] items={"lorem", "ipsum", "dolor", "sit", "amet"};
				 
	public TaikiTextRecipients(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.recipients);
	}
}
