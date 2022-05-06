package alex.primi;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String loc = Locale.getDefault().getLanguage();
    //EditText N2=(EditText) findViewById(R.id.editText2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText N2=(EditText) findViewById(R.id.editText2);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Button Go=(Button) findViewById(R.id.button);
        final TextView N2L=(TextView) findViewById(R.id.textView2);
        final EditText N=(EditText) findViewById(R.id.editText);
        final TextView answer=(TextView) findViewById(R.id.textView3);

        if (savedInstanceState!=null) {
            answer.setText(savedInstanceState.getString("output"));
        }
        N.requestFocus();
        N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        });
        N2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        });




        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter;
        if (loc.equals("it")) {
            adapter = ArrayAdapter.createFromResource(this, R.array.scelta, android.R.layout.simple_spinner_item);
        } else {
            adapter = ArrayAdapter.createFromResource(this, R.array.choose, android.R.layout.simple_spinner_item);
        }


        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        Go.setOnClickListener(this);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //N.setText("");
                //N2.setText("");
                switch (position) {
                    case 0:
                        N2L.setVisibility(View.INVISIBLE);
                        N2.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        N2L.setVisibility(View.INVISIBLE);
                        N2.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        N2L.setVisibility(View.INVISIBLE);
                        N2.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        N2L.setVisibility(View.INVISIBLE);
                        N2.setVisibility(View.INVISIBLE);
                        break;
                    case 4:
                        N2L.setVisibility(View.INVISIBLE);
                        N2.setVisibility(View.INVISIBLE);
                        break;
                    case 5:
                        N2L.setVisibility(View.VISIBLE);
                        N2L.setText("N2:");
                        N2.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        N2L.setVisibility(View.VISIBLE);
                        N2L.setText("B:");
                        N2.setVisibility(View.VISIBLE);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final TextView answer=(TextView) findViewById(R.id.textView3);
        final EditText N=(EditText) findViewById(R.id.editText);
        final EditText N2=(EditText) findViewById(R.id.editText2);
        int pos,num,prev,num2,i,size;
        String app;
        if (N.getText().toString().trim().length() != 0) {
            num = Integer.parseInt(N.getText().toString());
            //answer.setText(Integer.toString(num));
            pos = spinner.getSelectedItemPosition();
            PrimeCalculator pr= new PrimeCalculator();
            switch (pos) {

                case 0:
                    if (!pr.isPrime(num)) answer.setText(R.string.answer0_false);
                    else answer.setText(R.string.answer0_true);
                    break;
                case 1:
                    app = getResources().getString(R.string.next_prime);
                    answer.setText(app + " " + Integer.toString(pr.nextPrime(num)));
                    break;
                case 2:
                    app=getResources().getString(R.string.previous_prime);
                    prev=pr.previousPrime(num);
                    if (prev>=2) answer.setText(app + " " + Integer.toString(prev));
                    else answer.setText(getResources().getString(R.string.no_previous));
                    break;
                case 3:
                    answer.setText(R.string.eulero);
                    answer.append("  " + Integer.toString(pr.eulero(num)));
                    break;
                case 4:
                    if (!pr.isPrime(num)) {
                        if (num>1) {
                            List<Integer> p = new ArrayList<>();
                            p=pr.factorize(num);
                            size = p.size();
                            answer.setText(num + "=");
                            for (i = 0; i < size; i += 2) {
                                if (i>0) answer.append(" *");
                                if (p.get(i+1)>1) answer.append(" " + Integer.toString(p.get(i)) + "^" + Integer.toString(p.get(i + 1)));
                                else answer.append(" " + Integer.toString(p.get(i)));
                            }
                        }
                        else answer.setText(Integer.toString(num));
                    }
                    else answer.setText(R.string.already);
                    break;
                case 5:
                    if (N2.getText().toString().trim().length() != 0) {
                        num2=Integer.parseInt(N2.getText().toString());
                        if (num2<=num) answer.setText(R.string.N1_N2);
                        else {
                            List<Integer> p=new ArrayList<>();
                            p=pr.primesBetween(num,num2);
                            size=p.size();
                            if (size>0) {
                                answer.setText(R.string.between);
                                answer.append(" (" + Integer.toString(size) + "): \n\n");
                                for (i = 0; i < size; i++) {
                                    answer.append(Integer.toString(p.get(i)));
                                    answer.append("   ");
                                }
                            }
                            else answer.setText(R.string.no_between);
                        }
                    }
                    else {
                        answer.setText(R.string.no_val);
                    }
                    break;
                case 6:
                    if (N2.getText().toString().trim().length() != 0) {
                        num2 = Integer.parseInt(N2.getText().toString());
                        if (num2<1) answer.setText(R.string.b0);
                        else {
                            int p[]=new  int[num2];
                            answer.setText(R.string.b_next);
                            p=pr.primesFrom(num,num2);
                            for (i=1;i<=num2;i++) {
                                answer.append(Integer.toString(p[i-1]));
                                answer.append("   ");
                            }
                        }
                    }
                    else {
                        answer.setText(R.string.no_val);
                    }
                    break;

            }
        }
        else {
            answer.setText(R.string.no_val);
        }
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        final TextView answer=(TextView) findViewById(R.id.textView3);
        savedInstanceState.putString("output", answer.getText().toString());
    }
}

