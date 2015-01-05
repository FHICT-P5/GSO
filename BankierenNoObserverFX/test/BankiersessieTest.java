/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import bank.bankieren.Bank;
import bank.bankieren.CentraleBank;
import bank.bankieren.IBank;
import bank.bankieren.Money;
import bank.bankieren.Rekening;
import bank.internettoegang.Bankiersessie;
import bank.internettoegang.IBankiersessie;
import fontys.util.InvalidSessionException;
import fontys.util.NumberDoesntExistException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Juliusername
 */
public class BankiersessieTest {
    
    private IBank bank;
    private IBankiersessie sessie;
    private int rekeningNr1;
    private int rekeningNr2;
    private CentraleBank mockCentrale;
    
    public BankiersessieTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mockCentrale = new CentraleBank();
        bank = new Bank("Bank1", mockCentrale);
        rekeningNr1 = bank.openRekening("Rekening1", "Eindhoven");
        rekeningNr2 = bank.openRekening("Rekening2", "Eindhoven");
        
        try
        {
            sessie = new Bankiersessie(rekeningNr1, bank);
        }
        catch (RemoteException ex)
        {
            System.out.println("RemoteException: " + ex.getMessage());
        }
                
    }
    
    @After
    public void tearDown() {
    }

    /**
    * @returns true als de laatste aanroep van getRekening of maakOver voor deze
    * sessie minder dan GELDIGHEIDSDUUR geleden is
    * en er geen communicatiestoornis in de tussentijd is opgetreden, 
    * anders false
    */
    @Test
    public void TestIsGeldig()
    {
        boolean result = false;
        
        try {
            result = sessie.isGeldig();
        } catch (RemoteException ex) {
            System.out.println("RemoteException: " + ex.getMessage());
            result = false;
        }
        
        assertTrue(result);
                
        try {
            result = sessie.maakOver(rekeningNr2, new Money(500, "\u20AC"));
        } catch (NumberDoesntExistException ex) {
            System.out.println("NumberDoesntExistException: " + ex.getMessage());
        } catch (InvalidSessionException ex) {
            System.out.println("InvalidSessionException: " + ex.getMessage());
        } catch (RemoteException ex) {
            System.out.println("RemoteException: " + ex.getMessage());
        }
        
        if (result)
        {
            try {
                result = sessie.isGeldig();
            } catch (RemoteException ex) {
                System.out.println("RemoteException: " + ex.getMessage());
                result = false;
            }
            
            assertTrue(result);
        }
        else
        {
            fail("Maak over is niet geslaagd");
        }
    }
    
    @Test
    public void TestMaakOverRunTimeException1()
    {
        try {
            sessie.maakOver(rekeningNr1, new Money(500, "\u20AC"));
            fail("RuntimeException is niet thrown");
        }
        catch (RuntimeException ex)
        {
            assertTrue(true);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            fail("RuntimeException is niet thrown");
        }
    }
    
    @Test
    public void TestMaakOverRunTimeException2()
    {
        try {
            sessie.maakOver(rekeningNr2, new Money(-500, "\u20AC"));
            fail("RuntimeException is niet thrown");
        }
        catch (RuntimeException ex)
        {
            assertTrue(true);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            fail("RuntimeException is niet thrown");
        }
    }
    
    @Test
    public void TestLogOut()
    {
        try {
            sessie.logUit();
            assertTrue(true);
        } catch (RemoteException ex) {
            fail("RemoteException is trown");
        }
    }
    
    @Test
    public void TestGetRekening()
    {
        try {
            Rekening rekening = (Rekening)sessie.getRekening();
            assertTrue(true);
        } catch (InvalidSessionException ex) {
            System.out.println("InvalidSessionException ex: " + ex.getMessage());
            fail("InvalidSessionException is thrown");
        } catch (RemoteException ex) {
            System.out.println("RemoteException ex: " + ex.getMessage());
            fail("RemoteException is thrown");
        }
    }
}
