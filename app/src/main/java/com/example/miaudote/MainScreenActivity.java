package com.example.miaudote;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainScreenActivity extends AppCompatActivity {

    private Button buttonLogout;
    private GridLayout gridContainer;

    class Animal {
        String nome;
        String tipo;
        int imagemResId;
        int corDeFundo;
        String genero;
        String descricao;

        Animal(String nome, String tipo, int imagemResId, int corDeFundo, String genero, String descricao) {
            this.nome = nome;
            this.tipo = tipo;
            this.imagemResId = imagemResId;
            this.corDeFundo = corDeFundo;
            this.genero = genero;
            this.descricao = descricao;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogout = findViewById(R.id.buttonLogout);
        gridContainer = findViewById(R.id.gridContainer);

        // Lógica do botão de logout
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        // Lista de animais para exibir com o campo de gênero
        List<Animal> animais = Arrays.asList(
                new Animal("Fred", "Cachorro", R.drawable.fred, Color.parseColor("#FFE1CC"), "Macho", "Fred é um cachorro alegre e cheio de energia. Ele adora correr no parque e brincar com outros cães. Seu pelo dourado é macio, e ele é muito amigável com as crianças."),
                new Animal("Amora", "Gato", R.drawable.amora, Color.parseColor("#FFEEB9"), "Fêmea", "Amora é uma gata tranquila e independente. Ela adora dormir em lugares quentinhos e está sempre procurando um cantinho para se aconchegar. Quando está com fome, não hesita em chamar a atenção de todos para uma boa refeição."),
                new Animal("Pipoca", "Cachorro", R.drawable.pipoca, Color.parseColor("#D4FAF6"), "Macho", "Pipoca é um cãozinho brincalhão que adora companhia. Ele está sempre pronto para aventuras e se divertir com seus donos. Seu olhar curioso e energia contagiante fazem dele o melhor amigo de qualquer um."),
                new Animal("Mel", "Cachorro", R.drawable.mel, Color.parseColor("#E5D4FB"), "Fêmea", "Mel é uma cachorrinha doce e muito carinhosa. Ela adora receber carinho e fazer companhia para quem estiver por perto. Sempre que alguém chega em casa, ela corre para dar as boas-vindas com uma alegria imensa.")
        );

        LayoutInflater inflater = LayoutInflater.from(this);

        for (Animal animal : animais) {
            View card = inflater.inflate(R.layout.item_animal_card, gridContainer, false);

            TextView textName = card.findViewById(R.id.textName);
            TextView textType = card.findViewById(R.id.textType);
            ImageView imageAnimal = card.findViewById(R.id.imageAnimal);

            textName.setText(animal.nome);
            textType.setText(animal.tipo);
            imageAnimal.setImageResource(animal.imagemResId);
            card.setBackgroundColor(animal.corDeFundo);

            card.setOnClickListener(v -> {
                // Passando o gênero para a próxima activity
                Intent intent = new Intent(MainScreenActivity.this, AnimalDetailActivity.class);
                intent.putExtra("nome", animal.nome);
                intent.putExtra("tipo", animal.tipo);
                intent.putExtra("genero", animal.genero);
                intent.putExtra("imagem", animal.imagemResId);
                intent.putExtra("descricao", animal.descricao);
                startActivity(intent);
            });

            gridContainer.addView(card);
        }
    }
}

