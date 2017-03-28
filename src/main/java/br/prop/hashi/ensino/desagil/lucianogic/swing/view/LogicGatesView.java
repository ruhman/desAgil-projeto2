package br.prop.hashi.ensino.desagil.lucianogic.swing.view;

import java.awt.event.ItemEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;


public class LogicGatesView extends JPanel implements ItemListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;
	
	private Gate gate;
	private LinkedList<JCheckBox> checkBoxes;
	private JCheckBox resultCheckbox;
	
	public LogicGatesView(Gate gate) {
		this.gate = gate;
		
		// A componente JLabel representa simplesmente um texto fixo.
		JLabel inputField = new JLabel("Input");
		JLabel outputField = new JLabel("Output");

		this.gate = gate;
			
		// set checkbox here
		resultCheckbox = new JCheckBox("");
		resultCheckbox.setEnabled(false);

		// stack components at vertical layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Create the correct amount of checkboxes
		this.checkBoxes = new LinkedList<>();
		for (int i = 0; i < gate.getSize(); i++) {
			JCheckBox checkbox = new JCheckBox(""+i);
			checkbox.addItemListener(this);
			this.checkBoxes.add(checkbox);
		}
		
		add(inputField);
		
		// Adds all checkboxes to view
		for (JCheckBox checkBox : this.checkBoxes) {
			add(checkBox);
		}
		
		add(outputField);
		add(resultCheckbox);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	    Object source = e.getItemSelectable();
	    
	    
	    // Proximos passos:
	    // 1. Inicializar a porta logica com todos os pinos setados para 0 no construtor
	    // 2. Usar a classe switch para modificar os pinos
	    // 3. Settar a checkbox de resultado para o valor de output da porta
	    
	    if (source == checkBoxes.get(0)) {
	    	System.out.println("First");
	    	
	    } else if (source == checkBoxes.get(1)) {
	    	System.out.println("second");
	    } else  if (source == checkBoxes.get(2)) {
	    	System.out.println("third");
	    	
	    }

	    if (e.getStateChange() == ItemEvent.DESELECTED) {
	    	System.out.println("Deselected");
	    }
	    
	    
	    
	    
	}

}
