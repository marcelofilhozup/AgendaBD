package com.example.android.agendabd;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OndeleteListener, OnEditListener {


    private CompromissoViewModel mCompViewModel;
    private CompromissoDao compromissoDao;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final String EXTRA_MESSAGE_OBJECT =
            "OBJECT";
    public static final String EXTRA_MESSAGE_INDEX =
            "INDEX";

    public static final String EXTRA_MESSAGE_EDIT =
            "TRUE";
    private final int REQUEST_CODE_EDIT = 101;
    CompromissoListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Novo_Compromisso.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new CompromissoListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        mCompViewModel = ViewModelProviders.of(this).get(CompromissoViewModel.class);

        mCompViewModel.getAllWords().observe(this, new Observer<List<Compromisso>>() {
            @Override
            public void onChanged(@Nullable final List<Compromisso> compromissos) {
                // Update the cached copy of the words in the adapter.
                adapter.setCompromissos(compromissos);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.setDeleteListener(this);
        adapter.setEditListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.setDeleteListener(null);
        adapter.setEditListener(null);

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



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            Compromisso comp = data.getParcelableExtra(Novo_Compromisso.EXTRA_MESSAGE);

            mCompViewModel.insert(comp);
        } else if(requestCode == REQUEST_CODE_EDIT) {
            Compromisso comp = data.getParcelableExtra(EXTRA_MESSAGE_OBJECT);


           mCompViewModel.updateCompromisso(comp);
        }
    }

    public void deleteItem(String comp){

         mCompViewModel.deleteCompromisso(comp);

    }

    @Override
    public void editItem(Compromisso comp) {

        Intent intent = new Intent(this, Edit_Compromisso.class);
        intent.putExtra(EXTRA_MESSAGE_OBJECT, comp);

        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }
}
