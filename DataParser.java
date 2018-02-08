
package weatherdata;

import java.net.*;
import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import com.google.gson.*;



public class DataParser {
    ServerSocket serverSock;
    ArrayList<Socket> socks;
    ArrayList<InputStream> inputs;
    ArrayList<FileMaker> fms;
    
    DataParser(int port){
        
        int clusterCount = 100;
        //USE to adjust amount of clusters getting parsed
        
        
        socks = new ArrayList<>();
        inputs = new ArrayList<>();
        fms = new ArrayList<>();
        Gson gson = new Gson();
        
        try{
            serverSock = new ServerSocket(port);
            for(int i = 0; i < clusterCount; i++){
                socks.add(i,serverSock.accept());
                inputs.add(i,socks.get(i).getInputStream());
                fms.add(i,new FileMaker(inputs.get(i)));
            }
            
            
            
            
            SAXParserFactory factory = SAXParserFactory.newInstance();
            tester test = new tester();
            SAXworker parser = new SAXworker();
            
            File fil;
            int parsecount = 0;
            while(true){
                parser.newParseSet();
                for(int i =0; i<clusterCount;i++){
                    SAXParser saxParser = factory.newSAXParser();
                    fil = fms.get(i).getNextFile();
              
                    saxParser.parse(fil, parser);
                    
                    
                }
                Dataset tobesent = parser.parseSend();
                
                parsecount++;
                System.out.println("parseset"+ parsecount+" succesful, sending now");
                if(tobesent !=null){
                    gson.toJson(tobesent, new FileWriter("D:\\file"+(parsecount-30)+".json"));
                }
                
            }
            
            
            //while(true){
                //try{
                    
                //}catch(SAXParseException e){
                    
                //}
            //}
            
        }catch(Exception e){
            e.printStackTrace();
            }
    }
    
    
}
