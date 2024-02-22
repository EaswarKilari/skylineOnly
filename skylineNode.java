/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iitmandi.lcm.model.implementations.skyline;

import iitmandi.lcm.model.Node;
import iitmandi.lcm.utility.Accumulator;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
/**
 *
 * @author easwar
 */
public class skylineNode extends Node<Integer, Integer> {
    //we store id,list of nodes in current level
    int d,n;
    boolean flag=true;
    ArrayList<Integer> prefix=new ArrayList<>();
    ArrayList<Integer> nodes=new ArrayList<>();
    public final skylineData data;

    public skylineNode(skylineData data) { 
        this.d = data.d;
        this.n=data.n;
        for(int i=0;i<n;i++)nodes.add(i+1);
        this.data = data;
        System.out.println("===> skylineNode.skylineNode:: Node created ");//add some info regarding node such as node number,objects
    }
    public skylineNode(skylineNode node) {
        this.d = node.d;
        this.prefix=new ArrayList<>(node.prefix);
        this.nodes=new ArrayList<>(node.nodes);
        this.data = node.data;
    }
    public skylineNode(Integer[] items, skylineData data) {
        this.d = items[1];
        this.data = data;
    }
    @Override
    public String toString() {
        return d + "hi";
    }
    @Override
    public Integer[] toArray() {
        Integer[] arr = new Integer[1];
        arr[0] = d;
        return arr;
    }

    @Override
    public void enumStep(Accumulator acc){
        //if its an un-absorbed node 
        // then add its probability to accumulator
        acc.add((float)1);
    }
    
    @Override
    public BigInteger estimateWork(){
        int t=nodes.size();
        BigInteger work=BigInteger.valueOf(((t-1)*t)/2);
        return work;
        //number of children
        //let t=total nodes in the current level
        //let j=current node number in this level
        //children=((t-j)^2-(t+j))/2
        
    }
    
    @Override
    public Node<Integer,Integer> nextChild(){
//        traverse through nodes in current level
//                check for absorption
//                if absorbed 
//                        remove element from list

//                but what list?
//if its of parents,then we dont have access
//if list belongs to current node,then how can we inform that 4 is absorbed to remaining nodes
// 
//check absorption
//if currchild<n
//        create new nodes(level -1)
//else if marker<n 
        currChild++;
//        boolean flag=true;
//        if(currChild<n){
//            skylineNode node=new skylineNode(this);
//            if(currChild>0)node.nodes.remove(1);
//            //node.marker=0;
//            return node;
//        }else{
//            //find distinct
//            //this happens only once,so use some flag
//            if(flag){
//                boolean xabsorbed=false;
//                Set<String> distinctS = new HashSet<>();
//                for(int j=0;j<d;j++){
//                    for(int obj:prefix){
//                        distinctS.add(data.attr[obj][j]);
//                    }
//                }
//                Set<String> distinctOSx = new HashSet<>(distinctS);
//                for(int j=0;j<d;j++){
//                    distinctOSx.add(data.attr[nodes.get(1)][j]);
//                    distinctOSx.remove(data.attr[0][j]);
//                }
//                Iterator<Integer> it=nodes.iterator();
//                while(it.hasNext()){
//                    Set<String> distinctOSy = new HashSet<>(distinctS);
//                    int i=it.next();
//                    for(int j=0;j<d;j++){ 
//                        distinctOSy.add(data.attr[i][j]);
//                        distinctOSy.remove(data.attr[0][j]);
//                    }
//                    Set<String> distinctOSxy = new HashSet<>(distinctOSy);
//                    for(int j=0;j<d;j++){
//                        distinctOSxy.add(data.attr[nodes.get(1)][j]);
//                        distinctOSxy.remove(data.attr[0][j]);
//                    }
//                    if(distinctOSy.equals(distinctOSxy)){
//                        //x absorbs y
//                        it.remove();
//                    }else if(distinctOSx.equals(distinctOSxy)){
//                        //y absorbs x; break loop//
//                        xabsorbed=true;
//                        break;
//                    }
//                }
//                if(xabsorbed)nodes.remove(1);
//                flag=false;
//            }
//            
//        }
        if(nodes.size()>1){
            //find distinct
            //this happens only once,so use some flag
            if(flag){
                Set<String> distinctS = new HashSet<>();
                for(int j=0;j<d;j++){
                    for(int obj:prefix){
                        distinctS.add(data.attr[obj][j]);
                    }
                }
                Iterator<Integer> itx=nodes.iterator();
                while(itx.hasNext()){
                    Set<String> distinctOSx = new HashSet<>(distinctS);
                    int x=itx.next();
                    for(int j=0;j<d;j++){
                        distinctOSx.add(data.attr[x][j]);
                        distinctOSx.remove(data.attr[0][j]);
                    }
                    Iterator<Integer> ity=itx;
                    while(ity.hasNext()){
                        Set<String> distinctOSy = new HashSet<>(distinctS);
                        int y=ity.next();
                        for(int j=0;j<d;j++){
                            distinctOSy.add(data.attr[y][j]);
                            distinctOSy.remove(data.attr[0][j]);
                        }
                        Set<String> distinctOSxy = new HashSet<>(distinctOSy);
                        for(int j=0;j<d;j++){
                            distinctOSxy.add(data.attr[x][j]);
                            distinctOSxy.remove(data.attr[0][j]);
                        }
                        if(distinctOSy.equals(distinctOSxy)){
                            //x absorbs y
                            ity.remove();
                        }else if(distinctOSx.equals(distinctOSxy)){
                            //y absorbs x
                            itx.remove();
                        }
                    } 
                }
                flag=false;
            }
            int p=nodes.get(0);
            nodes.remove(0);
            skylineNode node=new skylineNode(this);
            node.prefix.add(p);
            return node;            
        }
        return null;
    }
}
