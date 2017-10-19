package encryption.logesh.com.personalencryptor;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Decrypt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText text = (EditText) findViewById(R.id.msg) ;
                char string[] = text.getText().toString().toCharArray();

                EditText key = (EditText) findViewById(R.id.key);
                int k = Integer.valueOf(key.getText().toString())%26;

                for(int i=0; i<string.length; i++){
                    if(i%2==0)
                    {
                        string[i]=(char)(string[i]-k-k);
                    }
                    else
                    {
                        string[i]=(char)(string[i]-k);
                    }
                }
                final ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                final ClipData clip = ClipData.newPlainText("label", new String(string));

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Decrypt.this);
                alertDialogBuilder.setMessage(new String(string));
                alertDialogBuilder.setPositiveButton("Copy",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                clipboard.setPrimaryClip(clip);
                                Toast.makeText(Decrypt.this,"Copied to Clipboard",Toast.LENGTH_LONG).show();
                            }
                        });

                alertDialogBuilder.setNegativeButton("Back",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }
}
