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
public class UserException extends Exception{

    public UserException(String message) {
        super(message);
    }
    
    public UserException() {
        this("The used does not existe");
    }
    
    
}
