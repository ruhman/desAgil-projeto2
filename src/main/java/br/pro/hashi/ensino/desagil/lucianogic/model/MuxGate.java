package br.pro.hashi.ensino.desagil.lucianogic.model;

public class MuxGate extends Gate {
	private NandGate nandGate;
	private NandGate nandGate1;
	private NandGate nandGate2;
	private NandGate nandGate3;
	public MuxGate() {
		super(3);
		nandGate = new NandGate();
		nandGate1 = new NandGate();
		nandGate2= new NandGate();
		nandGate3 = new NandGate();
	}
	
	@Override
	public boolean read() {
		return nandGate3.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		switch (index) {
		case 0:
			nandGate1.connect(emitter, 0);
			break;
		case 1:
			nandGate2.connect(emitter, 0);
			break;
		default:
			nandGate.connect(emitter, 0);
			nandGate.connect(emitter, 1);
			nandGate2.connect(emitter, 1);
			nandGate1.connect(nandGate ,1);
			nandGate3.connect(nandGate1 ,0);
			nandGate3.connect(nandGate2 ,1);	
			break;
		}

	}

}
