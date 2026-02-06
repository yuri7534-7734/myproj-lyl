import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ex48_client {
    public static void main(String[] args) {
       int count1 = 0;
       while(true){

        Socket socket = new Socket();
        try {

            //접속하려는 서버의 주소( IP, Port )
            socket.connect(new InetSocketAddress("127.0.0.1", 5001));
            System.out.println("서버 접속 성공!");

            //서버에 문자열형으로 데이터를 보내보자
            byte[] bytes = null;
            String message = null;

            //나가는 데이터의 흐름
            OutputStream os = socket.getOutputStream(); //나가는 데이터의 통로

            //랜덤형 숫자 보내기
            int number = (int) (Math.random() * 10) + 1;
            message = String.valueOf(number);
            System.out.println("서버의 숫자 : " + message);


//            message = "Hi, This is client!";


            //인터넷에서 UTF-8(유니코드)를 지원하지 않는
            //기기를 통과해야 되므로, Byte단위로 쪼개서 전송한다.
            bytes = message.getBytes("UTF-8");
            os.write(bytes);
            os.flush(); //버퍼(Buffer:완충기능의 여유메모리)
            //버퍼에 남아있는 데이터를 모두 내보낸다.
            //데이터 손실 방지

            //서버로부터 데이터를 받기
            InputStream is = socket.getInputStream();
            bytes = new byte[1024];
            int readByteCount = is.read(bytes);
            message = new String(bytes, 0, readByteCount, "UTF-8");
            System.out.println(message);
            System.out.println("클라가 서버로부터 데이터수신 성공!");
            System.out.println("클라가 서버로부터 받은 데이터 : " + message);

            if (message.equals("서버가 더 큰 수입니다.")) {
                count1++;
            } else if (message.equals("서버가 더 작은 수입니다.")) {
                count1++;
            } else {
                break;
            }

            is.close();
            os.close();

            os.close(); //마무리 ( 버퍼 비우기, 전송 끝 )
        } catch (Exception e) {
            e.printStackTrace(); //예외가 발생한 위치와 경로 출력
            System.out.println("서버 접속 에러!");
        }

      }
    }

}
