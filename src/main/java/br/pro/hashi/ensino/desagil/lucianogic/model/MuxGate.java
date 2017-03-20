package br.pro.hashi.ensino.desagil.lucianogic.model;

public class MuxGate extends Gate {
	private NandGate nandGate;
	private NandGate nandGate2;
	private OrGate orGate;
	private NotGate notGate;

	public MuxGate() {
		super(1);
		nandGate = new NandGate();
		nandGate2 = new NandGate();
		orGate = new OrGate();
		notGate = new NotGate();
	}

	@Override
	public boolean read() {
		return orGate.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		// logic; http://improve.dk/creating-multiplexers-using-logic-gates/
		switch (index) {
		case 0:
			nandGate.connect(emitter, 0);
			break;
		case 1:
			nandGate2.connect(emitter, 1);
			break;
			
		// Case 2 => selector input
		default:
			notGate.connect(emitter, 0);
			nandGate.connect(notGate, 1);
			nandGate2.connect(emitter, 0);
			break;
		}
		
		orGate.connect(nandGate, 0);
		orGate.connect(nandGate2, 1);
	}

}
