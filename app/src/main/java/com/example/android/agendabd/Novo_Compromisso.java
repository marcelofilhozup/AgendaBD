package com.example.android.agendabd;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Novo_Compromisso extends AppCompatActivity {

    public static final String EXTRA_MESSAGE =
            "com.example.android.novo.extra.MESSAGE";

    private EditText mMessageCompromisso;
    private EditText mMessageDescricao;
    private EditText mMessageData;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_novo__compromisso);
            mMessageCompromisso = findViewById(R.id.editTextCompromisso);
            mMessageDescricao = findViewById(R.id.editTextDescricao);
            mMessageData = findViewById(R.id.editTextData);
        }



    public void launchSalvar(View view) {
        Compromisso reply2 = new Compromisso(
                mMessageCompromisso.getText().toString(),mMessageDescricao.getText().toString(),mMessageData.getText().toString());
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_MESSAGE, reply2);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

}
