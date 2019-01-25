package com.example.android.agendabd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Edit_Compromisso extends AppCompatActivity {
    private EditText editTextCompromisso;
    private EditText editTextDescricao;
    private EditText editTextData;
    private EditText editTextteste;

    private int index;

    public static Compromisso compromisso;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__compromisso);

        setupViews();
        initComp();
    }

    private void setupViews(){
        editTextCompromisso = findViewById(R.id.editTextCompromisso2);
        editTextDescricao = findViewById(R.id.editTextDescricao2);
        editTextData = findViewById(R.id.editTextData2);

    }

    private void initComp() {
        Intent intent = getIntent();
        if(intent != null){

            compromisso = intent.getParcelableExtra(MainActivity.EXTRA_MESSAGE_OBJECT);
            index = intent.getIntExtra(MainActivity.EXTRA_MESSAGE_INDEX, -1);
            System.out.println(compromisso.getID());
            editTextCompromisso.setText(compromisso.getCompromisso());
            editTextDescricao.setText(compromisso.getDescricao());
            editTextData.setText(compromisso.getData());

        }
    }

    public void editar(View v){

        String comp = editTextCompromisso.getText().toString().trim();
        String descricao = editTextDescricao.getText().toString().trim();
        String data = editTextData.getText().toString().trim();
        compromisso.setCompromisso(comp);
        compromisso.setDescricao(descricao);
        compromisso.setData(data);


        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        System.out.println("ID -- ");
        System.out.println(compromisso.getCompromisso());

        bundle.putParcelable(MainActivity.EXTRA_MESSAGE_OBJECT, compromisso);

        bundle.putInt(MainActivity.EXTRA_MESSAGE_INDEX, index);
        intent.putExtras(bundle);

        setResult(RESULT_OK, intent);
        finish();


    }
}
