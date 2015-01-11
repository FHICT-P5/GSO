/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bank.bankieren.Bank;
import bank.bankieren.CentraleBank;
import bank.internettoegang.Balie;
import java.rmi.RemoteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bart
 */
public class BalieTest {
    
    private Bank mockBank;
    private Balie mockBalie;
    private CentraleBank mockCentrale;
    
    public BalieTest() {
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
            mockBank = new Bank("mockBank", mockCentrale);
            mockBalie = new Balie(mockBank);
        } catch (RemoteException ex) {
            System.out.println("RemoteException at Balie setUp: " + ex.getMessage());
        }
    }
    
    @After
    public void tearDown() {
    }

      /**
   * creatie van een nieuwe bankrekening; het gegenereerde bankrekeningnummer is
   * identificerend voor de nieuwe bankrekening en heeft een saldo van 0 euro
   * @param naam van de eigenaar van de nieuwe bankrekening
   * @param plaats de woonplaats van de eigenaar van de nieuwe bankrekening
   * @param wachtwoord van het account waarmee er toegang kan worden verkregen 
   * tot de nieuwe bankrekening
   * @return null zodra naam of plaats een lege string of wachtwoord minder dan 
   * vier of meer dan acht karakters lang is en anders de gegenereerde 
   * accountnaam(8 karakters lang) waarmee er toegang tot de nieuwe bankrekening
   * kan worden verkregen
   */
    @Test
    public void testOpenRekening() {
        String naam = "test";
        String plaats = "testplaats";
        String wachtwoord = "test";
        
        assertEquals("String naam is null.", null, mockBalie.openRekening(null, plaats, wachtwoord));
        assertEquals("String naam is leeg.", null, mockBalie.openRekening("", plaats, wachtwoord));
        
        assertEquals("String plaats is null.", null, mockBalie.openRekening(naam, null, wachtwoord));
        assertEquals("String plaats is leeg.", null, mockBalie.openRekening(naam, "", wachtwoord));
        
        assertEquals("String wachtwoord is null.", null, mockBalie.openRekening(naam, plaats, null));
        assertEquals("String wachtwoord is leeg.", null, mockBalie.openRekening(naam, plaats, ""));
        assertEquals("String wachtwoord is korter dan 4 tekens.", null, mockBalie.openRekening(naam, plaats, "123"));
        assertEquals("String wachtwoord is langer dan 8 tekens.", null, mockBalie.openRekening(naam, plaats, "123456789"));
        
        assertEquals("String accountname is niet 8 karakters lang.", 8, mockBalie.openRekening(naam, plaats, wachtwoord).length());
        assertNotNull(mockBalie.openRekening(naam, plaats, wachtwoord));
    }
    
    /**
   * er wordt een sessie opgestart voor het login-account met de naam
   * accountnaam mits het wachtwoord correct is
   * @param accountnaam
   * @param wachtwoord
   * @return de gegenereerde sessie waarbinnen de gebruiker 
   * toegang krijgt tot de bankrekening die hoort bij het betreffende login-
   * account mits accountnaam en wachtwoord matchen, anders null
   */
    @Test
    public void testLogIn() {
        try {
            String naam = "test";
            String plaats = "testplaats";
            String wachtwoord = "test";
            String login = mockBalie.openRekening(naam, plaats, wachtwoord);
            
            assertEquals("String accountname is null", null, mockBalie.logIn(null, wachtwoord));
            assertEquals("String accountname is leeg", null, mockBalie.logIn("", wachtwoord));
            
            assertEquals("String wachtwoord is null", null, mockBalie.logIn(login, null));
            assertEquals("String wachtwoord is leeg", null, mockBalie.logIn(login, ""));
            
            assertNotNull(mockBalie.logIn(login, wachtwoord));
        } catch (RemoteException ex) {
            System.out.println("RemoteException at testLogin: " + ex.getMessage());
        }
    }
}
