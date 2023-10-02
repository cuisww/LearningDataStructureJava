package sparsearray;

public class SparseArray {

    public static void main(String[] args){
        //创建一个原始的二维数组 11 * 11
        //0: 表示没有棋子, 1表示黑子, 2表示篮子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;//第二行第三列
        chessArr1[2][3] = 2;//第三行第四列
        //输出原始的二维数组
        for(int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组转为稀疏数组
        //1.先遍历二维数组, 得到非0数据的个数
        int sum = 0;

        for(int i = 0; i < 11; i++){
            for (int j = 0; j < 11; j++){
                if (chessArr1[i][j] != 0){sum++;}
            }
        }
        System.out.println("sum=" + sum);

        //2. 创建对应的稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        //给稀疏数组赋值
        //第一行
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组, 将非0的值存放到稀疏数组中
        int count = 0; //用于记录是第几个非零数据
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 11; j++){
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为:");

        for (int i = 0; i < sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
            System.out.println();
        }


        //将稀疏数组恢复成原始的二维数组
        /*
        * 1.先读取稀疏数组的一行
        * 2.在读取后几行的数据, 并赋值给原来的二维数组.
        * */

        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        System.out.println();
        System.out.println("恢复后的二维数组");

        for(int i = 1; i < sparseArr.length; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for(int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


    }



}
