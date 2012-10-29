package com.gdroid.visao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gdroid.visao.R;

public class TelaInicial extends ArrayAdapter<String>{
	
	private final Context context;
	private final String[] values;
	
	public TelaInicial(Context context, String[] values) {
		super(context, R.layout.tela_inicial, values);
		this.context = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.tela_inicial, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.tela_inicial);
				
		textView.setText(values[position]);

		String s = values[position];
		if (s.startsWith("Localize-se na UFRJ")) {
					
		}
		else if(s.startsWith("  Locais Próximos")){
		
		}
		else if(s.startsWith("Buscar Assuntos")){
		
		}
		
		return rowView;
	}
}

