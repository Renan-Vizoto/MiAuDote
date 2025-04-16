package com.example.miaudote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.miaudote.API.ApiService;
import com.example.miaudote.API.RetrofitClient;
import com.example.miaudote.model.Adotante;
import com.example.miaudote.model.Ong;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroUserActivity extends AppCompatActivity {

    Spinner spinnerTipo;
    EditText inputCPF, inputCNPJ, inputEndereco;
    EditText inputNome, inputEmail, inputConfirmarEmail, inputSenha, inputConfirmarSenha;
    Button buttonFinalizar;
    private ImageButton buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);

        // Inputs comuns
        inputNome = findViewById(R.id.inputNome);
        inputEmail = findViewById(R.id.inputEmail);
        inputConfirmarEmail = findViewById(R.id.inputConfirmarEmail);
        inputSenha = findViewById(R.id.inputSenha);
        inputConfirmarSenha = findViewById(R.id.inputConfirmarSenha);
        buttonFinalizar = findViewById(R.id.buttonFinalizarCadastro);

        // Dinâmicos
        spinnerTipo = findViewById(R.id.spinnerTipo);
        inputCPF = findViewById(R.id.inputCPF);
        inputCNPJ = findViewById(R.id.inputCNPJ);
        inputEndereco = findViewById(R.id.inputEndereco);
        buttonVoltar = findViewById(R.id.buttonVoltar);

        buttonVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(CadastroUserActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Spinner adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipos_usuarios, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);

        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) { // Adotante
                    inputCPF.setVisibility(View.VISIBLE);
                    inputCNPJ.setVisibility(View.GONE);
                    inputEndereco.setVisibility(View.GONE);
                } else if (position == 1) { // ONG
                    inputCPF.setVisibility(View.GONE);
                    inputCNPJ.setVisibility(View.VISIBLE);
                    inputEndereco.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Não faz nada
            }
        });

        // Cadastrando
        buttonFinalizar.setOnClickListener(v -> cadastrarUsuario());
    }

    private void cadastrarUsuario() {
        String nome = inputNome.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String confirmarEmail = inputConfirmarEmail.getText().toString().trim();
        String senha = inputSenha.getText().toString().trim();
        String confirmarSenha = inputConfirmarSenha.getText().toString().trim();
        String tipo = spinnerTipo.getSelectedItem().toString();
        String cpf = inputCPF.getText().toString().trim();
        String cnpj = inputCNPJ.getText().toString().trim();
        String endereco = inputEndereco.getText().toString().trim();

        if (!email.equals(confirmarEmail)) {
            Toast.makeText(this, "Os e-mails não coincidem.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            Toast.makeText(this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        if (tipo.equals("Adotante")) {
            Adotante adotante = new Adotante(nome, email, senha, cpf);
            apiService.cadastrarAdotante(adotante).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(CadastroUserActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CadastroUserActivity.this, "Erro no cadastro: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(CadastroUserActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else if (tipo.equals("ONG")) {
            Ong ong = new Ong(nome, email, senha, cnpj, endereco);
            apiService.cadastrarOng(ong).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(CadastroUserActivity.this, "Cadastro de ONG realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CadastroUserActivity.this, "Erro no cadastro da ONG: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(CadastroUserActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
