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
	private Color inactiveColor;

	public GateView(Gate gate) {
		super(150, 300);
		this.gate = gate;

		this.output = new LED(40, 40, 40);
		this.ledColor = new Color(46, 204, 113);
		this.inactiveColor = new Color(149, 165, 166);
				
		image = loadImage(gate.toString());
		
		JLabel inLabel = new JLabel("Entrada:");
		JLabel outLabel = new JLabel("Saida:");

		int size = gate.getSize();

		inBoxes = new JCheckBox[size];

		switches = new Switch[size];

		for(int i = 0; i < size; i++) {
			inBoxes[i] = new JCheckBox();
			inBoxes[i].addItemListener(this);
			switches[i] = new Switch();
			gate.connect(switches[i], i);
		}

		add(inLabel, 10, 0, 100, 25);
		int index = 1;
		for(JCheckBox inBox: inBoxes) {
			add(inBox, 10, 20*index, 50, 20);
			index += 1;
		}
		add(outLabel, 10, 80, 100, 25);
		
		colorButton = new JButton();
		colorButton.setOpaque(true);
		colorButton.addActionListener(this);
		add(colorButton, 50, 115, 15, 15);
		
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
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Color color = JColorChooser.showDialog(this, null, null);

		if(color != null) {
			ledColor = color;
			repaint();
		}
	}
	
		private Image loadImage(String filename) {
			URL url = getClass().getResource("/img/" + filename + ".png");
			ImageIcon icon = new ImageIcon(url);
			return icon.getImage();
		}
		@Override
		public void paintComponent(Graphics g) {
			System.out.println("Called");
			g.drawImage(image, 10, 150, 100, 100, null);
			Graphics2D g2d = (Graphics2D)g;
			
			//TODO: Mudar aqui para o valor correto: inactiveColor se estiver apagado, ou ledColor
			g2d.setPaint(ledColor);
			Ellipse2D.Double circle = new Ellipse2D.Double(10, 110, 30, 30);
			g2d.fill(circle);
			
			getToolkit().sync();
	    }
}
