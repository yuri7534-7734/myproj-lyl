import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class ex47 {
    public static void main(String[] args) {
        //연습문제
        //철수는 초등학교 교사이다.
        //학생 영희, 수만, 순신의 성적관리 프로그램을 작성하고자 한다.
        //score.txt에 저장하고, 읽어 오는 프로그램을 작성해 보자.
        //파일 형식
        //이름 영어 수학 국어\n
        //영희 100 80 70\n
        //수만 80 80 70\n
        //순신 90 80 70\n

        //그다음 학생이름과 과목을 Scanner로 입력받고,
        //점수를 출력하는 프로그램을 작성하시오.
        //입력예) 영희 영어
        //출력예) 100

        //FileReader : 바이트 단위로 읽어오기
        //BufferedReader : \n문자까지 한줄 읽어오기
        Scanner scanner = new Scanner(System.in);
        System.out.println("이름 입력");
        String name2 = scanner.next();
        System.out.println("과목 입력");
        String subject2 = scanner.next();

        try {
            FileReader fr = new FileReader("score.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            line = br.readLine(); // \n으로 읽는다.

            //과목
            String[] header = line.split(" ");
            int subjectIndex = -1;
            for (int i = 0; i < header.length; i++) {
                if (header[i].equals(subject2)) {
                    subjectIndex = i;
                    break;
                }
            }

            if (subjectIndex == -1) {
                System.out.println("없는 과목입니다.");
                return;
            }

            //이름

            while ((line = br.readLine()) != null) {
                //line이 null이지 않는 이상 계속 true

                //line으로 읽고 공백으로 나누었기 때문에
                //data = {영희, 100, 80, 70}
                //data[0] = 영희
                String[] data = line.split(" ");
                String name = data[0];

                //이름이 같으면
                if (name.equals(name2)) {
                    System.out.println(name2+"의 "+subject2+"점수는 : " + data[subjectIndex]+"점");
                    //data[2] 위 과목이 몇번째의 점수인지를 정해준다.
                    return;
                }
            }

            System.out.println("해당 학생이 없습니다.");

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//Exception 자주 발생하는 곳
//1. 파일 처리할 떄
//2. 통신
//3. 형변화(10진수 -> 2진수)
