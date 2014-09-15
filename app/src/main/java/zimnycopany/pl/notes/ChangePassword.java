package zimnycopany.pl.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import zimnycopany.pl.notes.R;

public class ChangePassword extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.change_password, menu);
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

    public void ZapiszHaslo(View view)
    {
        EditText et1= (EditText)findViewById(R.id.editText1);
        EditText et2= (EditText)findViewById(R.id.editText2);
        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();
        if(s1.contains(s2)==false)
        {
            Toast.makeText(this,"Oba hasła muszą być takie same.",Toast.LENGTH_SHORT).show();
        }
        if(s1.contains(s2)==true)
        {
            String haslo=et1.getText().toString();
            Intent i = new Intent(ChangePassword.this, OpenPassword.class);
            i.putExtra("haslo",haslo);
            startActivity(i);
            finish();
        }

    }

    public void AnulujZmianeHasla(View view)
    {
        Intent i = new Intent(ChangePassword.this,MyActivity.class);
        startActivity(i);
        finish();
    }
}
