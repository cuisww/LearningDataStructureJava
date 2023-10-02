package queue;

import java.util.Scanner;

class ArrayQueue {
    private int maxsize;//数组的最大容量
    private int front; //队列头, 指向的是队列的第一个数据的前一个位置.
    private int rear; //队列尾, 指向的是队列的最后一个数据.
    //创建数组存放队列
    private int[] queue;

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxsize = arrMaxSize;
        front = -1;
        rear = -1;
        queue = new int[maxsize];
    }
    //判断队列是否满
    public boolean isFull(){
        return rear == (maxsize - 1);
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }
    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if (isFull()){
            throw new RuntimeException("数组满了!");//要throw new
        }
        rear++;
        queue[rear] = n;
    }

    //获取队列的数据, 出队列
    public int getQueue(){
        //判断队列是否空
        if (isEmpty()){
            throw new RuntimeException("数组是空的!");
        }

        front++;
        int res = queue[front];
        queue[front] = 0;

        return res;
    }

    //显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列空的, 没有数据");
            return;
        }

        for (int i = 0; i < maxsize; i++){
            System.out.printf("第%d个 : %d\n", i, queue[i]);
        }
    }

    //显示队列的头数据, 注意不是取出数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列是空的");
        }
        return queue[front + 1];
    }

}

    //测试
public class ArrayQueueDemo{
        public static void main(String[] args){
            ArrayQueue myq = new ArrayQueue(3);
            char key = ' ';
            Scanner scanner = new Scanner(System.in);
            boolean loop = true;
            while (loop){

                System.out.println("s(show) 显示队列");
                System.out.println("e(exit) 退出程序");
                System.out.println("a(add) 添加数据到队列");
                System.out.println("g(get) 从队列取出数据");
                System.out.println("h(head) 查看队列的头数据");
                key = scanner.next().charAt(0);

                switch (key){
                    case 's':
                        myq.showQueue();
                        break;

                    case 'a':
                        System.out.println("输出一个数");
                        int value = scanner.nextInt();
                        try {
                            myq.addQueue(value);

                        } catch(Exception e){
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 'g':
                        try{
                            int res = myq.getQueue();
                            System.out.printf("取出的数据是%d\n", res);
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 'h':
                        try {
                            int res = myq.headQueue();
                            System.out.printf("队列头的数据是%d\n", res);
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 'e':
                        scanner.close();
                        loop = false;
                        break;
                }

            }
            System.out.println("程序退出");
        }

    }