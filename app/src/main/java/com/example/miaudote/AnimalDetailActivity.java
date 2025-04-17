package com.example.miaudote;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AnimalDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);

        // Seta de voltar
        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(v -> finish());

        // Dados recebidos
        String nome = getIntent().getStringExtra("nome");
        String tipo = getIntent().getStringExtra("tipo");
        String genero = getIntent().getStringExtra("genero");
        int imagemResId = getIntent().getIntExtra("imagem", 0);
        String descricao = getIntent().getStringExtra("descricao");

        TextView textNome = findViewById(R.id.textNomeAnimal);
        TextView textTipo = findViewById(R.id.textTipoAnimal);
        TextView textGenero = findViewById(R.id.textGeneroAnimal);
        ImageView imageAnimal = findViewById(R.id.imageAnimalDetail);
        TextView textDescricao = findViewById(R.id.textDescricao);

        textNome.setText(nome);
        textTipo.setText(tipo);
        textGenero.setText(genero);
        imageAnimal.setImageResource(imagemResId);
        textDescricao.setText(descricao);
    }
}
