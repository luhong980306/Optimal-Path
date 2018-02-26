/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lu Hong <luhong980306@163.com>
 */
public class Solution2 {
    
    /*Mr. Kim has to deliver refrigerators to N customers. 
    From the office, he is going to visit all the customers and then return to his home. 
    Each location of the office, his home, and the customers is given in the form of integer coordinates 
    (x,y) (0≤x≤100, 0≤y≤100) . 
    The distance between two arbitrary locations (x1, y1) and (x2, y2) is computed by |x1-x2| + |y1-y2|, 
    where |x| denotes the absolute value of x; for instance, |3|=|-3|=3. 
    The locations of the office, his home, and the customers are all distinct. 
    You should plan an optimal way to visit all the N customers and return to his among all the possibilities
    */

    class Node {

        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int start = 2;

    int calcDistance(int startx, int starty, int stopx, int stopy) {
        return Math.abs(stopx - startx) + Math.abs(stopy - starty);
    }

    List<Node> organizeCustomerNodes(int customerNumber, int[] locations) {
        List<Node> nodes = new ArrayList<>();
        for (int i1 = 0; i1 < customerNumber; i1++) {
            nodes.add(new Node(locations[2 * (i1 + start)], locations[2 * (i1 + start) + 1]));
        }
        return nodes;
    }

    int[] calcAllPathDistances(Node startNode, Node stopNode, Node[][] allPossPaths) {
        int[] distances = new int[allPossPaths.length];
        for (int i1 = 0; i1 < allPossPaths.length; i1++) {
            distances[i1] = distances[i1] + calcDistance(startNode.x, startNode.y, allPossPaths[i1][0].x, allPossPaths[i1][0].y);
            for (int j1 = 0; j1 < allPossPaths[0].length - 1; j1++) {
                distances[i1] = distances[i1]
                        + calcDistance(allPossPaths[i1][j1].x, allPossPaths[i1][j1].y, allPossPaths[i1][j1 + 1].x, allPossPaths[i1][j1 + 1].y);
            }
            distances[i1] = distances[i1] + calcDistance(allPossPaths[i1][allPossPaths[0].length - 1].x,
                    allPossPaths[i1][allPossPaths[0].length - 1].y, stopNode.x, stopNode.y);
        }
        return distances;
    }

    int calcShortestPath(int customerNumber, int[] locations) {
        List<Node> nodes = organizeCustomerNodes(customerNumber, locations);
        int[][] allPossPathIndices = MathUtil.enumAllPossibleArray(nodes.size());
        Node[][] allPossPaths = new Node[allPossPathIndices.length][allPossPathIndices[0].length];
        for (int i1 = 0; i1 < allPossPathIndices.length; i1++) {
            for (int j1 = 0; j1 < allPossPathIndices[0].length; j1++) {
                allPossPaths[i1][j1] = nodes.get(allPossPathIndices[i1][j1]);
            }
        }
        int[] distances = calcAllPathDistances(new Node(locations[0], locations[1]), new Node(locations[2], locations[3]), allPossPaths);
        return distances[MathUtil.getMinValueIndex(distances)];
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution2 sol = new Solution2();

        int customerNumber = 5;
        int[] locations = {0, 0, 100, 100, 70, 40, 30, 10, 10, 5, 90, 70, 50, 20};
        System.out.println("#1 " + sol.calcShortestPath(customerNumber, locations));

        customerNumber = 6;
        locations = new int[]{88, 81, 85, 80, 19, 22, 31, 15, 27, 29, 30, 10, 20, 26, 5, 14};
        System.out.println("#2 " + sol.calcShortestPath(customerNumber, locations));

        customerNumber = 7;
        locations = new int[]{22, 47, 72, 42, 61, 93, 8, 31, 72, 54, 0, 64, 26, 71, 93, 87, 84, 83};
        System.out.println("#3 " + sol.calcShortestPath(customerNumber, locations));

        customerNumber = 8;
        locations = new int[]{30, 20, 43, 14, 58, 5, 91, 51, 55, 87, 40, 91, 14, 55, 28, 80, 75, 24, 74, 63};
        System.out.println("#4 " + sol.calcShortestPath(customerNumber, locations));

        customerNumber = 9;
        locations = new int[]{3, 9, 100, 100, 16, 52, 18, 19, 35, 67, 42, 29, 47, 68, 59, 38, 68, 81, 80, 37, 94, 92};
        System.out.println("#5 " + sol.calcShortestPath(customerNumber, locations));

        customerNumber = 10;
        locations = new int[]{39, 9, 97, 61, 35, 93, 62, 64, 96, 39, 36, 36, 9, 59, 59, 96, 61, 7, 64, 43, 43, 58, 1, 36};
        System.out.println("#6 " + sol.calcShortestPath(customerNumber, locations));

        customerNumber = 10;
        locations = new int[]{26, 100, 72, 2, 71, 100, 29, 48, 74, 51, 27, 0, 58, 0, 35, 2, 43, 47, 50, 49, 44, 100, 66, 96};
        System.out.println("#7 " + sol.calcShortestPath(customerNumber, locations));

        customerNumber = 10;
        locations = new int[]{46, 25, 16, 6, 48, 82, 80, 21, 49, 34, 60, 25, 93, 90, 26, 96, 12, 100, 44, 69, 28, 15, 57, 63};
        System.out.println("#8 " + sol.calcShortestPath(customerNumber, locations));

        customerNumber = 10;
        locations = new int[]{94, 83, 72, 42, 43, 36, 59, 44, 52, 57, 34, 49, 65, 79, 14, 20, 41, 9, 0, 39, 100, 94, 53, 3};
        System.out.println("#9 " + sol.calcShortestPath(customerNumber, locations));

        customerNumber = 10;
        locations = new int[]{32, 79, 0, 0, 69, 58, 100, 31, 67, 67, 58, 66, 83, 22, 44, 24, 68, 3, 76, 85, 63, 87, 7, 86};
        System.out.println("#10 " + sol.calcShortestPath(customerNumber, locations));
        // TODO code application logic here
    }
}
