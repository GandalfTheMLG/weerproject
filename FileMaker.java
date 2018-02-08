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
public class FileMaker {
    private InputStream input;
    private boolean firstfile;
    
    public FileMaker(InputStream in){
        input = in;
        firstfile = true;
    }
    
    public File getNextFile() throws IOException {
        File fil = new File("file");
        String str = "";
        String str2;
        int a;
        char ch;
        int questionCount = 0;
        if(firstfile == false){
            str = "<?";
            questionCount = 1;
        }
        
        while(true){
            a = input.read();
            ch = (char)a;
            
            
            if(ch == '?'){
                questionCount +=1;
                //System.out.println("found questionmark");
            }
            
            if(questionCount > 2){
                //System.out.println("question limit reached, creating file");
                break;
                
            }
            str += ch;
        }
        //System.out.println("question limit reached, creating file");
        str2 = str.substring(0, str.length() - 1);
        //System.out.print(str2);
        firstfile = false;
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(fil));
        writer.write(str2);
        writer.close();
        return fil;
    }
    
}
