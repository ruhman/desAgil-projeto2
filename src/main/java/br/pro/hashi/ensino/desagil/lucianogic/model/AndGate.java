package br.pro.hashi.ensino.desagil.lucianogic.model;

public class AndGate extends Gate{
	private NandGate nandGate;
	private OrGate orGate;

	public AndGate() {
		super(2);
		nandGate = new NandGate();
		orGate = new OrGate();
	}

	@Override
	public boolean read() {
		// Quero ler apenas a saída do Or
		return nandGate.read() && orGate.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		
		// Queria conectar o nand na entrada do or, mas não entendi como faz isso
		
		nandGate.connect(emitter, 0);
		nandGate.connect(emitter, 1);
		
		orGate.connect(emitter, 0);
		orGate.connect(emitter, 1);
	}

}