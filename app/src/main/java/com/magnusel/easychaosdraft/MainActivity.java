package com.magnusel.easychaosdraft;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText txtBoosterName, txtBoosterCount, txtPlayerName;
    TextView txtViewBoosters, txtViewPlayers;
    Button btnAddBoosters, btnAddPlayer, btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBoosterName = (EditText) findViewById(R.id.txtBoosterName);
        txtBoosterCount = (EditText) findViewById(R.id.txtBoosterCount);
        txtPlayerName = (EditText) findViewById(R.id.txtPlayerName);
        txtViewBoosters = (TextView) findViewById(R.id.txtViewBoosters);
        txtViewPlayers = (TextView) findViewById(R.id.txtViewPlayers);
        btnAddBoosters = (Button) findViewById(R.id.btnAddBooster);
        btnAddPlayer = (Button) findViewById(R.id.btnAddPlayer);
        btnStart = (Button) findViewById(R.id.btnStart);

        final ArrayList<Booster> thePool = new ArrayList<>();
        final ArrayList<Player> thePlayers = new ArrayList<>();

        btnAddBoosters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Booster newBooster = new Booster(txtBoosterName.getText().toString());
                int numberOfBoosters;

                try {
                    if(txtBoosterCount.getText().toString().equals("")) {
                        numberOfBoosters = 1;
                    } else {
                        numberOfBoosters = Integer.parseInt(txtBoosterCount.getText().toString());
                    }

                    for(int i = 0; i < numberOfBoosters; i++) {
                        thePool.add(newBooster);
                    }
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                }

                StringBuilder currentPool = new StringBuilder();

                for(int i = 0; i <= thePool.size() - 1; i++) {
                    currentPool.append(thePool.get(i).toString() + "\n");
                }

                txtViewBoosters.setText(currentPool);

                txtBoosterName.setText("");
            }
        });

        btnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = txtPlayerName.getText().toString();
                Player newPlayer = new Player(playerName);

                thePlayers.add(newPlayer);

                StringBuilder currentPlayers = new StringBuilder();

                for(int i = 0; i <= thePlayers.size() - 1; i++) {
                    currentPlayers.append(thePlayers.get(i).getName() + "\n");
                }

                txtViewPlayers.setText(currentPlayers);

                txtPlayerName.setText("");
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int poolSize = thePool.size();
                    int playerNumber = thePlayers.size();
                    int boosterPrPlayer = poolSize / playerNumber;

                    Random rnd = new Random();

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_deal, null);
                    TextView txtDeal = (TextView) mView.findViewById(R.id.txtViewDeal);

                    mBuilder.setView(mView);
                    AlertDialog dialog = mBuilder.create();

                    StringBuilder dealing = new StringBuilder();

                    for(int i = 0; i < thePlayers.size(); i++) {
                        Player currentPlayer = thePlayers.get(i);

                        for(int x = 0; x < boosterPrPlayer; x++) {
                            int boosterIndex = rnd.nextInt(thePool.size());
                            Booster currentBooster = thePool.get(boosterIndex);

                            currentPlayer.recieveBooster(currentBooster);

                            if(!thePool.isEmpty()) {
                                thePool.remove(boosterIndex);
                            }
                        }

                        dealing.append(currentPlayer.toString() + "\n\n");
                        txtDeal.setText(dealing);
                    }

                    dialog.show();

                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
