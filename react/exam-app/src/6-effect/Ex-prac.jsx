//Ex.jsx
import React, { useState, useEffect } from "react";

//연습문제1: API 호출 및 데이터 로드
// 목표: 컴포넌트가 마운트될 때 API 호출을 통해 데이터를 가져와
//    화면에 표시하세요.
// 요구사항:
// 1. URL: https://jsonplaceholder.typicode.com/posts
// 2. 상위 10개의 포스트 테이터만 출력하세요.
// 3. useEffect를 사용하여 컴포넌트가 마운트될 때 데이터를 로드하세요.
// 4. 데이터를 로드한 후 상태에 저장하고 화면에 출력하세요.
// 힌트: fetch 또는 axios 모듈 사용 가능합니다.

export const DateFetchJS = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    console.log("마운트 되었습니다!");

    const fatchdata = async () => {
      try {
        const response = await fetch(
          "https://jsonplaceholder.typicode.com/posts",
        );
        const result = await response.json();

        if (!response.ok) {
          console.log("오류가 있습니다!");
        }
        console.log(result.slice(0, 10));
        setData(result.slice(0, 10));
      } catch (error) {
        console.error("데이타 가져오기 실패", error);
      }
    };
    fatchdata(); //렌더마다 바뀐다
  }, []); 

  return (
    <>
      <h2>fetch 함수를 통한 데이터 가져오기</h2>
      <ul>
        {data.map((post) => {
          return <li key={post.id}>{post.title}</li>;
        })}
      </ul>
    </>
  );
};
