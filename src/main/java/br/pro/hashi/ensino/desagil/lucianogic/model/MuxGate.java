package br.pro.hashi.ensino.desagil.lucianogic.model;

public class MuxGate extends Gate {
	//Não fiz ainda
	private NandGate nandGate;

	public MuxGate() {
		super(1);
		nandGate = new NandGate();
	}

	@Override
	public boolean read() {
		return nandGate.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		nandGate.connect(emitter, 0);
		nandGate.connect(emitter, 1);
	}

}
