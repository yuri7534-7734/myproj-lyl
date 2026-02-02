public class ex16 {
    public static void main(String[] args) {
        //2차배열의 선언
        //1.
        int[][] arrNum1 = {{10,20,30},{30,40,60}};

        //2.
        int[][] arrNum2 = new int[2][3]; //2행 2열

        //3.
        int[][] arrNum3 = new int[][]{{10,20,30},{40,50,60}};
        System.out.println(arrNum3[0][1]);

        //4.
        int[][] arrNum4 = new int[2][]; //2행인데, 열은 없음
        //열은 나중에 초기화
        arrNum4[0] = new int[3];
        arrNum4[1] = new int[3];

        //행의 길이
        System.out.println(arrNum4.length);
        //열의 길이
        System.out.println(arrNum4[0].length);
        System.out.println(arrNum4[1].length);

        //2차배열의 순회
        for(int i=0; i< arrNum1.length; i++){
            for(int j=0; j<arrNum1[i].length; j++){
                System.out.println(arrNum1[i][j]);
            }
        }

        //향상된 for문, for콜론문
        for(int[] nums: arrNum1){
            for(int num: nums) {
                System.out.println(num);
            }
        }




    }
}
