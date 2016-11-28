package group22.travelstories;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.media.TimedMetaData;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Felix on 27/11/2016.
 */

public class EntryFormActivity extends AppCompatActivity {

    static TextView fromDate;
    static TextView fromTime;
    static TextView endDate;
    static TextView endTime;
    static boolean end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_form);

        final EditText name = (EditText) findViewById(R.id.newlocationname);

        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                name.setEnabled(true);
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    name.setEnabled(false);
//                    name.setTextColor(0);
                }
                return false;
            }

        });

        end = false;
        fromDate = (TextView) findViewById(R.id.editFromDate);
        fromTime = (TextView) findViewById(R.id.editFromTime);
        endDate = (TextView) findViewById(R.id.editEndDate);
        endTime = (TextView) findViewById(R.id.editEndTime);

        fromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
                end = false;
            }
        });

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
                end = false;
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
                end = true;
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
                end = true;
            }
        });

        Button add = (Button) findViewById(R.id.addEntry);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFrag();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFrag();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class TimePickerFrag extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String time = hourOfDay + ":" + minute;
            System.out.println("The Time is: " + time);
            if (end) {
                endTime.setText(time);
            } else {
                fromTime.setText(time);
            }

        }
    }


    public static class DatePickerFrag extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String date = day + "/" + month + "/" + year;
            System.out.println("The Date is: " + date);
            if (end) {
                endDate.setText(date);
            } else {
                fromDate.setText(date);
            }
        }

    }
}
