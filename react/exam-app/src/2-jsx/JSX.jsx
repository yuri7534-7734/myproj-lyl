// JSX.jsx

// JSX란?
// JSX는 JavaScript XML의 약자로, React에서 UI를 정의하기 위해 사용되는 문법입니다.
// JSX는 HTML과 비슷해 보이지만 JavaScript 코드 안에서 작성되며 컴파일러에 의해
// 순수한 JavaScript로 변환됩니다.
// React 컴포넌트는 JSX로 UI 구조를 선언하고 이를 렌더링할 수 있습니다.
// 예를 들어, JSX를 사용하면 HTML 구조를 정의하는 것처럼 React 컴포넌트를 정의할 수 있습니다.

// 1. JSX의 주요 특징
// HTML과 비슷한 문법으로 UI를 정의할 수 있음
// 브라우저에서 직접 실행되지 않으며 Babel 같은 도구에 의해 JavaScript로 변환됨
// 동적 데이터 바인딩 가능 ({} 중괄호로 JavaScript 표현식을 삽입)
//
// 2. JSX의 장점
// 가독성: HTML과 비슷하여 코드를 이해하기 쉬움
// 직관적인 동적 데이터 렌더링: 중괄호를 사용해 JavaScript 변수를 쉽게 렌더링할 수 있음
// 컴포넌트 기반 개발: 작은 UI 컴포넌트를 재사용 가능하게 함

import React from "react";

// JSX문법 // 리액트 엘리먼트 객체 // 화면에 그릴 수 있는 값
export const E1 = <h1 className="greeting">Hello JSX!</h1>;
//“E1(JSX)이 Babel에 의해 E2 형태의 자바스크립트 코드로 변환된다”
//바벨(Babel)을 통해서 순수 자바스크립트로 변환된다.

//Babel은 ‘브라우저가 이해 못하는 최신 문법(JSX, 최신 JS)을
//브라우저가 이해할 수 있는 평범한 자바스크립트로 바꿔주는 컴파일러(트랜스파일러)’다.
//Babel 출력 시
export const E2 = React.createElement(
  "h1",
  { className: "greeting" },
  "Hello JSX!",
);

// 자식요소가 있는 JSX
export const E3 = (
  <div>
    <h1>Hello JSX!</h1>
    <p>This is simple text.</p>
  </div>
);

export const E4 = React.createElement(
  "div",
  null,
  React.createElement("h1", null, "Hello JSX!"),
  React.createElement("P", null, "This is simple text."),
);

//조건부 렌더링
const isLoggedIn = true;
export const E5 = (
  <div>{isLoggedIn ? <h1>Welcome!</h1> : <h1>Please Sign in</h1>}</div>
);

//리스트 렌더링
const items = ["apple", "banana", "cherry"];
export const E6 = (
  <ul>
    {items.map((item, index) => {
      return <li key={index}>{item}</li>;
    })}
  </ul>
);

export const E7 = (items) => {
  return (
    <ul>
      {items.items.map((item, index) => {
        return (
          <li key={index}>
            {index + 1}.{item.name} - {item.price}원
          </li>
        );
      })}
    </ul>
  );
};

//조건부 렌더링
const isLoggedIn1 = true;
export const E51 = (
  <div>{isLoggedIn1 ? <h1>Welcome!</h1> : <h1>Please Sign in</h1>}</div>
);

export const E8 = ({ props }) => {
  return <>{props === true ? <h1>Welcome!</h1> : <h1>Please Sign in</h1>}</>;
};
