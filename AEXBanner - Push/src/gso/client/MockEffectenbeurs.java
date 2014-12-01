/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.client;

import gso.shared.Fonds;
import gso.shared.IEffectenbeurs;
import gso.shared.IFonds;
import java.rmi.RemoteException;

/**
 *
 * @author Bart
 */
public class MockEffectenbeurs implements IEffectenbeurs
{
    private IFonds[] fondsen;

    public MockEffectenbeurs() throws RemoteException
    {
        this.fondsen = new IFonds[] {new Fonds("Philips"), new Fonds("Unilever"), new Fonds("Fontys")};
    }
    
    @Override
    public IFonds[] getKoersen()
    {
        return this.fondsen;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
