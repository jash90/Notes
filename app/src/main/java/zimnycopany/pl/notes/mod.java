package zimnycopany.pl.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import zimnycopany.pl.notes.R;

public class mod extends Activity {


    EditText tytul;
    EditText notatka;
    int id=0;
    String akcja =new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mod);
        Bundle stkosz = getIntent().getExtras();
        id=stkosz.getInt("id");

        tytul = (EditText)findViewById(R.id.editText);
        notatka = (EditText)findViewById(R.id.editText2);
        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);


       if (stkosz!=null) {
           akcja=stkosz.getString("akcja");
            if (stkosz.getString("akcja").contains("dodaj")==true) {
                b1.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
            }
            else
            {
                b1.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                tytul.setText(stkosz.getString("tytul", ""));
                notatka.setText(stkosz.getString("notatka", ""));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mod, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void Anuluj(View view)
    {
        Bundle nowykosz = new Bundle();
        nowykosz.putString("akcja","anuluj");
        Intent i = new Intent(this,MyActivity.class);
        i.putExtras(nowykosz);
        startActivity(i);
        finish();

    }

    public void Usun(View view)
    {
        Bundle nowykosz = new Bundle();
        nowykosz.putInt("id",id);
        nowykosz.putString("akcja","usun");
        Intent i = new Intent(this,MyActivity.class);
        i.putExtras(nowykosz);
        startActivity(i);
        finish();

    }

    public void Zapisz(View view)
    {
        Bundle nowykosz = new Bundle();
        nowykosz.putInt("id",id);
        nowykosz.putString("tytul",tytul.getText().toString());
        nowykosz.putString("notatka",notatka.getText().toString());
        if (akcja.contains("wyswietl")==true)
        {
            nowykosz.putString("akcja","zapisz");
        }
        else
        {
            nowykosz.putString("akcja","dodaj");
        }
        Intent i = new Intent(this,MyActivity.class);
        i.putExtras(nowykosz);
        startActivity(i);
        finish();
    }
}
