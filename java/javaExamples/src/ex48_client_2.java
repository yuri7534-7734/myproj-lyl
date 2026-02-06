import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class ex48_client_2 {
    public static void main(String[] args) {
        try {
            int count = 0;

            while (true) {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress("192.168.0.98", 5001));
                System.out.println("서버 접속 성공");

                // 클라이언트 -> 서버 문자열형 데이터 보내기
                Random random = new Random();
                int randomNum = random.nextInt(100);
                String number = String.valueOf(randomNum);
                System.out.println("랜덤값 : " + randomNum); // 랜덤값 생성

                byte[] bytes = null;
                String message = number;
                OutputStream os = socket.getOutputStream();
                message = number;

                bytes = message.getBytes("UTF-8");
                os.write(bytes);
                os.flush();

                // 클라이언트 <- 서버 데이터 받기
                InputStream is = socket.getInputStream();
                bytes = new byte[1024];
                int readByteCount = is.read(bytes); // 서버대기
                message = new String (bytes, 0, readByteCount, "UTF-8"); // 서버대기
                System.out.println(message);


                if (message.equals("서버가 더 큰 수입니다.") ) {
                    count++;
                } else if (message.equals("서버가 더 작은 수입니다.")){
                    count++;
                } else if (message.equals("정답입니다.")) {
                    System.out.println("정답 총 시도횟수 : " + count);
                    break;
                }

                is.close();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("서버 접속 에러");
        }
    }
}