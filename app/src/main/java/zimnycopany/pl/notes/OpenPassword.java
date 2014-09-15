package zimnycopany.pl.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import zimnycopany.pl.notes.R;

public class OpenPassword extends Activity implements View.OnClickListener{

    SharedPreferences sh;
    SharedPreferences.Editor shEditor;
    String haslo = "0000";
    String noweHaslo= new String();
    int ileRazyOtwarty=0;
    int zmianaHasla=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_password);
        noweHaslo=getIntent().getStringExtra("haslo");
        sh=getSharedPreferences("zimnycopany.pl.notes", Context.MODE_PRIVATE);
        shEditor=sh.edit();
        ileRazyOtwarty++;
        Button b1 =(Button)findViewById(R.id.button);
        b1.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        shEditor.putInt("ileRazyOtwarty", ileRazyOtwarty);
        shEditor.putInt("zmianaHasla",zmianaHasla);
        shEditor.putString("haslo",haslo);
        shEditor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ileRazyOtwarty=sh.getInt("ileRazyOtwarty",0);
        zmianaHasla=sh.getInt("zmianaHasla",0);
        if (noweHaslo!=null)
        {
            haslo=noweHaslo;
            Intent i = new Intent(OpenPassword.this, MyActivity.class);
            startActivity(i);
            finish();
        }
        else
        {
            haslo=sh.getString("haslo","0000");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.open_password, menu);
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


    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.button:
            {
                EditText et = (EditText)findViewById(R.id.editText);
                if (et.getText().toString().contains(haslo)==true)
                {
                    if(zmianaHasla==0) {
                        Intent i = new Intent(OpenPassword.this, ChangePassword.class);
                        startActivity(i);
                        finish();
                        zmianaHasla++;
                    }
                    else
                    {
                        Intent i = new Intent(OpenPassword.this, MyActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
                else
                {
                    Toast.makeText(OpenPassword.this,"Złe hasło. Próbuj dalej.",Toast.LENGTH_SHORT).show();
                }
                if (ileRazyOtwarty==1)
                {
                    Toast.makeText(this,"Domyślne hasło to 0000.",Toast.LENGTH_SHORT).show();
                }
            }
                break;
        }
    }
}
