//자바에서는 .java파일이름과 클래스이름이 동일해야 됨.
//리팩토링 -> 참조하는 모든 코드를 검색해서, 일괄 변경.

//인터넷 통신
//1. TCP/IP(Socket통신) : 게임, 채팅에 주로 사용됨.
//        : 속도가 빠르다.
//        : 연결지향(한번 접속하면, 계속 연결이 지속된다.)
//        : 비공개 포트(Port)방식
//        : 공식 포트 80 HTTP, 21 FTP, 443 HTTPS
//2. HTTP통신 : 대부분의 웹앱(web,app)에서 사용
//        : 웹브라우저 서비스 대응 용도
//3. SMTP,POP(이메일),TELNET(원격접속),SSH(보안원격접속)
// TCP : 연결지향, 데이터 체크 기능(체크섬)
// UDP : 비연결지향, 방송용 - 데이터체크 안함.

//클라이언트(서비스를 제공받으면) <-----> 서버(서비스 제공)

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ex48_server {
    public static void main(String[] args) {

        //C언어(제대로된 Lib제공x) -> Java
        //통신,보안 미비           -> JDK를 제공(종합선물세트)
        try {
            //클라이언트의 데이터를 수신한다.
            byte[] bytes = null;
            String message = null;

            ServerSocket serverSocket = new ServerSocket(); //클라이언트 접속 기다림
            //localhost(127.0.0.1) : 내 PC 주소(IP)
            //5001 : ex48_server프로그램의 주소(Port)
            InetSocketAddress address =  //ip주소 + 포트 번호 묶음
                    new InetSocketAddress("127.0.0.1", 5001);
            //클라가 접속하기를 무한대기한다. 동기(Sync)방식.
            serverSocket.bind(address); //5001번 포트 사용 시작, 개통완료 .bind() 포트열기
            while (true) { //서버는 항상 켜져 있어야해서 무한루프 사용 계속 클라 받음.

                System.out.println("서버가 연결을 기다리는 중...");
                //서버의 핵심 클라이언트가 접속할 때까지 멈춰서 기다린다(동기/블로킹)
                //클라 접속 시 Socket 객체 하나 생성 - 클라이언트 1명 전용 통신 통로
                Socket socket = serverSocket.accept();  //동기(Sync), 무한대기중...
//                socket.connect( new InetSocketAddress("192.168.0.98", 5001));
                //.accept() 클라이언트 접속 수락하여 socket 생성


                //누가 접속했는지 확인, 접속한 클라이언트 정보 가져오기
                InetSocketAddress isa =
                        (InetSocketAddress) socket.getRemoteSocketAddress();
                //어떤 컴퓨터가 접속했는지 출력
                System.out.println("서버가 연결을 수락함:" + isa.getHostName());


                //데이터 통로 만들기
                InputStream is = socket.getInputStream();
                bytes = new byte[1024]; //1024바이트만큼 버퍼 생성

                //실제 데이터 받기
                int readByteCount = is.read(bytes);

                //문자열로 복원
                message = new String(bytes, 0, readByteCount, "UTF-8");
                System.out.println("서버가 데이터받기 성공함");
                System.out.println("서버가 받은 데이터 : " + message);

//                서버가 클라이언트에 데이터 보내기
                OutputStream os = socket.getOutputStream();
                int answer = 0;
                String answer1 = null;
                answer = (int)(Math.random() * 100) + 1;
                System.out.println("서버가 정한 랜덤숫자 : " + answer);

                Integer number = Integer.parseInt(message);
                if (answer == number ) {
                    answer1 = "정답입니다.";
                } else if ( answer > number) {
                    answer1 = "서버가 더 큰 수입니다.";
                } else {
                    answer1 = "서버가 더 작은 수입니다.";
                }
                System.out.println("답변 : " + answer1);

                message = answer1;

                bytes = message.getBytes("UTF-8");
                os.write(bytes);
                os.flush(); // 데이터 받았다가 한번에 보내기
                System.out.println("서버가 클라에게 데이터보내기 성공");




                is.close();
//                os.close();

//                socket.close(); //정리(메모리,자원),TCP통신(통신종료 알림),
                //입출력스트림(Stream - 물줄기) 종료





            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}