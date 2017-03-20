package com.coffee.tab_cal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.japisoft.formula.Formula;
import com.japisoft.formula.FormulaFactory;
import com.japisoft.formula.Variant;
import com.japisoft.formula.node.EvaluateException;

public class MainActivity extends AppCompatActivity {
  FormulaFactory f;

  // 계산중인걸 저장하는 공간을 만들어둔다.
  String saveTextInjava = "";
  String littlesaveTextInjava = "";
  String resultTextInjava;

  Double resultDouble = 0.0;
  // 리턴 받을 공간 만들어두기
  TextView saveTextView;
  TextView resultTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // 객체 리턴 받는다.
    saveTextView = (TextView) findViewById(R.id.save_text);
    resultTextView = (TextView) findViewById(R.id.result_text);


  }

  public void btnClick(View view) {
    // v.getId() 버튼 id
    // v.getText() 버튼 text

    int clickId = view.getId();
    String clickText = ((Button) view).getText().toString();

    //Toast.makeText(MainActivity.this,clickText+"",Toast.LENGTH_SHORT).show();

    if (clickText.equals("=") || clickText == "=") {

      Formula f = new Formula(saveTextInjava);
      Variant variant = null;

      try {
        variant = f.evaluate();
      } catch (EvaluateException e) {
        e.printStackTrace();
      }
      saveTextView.setText(saveTextInjava);
      resultTextView.setText("=" + variant.toString());
      // 개체 값 초기화
      saveTextInjava = "";
      resultTextInjava = "";
      littlesaveTextInjava = "";
    } else if (clickText.equals("C") || clickText == "C") {
      saveTextInjava = "0";
      resultTextInjava = "0";
      saveTextView.setText(saveTextInjava);
      resultTextView.setText(resultTextInjava);
    } else if (clickText.equals("ㅁ") || clickText == "ㅁ") {
      MainActivity.this.finish();
    } else if ((clickText.equals("+") || clickText == "+") || (clickText.equals("-") || clickText == "-") || (clickText.equals("*") || clickText == "*") || (clickText.equals("/") || clickText == "/")) {
      saveTextInjava += clickText;
      saveTextView.setText(saveTextInjava);
      resultTextView.setText("0");
      littlesaveTextInjava = "";
    } else {
      saveTextInjava += clickText;
      littlesaveTextInjava += clickText;
      resultTextView.setText(littlesaveTextInjava);
    }
  }
}
