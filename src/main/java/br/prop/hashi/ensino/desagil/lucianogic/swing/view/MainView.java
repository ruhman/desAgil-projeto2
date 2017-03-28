package br.prop.hashi.ensino.desagil.lucianogic.swing.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;

public class MainView extends JPanel implements ActionListener {
	
	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;
	
	
	// A componente JComboBox representa um menu.
	// https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html
	private JComboBox<Gate> comboBox;
	
	private LogicGatesView logicGatesView;
	
	
	public MainView(List<Gate> model) {
		comboBox = new JComboBox<>();
		
		for(Gate gate: model) {
			comboBox.addItem(gate);
		}
		
		comboBox.addActionListener(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(comboBox);
		
		addLogicGateView(comboBox.getItemAt(0));
	}
	
	private void addLogicGateView(Gate gate) {
		logicGatesView = new LogicGatesView(gate);
		add(logicGatesView);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		remove(logicGatesView);
		
		int index = comboBox.getSelectedIndex();
		Gate gate = comboBox.getItemAt(index);
		addLogicGateView(gate);
		
		// Necessario para atualizar a janela.
		JFrame frame = (JFrame) SwingUtilities.getRoot(this);
		frame.pack();
	}
}