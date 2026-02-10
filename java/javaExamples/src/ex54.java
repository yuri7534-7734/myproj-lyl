import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ex54 {
    public static void main(String[] args) {
        //미팅 약속
        //어디서? 위치 - Gps 좌표(위도, 경도) 오차 10미터 ~ 100미터
        //             행정주소(서울시 중구 충정로 123로 123길 10번지)
        //             구글 Geocoding API(주소 <-> GPS 좌표)
        //언제? 날짜시간
         
        //날짜와 시간 클래스
        // 1. Date : 약한 수순의 기능 제공
        // 2. Calender : 높은 수준의 기능 제공. 내부 달력이 있음.
        //               +- 2억9천만년 범위. 윤년(4년마다 하루더길게 2월 29일)
        //               지구의 공전주기 약 365.2422일
        //양력 달력 - 전세계 ( 단 하나 태국 캘린더)
        //한국시간 = 세계표준시(UTC) + 9시간
        Date date = new Date(); //현재시간
        System.out.println("date = " + date);
        
        //유닉스 타임스탬프(time stamp)
        //1970년 01월 01일 0시 0분 0초 000밀리초부터 시작된
        //밀리초단위 정수
        //예) 시간은 단위가 많다. 년월일시분초밀리초
        long millis = date.getTime();
        System.out.println("millis = " + millis);

        //날짜시간 포맷(형식화)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //                                                        M:month H:24 h:12
        System.out.println(sdf.format(date));
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf2.format(date));

        //캘린더 클래스
        Calendar cal = new GregorianCalendar(Locale.KOREA);
        //Date 객체 가져오기
        System.out.println(cal.getTime());
        //타임스탬프 값 가져오기
        System.out.println(cal.getTimeInMillis());

        //년도
        System.out.println(cal.get(Calendar.YEAR));
        //달 : 1월은 0이다. 12월은 11이다.
        System.out.println(cal.get(Calendar.MONTH));
        //일
        System.out.println(cal.get(Calendar.DAY_OF_MONTH));
        //요일 : 1 일요일 2 월 3 화 ... 7 토
        System.out.println(cal.get(Calendar.DAY_OF_WEEK));

        //이 달의 몇주차인가?
        System.out.println(cal.get(Calendar.WEEK_OF_MONTH));
        //올해의 몇주차인가?
        System.out.println(cal.get(Calendar.WEEK_OF_YEAR));
        //시간 : 24시간제
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        //시간 : 12시간제
        System.out.println(cal.get(Calendar.HOUR));
        //오전오후 0 AM 1 PM
        System.out.println(Calendar.AM_PM);
        //분
        System.out.println(cal.get(Calendar.SECOND));
        //밀리초
        System.out.println(cal.get(Calendar.MILLISECOND));

        //전후 시간 구하기
        // 예) 1달 후 무료회원 마감일 구하기
        // 10시간 후
        cal.add(Calendar.HOUR,10);
        printTime(cal); //2026-02-11 03:19:11

        //10시 전
        cal.add(Calendar.HOUR,-10);
        printTime(cal);
        //3달 후
        cal.add(Calendar.MONTH,3);
        printTime(cal);
        //1년 후
        cal.add(Calendar.YEAR,1);
        printTime(cal);



    }//main

    static void printTime(Calendar cal){
        SimpleDateFormat sdf =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(cal.getTime());
        System.out.println(str);
    }

}
