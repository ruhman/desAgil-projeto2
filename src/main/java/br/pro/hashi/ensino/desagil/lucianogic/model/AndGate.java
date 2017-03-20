package br.pro.hashi.ensino.desagil.lucianogic.model;

public class AndGate extends Gate{
	private NandGate nandGate1;
	private NandGate nandGate2;

	public AndGate() {
		super(2);
		nandGate1 = new NandGate();
		nandGate2 = new NandGate();
	}

	@Override
	public boolean read() {
		return nandGate2.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		
		// Queria conectar a saida do nand1 na entrada do nand2, mas não entendi como faz isso		
		if (index == 0) {
			nandGate1.connect(emitter, 0);
		} else {
			nandGate1.connect(emitter, 1);
		}
		nandGate2.connect(nandGate1, 0);
		nandGate2.connect(nandGate1, 1);
		
	

	}

}