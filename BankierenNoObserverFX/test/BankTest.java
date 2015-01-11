/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import bank.bankieren.Bank;
import bank.bankieren.CentraleBank;
import bank.bankieren.IBank;
//import bank.bankieren.IRekeningTbvBank;
import bank.bankieren.Money;
import bank.bankieren.Rekening;
import bank.internettoegang.Balie;
import fontys.util.NumberDoesntExistException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juliusername
 */
public class BankTest {
    
    private IBank bank;
    private CentraleBank mockCentrale;
    private Balie balie;
    
    public BankTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            mockCentrale = new CentraleBank();
            bank = new Bank("Bank1", mockCentrale);
            balie = new Balie(bank);
            bank.addBalie(balie);
            
        } catch (RemoteException ex) {
            Logger.getLogger(BankTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @After
    public void tearDown() {
    }    
    
    /**
     * creatie van een nieuwe bankrekening met een identificerend rekeningnummer; 
     * alleen als de klant, geidentificeerd door naam en plaats, nog niet bestaat 
     * wordt er ook een nieuwe klant aangemaakt
     * 
     * @param naam
     *            van de eigenaar van de nieuwe bankrekening
     * @param plaats
     *            de woonplaats van de eigenaar van de nieuwe bankrekening
     * @return -1 zodra naam of plaats een lege string en anders het nummer van de
     *         gecreeerde bankrekening
     */
    @Test
    //(expected=IllegalArgumentException.class)
    public void TestOpenRekening()
    {
        String naam = "Rekening1";
        String plaats = "Eindhoven";
        
        try
        {
        assertEquals(bank.openRekening("",plaats), -1);
        assertEquals(bank.openRekening(naam,""), -1);
        assertEquals(bank.openRekening(naam,plaats), 100000000);
        }
        catch (RemoteException ex)
        {
            System.out.println("RemoteException: " + ex.getMessage());
            fail("Assert failed; zie RemoteException");
        }
    }
    
    /**
     * er wordt bedrag overgemaakt van de bankrekening met nummer bron naar de
     * bankrekening met nummer bestemming, mits het afschrijven van het bedrag
     * van de rekening met nr bron niet lager wordt dan de kredietlimiet van deze
     * rekening 
     * 
     * @param bron
     * @param bestemming
     *            ongelijk aan bron
     * @param bedrag
     *            is groter dan 0
     * @return <b>true</b> als de overmaking is gelukt, anders <b>false</b>
     * @throws NumberDoesntExistException
     *             als een van de twee bankrekeningnummers onbekend is
     */
    @Test
    public void TestMaakOver()
    {
        boolean success;
        
        int bron;
        int bestemming;
        try {
            bron = bank.openRekening("Rekening1", "Eindhoven");
            bestemming = bank.openRekening("Rekening2", "Son");
            
            Money bedrag = new Money(20000, "\u20AC");
        
            try
            {
                success = bank.maakOver(bron, bestemming, bedrag);
            }
            catch (NumberDoesntExistException ex)
            {
                success = false;
                System.out.println("NumberDoesntExistException: " + ex.getMessage());
            }
            assertFalse("Bron rekening heeft niet genoeg geld", success);

            bron = bank.openRekening("Rekening1", "Eindhoven");
            bestemming = bank.openRekening("Rekening2", "Son");
            Rekening rekening = (Rekening)bank.getRekening(bron);
            rekening.muteer(new Money(30000, "\u20AC"));

            try
            {
                success = bank.maakOver(bron, bestemming, bedrag);
            }
            catch (NumberDoesntExistException ex)
            {
                success = false;
                System.out.println("NumberDoesntExistException: " + ex.getMessage());
            }
            assertTrue("Geld is niet overgemaakt", success);

            bron = bank.openRekening("Rekening1", "Eindhoven");
            bestemming = bank.openRekening("Rekening2", "Son");
            rekening = (Rekening)bank.getRekening(bron);
            rekening.muteer(new Money(30000, "\u20AC"));

            try
            {
                success = bank.maakOver(bron, bron, bedrag);
            }
            catch (RuntimeException ex)
            {
                success = false;
                System.out.println("RunTimeException: " + ex.getMessage());
            }
            catch (NumberDoesntExistException ex)
            {
                success = false;
                System.out.println("NumberDoesntExistException: " + ex.getMessage());
            }
            assertFalse("Bron en bestemmingsrekening zijn hetzelfde", success);

            bron = bank.openRekening("Rekening1", "Eindhoven");
            bestemming = bank.openRekening("Rekening2", "Son");
            rekening = (Rekening)bank.getRekening(bron);
            rekening.muteer(new Money(30000, "\u20AC"));

            try
            {
                success = bank.maakOver(bron, bestemming, null);
            }
            catch (NullPointerException ex)
            {
                success = false;
                System.out.println("NullPointerException: " + ex.getMessage());
            }
            catch (NumberDoesntExistException ex)
            {
                success = false;
                System.out.println("NumberDoesntExistException: " + ex.getMessage());
            }
            assertFalse("Money is null", success);

            bron = bank.openRekening("Rekening1", "Eindhoven");
            bestemming = bank.openRekening("Rekening2", "Son");
            rekening = (Rekening)bank.getRekening(bron);
            rekening.muteer(new Money(30000, "\u20AC"));

            Rekening rekening2 = (Rekening)bank.getRekening(bestemming);
            long bronCents = 0;
            long bestemmingCents = 0;

            try
            {
                success = bank.maakOver(bron, bestemming, bedrag);
            }
            catch (NumberDoesntExistException ex)
            {
                success = false;
                System.out.println("NumberDoesntExistException: " + ex.getMessage());
            }

            bronCents = rekening.getSaldo().getCents();
            bestemmingCents = rekening2.getSaldo().getCents();


            assertTrue("Overmaking is niet geslaagd", success);
            assertEquals("Bron rekening bedrag is niet correct", 11000, bronCents);
            assertEquals("Bestemming rekening bedrag is niet correct", 21000, bestemmingCents);
            
        } catch (RemoteException ex) {
            Logger.getLogger(BankTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Assert failed; zie RemoteException");
        }
       
        
    }
    
    @Test
    public void TestMaakOverBronIncorrect1()
    {
        
        try {
            int bron = bank.openRekening("Rekening1", "Eindhoven");
            int bestemming = bank.openRekening("Rekening2", "Son");
            Money bedrag = new Money(500, "\u20AC");
            Rekening rekening = (Rekening)bank.getRekening(bron);
            rekening.muteer(new Money(1000, "\u20AC"));

            try
            {
                bank.maakOver(-bron, bestemming, bedrag);
            }
            catch (NumberDoesntExistException ex)
            {
                System.out.println("NumberDoesntExistException: " + ex.getMessage());
                assertTrue(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BankTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Assert failed; zie RemoteException");
        }
        
    }
    
    @Test
    public void TestMaakOverBronIncorrect2()
    {
        try {
            int bron = bank.openRekening("Rekening1", "Eindhoven");
            int bestemming = bank.openRekening("Rekening2", "Son");
            Money bedrag = new Money(500, "\u20AC");
            Rekening rekening = (Rekening)bank.getRekening(bron);
            rekening.muteer(new Money(1000, "\u20AC"));

            try
            {
                bank.maakOver(bron + 5, bestemming, bedrag);
            }
            catch (NumberDoesntExistException ex)
            {
                System.out.println("NumberDoesntExistException: " + ex.getMessage());
                assertTrue(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BankTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Assert failed; zie RemoteException");
        }
        
    }
    
    @Test
    public void TestMaakOverBestemmingIncorrect1()
    {
        try {
            int bron = bank.openRekening("Rekening1", "Eindhoven");
            int bestemming = bank.openRekening("Rekening2", "Son");
            Money bedrag = new Money(500, "\u20AC");
            Rekening rekening = (Rekening)bank.getRekening(bron);
            rekening.muteer(new Money(1000, "\u20AC"));

            System.out.println("--BestemmingIncorrect1");
            try
            {
                bank.maakOver(bron, bestemming + 10, bedrag);


            }
            catch (NumberDoesntExistException ex)
            {
                System.out.println("NumberDoesntExistException: " + ex.getMessage());
                assertTrue(true);
            }
            System.out.println("--BestemmingIncorrect1");
        } catch (RemoteException ex) {
            Logger.getLogger(BankTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Assert failed; zie RemoteException");
        }
        
        
        
    }
    
    @Test
    public void TestMaakOverBestemmingIncorrect2()
    {
        try {
            int bron = bank.openRekening("Rekening1", "Eindhoven");
            int bestemming = bank.openRekening("Rekening2", "Son");
            Money bedrag = new Money(500, "\u20AC");
            Rekening rekening = (Rekening)bank.getRekening(bron);
            rekening.muteer(new Money(1000, "\u20AC"));

            try
            {
                bank.maakOver(bron, bestemming + 5, bedrag);
            }
            catch (NumberDoesntExistException ex)
            {
                System.out.println("NumberDoesntExistException: " + ex.getMessage());
                assertTrue(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BankTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Assert failed; zie RemoteException");
        }
        
    }
    
    @Test
    public void TestMaakOverTerugstorten()
    {
        try {
            int bron = bank.openRekening("Rekening1", "Eindhoven");
            int bestemming = bank.openRekening("Rekening2", "Son");
            Money bedrag = new Money(500, "\u20AC");
            Rekening rekening = (Rekening)bank.getRekening(bron);
            //rekening.muteer(new Money(1000, "\u20AC"));

            try
            {
                bank.maakOver(bron, bestemming, bedrag);
            }
            catch (NumberDoesntExistException ex)
            {
                System.out.println("NumberDoesntExistException: " + ex.getMessage());
            }

            long bronCents = rekening.getSaldo().getCents();

            assertEquals(500, bronCents);
        } catch (RemoteException ex) {
            Logger.getLogger(BankTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Assert failed; zie RemoteException");
        }
        
    }
    
    /**
     * @param nr
     * @return de bankrekening met nummer nr mits bij deze bank bekend, anders null
     */
    @Test
    public void TestGetRekening()
    {
        try {
            int rekeningNr = bank.openRekening("Rekening1", "Eindhoven");
            Rekening rekening = (Rekening)bank.getRekening(rekeningNr);
        
            assertEquals("Onjuist rekeningnummer", rekeningNr, rekening.getNr());
        } catch (RemoteException ex) {
            Logger.getLogger(BankTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Assert failed; zie RemoteException");
        }
        
        
    }
    
    /**
     * @return de naam van deze bank
     */
    @Test
    public void TestGetName()
    {
        try {
            String name = bank.getName();
            assertEquals("Onjuiste bank naam", name, "Bank1");
        } catch (RemoteException ex) {
            Logger.getLogger(BankTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Assert failed; zie RemoteException");
        }
        
    }
}
