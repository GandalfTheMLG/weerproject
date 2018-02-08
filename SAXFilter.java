/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherdata;

import java.io.*;
import java.util.*;
/**
 *
 * @author Jesse
 */
import java.util.Arrays;
public class SAXFilter extends FilterInputStream{
    int[] check = {60,47,87,69};
    int[] latest = {0,0,0,0};
    int a;
    
    public SAXFilter(InputStream in){
        super(in);         
        
        
    }
    
    @Override
    public int read()throws IOException{
        a = super.read();
        latest[3] = latest[2];
        latest[2] = latest[1];
        latest[1] = latest[0];
        latest[0] = a;
        if(Arrays.equals(latest, check)){
            super.skip(10);
            return(-1);
        }
        
        return a;
    }
            
    
}
