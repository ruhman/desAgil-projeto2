package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Ellipse2D;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;

import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.LED;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;


// Esta classe representa a interface de uma porta logica.
public class GateView extends FixedPanel implements ItemListener, ActionListener {

	private static final long serialVersionUID = 1L;


	private JCheckBox[] inBoxes;

	private Switch[] switches;
	private Gate gate;
	
	private JButton colorButton;
	
	private Image image;
	
	private LED output;
	private Color ledColor;
	static Color activeColor;
	static Color inactiveColor;

	public GateView(Gate gate) {
		super(300, 300);
		this.gate = gate;

		this.output = new LED(40, 40, 40);

		if (activeColor == null) {
			activeColor = new Color(46, 204, 113);
		} 
		if (inactiveColor == null) {
			inactiveColor = new Color(149, 165, 166);
		}

		output.connect(gate, 0);
				
		image = loadImage(gate.toString());
		
		JLabel inLabel = new JLabel("<html><body>Mude as entradas abaixo para alterar o LED<br>Você pode trocar a cor do LED ON e do OFF</body></html>");
		JLabel outLabel = new JLabel("LED Out");

		int size = gate.getSize();

		inBoxes = new JCheckBox[size];

		switches = new Switch[size];

		for(int i = 0; i < size; i++) {
			inBoxes[i] = new JCheckBox();
			inBoxes[i].addItemListener(this);
			switches[i] = new Switch();
			gate.connect(switches[i], i);
		}

		add(inLabel, 10, -20, 300, 100);

		// In checkboxes
		for (int index = 0; index < inBoxes.length; index++) {
			switch (index) {
			case 0:
				add(inBoxes[index], 55, 110, 30, 30);
				break;
			case 1:
				add(inBoxes[index], 55, 150, 30, 30);				
				break;
			default:
				add(inBoxes[index], 130, 215, 30, 30);				
				break;
			}

		}
		
		add(outLabel, 220, 100, 100, 25);
		
		colorButton = new JButton();
		colorButton.setOpaque(true);
		colorButton.addActionListener(this);
		add(colorButton, 280, 103, 15, 15);
		
		checkLedStatus();
	}
	
	private void checkLedStatus(){
		if (output.isOn() == true){
			ledColor = activeColor;
		} else {
			ledColor = inactiveColor;
		}

		repaint();
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		int i;
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == event.getSource()) {
				break;
			}
		}

		switches[i].setOn(inBoxes[i].isSelected());
		checkLedStatus();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Color color = JColorChooser.showDialog(this, null, null);
		this.output = new LED(40, 40, 40);
		output.connect(gate, 0);
		
		if (output.isOn() == true){
			activeColor = color;
		} else {
			inactiveColor = color;
		}

		checkLedStatus();
	}
	
		private Image loadImage(String filename) {
			URL url = getClass().getResource("/img/" + filename + ".png");
			ImageIcon icon = new ImageIcon(url);
			return icon.getImage();
		}
		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(image, 75, 75, 150, 150, null);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setPaint(ledColor);
			Ellipse2D.Double circle = new Ellipse2D.Double(225, 125, 50, 50);
			g2d.fill(circle);
			
			getToolkit().sync();
	    }
}
