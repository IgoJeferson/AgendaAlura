package br.com.alura.igojeferson.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.alura.igojeferson.agenda.dao.AlunoDAO;
import br.com.alura.igojeferson.agenda.helper.FormularioHelper;
import br.com.alura.igojeferson.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if(aluno != null){
            helper.preencheFormulario(aluno);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:

                Aluno novoAluno = helper.getAluno();
                AlunoDAO dao = new AlunoDAO(this);
                dao.insere(novoAluno);
                dao.close();

                Toast.makeText(FormularioActivity.this, "Aluno " + novoAluno.getNome() + " salvo!", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
