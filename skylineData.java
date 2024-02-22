/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iitmandi.lcm.model.implementations.skyline;

import iitmandi.lcm.model.Data;
import iitmandi.lcm.utility.Accumulator;
import iitmandi.lcm.utility.FloatAccumulator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author easwar
 */
public class skylineData extends Data{
    //data common to all nodes
    int n,d;
    float p;
    String[][] attr;
    public skylineData(){
        n=0;
        d=0;
        p=0;
    }
    
    @Override
    public int probType() {
        return Data.ENUMERATION;
    }

    @Override
    public void readFromFile(File file) throws IOException, NumberFormatException {
        // First line size of pattern graph and target graph, then pattern graph details, then target graph details.
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        int ind=0;
        while ((line = bufferedReader.readLine()) != null) {
            String[] stringArray = line.split(" ");
            if (stringArray.length > 1) {
                if ("n".equals(stringArray[0])) {
                    this.n = Integer.parseInt(stringArray[1]);
                }
                else if ("d".equals(stringArray[0])) {
                    this.d = Integer.parseInt(stringArray[1]);
                    attr=new String[n+1][d];
                }
                else if ("p".equals(stringArray[0])) {
                    this.p = Float.parseFloat(stringArray[1]);
                }
            }
            else if (stringArray.length == 1) {
                String[] arr = line.split(",");
                for(int i=0;i<d;i++){
                    attr[ind][i]=arr[i];
                }
                ind++;
            }
        }
    }
    
    @Override
    public Accumulator getAccumulator() {
        Accumulator acc = new FloatAccumulator();
        return acc;
    }
}
