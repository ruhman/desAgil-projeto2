package br.pro.hashi.ensino.desagil.lucianogic.model;

public class OrGate extends Gate{
	// Não fiz ainda
	private NandGate nandGate;

	public OrGate() {
		super(2);
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
