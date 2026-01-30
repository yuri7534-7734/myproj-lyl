public class ex13 {
    public static void main(String[] args) {
        //이중반복문
        //일차반복문 : 1차배열 접근
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("일차반복문" + i + ", " + j);
            }
        }


        //이중반복문 : 2차배열 접근
        //정수형 2차배열
        int[][] nums2D = {{1, 2}, {3, 4}};
        //전체 순회
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println("2차반복문" + nums2D[i][j]);
            }
        }

        //별찍기
        //******
        //******
        //******
        //******
        //******
//        for (int i=0; i<5; i++) {
//            for (int j=0; j<5; j++){
//                System.out.print("*"); //줄바꿈없이 별찍기
//            }
//            System.out.println(); //줄바꿈
//        }

//        for (int i=0; i<5; i++) {
//
//            for(int j=0; j<i+1; j++){
//                System.out.printf("*");
//            }
//            System.out.println();
//        }
        //연습문제
        //1
        //출력 예)
        //    *
        //   **
        //  ***
        // ****
        //*****
//        int n = 5;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n-i; j++) {
//                System.out.print(" ");
//            }
//                System.out.println("*");
//        }
        int n = 5;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i+1; j++) {
                System.out.print("*");
            }
            System.out.println(" ");
        }


        //2
        //출력 예)
        //******* i==0
        //*    ** j==0 + j=n-2, 5 +j==n-1
        //*   * * j==0 + j=n-3, 4 +j==n-1
        //*  *  *
        //* *   *
        //**    *
        //******* i==n-1
        n = 7;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++){
                if ( i==0 || i==n-1 || j==0 ||j==n-1 || j==n-i-1 ){
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println(" ");
        }

    }
}
