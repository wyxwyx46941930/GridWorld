package solution;

import java.util.ArrayList;
import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
    	//list the next node
        ArrayList<JigsawNode> first = new ArrayList<>();
        //list the next node
        ArrayList<JigsawNode> last = new ArrayList<>();
        beginJNode = bNode;
        endJNode = eNode;
        //add the first node
        first.add(bNode);
        //直到待访问列表为空
        while(!first.isEmpty() ){
        	//find the queue's size
            int firstSize = first.size();
            for(int i = 0; i < firstSize;i++) {
                currentJNode = first.get(0);
                //if the now node == final node return true
                if(currentJNode.equals(eNode)) {
                    return true;
                }
                
                for(int j = 0; j < 4; j++) {
                	//find the four part of node
                    if(currentJNode.canMove()[j] == 1) {
                        JigsawNode temp = new JigsawNode(currentJNode);
                        temp.move(j);
                        if(!last.contains(temp)) {
                            first.add(temp);
                        }
                    }
                }
                //move the now node to the queue(arraylist)
                last.add(currentJNode);
                first.remove(0);
            }
        }
        // if can't find the final node return false 
        return false;
    }
    
    
    
    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        // 后续节点不正确的数码个数
        int s = 0;
        int dimension = JigsawNode.getDimension();
        for(int index =1 ; index<dimension*dimension; index++){
            if(jNode.getNodesState()[index]+1!=jNode.getNodesState()[index+1]) {
                s++;
            }
        }
        int e = 0;
        for (int index = 1; index <= dimension*dimension; index++) {
            if (jNode.getNodesState()[index] != endJNode.getNodesState()[index] && 
                jNode.getNodesState()[index] != 0) {
                e++;
            }
        }
        // Distance
        int d = 0;
        for (int i = 1; i <= dimension*dimension; i++) {
            if (jNode.getNodesState()[i] != endJNode.getNodesState()[i] &&
                jNode.getNodesState()[i] != 0) {
                int row1 = getRow(i);
                int row2 = getRow(jNode.getNodesState()[i]);
                int col1 = getCol(i);
                int col2 = getCol(jNode.getNodesState()[i]);
                d += (int)(Math.abs(row1-row2)+Math.abs(col1-col2));
            }
        }
        int z = 0;
        for (int i = 1; i < dimension*dimension; i++) {
            if (jNode.getNodesState()[i] != endJNode.getNodesState()[i]) {
                for (int j = 1; j <= dimension*dimension; j++) {
                    if (jNode.getNodesState()[j] == i) {
                        int row1 = getRow(j);
                        int row2 = getRow(jNode.getNodesState()[0]);
                        int col1 = getCol(j);
                        int col2 = getCol(jNode.getNodesState()[0]);
                        z += (int)(Math.abs(row1-row2)+Math.abs(col1-col2));
                        break;
                    }
                }
                break;
            }
        }
        int value = (int)(4*s+8*d+7*z);
        jNode.setEstimatedValue(value);
    }

    private int getRow(int index) {
        int dimension = JigsawNode.getDimension();
        return (index-1) / dimension;
    }
    private int getCol(int index) {
        int dimension = JigsawNode.getDimension();
        return (index-1) % dimension;
    }
}
  