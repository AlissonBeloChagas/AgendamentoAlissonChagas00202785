package br.com.unipar.agendaconsulta;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText edNome, edData, edMedico, edTelefone, edEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        //Crio a tabela
        criarTabela();

        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);

        edNome = findViewById(R.id.edNome);
        edData = findViewById(R.id.edData);
        edMedico = findViewById(R.id.edMedico);
        edTelefone = findViewById(R.id.edTelefone);
        edEmail = findViewById(R.id.edEmail);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                try {
                    //Chamos a classe agendamento e preencho a mesma com as informações
                    Agendamento agendamento = new Agendamento();
                    agendamento.setNome(edNome.getText().toString());
                    agendamento.setData(edData.getText().toString());
                    agendamento.setMedico(edMedico.getText().toString());
                    agendamento.setTelefone(edTelefone.getText().toString());
                    agendamento.setEmail(edTelefone.getText().toString());

                    //Clico para salvar o Agendamento
                    salvarAgendamento(agendamento);

                    voltarListagem();

                }catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "Occoreu um Erro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void criarTabela(){
        SQLiteDatabase db = null;
        try {
            db = openOrCreateDatabase("agendamento.db", Context.MODE_PRIVATE, null);

            StringBuilder sql = new StringBuilder();
            sql.append("CREATE TABLE IF NOT EXISTS agendamento(");
            sql.append("_id integer primary key autoincrement,");
            sql.append("nome varchar(120),");
            sql.append("data varchar(120),");
            sql.append("medico varchar(120),");
            sql.append("numero varchar(120),");
            sql.append("email varchar(120))");

            db.execSQL(sql.toString());
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Erro Occoreu", Toast.LENGTH_LONG).show();
        }finally {
            db.close();
        }
    }

    public void salvarAgendamento(Agendamento agendamento) {
        SQLiteDatabase db = null;
        try {
            db = openOrCreateDatabase("agendamentos.db", Context.MODE_PRIVATE, null);

            ContentValues contentInsert = new ContentValues();
            contentInsert.put("nome", agendamento.getNome());
            contentInsert.put("data", agendamento.getData());
            contentInsert.put("medico", agendamento.getMedico());
            contentInsert.put("telefone", agendamento.getTelefone());
            contentInsert.put("email", agendamento.getEmail());
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Erro ao inserir", Toast.LENGTH_SHORT).show();
            ;
        } finally {
            Toast.makeText(getApplicationContext(), "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
        public void voltarListagem(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
}
