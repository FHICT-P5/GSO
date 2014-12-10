/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.shared;

import java.rmi.RemoteException;

/**
 *
 * @author Julius
 */
public interface IPublisher {
    
    void meldAan(IListener listener) throws RemoteException;
}
