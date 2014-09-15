package zimnycopany.pl.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyActivity extends Activity {

    List<String> notatki = new ArrayList<String>();
    List<String> tytuly = new ArrayList<String>();
    ListView list;
    Integer iloscNotatek=0;
    String akcja= new String();
    SharedPreferences sh;
    SharedPreferences.Editor shEditor;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        list=(ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tytuly);
        list.setAdapter(adapter);
        sh=getSharedPreferences("zimnycopany.pl.notes", Context.MODE_PRIVATE);
        shEditor=sh.edit();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Bundle nowykosz = new Bundle();
                nowykosz.putInt("id",position);
                nowykosz.putString("tytul",tytuly.get(position));
                nowykosz.putString("notatka",notatki.get(position));
                nowykosz.putString("akcja","wyswietl");
                Intent i = new Intent(MyActivity.this,mod.class);
                i.putExtras(nowykosz);
                startActivity(i);
                finish();
            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();
        shEditor.putInt("ilosc",notatki.size());
        for(int i=0;i<notatki.size();i++)
        {
            shEditor.putString("not"+i,notatki.get(i));
            shEditor.putString("tyt"+i,tytuly.get(i));
        }
        shEditor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (sh != null) {
                iloscNotatek = sh.getInt("ilosc", 0);
                if (iloscNotatek > 0) {
                    for (int i = 0; i < iloscNotatek; i++) {
                        notatki.add(sh.getString("not" + i, ""));
                        tytuly.add(sh.getString("tyt" + i, ""));
                    }
                }

            }
            Bundle stkosz = getIntent().getExtras();
            if (stkosz != null) {
                akcja = stkosz.getString("akcja");
                if (akcja.contains("dodaj") == true) {
                    Toast.makeText(this, "Dodano.", Toast.LENGTH_SHORT).show();
                    tytuly.add(stkosz.getString("tytul"));
                    notatki.add(stkosz.getString("notatka"));
                }
                if (akcja.contains("usun") == true) {
                    Toast.makeText(this, "Usunięto.", Toast.LENGTH_SHORT).show();

                    tytuly.remove(stkosz.getInt("id"));
                    notatki.remove(stkosz.getInt("id"));
                }

                if (akcja.contains("zapisz") == true) {
                    Toast.makeText(this, "Zapisano.", Toast.LENGTH_SHORT).show();
                    tytuly.set(stkosz.getInt("id"), stkosz.getString("tytul"));
                    notatki.set(stkosz.getInt("id"), stkosz.getString("notatka"));
                }
            }

        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        EasterEgg();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settingmenu, menu);
        return true;
    }

    public void EasterEgg()
    {
        if(tytuly.size()==2) {
            if (tytuly.get(0).toString().toUpperCase().contains("kocham".toUpperCase()) == true) {
                if (tytuly.get(1).toString().toUpperCase().contains("mika".toUpperCase()) == true) {
                    Toast.makeText(this, "Też cię kocham synku. Miłego dnia :)", Toast.LENGTH_SHORT).show();
                }
            }
            if (tytuly.get(0).toString().toUpperCase().contains("kocham".toUpperCase()) == true) {
                if (tytuly.get(1).toString().toUpperCase().contains("Justynę".toUpperCase()) == true) {
                    Toast.makeText(this, "Też cię kocham. Miłego dnia :)", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    public void dodaj(View view)
    {
        try {
            Bundle nowykosz = new Bundle();
            nowykosz.putInt("id", iloscNotatek);
            nowykosz.putString("akcja", "dodaj");
            Intent intent = new Intent(MyActivity.this, mod.class);
            intent.putExtras(nowykosz);

            startActivity(intent);
            finish();
        }
        catch (Exception e)
        {
            Toast t = Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT);
            t.show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ChangePassword:
                ChangePassword();
                return true;
            case R.id.Settings:
                Settings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void ChangePassword()
    {
        Intent i = new Intent(MyActivity.this,ChangePassword.class);
        startActivity(i);
    }
    public void Settings()
    {
        Intent i = new Intent(MyActivity.this,SettingsActivity.class);
        startActivity(i);
    }

}
