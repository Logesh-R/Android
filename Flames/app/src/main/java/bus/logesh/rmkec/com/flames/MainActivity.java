package bus.logesh.rmkec.com.flames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button b=(Button)findViewById(R.id.button);
        final EditText text1= (EditText)findViewById(R.id.editText1);
        final EditText text2= (EditText)findViewById(R.id.editText2);
        final TextView tv=(TextView)findViewById(R.id.display);

        try
        {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    String s = text1.getText().toString().toUpperCase();
                    String t = text2.getText().toString().toUpperCase();

                    if(s.length()==0 || t.length()==0)
                    {
                        tv.setText(R.string.empty);
                        return;
                    }

                    int a[] = new int[26];
                    int b[] = new int[26];
                    int count=0,temp=0;
                    for (int i = 0; i < 26; i++)
                    {     a[i]=0;  b[i]=0;    }
                    for(int i=0;i<s.length();i++)
                    {
                        int x;
                        if(s.charAt(i)>='A' && s.charAt(i)<='Z')
                        {
                            x=s.charAt(i)-'A';
                            a[x]++;
                        }
                        else if(!(s.charAt(i)==' ' || s.charAt(i)=='.'))
                        {
                            tv.setText(R.string.invalidcharacters);
                            return;
                        }
                    }
                    for(int i=0;i<t.length();i++)
                    {
                        int x;
                        if(t.charAt(i)>='A' && t.charAt(i)<='Z')
                        {
                            x=t.charAt(i)-'A';
                            b[x]++;
                        }
                        else if(!(t.charAt(i)==' ' || t.charAt(i)=='.'))
                        {
                            tv.setText(R.string.Invalid);
                            return;
                        }
                    }
                    for(int i=0;i<26;i++)
                        count=count+Math.abs(a[i]-b[i]);
                    if(count==0)
                    {
                        tv.setText(R.string.special);
                        return;
                    }
                    StringBuilder sb=new StringBuilder("flames");
                    for(int i=0;i<5;i++)
                    {
                        int tt=temp;
                        temp=(temp+count-1)%(sb.length());
                        sb.deleteCharAt((tt+count-1)%(sb.length()));


                    }
                    switch (sb.toString())
                    {
                        case "f":s="Friends";
                            break;
                        case "l":s="Love";
                            break;
                        case "a":s="Affection";
                            break;
                        case "m":s="Marriage";
                            break;
                        case "e":s="Enemies";
                            break;
                        case "s":s="Sister";
                            break;
                    }
                    tv.setText(s);
                }
            });
        }
        catch(Exception e){
            tv.setText(R.string.valid);
        }
    }
}
