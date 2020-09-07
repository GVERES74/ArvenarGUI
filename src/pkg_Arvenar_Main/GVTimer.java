/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_Arvenar_Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import static pkg_Arvenar_Main.ArvenarCredits.creditsText;

/**
 *
 * @author Gabor Veres
 */
public class GVTimer {
    
    static Timer delayer;
    ArvenarFXMain arvmain;
    
    public GVTimer(){
        
        
        
    }        
    
    public void textMover(int delayms) throws InterruptedException {
    
        ActionListener taskPerformer = new ActionListener() {
                    
            public void actionPerformed(ActionEvent evt) {
                           
                    ArvenarCredits.moveText();
                    
            }
    };
        delayer = new Timer(delayms, taskPerformer);
        delayer.start();
    }
    
    public void textScaler(int delayms) throws InterruptedException {
    
        ActionListener taskPerformer = new ActionListener() {
                    
            public void actionPerformed(ActionEvent evt) {
                           
                    ArvenarCredits.vibraText();
                    
            }
    };
        delayer = new Timer(delayms, taskPerformer);
        delayer.start();
    }   
    
    public void mainTimer(int delayms) throws InterruptedException {
    
        ActionListener taskPerformer = new ActionListener() {
                    
            public void actionPerformed(ActionEvent evt) {
                           
                    ArvenarFXMain.buttonTxtFxer();
                    
                    
            }
    };
        delayer = new Timer(delayms, taskPerformer);
        delayer.start();
    }   
    
    
    public void starFaller(int delayms) throws InterruptedException {
    
        ActionListener taskPerformer = new ActionListener() {
                    
            public void actionPerformed(ActionEvent evt) {
                           
                    ArvenarFXMain.fallingStar();
                    
                    
            }
    };
        delayer = new Timer(delayms, taskPerformer);
        delayer.start();
    }   
    
    
}
