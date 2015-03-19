package app.sacnorth.ml.starme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Sachin on 3/18/2015.
 */
public class DataInput extends Activity {
    public int STARNO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputlayout);
        Spinner s = (Spinner) findViewById(R.id.starselector);

        final Button button =(Button) findViewById(R.id.getit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent("android.intent.action.displayresult");
                startActivity(i);
            }
        });
    }


}
