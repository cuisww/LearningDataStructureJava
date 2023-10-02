package linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args){
        HeroNode h1 = new HeroNode(1, "Doingb", "猴子");
        HeroNode h2 = new HeroNode(2, "369", "练一个纳尔那么难吗");
        HeroNode h3 = new HeroNode(3, "Knight", "软脚虾");
        HeroNode h4 = new HeroNode(4, "小何", "小何gjls");

        SingleLinkedList l1 = new SingleLinkedList();
//        l1.add(h2);
//        l1.add(h3);
//        l1.add(h4);
//        l1.add(h1);
//        l1.list();
//        System.out.println("\n");
//        l1.addByOrder(h2);
//        l1.addByOrder(h3);
//        l1.addByOrder(h4);
//        l1.addByOrder(h1);
//        l1.list();
//
//        //l1.delete(4);
//
//        System.out.println("\n");
//        System.out.println("找到最后k个节点\n");
//        System.out.println(SingleLinkedList.findLastk(l1.getHead(), 3));
//
//        System.out.println("\n");
//        System.out.println("反转\n");
//        SingleLinkedList.reverseList(l1.getHead());
//        l1.list();

        HeroNode h5 = new HeroNode(5, "h5", "5");
        HeroNode h6 = new HeroNode(6, "h6", "6");
        HeroNode h7 = new HeroNode(7, "h7", "7");


        SingleLinkedList l2 = new SingleLinkedList();
        SingleLinkedList l3 = new SingleLinkedList();
        l2.add(h3);
        l2.add(h7);


        l3.add(h1);
        l3.add(h2);
        l3.add(h5);
        l3.add(h6);

//        System.out.println("测试合并1");
//        SingleLinkedList.mergeList(l2.getHead(), l3.getHead());
//        l2.list();

        System.out.println("测试合并2");
        HeroNode newHead = SingleLinkedList.mergeList2(l2.getHead(), l3.getHead());
        HeroNode temp = newHead.next;
        while(temp != null){
            System.out.println(temp.toString());
            temp = temp.next;
        }

    }
}

class SingleLinkedList{
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private final HeroNode head = new HeroNode(0," "," ");

    public HeroNode getHead(){
        return head;
    }

