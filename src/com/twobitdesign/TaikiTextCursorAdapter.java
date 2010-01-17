package com.twobitdesign;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class TaikiTextCursorAdapter extends CursorAdapter {

	public TaikiTextCursorAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView name = (TextView)view.findViewById(R.id.name_entry);
		name.setText(cursor.getString(
				cursor.getColumnIndex("name")));
		TextView number = (TextView)view.findViewById(R.id.number_entry);
		number.setText(cursor.getString(
				cursor.getColumnIndex("number")));
		TextView email = (TextView)view.findViewById(R.id.email_entry);
		email.setText("email");	
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.recipients, parent, false);
		LinearLayout myRoot = new LinearLayout(context);
		//View view = inflater.inflate(R.layout.recipients, myRoot);
		bindView(view, context, cursor);
		return null;
	}
}
