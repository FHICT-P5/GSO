/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author Julius
 */
public interface IEffectenbeursObserver extends Remote {

    void setKoersen(String fondsen) throws RemoteException;
}
