//Ex.jsx
import React, { useState, useEffect } from "react";
//서버와 JS(React.js)에서 통신을 해보자.
//1. JS : fetch()함수
//2. Axios : axios()함수

// 설치 : npm i axios

//연습문제1: API 호출 및 데이터 로드
// 목표: 컴포넌트가 마운트될 때 API 호출을 통해 데이터를 가져와
//    화면에 표시하세요.
// 요구사항:
// 1. URL: https://jsonplaceholder.typicode.com/posts
// 2. 상위 10개의 포스트 테이터만 출력하세요.
// 3. useEffect를 사용하여 컴포넌트가 마운트될 때 데이터를 로드하세요.
// 4. 데이터를 로드한 후 상태에 저장하고 화면에 출력하세요.
// 힌트: fetch 또는 axios 모듈 사용 가능합니다.

// {
//   "userId": 1,
//   "id": 1,
//   "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
//   "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
// }

//JS의 fetch함수
// axios : JSON 자동 변환 + 에러 처리 편함 + 요청/응답 인터셉터 가능 + 더 편리한 API
// 마운트 될 때 서버에 데이터를 요청하고, 받은 데이터를 state에 저장해서 화면을 다시 그리게하는 구조.
// 리액트에서 서버 데이터를 가져와 화면에 반영하는 기본 패턴
export const DateFetchJS = () => {
  const [data, setData] = useState([]);

  //async/await 구문 : JS에서 비동기적인 함수를 처리를 할 때 사용하는 키워드(예약어)
  //promise/then 구문 : 구조화된 호출과 응답을 위해 처리하는 예약어.
  //비동기 : 요청 던지고 나중에 결과 오면 처리
  //async : 이 함수는 비동기다 //async는 항상 자동 promise를 반환한다.
  //await : 여기서 결과 올 때까지 잠깐 기다리기
  const fetchData = async () => {
    try {
      //fetch도 promise를 반환함. 나중에 값이 생길 약속객체
      const response = await fetch(
        //fetch : 서버에 요청하고 promise를 반환
        //response : 상태코드 + HTTP통신의 결과값(헤더+바디(data)), 응답객체
        //await promise가 끝날 때까지 잠깐 멈춰서 결과를 그 줄에서 기다렸다가 끝나면 결과를 꺼내서 변수에 넣어준다. //await은 비동기함수 안에서만 가능
        "https://jsonplaceholder.typicode.com/posts",
      );
      //Json 문자열 : { id: 1, title: '...' }
      //Json() : 결과값을 읽는 함수. JSON형태의 문자열을 json KV객체로 변환해주는 함수. { id: 1, title: '...' } => JS 객체(배열)
      const result = await response.json(); //response.json()도 promise를 반환함. JSON 파싱도 비동기 처리.

      console.log(response);

      // promise가 reject(실패)로 안떨어져서 직접 체크해야함
      if (!response.ok) {
        throw new Error("네트워크 응답이 올바르지 않습니다!");
      }

      console.log(result.slice(0, 10)); //result에 뭐가 들었는지 확인
      setData(result.slice(0, 10)); //배열 0번부터 10개만 가져오기
    } catch (error) {
      console.error("데이타 가져오기 실패", error);
    }
  };

  useEffect(() => {
    console.log("DidMount");
    fetchData(); //마운트시에 1회만 호출 //컴포넌트가 처음 화면에 나타났을 때 서버 요청해
  }, []); //처음 마운트될 때만 실행해라

  return (
    <div>
      <h2>fetch 통신으로 가져온 데이터</h2>
      <ul>
        {data.map((post) => {
          return <li key={post.id}>{post.title}</li>;
        })}
      </ul>
    </div>
  );
};
//fetch: 내장이라 가볍지만, JSON 파싱/HTTP 에러 처리 등을 “내가 직접” 챙겨야 함
//axios: 라이브러리지만, 실무에서 자주 필요한 기능(에러처리/설정/인터셉터)이 “기본으로” 좋아서 편함
//     : 2xx 아니면 실패로 간주하기 때문에 404, 500도 reject(실패)된다. resolve가 성공
//Axios의 axios함수
import axios from "axios";
export const DataFetchAxios = () => {
  const [data, setData] = useState([]);

  //의존생배열이 비어있으면, 마운트/언마운트 시 호출, 1번만 호출
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "https://jsonplaceholder.typicode.com/posts",
        );
        //axios함수의 기본응답이 json형태이다.
        console.log(response.data.slice(0, 10));
        setData(response.data.slice(0, 10));
      } catch (error) {
        //throw new Error("데이터 가져오기 오류", error);
        throw new Error(`데이터 가져오기 오류: ${error.message}`);
      }
    };
    fetchData(); //마운트 시 한번만 호출된다.
  }, []);

  return (
    <div>
      <h2>axios 통신으로 가져온 데이터</h2>
      <ul>
        {data.map((post) => {
          return <li key={post.id}>{post.title}</li>;
        })}
      </ul>
    </div>
  );
};

