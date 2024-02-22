/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iitmandi.lcm.model.implementations.skyline;

import iitmandi.lcm.model.Node;
import iitmandi.lcm.model.NodeFactory;
import java.util.ArrayList;
/**
 *
 * @author easwar
 */
public class skylineNodeFactory extends NodeFactory<Integer, skylineData> {
    @Override
    public Node create(skylineData data) {
        return new skylineNode(data);
    }
    @Override
    public Node create(Integer[] array, skylineData data) {
        return new skylineNode(array, data);
    }
    @Override
    public Node create(ArrayList<Integer> array, skylineData data) {
        Integer[] arr = new Integer[2];
        arr[0] = array.get(0);
        arr[1] = array.get(1);
        return new skylineNode(arr, data);
    }
}
