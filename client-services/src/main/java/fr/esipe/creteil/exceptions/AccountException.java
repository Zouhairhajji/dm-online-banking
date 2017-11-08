/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esipe.creteil.exceptions;

/**
 *
 * @author zouhairhajji
 */
public class AccountException extends Exception{

    public AccountException() {
        this("An error was detected");
    }
    
    public AccountException(String message) {
        super(message);
    }
    
    
    
    
}