    //添加节点到单向链表
    /*思路: 当不考虑编号的顺序时
    * 1.找到当前链表的最后节点
    * 2.将这个最后这个节点的next指向新的节点*/
    public void add(HeroNode heroNode){
        //因为头节点不能动, 因此我们需要一个辅助遍历temp
        HeroNode temp = head;

        //遍历链表, 找到最后
        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;

        }
        temp.next = heroNode;
    }

    //按照顺序插入链表
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动, 因此我们仍然通过一个辅助变量来帮助我们找到添加的位置.
        HeroNode temp = head;
        //检验是否有相等的情况发生
        boolean flag = false;
        while(true){
            if(temp.next == null){//已经遍历完
                break;
            }else if(temp.next.no > heroNode.no){//找到了, temp的后面就是要放的位置了
                break;
            }else if(temp.next.no == heroNode.no){//找到了重复的
                flag = true;
                break;
            }
            temp = temp.next;//这个一定要写.
        }
        if(flag){
            System.out.println("有相同的节点, 不能插入");
            return;
        }
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    //显示链表(遍历)
    public void list(){
        if(head.next == null){
            System.out.println("链表是空的");
            return;
        }
        HeroNode temp = head.next;
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //修改节点的信息, 根据no编号来修改, 即no编号不能改.
    /*说明
    * 1. 根据newHeroNode的no来修改*/
    public void update(HeroNode newNode){
        HeroNode temp = head.next;//不是head, 注意.
        if (temp == null){
            System.out.println("链表为空");
            return;
        }

        boolean flag = false;//找到了会是true
        while(true){
            if(temp == null){
                break;
            }else if(temp.no == newNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.nickname = newNode.nickname;
            temp.name = newNode.name;
        } else {
            System.out.println("没有找到!");
        }

    }


    //删除节点
    /*1. head不能动, 需要一个temp辅助节点, 找到待删除节点的前一个节点
    * 2. 在Java中, 没有任何引用指向的对象会被删除.*/
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no == no){
                //找到了待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag){
            temp.next = temp.next.next;
            //被删除的节点会自动被Java gc回收.
        }else{
            System.out.printf("要删除的%d 节点不存在\n", no);
        }

    }

    //方法: 获取到单链表的节点个数(如果是带头节点的链表, 需求不统计头节点)
    /*
    * @param head 链表的头节点
    * @return 返回的就是有效节点的个数*/

    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode current = head.next;
        while(current != null){
            length ++;
            current = current.next;
        }
        return length;
    }

    //新浪: 查找单链表中的倒数第k个节点
    /*1.编写一个方法, 接收head节点, 同时接收一个k.
    * 2.k表示的是倒数第k个节点
    * 3.先把链表从头到尾遍历, 得到链表的长度length.
    * 4.得到长度后, 我们从链表第一个开始遍历, 第length-k就是了 */
    public static HeroNode findLastk(HeroNode head, int k){
        int length = SingleLinkedList.getLength(head);
        if (head.next == null){
            throw new RuntimeException("链表是空的!");
        }
        if (k<0 || k>length){
            return null;
        }
        HeroNode cur = head.next;
        int index = 0;
        while(index < length - k){
            index ++;
            cur = cur.next;
        }
        return cur;
    }

    //腾讯: 单链表的反转
    /*1. 先定义一个节点reverseHead = new HeroNode();
    * 2. 从头到尾遍历原来的链表, 每遍历一个节点, 就将其去除, 并放在新的链表的最前端.
    * 3. 原来的链表的head.next = reverseHead.next*/
    public static void reverseList(HeroNode head){

        //如果链表为空, 或者只有一个节点, 无需反转, 直接返回
        if(head.next == null || head.next.next == null){
            return;
        }

        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reversedHead = new HeroNode(0, "", "");

        while (cur != null){
            next = cur.next;
            cur.next = reversedHead.next;
            reversedHead.next = cur;
            cur = next;
        }

        head.next = reversedHead.next;
    }


    //百度: 逆序打印单链表
    /*方式1: 先将单链表进行反转操作, 然后再进行遍历. 但是这样会改变原来的结构.
    * 方式2: 利用栈, 将各个节点压入栈中, 然后利用栈的先进后出的特点就实现了逆序打印*/
    public static void reversePrint(HeroNode head){
        if(head.next == null){//注意, 在这个Demo里面头节点是空的.
            return ;
        }

        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while(stack.size() > 0){
            System.out.println(stack.pop());
        }

    }

    //练习: 将两个有序的链表联合起来, 组成一个新的有序链表
    //我这个算法直接对第一个表做修改, 把第二个表全部连接到第一个表上, 但是空间复杂度O(1), 时间复杂度为O(n^2).
    public static void mergeList(HeroNode head1, HeroNode head2){
        HeroNode cur = head2.next;//当前待插入的节点.
        HeroNode cur2 = head2.next.next;//防断
        while(cur != null){
            HeroNode temp = head1;
            //找到插入的位置.
            while(true){
                if(temp.next == null){//一直找到了最后, 那么就插在最后.
                    break;
                }
                if (temp.next.no >= cur.no){
                    break;
                }
                temp = temp.next;
//                System.out.println(temp.no);
            }
            cur.next = temp.next;
            temp.next = cur;

            cur = cur2;
            if(cur2 != null) {
                cur2 = cur2.next;
//                System.out.println(cur2.no);
            }
        }

    }

    //另一种解法, 基于归并排序的解法,时间复杂度O(n), 空间复杂度O(n)
    public static HeroNode mergeList2(HeroNode head1, HeroNode head2){
        HeroNode i = head1.next;
        HeroNode j = head2.next;
        HeroNode newHead = new HeroNode(-1, " ", " ");
        HeroNode temp = newHead;//保持newHead用于输出, 指向新加入的HeroNode

        while (i != null && j != null){
            HeroNode temp1 = i.next;//防断
            HeroNode temp2 = j.next;//防断
            if (i.no <= j.no){
                i.next = temp.next;
                temp.next = i;

                i = temp1;//i加入了, i往前走.
            } else{
                j.next = temp.next;
                temp.next = j;

                j = temp2;
            }

            temp = temp.next;//让temp始终在新链表的尾端.
        }

        while (i != null){
            HeroNode temp1 = i.next;
            //这里要把i按次序加到newHead中去, 因为我们不确定两个链表是不是绝对的一个比另一个大.
            temp = newHead;
            while(true) {
                if (temp.next == null) {
                    break;
                }
                if (temp.next.no >= i.no) {
                    break;
                }
                temp = temp.next;
            }
            i.next = temp.next;
            temp.next = i;
            i = temp1;

        }
        while (j != null){
            HeroNode temp2 = j.next;
            //这里要把j按次序加到newHead中去, 因为我们不确定两个链表是不是绝对的一个比另一个大.
            temp = newHead;
            while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no >= j.no){
                break;
            }
            temp = temp.next;
        }

            j.next = temp.next;
            temp.next = j;

            j = temp2;
        }

        return newHead;
    }



}



class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
        this.next = null;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}