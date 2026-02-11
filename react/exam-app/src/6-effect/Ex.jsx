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
  }, []);

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
//Axios의 axios함수