//통신응답으로 반환된 JSON형태의 문자열
// [
// {
//   "userId": 1,
//   "id": 1,
//   "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
//   "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
// },
// ]

//JS 객체
// [
// {
//   userId : 1,
//   id: 1,
//   title: "sunt aut facere repel",
//   body : "quia et suscip"
// }
// ]

//예제 - 윈도우 크기 변경 감지
// 목표: 윈도우의 크기가 변경될 때마다
//     현재 창의 너비를 화면에 표시하세요.
// 요구사항:
// useEffect로 이벤트 리스너를 등록하고
// 창 크기가 변경될 때마다 상태를 업데이트하세요.
// 언마운트 시 이벤트 리스너를 해제하세요.
// 힌트: window.addEventListener와 window.removeEventListener
export function WindowSizeTracker() {
  const [windowWidth, setWindowWidth] = useState(window.innerWidth);
  //JS의 이벤트 리스너를 등록한다 - 마운트시 한번만
  //                     등록해제 - 언마운트시 한번만
  useEffect(() => {
    const handleResize = () => {
      setWindowWidth(window.innerWidth);

      //언마운트시 리스너 제거
      return () => {
        window.removeEventListener("resize", handleResize);
      };
    };

    window.addEventListener("resize", handleResize); //이벤트 등록 방식 설정
  }, []);

  return <h1>현재 창의 너비 : {windowWidth}px</h1>;
}

// 연습예제 - 타이머 기능 (마운트 및 언마운트)
// 목표: 컴포넌트가 마운트되면 1초마다 상태값이 증가하는 타이머를 시작하고, 컴포넌트가 언마운트될 때 타이머를 정리하세요.
// 요구사항:
// useEffect를 사용하여 마운트 시 타이머를 시작하고, 언마운트 시 타이머를 정리하세요.
// 1초마다 상태값을 증가시키세요.
// 힌트: setInterval과 clearInterval 사용
export const Timer = () => {
  const [seconds, setSeconds] = useState(0);

  useEffect(() => {
    //일정 시간마다 함수를 반복 실행하는 브라우저 내장 함수
    // setInterval(실행할함수, 시간(ms)) 1000ms = 1초
    const timer = setInterval(() => {
      //일반 업데이트 : 현재 렌더링 시점의 변수 값
      //    : 비동기상황이나 클로저(화살표함수)내부에서 위험함.
      //      비동기 : 어떤 작업이 끝날 때까지 기다리지 않고 다음 코드를 먼저 실행
      //               오래 걸리는 작업을 "백그라운드"로 보내는 게 비동기

      //setSeconds(seconds + 1);
      setSeconds((prev) => {
        return prev + 1;
        //prev는 이전 상태 값을 가지는 변수명. previous(이전 값)
        //상태(state)를 현재값으로 계산하지 말고 직전 상태를 기준으로 계산하라.
        //useState 상태변경 함수 안에서 화살표함수의 매개변수로 사용 가능함.
        //왜? 필요한가?
        //prev를 사용하면, React가 보장하는 최신 상태 값에 기반해
        //안전하게 상태를 업데이트 할 수 있다.
        //사용예) 카운터, 타이머, 체크박스, 토클
        //        useEffect, setTimeout, setInterval 비동기함수(선언, 실행)에서
        //        이전 상태를 통해서 현재 상태를 변경할 때
      });
    }, 1000);
    //언카운트시 타이머 제거
    return () => clearInterval(timer); //setInterval 멈추기
  }, []);
  return (
    <>
      <h1>타이머: {seconds}초</h1>
    </>
  );
};
