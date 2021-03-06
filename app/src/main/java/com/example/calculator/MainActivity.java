package com.example.calculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private BigDecimal viewing;
    private BigDecimal saved;
    private String currentOperator;
    private BigDecimal percentMultiplier;
    boolean currentDouble, newDouble;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        init();
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

    public void operationCheck() {
        TextView c = (TextView) findViewById(R.id.Output);
        String id = (c.getResources().getResourceName(c.getId())).split("/")[1];
        if (id.equals("+")) {


        }

    }

    public void click(View c) {

        switch (c.getId()) {
            case R.id.button0:
                addToViewing("0");
                break;
            case R.id.button1:
                addToViewing("1");
                break;
            case R.id.button2:
                addToViewing("2");
                break;
            case R.id.button3:
                addToViewing("3");
                break;
            case R.id.button4:
                addToViewing("4");
                break;
            case R.id.button5:
                addToViewing("5");
                break;
            case R.id.button6:
                addToViewing("6");
                break;
            case R.id.button7:
                addToViewing("7");
                break;
            case R.id.button8:
                addToViewing("8");
                break;
            case R.id.button9:
                addToViewing("9");
                break;
            case R.id.addition:
                setCurrentOperand("ADDITION");
                break;
            case R.id.subtraction:
                setCurrentOperand("SUBTRACTION");
                break;
            case R.id.division:
                setCurrentOperand("DIVISION");
                break;
            case R.id.signSwap:
                viewing = viewing.negate();
                break;
            case R.id.clear:
                init();
                break;
            case R.id.decimal:
                if(!isWholeNumber(viewing) && !currentDouble){
                    currentDouble = true;
                    newDouble = true;
                }
                break;
            case R.id.multiplication:
                setCurrentOperand("MULTIPLICATION");
                break;
            case R.id.percentage:
                viewing = viewing.multiply(percentMultiplier);
                break;
            case R.id.equals:
                equals(currentOperator);
                break;
            case R.id.sqrRoot:
                if(viewing.doubleValue() > 0)
                    viewing = new BigDecimal(Double.toString(Math.sqrt(viewing.doubleValue())));
                    viewing = viewing.stripTrailingZeros();
                break;
        }
        updateViewing(viewing);
    }

    public void updateViewing(BigDecimal n) {
        TextView c = findViewById(R.id.Output);
        if(newDouble){
            c.setText(viewing.toPlainString() + ".");
        }
        c.setText(viewing.toPlainString());
    }
    public void addToViewing(String newNum){
        String newValue = viewing.toPlainString() + newNum;
        if(newDouble){
            viewing = new BigDecimal(viewing.toPlainString() + "." + newNum);
            newDouble = false;
            return;
        }
        else
        viewing = new BigDecimal(newValue);
    }
    public void setCurrentOperand(String d){
        if(currentOperator ==""){
            saved = viewing;
            viewing = new BigDecimal(0);
            currentDouble = false;
        }
        currentOperator = d;
    }
    public void init(){
        viewing = new BigDecimal("0");
        saved = new BigDecimal("0");
        percentMultiplier = new BigDecimal(".01");
        currentOperator = "";
        currentDouble = false;
        newDouble = false;
        updateViewing(viewing);

    }
    private boolean isWholeNumber(BigDecimal n){
        return !(n.doubleValue() % 1 == 0);
    }

    public void equals(String currentOperator){
        switch (currentOperator){
            case "ADDITION":
                viewing = viewing.add(saved);
                break;
            case "SUBTRACTION":
                viewing = saved.subtract(viewing);
                break;
            case "MULTIPLICATION":
                viewing = viewing.multiply(saved);
                break;
            case "DIVISION":
                viewing = saved.divide(viewing);
                break;
        }

    }

}
