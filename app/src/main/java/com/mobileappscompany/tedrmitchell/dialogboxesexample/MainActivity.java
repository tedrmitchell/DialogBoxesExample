package com.mobileappscompany.tedrmitchell.dialogboxesexample;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toastButton = (Button) findViewById(R.id.toast);

        toastButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {

            Toast.makeText(getApplicationContext(),
                    "This is a toast message", Toast.LENGTH_LONG).show();
        }
    });

        Button alertButton = (Button) findViewById(R.id.alert);

        alertButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);

                // set title
                alertBuilder.setTitle("Alert Dialog");

                // set dialog message
                alertBuilder.setMessage("This is an Alert Dialog");
                alertBuilder.setCancelable(false);
                alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        dialog.cancel();
                    }
                });
                alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

                // create alert dialog
                AlertDialog alertDialog = alertBuilder.create();

                // show it
                alertDialog.show();
            }

        });

        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        Button datePicker = (Button) findViewById(R.id.date);

        final TextView dateText = (TextView) findViewById(R.id.dateText);

        datePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateText.setText(monthOfYear + "/" + dayOfMonth + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });

        final Button popupMenu = (Button) findViewById(R.id.menu);
        popupMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, popupMenu);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu_popup, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });//closing the setOnClickListener method

        final Button windowButton = (Button)findViewById(R.id.window);
        windowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                LayoutInflater inflater = (LayoutInflater)
                        MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.window_popup, null);
                final PopupWindow window = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        true);
                // The code below assumes that the root container has an id called 'main'

                window.showAtLocation(MainActivity.this.findViewById(R.id.main), Gravity.TOP, 0, 0);

                Button windowButton = (Button)popupView.findViewById(R.id.windowButton);
                windowButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        window.dismiss();
                    }});

            }});

        final Button fragmentButton = (Button) findViewById(R.id.fragment);
                fragmentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            new AlertDialogFragment().show(getFragmentManager(), "Alert Dialog");
                    }
                });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
