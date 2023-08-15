package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements OnClickListener {


    AppCompatButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, sum, sub, mul, div, dot, sqrt, clr, aclr, mod, eql;
    TextView Result, soln;
    String op;
    double exp1, exp2, res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialize();

        setBtn(b0, R.id.b0);
        setBtn(b1, R.id.b1);
        setBtn(b2, R.id.b2);
        setBtn(b3, R.id.b3);
        setBtn(b4, R.id.b4);
        setBtn(b5, R.id.b5);
        setBtn(b6, R.id.b6);
        setBtn(b7, R.id.b7);
        setBtn(b8, R.id.b8);
        setBtn(b9, R.id.b9);
        setBtn(aclr, R.id.aclr);
        setBtn(sum, R.id.add);
        setBtn(sub, R.id.sub);
        setBtn(mul, R.id.mul);
        setBtn(div, R.id.div);
        setBtn(sqrt, R.id.sqrt);
        setBtn(clr, R.id.clr);
        setBtn(dot, R.id.dot);
        setBtn(eql, R.id.equal);
        setBtn(mod, R.id.modulo);
    }

        void setBtn (AppCompatButton btn,int id){
            btn = findViewById(id);
            btn.setOnClickListener(this);

        }


    @Override
    public void onClick(View v) {
        AppCompatButton button = (AppCompatButton) v;
        String B1 = button.getText().toString();
        String cnct = soln.getText().toString();




        if (B1.equals("AC")) {
            soln.setText("");
            Result.setText("0");
            return;
        }
        if (B1.equals("=")) {
            soln.setText(Result.getText());
            return;
        }
        if (B1.equals("⌫")) {
            cnct = cnct.substring(0, cnct.length() - 1);
        } else {

               cnct = cnct+B1;
        }


        soln.setText(cnct);
        String finalResult = getResult(cnct);
        if(!finalResult.equals("Err")) {
            Result.setText(finalResult);

        }}


    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
           String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if(finalResult.endsWith(".0")) {
               finalResult = finalResult.replace(".0","");
            }
           return finalResult;
        }catch(Exception e1 )
        {
            return "Err";
        }

    }
    private void initialize() {
        Result = findViewById(R.id.Result);
        soln = findViewById(R.id.Soln);
    }

}