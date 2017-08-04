package sg.edu.rp.webservices.p11_knowyournationalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvNational;

    ArrayList<String> alNational;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNational = (ListView) findViewById(R.id.listViewNational);

        alNational = new ArrayList<String>();
        alNational.add("Singapore National Day");
        alNational.add("Singapore is 52th years old");
        alNational.add("Theme is #OneNationTogether ");

        aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alNational);
        lvNational.setAdapter(aa);

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout accessCode =
                (LinearLayout) inflater.inflate(R.layout.access_code, null);

        final EditText etPassphrase = (EditText) accessCode
                .findViewById(R.id.editTextPassPhrase);

        lvNational.setVisibility(View.GONE);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Login")
                .setView(accessCode)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        if (etPassphrase.getText().toString().equals("738964")) {

                            lvNational.setVisibility(View.VISIBLE);

                            Toast.makeText(MainActivity.this, "Successfully login ", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Access denied", Toast.LENGTH_LONG).show();
                        }
                    }
                })

                .setNegativeButton("No Access code", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        finish();
                        Toast.makeText(MainActivity.this, "No access code",
                                Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_quit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit?")
                    // Set text for the positive button and the corresponding
                    //  OnClickListener when it is clicked
                    .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            finish();
                            Toast.makeText(MainActivity.this, "You have quit",
                                    Toast.LENGTH_LONG).show();
                        }
                    })
                    // Set text for the negative button and the corresponding
                    //  OnClickListener when it is clicked
                    .setNegativeButton("Not Really", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(MainActivity.this, "You clicked Not Really",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
            // Create the AlertDialog object and return it

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else if (item.getItemId() == R.id.action_send) {

            String[] list = new String[]{"Email", "SMS"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")
                    .setItems(list, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {

                        Intent email = new Intent(Intent.ACTION_SEND);
                        // Put essentials like email address, subject & body text
                        email.putExtra(Intent.EXTRA_EMAIL,
                                new String[]{""});

                        email.putExtra(Intent.EXTRA_SUBJECT,
                                "Test Email from C347");
                        email.putExtra(Intent.EXTRA_TEXT,
                                "");
                        // This MIME type indicates email
                        email.setType("message/rfc822");
                        // createChooser shows user a list of app that can handle
                        // this MIME type, which is, email
                        startActivity(Intent.createChooser(email,
                                "Choose an Email client :"));
                        Toast.makeText(MainActivity.this, "Email", Toast.LENGTH_SHORT).show();

                    } else if (which == 1) {

                        Toast.makeText(MainActivity.this, "SMS", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else if (item.getItemId() == R.id.action_quiz) {

            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LinearLayout testing =
                    (LinearLayout) inflater.inflate(R.layout.test, null);

            final RadioGroup rg1, rg2, rg3;

            rg1 = (RadioGroup) testing.findViewById(R.id.rg1);
            rg2 = (RadioGroup) testing.findViewById(R.id.rg2);
            rg3 = (RadioGroup) testing.findViewById(R.id.rg3);



            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Test Yourself!")
                    .setView(testing)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            int result = 0;

                            if (rg1.getCheckedRadioButtonId() == R.id.radioButtonNo) {
                                result ++;
                            }
                            if (rg2.getCheckedRadioButtonId() == R.id.radioButtonYes) {
                                result ++;
                            }
                            if (rg3.getCheckedRadioButtonId() == R.id.radioButtonYes) {
                                result ++;
                            }

                            Toast.makeText(MainActivity.this, "Your result is " + result, Toast.LENGTH_SHORT).show();
                        }
                    })

                    .setNegativeButton("Cancel", null);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
