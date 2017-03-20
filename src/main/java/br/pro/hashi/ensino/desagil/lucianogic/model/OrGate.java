package br.pro.hashi.ensino.desagil.lucianogic.model;

public class OrGate extends Gate {
	private NandGate nandGate;
	private NandGate nandGate1;
	private NandGate nandGate2;

	public OrGate() {
		super(2);
		nandGate = new NandGate();
		nandGate1 = new NandGate();
		nandGate2 = new NandGate();
	}

	@Override
	public boolean read() {
		return nandGate2.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		if(index==0){
			nandGate.connect(emitter, 0);
			nandGate.connect(emitter, 1);
		}
		if(index==1){
			nandGate1.connect(emitter, 0);
			nandGate1.connect(emitter, 1);
			nandGate2.connect(nandGate, 0);
			nandGate2.connect(nandGate1, 1);			
		}
	}
}