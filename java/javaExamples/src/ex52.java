import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int kor;
    int eng;
    int math;

    public Student(String name, int kor, int eng, int math) {
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }


    public String toString() {
        return "이름 : " + name + "\n" + "국어점수 : " + kor + "\n" + "영어점수 : " + eng + "\n" + "수학점수 : " + math
                + "\n" + "총점 : " + (kor + eng + math) + "\n" + "평균 : " + Math.round((kor + eng + math) / 3);
    }
}


public class ex52 {
    //전역 변수 설정
    //public static ArrayList<Student> scoreList = new ArrayList<>();

    static void first() {
        System.out.println("-----------성적 관리 프로그램-------------");
        System.out.println("1.입력 2.전체출력 3.검색 4.수정 5.삭제 6.종료");
        System.out.println("숫자 입력");
    }

    public static void main(String[] args) {
        //ArrayList 관련 함수들 : 전체지우기, 추가, 삭제, 검색(직접 코드로)
        //연습문제 - 성적 관리 프로그램
        ArrayList<Student> students = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        while (true) {
            first();
            int in = Integer.parseInt(scan.nextLine());
            // ArrayList 클래스 객체 배열을 사용해보자.
            //입력 및 출력 예시
            //-----------성적 관리 프로그램-------------
            //1.입력 2.전체출력 3.검색 4.수정 5.삭제 6.종료 : 1
            //이름 입력 : 홍길동
            //국어점수 입력 : 70
            //영어점수 입력 : 80
            //수학점수 입력 : 90

            if (in == 1) {
                System.out.println("이름 입력");
                String name = scan.nextLine();
                System.out.println("국어점수 입력");
                int kor = Integer.parseInt(scan.nextLine());
                System.out.println("영어점수 입력");
                int eng = Integer.parseInt(scan.nextLine());
                System.out.println("수학점수 입력");
                int math = Integer.parseInt(scan.nextLine());
                students.add(new Student(name, kor, eng, math));


                //-----------성적 관리 프로그램-------------
                //1.입력 2.전체출력 3.검색 4.수정 5.삭제 6.종료 : 2
                //이름: 홍길동 국어: 70 영어: 80 수학: 90 총점: 240 평균: 80.0

            } else if (in == 2) {
                for (Student s : students){
                    System.out.println(s);
                }

            }
            //-----------성적 관리 프로그램-------------
            //1.입력 2.전체출력 3.검색 4.수정 5.삭제 6.종료 : 3
            //이름 입력 : 홍길동
            //이름: 홍길동 국어: 70 영어: 80 수학: 90 총점: 240 평균: 80.0
            else if (in == 3) {
                System.out.println("이름 입력");
                String find = scan.nextLine();


                for (Student s : students) {
                    if (s.name.equals(find)) {
                        System.out.println(s);
                        break;
                    }
                }
                System.out.println("해당 내역을 찾을 수 없습니다.");
            }
            //-----------성적 관리 프로그램-------------
            //1.입력 2.전체출력 3.검색 4.수정 5.삭제 6.종료 : 4
            //이름 입력 : 홍길동
            //수정될 국어점수 입력 : 70
            //수정될 영어점수 입력 : 80
            //수정될 수학점수 입력 : 90
            else if (in == 4) {
                System.out.println("이름 입력");
                String find = scan.nextLine();
                for (Student s : students) {
                    if (s.name.equals(find)) {
                        System.out.println("수정될 국어점수 입력");
                        s.kor = Integer.parseInt(scan.nextLine());
                        System.out.println("수정될 영어점수 입력");
                        s.eng = Integer.parseInt(scan.nextLine());
                        System.out.println("수정될 수학점수 입력");
                        s.math = Integer.parseInt(scan.nextLine());
                        break;
                    } else {
                        System.out.println("해당 내역을 찾을 수 없습니다.");
                    }
                }


            }

            //-----------성적 관리 프로그램-------------
            //1.입력 2.전체출력 3.검색 4.수정 5.삭제 6.종료 : 5
            //이름 입력 : 홍길동
            //홍길동 삭제됨.
            else if (in == 5) {
                System.out.println("이름 입력");
                String find = scan.nextLine();
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).name.equals(find)) {
                        students.remove(i);
                        System.out.println(find + " 삭제됨");
                        break;
                    }
                }


            }
            //-----------성적 관리 프로그램-------------
            //1.입력 2.전체출력 3.검색 4.수정 5.삭제 6.종료 : 6
            //프로그램 종료되었습니다.
            else if (in == 6) {
                System.out.println("프로그램 종료되었습니다.");
                break;

            }


        }
    }
}