/* Generated by Together */

package bank.bankieren;

import java.io.*;
import java.text.*;

public class Money implements Serializable, Comparable<Money> {

	private static final long serialVersionUID = 1L;

	public static final String EURO = "\u20AC";

/*	private Money() {
		currency="undefined";
	}
*/
	/**
	 * er is een geldbedrag van munteenheid currency en waarde cents gecreeerd
	 * 
	 * @param amount
	 * @param currency
	 * @throws RuntimeException
	 *             als currency een lege string is
	 */
	public Money(long cents, String currency) {
		if (currency.equals(""))
			throw new RuntimeException("currency may not be the empty string");

		this.cents=cents;
		this.currency = currency;
	}

	/**
	 * 
	 * @return de munteenheid gevolgd door een spatie en de waarde in twee
	 *         decimalen nauwkeurig
	 */
	public String toString() {

		return currency + " " + getValue();
	}

	/**
	 * 
	 * @return <b>true</b> als het Money-object groter dan 0 is, anders
	 *         <b>false</b>
	 */
	public boolean isPositive() {
		return cents > 0;
	}

	public String getCurrency() {
		return currency;
	}

	/**
	 * 
	 * @return de waarde in twee decimalen nauwkeurig
	 */
	public String getValue() {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(((double) cents) / 100);

	}

	public long getCents() {
		return cents;
	}

	/**
	 * voorwaarde: currency van m1 en m2 moeten gelijk zijn
	 * @returns het verschil tussen m1 en m2
	 */
	public static Money difference(Money m1, Money m2) {
		if (!m1.currency.equals(m2.currency))
			throw new RuntimeException("munteenheden in aanroep 'difference' ongelijk, te weten: " +
					m1.currency + " en " + m2.currency
					);
		return new Money(m1.cents-m2.cents, m1.currency);
	}

	/**
	 * voorwaarde: currency van m1 en m2 moeten gelijk zijn
	 * @returns de som van m1 en m2
	 */
	public static Money sum(Money m1, Money m2) {
		if (!m1.currency.equals(m2.currency))
			throw new RuntimeException("munteenheden in aanroep 'sum' ongelijk, te weten: " +
					m1.currency + " en " + m2.currency
					);
		return new Money(m1.cents+m2.cents, m1.currency);
                
	}

	public boolean equals(Object o) {
		if (!(o instanceof Money))
			return false;
		Money m = (Money) o;
		return this.currency.equals(m.currency) && this.cents == m.cents;
	}

	private String currency;

	private long cents;

	public int compareTo(Money o) {
		Money m = (Money) o;
		if (!this.currency.equals(m.currency)) 
			throw new RuntimeException("comparing amounts with different currency is not implemented");
		if (this.cents==m.cents) return 0;
		if (this.cents<m.cents) return -1;
		else return +1;
		
	}
}
