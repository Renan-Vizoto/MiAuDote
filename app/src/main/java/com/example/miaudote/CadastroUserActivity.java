package com.example.miaudote;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

public class CadastroUserActivity extends AppCompatActivity {

    Spinner spinnerTipo;
    EditText inputCPF, inputCNPJ, inputEndereco;

    private ImageButton buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);

        spinnerTipo = findViewById(R.id.spinnerTipo);
        inputCPF = findViewById(R.id.inputCPF);
        inputCNPJ = findViewById(R.id.inputCNPJ);
        inputEndereco = findViewById(R.id.inputEndereco);
        buttonVoltar = findViewById(R.id.buttonVoltar);

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroUserActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        // Adapter para preencher o Spinner
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
    }
}
