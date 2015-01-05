package bank.internettoegang;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import bank.bankieren.IBank;
import bank.bankieren.IRekening;
import bank.bankieren.Money;
import bank.gui.BankierSessieController;
import static bank.internettoegang.IBankiersessie.GELDIGHEIDSDUUR;
import fontys.observer.BasicPublisher;

import fontys.util.InvalidSessionException;
import fontys.util.NumberDoesntExistException;

public class Bankiersessie extends UnicastRemoteObject implements
		IBankiersessie{

	private static final long serialVersionUID = 1L;
	private long laatsteAanroep;
	private int reknr;
	private IBank bank;
        public long money;
        private BasicPublisher publisher;
        private BankierSessieController controller;

	public Bankiersessie(int reknr, IBank bank) throws RemoteException {
		laatsteAanroep = System.currentTimeMillis();
		this.reknr = reknr;
		this.bank = bank;
	}
        
	public boolean isGeldig() {
		return System.currentTimeMillis() - laatsteAanroep < GELDIGHEIDSDUUR;
	}

	@Override
	public boolean maakOver(int bestemming, Money bedrag)
			throws NumberDoesntExistException, InvalidSessionException,
			RemoteException {
		
		updateLaatsteAanroep();
		
		if (reknr == bestemming)
			throw new RuntimeException(
					"source and destination must be different");
		if (!bedrag.isPositive())
			throw new RuntimeException("amount must be positive");
		
		return bank.maakOver(reknr, bestemming, bedrag);
	}

	private void updateLaatsteAanroep() throws InvalidSessionException {
		if (!isGeldig()) {
			throw new InvalidSessionException("session has been expired");
		}
		
		laatsteAanroep = System.currentTimeMillis();
	}

	@Override
	public IRekening getRekening() throws InvalidSessionException,
			RemoteException {

		updateLaatsteAanroep();
		
		return bank.getRekening(reknr);
	}

	@Override
	public void logUit() throws RemoteException {
		UnicastRemoteObject.unexportObject(this, true);
	}

//    @Override
//    public void update(Observable o, Object arg) {
//        
//            try {
//                this.money = getRekening().getSaldo().getCents();
//            } catch (InvalidSessionException ex) {
//                Logger.getLogger(Bankiersessie.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (RemoteException ex) {
//                Logger.getLogger(Bankiersessie.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            System.out.println("Money: " + this.money);
//    }

    @Override
    public void update() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getRekeningnummer() {
        return this.reknr;
    }

    public void addController(BankierSessieController controller) {
        this.controller = controller;
    }
    
    public void updateSaldo(long cents) {
        controller.updateSaldo(cents);
    }
}
