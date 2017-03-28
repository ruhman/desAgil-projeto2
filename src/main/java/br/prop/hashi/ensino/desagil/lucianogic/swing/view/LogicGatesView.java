package br.prop.hashi.ensino.desagil.lucianogic.swing.view;

import java.awt.event.ItemEvent;
import java.util.LinkedList;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;


public class LogicGatesView extends JPanel implements ItemListener {
	
	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;
	
	public Gate gate;
	private LinkedList<JCheckBox> checkBoxes;
	private JCheckBox resultCheckbox;
	private Switch pino0;
	private Switch pino1;
	private Switch pino2;
	private int tamanho;
	
	public LogicGatesView(Gate gate) {
		this.gate = gate;
		
		tamanho = gate.getSize();
		pino0 = new Switch();
		pino1 = new Switch();
		pino2 = new Switch();
		gate.connect(pino0, 0);
		if (tamanho > 1){
			gate.connect(pino1, 1);
		}
		if (tamanho > 2){
			gate.connect(pino2, 2);
		}
		// A componente JLabel representa simplesmente um texto fixo.
		JLabel inputField = new JLabel("Input");
		JLabel outputField = new JLabel("Output");
		
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
		if(gate.read() == true){
			resultCheckbox.setSelected(true);
		}else{
			resultCheckbox.setSelected(false);
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();
		
		if (source == checkBoxes.get(0)) {
			System.out.println("First");
			System.out.println(gate);
			pino0.setOn(true);
			System.out.println(gate.read());
			
		} else if (source == checkBoxes.get(1)) {
			System.out.println("second");
			pino1.setOn(true);
			System.out.println(gate);
			System.out.println(gate.read());
			
		} else  if (source == checkBoxes.get(2)) {
			System.out.println("third");
			pino2.setOn(true);
			System.out.println(gate);
			System.out.println(gate.read());
			
		}
		
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			System.out.println("Deselected");
			if (source == checkBoxes.get(0)) {
				pino0.setOn(false);
				
			} else if (source == checkBoxes.get(1)) {
				pino1.setOn(false);
			} else  if (source == checkBoxes.get(2)) {
				pino2.setOn(false);
				
			}
		}
		if(gate.read() == true){
			resultCheckbox.setSelected(true);
		}else{
			resultCheckbox.setSelected(false);
		}
		
		
		
	}
	
}
