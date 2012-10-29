package com.gdroid.visao;

import com.gdroid.controle.CadastroClientes;
import com.gdroid.controle.CadastroPedidos;
import com.gdroid.visao.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button botao_cadastro_pedidos;
	private Button botao_cadasto_usuario;
	private Button botao_cadastro_clientes;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		botao_cadastro_pedidos = (Button) findViewById(R.id.botao_cadastro_pedidos);
		botao_cadasto_usuario = (Button) findViewById(R.id.botao_cadasto_usuario);
		botao_cadastro_clientes = (Button) findViewById(R.id.botao_cadastro_clientes);
		botao_cadasto_usuario
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View target) {
						
					Intent it = new Intent(MainActivity.this, Login.class);
						startActivity(it);
					}
					
				});
		
		botao_cadastro_clientes
		.setOnClickListener(new View.OnClickListener() {
			public void onClick(View target) {
				
				Intent it = new Intent(MainActivity.this, CadastroClientes.class);
				startActivity(it);
				
			}
		});
		
		botao_cadastro_pedidos
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View target) {
						
						Intent it = new Intent(MainActivity.this, CadastroPedidos.class);
						startActivity(it);
						
					}
				});
    }
}