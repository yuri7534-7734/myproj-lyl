//Hooks.jsx
// 리액트 훅(React Hooks)는 함수형 컴퍼넌트에서도
// 클래스 컴퍼넌트의 기능(오버라이트 함수)들을 사용하도록
// 고안된 기능이다.
// useState, useEffect, useContext 등 다양한 훅이 제공된다.

import React, { useMemo, useCallback, useRef, useState } from "react";

//useState, useEffect 훅만으로도 간단한 웹앱을 만들 수 있다.
//useMemo 훅
// 특정 연산의 결과를 메모이제이션(저장)하여
// 불필요한 반복적 계산을 하지 않게 해줌.
// 컴퍼넌트가 렌더링 될 때 마다 매번 계산하는 대신
// 의존성 배열에 명시된 상태값이 변경될 때만 해당 연산을
// 다시 수행한다.
// 용도 :
// 1. 비용이 큰 연산을 최적화 할 때(통신, 큰 데이터 연산..)
// 2. 렌더링 성능을 개선할 때

// 사용자가 입력값의 2배를 계산하는 함수
export const CounterMemo = () => {
  //count가 바뀌면, useMemo가 호출됨.
  const [count, setCount] = useState(0);
  //inputValue가 바뀌면, useMemo는 호출되지 않음.
  const [inputValue, setInputValue] = useState("");

  // count 상태변수가 변경되지 않는 한
  // doubleValue가 재연산되지 않게 한다.
  const doubleValue = useMemo(() => {
    console.log("두배 연산중...");
    return count * 2;
  }, [count]); //값을 기억해서 불필요한 재계산 방지

  // const doubleValue = count * 2;

  return (
    <div>
      <h1>useMemo</h1>
      <h1>입력한 숫자 : {count}</h1>
      <h1>두 배 결과 : {doubleValue}</h1>
      <input
        type="number"
        value={count}
        onChange={(e) => {
          setCount(parseInt(e.target.value));
          //브라우저는 input의 값을 항상 문자열로 반환한다.
        }}
      />
      <h3>
        inputValue는 바뀌어도 <br />
        useMemo 호출안됨
      </h3>
      <input
        type="text"
        value={inputValue}
        onChange={(e) => {
          setInputValue(e.target.value);
        }}
      />
    </div>
  );
};

//useCallback 훅
//      : 메모이제이션된 콜백 함수를 반환하여
//      : 함수가 불필요하게 새로 생성되는 것을 방지하는 훅이다.
//      : useMemo는 값을, useCallback은 함수를 반환한다.
//      : useCallback 함수 참조를 고정
//      : React.memo는 props가 안바뀌면 렌더를 막음.
// 언제쓰는지?
// 자식이 React.memo로 감싸져 있을 때
// React.memo : props가 이전과 같으면 컴포넌트를 다시 렌더하지 않는 기능

// 용도
//   1. 컴퍼넌트가 렌더링될 때 마다 동일한 함수를 다시 생성하는 것을 방지.
//   2. 자식 컴퍼넌트에 함수를 props로 전달할 때, 불필요한 재렌더링을 방지.
export const CounterCallback = () => {
  const [count, setCount] = useState(0);
  const [inputValue, setInputValue] = useState("");

  const doubleValue = useMemo(() => {
    console.log("두배 연산중..");
    return count * 2;
  }, [count]);

  //JS의 이벤트함수를 함수형 변수로 바인딩했을 때,
  // 이벤트객체 e를 가져올 수 있다.
  // const handleChange = (e) => {
  //   console.log(e.target.value);
  // };
  const handleChange = useCallback((e) => {
    // 매 렌더마다 새로운 함수 객체
    console.log("useCallback 메모이제이션");
    setCount(Number(e.target.value));
  }, []); //의존성 배열이 빈배열
  // count를 넣으면, count가 될 때 마다 handleChange 생성
  // 그래서 성능개선을 위해, 빈배열이 좋다.

  return (
    <div>
      <h1>useCallback</h1>
      <h2>입력한 숫자 : {count}</h2>
      <h2>두 배 결과 : {doubleValue}</h2>
      <input type="number" value={count} onChange={handleChange} />
    </div>
  );
};
//
//
//
// useMemo: 계산 결과를 캐시해서(메모이제이션) 불필요한 재계산을 줄임
// React.memo: props가 이전과 같으면 자식 컴포넌트 리렌더를 건너뜀(얕은 비교)
const Child = React.memo(({ func }) => {
  //자식 컴퍼넌트 ( 캐시 )
  // 부모가 리렌더되더라도 Child로 내려가는 props(func)가 동일하면 Child는 리렌더되지 않음
  // useCallback은 func 참조를 고정해 React.memo의 props 비교를 통과시키는 데 도움

  console.log("자식 컴퍼넌트 렌더링");
  return <button onClick={func}>자식 버튼</button>;
});
// const ChildNormal = ({ func }) => {
//   console.log("자식 컴퍼넌트2 렌더링");
//   return <button onClick={func}>자식 버튼</button>;
// };

export function CounterCallback2() {
  //부모 컴퍼넌트가 재렌더링되는 상황
  // 1. props가 변경   //부모의 state setter 호출
  // 2. 상태변수 변경  //부모의 context 값 변경
  // 부모의 부모가 리렌더되며 본인이 다시 렌더될 수 있음
  const [count, setCount] = useState(0);
  const [other, setOther] = useState(0);

  console.log("부모 컴퍼넌트 렌더링");

  // 부모가 리렌더되어도 handleClick 참조를 유지(자식 props 변경 방지)
  const handleClick = useCallback(() => {
    console.log("useCallback 사용!");
    setCount((prev) => prev + 1);
  }, []);

  return (
    <div>
      <p>
        Count:{count} | Other:{other}
      </p>
      {/* 핵심: other로 부모가 리렌더돼도, Child에 내려가는 func 참조가 같으면 Child는 리렌더를 건너뜀 */}
      {/* 부모가 리렌더링 될 때 handleClik 함수 주소가 바뀌지 않게 해서
          memo된 자식이 불필요하게 리렌더링되는 걸 막는다. */}
      <button onClick={() => setOther((prev) => prev + 1)}>
        부모만 렌더링 유발
      </button>
      <Child func={handleClick} />
    </div>
  );
}
