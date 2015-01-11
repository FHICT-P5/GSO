package bank.bankieren;

import bank.internettoegang.Balie;
import fontys.util.*;
import java.rmi.RemoteException;

import java.util.*;

public class Bank implements IBank  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8728841131739353765L;
	private Map<Integer,IRekeningTbvBank> accounts;
	private Collection<IKlant> clients;
	private int nieuwReknr;
	private String name;
        private ICentraleBank centraleBank;
        private Balie balie;

	public Bank(String name, ICentraleBank cb) {
		accounts = new HashMap<Integer,IRekeningTbvBank>();
		clients = new ArrayList<IKlant>();
		nieuwReknr = 100000000;	
		this.name = name;
                centraleBank = cb;
                //cb.addBank(this);
	}

	public int openRekening(String name, String city) {
		if (name.equals("") || city.equals(""))
			return -1;

		IKlant klant = getKlant(name, city);
		IRekeningTbvBank account = new Rekening(nieuwReknr, klant, Money.EURO);
		accounts.put(nieuwReknr,account);
		nieuwReknr++;
		return nieuwReknr-1;
	}

	private IKlant getKlant(String name, String city) {
		for (IKlant k : clients) {
			if (k.getNaam().equals(name) && k.getPlaats().equals(city))
				return k;
		}
		IKlant klant = new Klant(name, city);
		clients.add(klant);
		return klant;
                
	}

	public IRekening getRekening(int nr) {
		return accounts.get(nr);
	}

	public boolean maakOver(int source, int destination, Money money)
			throws NumberDoesntExistException {
            
            Money negative = Money.difference(new Money(0, money.getCurrency()),
				money);
            
		if (source == destination)
			throw new RuntimeException(
					"cannot transfer money to your own account");
		if (!money.isPositive())
			throw new RuntimeException("money must be positive");

		IRekeningTbvBank source_account = (IRekeningTbvBank) getRekening(source);
		if (source_account == null) {
                    throw new NumberDoesntExistException("account " + destination
                    + " unknown at " + name);
                }
                

		
		boolean success = source_account.muteer(negative);
		if (!success)
			return false;

		IRekeningTbvBank dest_account = (IRekeningTbvBank) getRekening(destination);
		if (dest_account == null) 
                {
                    Bank bank = null;
                    
                    try
                    {
                        bank = (Bank)centraleBank.getBank(destination);
                    }
                    catch(RemoteException ex)
                    {
                        System.out.println("RemoteException: " + ex.getMessage());
                    }
                    
                    if(bank == null) {
                        source_account.muteer(money);
			throw new NumberDoesntExistException("account " + source
					+ " unknown at every bank");
                    }
                    else {
                        if(!bank.maakOverAndereBank(destination, money)) {
                            source_account.muteer(money);
                            return false;
                        }
                        
                        //bank.balie.updateBankiersessie(destination, money);
                        //balie.updateBankiersessie(source, money);
                        return true;
                    }
                    
                }
			
		success = dest_account.muteer(money);

		if (!success) // rollback
			source_account.muteer(money);
                else
                {
                    //balie.updateBankiersessie(destination, money);
                    //balie.updateBankiersessie(source, money);
                }
                
		return success;
	}
        
        public boolean maakOverAndereBank(int destination, Money money){
		Money negative = Money.difference(new Money(0, money.getCurrency()),
				money);
		boolean success;

		IRekeningTbvBank dest_account = (IRekeningTbvBank) getRekening(destination);

		success = dest_account.muteer(money);

		return success;
        }

	@Override
	public String getName() {
		return name;
	}
}
