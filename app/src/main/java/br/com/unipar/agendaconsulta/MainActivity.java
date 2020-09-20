package br.com.unipar.agendaconsulta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Cursor cursor;
    SimpleCursorAdapter ad;
    ListView listViewAgendamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Busca os dados do banco
        buscarDados();

        //Seta o Adapter
        criarListagem();
    }

    public  void buscarDados(){
        try {
            db = openOrCreateDatabase("agendamento.db", Context.MODE_PRIVATE, null);
            cursor = db.rawQuery("SELECT * from agendamentos", null);
        } catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
        }
    }

    public void criarListagem(){
        //Pegar a listview
        listViewAgendamento = findViewById(R.id.list);

        String[] from = {"nome", "data", "medico", "telefone", "email"};
        int[] to = {R.id.nomeTextView, R.id.dataTextView, R.id.medicoTextView, R.id.telefoneTextView, R.id.emailTextView};

        ad = new SimpleCursorAdapter(getApplicationContext(), R.layout.lista, cursor, from, to);

        listViewAgendamento.setAdapter(ad);
    }

    public void telaCadastro(){
        Intent intent = new Intent(this,CadastroActivity.class);
        startActivity(intent);
    }
}