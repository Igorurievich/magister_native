package com.example.ibodia.bodia_benchmark;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ibodia on 5/1/2017.
 */

public class BigWhileTestActivity extends AppCompatActivity {

    EditText editTextWhileCount;
    TextView firstResultText;
    TextView secondResultText;
    TextView thirdResultText;
    TextView averageTimeResultText;
    Button btnRunWhileTest;

    private int testNumber = 0;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_big_while_test);

        editTextWhileCount = (EditText) findViewById(R.id.input_while_count);
        firstResultText = (TextView) findViewById(R.id.first_while_test_result);
        secondResultText = (TextView) findViewById(R.id.second_while_test_result);
        thirdResultText = (TextView) findViewById(R.id.third_while_test_result);
        averageTimeResultText = (TextView) findViewById(R.id.arithmetic_main);

        btnRunWhileTest = (Button) findViewById(R.id.run_while_test_btn);
    }

    public void onStartTestClick(View v)
    {
        if (editTextWhileCount.getText() == null || editTextWhileCount.getText().toString().isEmpty()){
            Toast.makeText(this, R.string.message_inser_valid_count, Toast.LENGTH_LONG).show();
            return;
        }

        if (testNumber < 3) {
            editTextWhileCount.setEnabled(false);
            double elapsedTime = EraseWhile(Long.parseLong(editTextWhileCount.getText().toString()));
            testNumber++;
            FillResults(testNumber, elapsedTime);
        }
        if (testNumber==3){
            double average = (Double.parseDouble(firstResultText.getText().toString()) +
                    Double.parseDouble(secondResultText.getText().toString()) +
                    Double.parseDouble(thirdResultText.getText().toString())) / 3;
            averageTimeResultText.setText(Double.toString(average));
            btnRunWhileTest.setText(R.string.start_new_tests_series_with_while_btn_label);
            testNumber++;
            return;
        }

        if (btnRunWhileTest.getText() == getResources().getString(R.string.start_new_tests_series_with_while_btn_label))
        {
            btnRunWhileTest.setText(R.string.label_button_run_while);
            ClearTestsResults();
            testNumber = 0;
            editTextWhileCount.setEnabled(true);
        }
    }

    private double EraseWhile(long count) {
        long tStart = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {

        }
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        return tDelta / 1000.0;
    }

    private void ClearTestsResults()
    {
        firstResultText.setText(" ");
        secondResultText.setText(" ");
        thirdResultText.setText(" ");
        averageTimeResultText.setText(" ");
    }

    private void FillResults(int numberOfTest, double time)
    {
        switch (numberOfTest) {
            case 1:
                firstResultText.setText(Double.toString(time));
                break;
            case 2:
                secondResultText.setText(Double.toString(time));
                break;
            case 3:
                thirdResultText.setText(Double.toString(time));
                break;
        }
    }
}
