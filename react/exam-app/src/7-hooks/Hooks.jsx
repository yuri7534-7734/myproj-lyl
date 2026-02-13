//Hooks.jsx
// 리액트 훅(React Hooks)는 함수형 컴퍼넌트에서도
// 클래스 컴퍼넌트의 기능(오버라이트 함수)들을 사용하도록
// 고안된 기능이다.
// useState, useEffect, useContext 등 다양한 훅이 제공된다.

import React, {
  useMemo,
  useCallback,
  useRef,
  useState,
  useEffect,
} from "react";

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

//useRef 훅
// 개념 : useRef는 리액트에서 변경 가능한 참조 객체를 제공하는 훅이다.
//        useRef로 생성한 객체는 컴퍼넌트가 리렌더링되더라도 값이 유지됨.
//사용용도 :
// 1. DOM 요소에 접근하기 위해( 예 : 포커스, 스크롤 제어 )
// 2. 상태값과 다르게 리렌더링 없이 값 유지가 필요한 경우
//    (예: 이전 값 저장, 타이머 등)
// 3. 성능 최적화에 유리함. 값이 변하더라도 불필요한 리렌더링을 방지함.

export const CounterRef = () => {
  const [count, setCount] = useState(0);
  let ClickCount = 0; //일반변수는 렌더링 되면 초기화되어 버린다.
  //리액트는 변수 변화로는 리렌더링 안하고 state가 바뀌어야 화면이 바뀌기 때문
  const countRef = useRef(0); // {current : 0}형태의 객체
  //countRef는 컴퍼넌트가 리렌더링 될 때, 값이 초기화되지 않는다.
  //countRef는 값이 변경되더라도, 리렌더링을 발생시키지 않는다.
  //count 상태변수는 값이 변경되면, 즉시 화면을 갱신한다.

  const handleClick = () => {
    ClickCount++;
    countRef.current += 1;
    setCount((prev) => {
      return prev + 1;
    });
  };

  console.log("리렌더링됨1: ", count);
  console.log("리렌더링됨2: ", ClickCount); //일반변수는 초기화된다.
  console.log("리렌더링됨3: ", countRef);

  return (
    <div>
      <h2>Counter : {count}</h2>
      <h2>버튼 클릭 횟수 : {countRef.current}</h2>
      <button onClick={handleClick}>증가</button>
    </div>
  );
};

export const CounterRefInput = () => {
  const [count, setCount] = useState(0);
  const clickCountRef = useRef(0);
  const inputRef = useRef(null);

  const handleClick = () => {
    setCount((prev) => prev + 1);
    clickCountRef.current += 1;
    //버튼 클릭시 입력창에 포커스 설정
    if (inputRef.current) {
      //getElementById 대신
      inputRef.current.value = `현재 카운트 : ${count + 1}`;
      inputRef.current.focus();
    }
  };

  return (
    <div>
      <h2>Counter : {count}</h2>
      <h2>버튼 클릭 횟수 : {clickCountRef.current}</h2>
      <input type="text" ref={inputRef} placeholder="텍스트를 입력하세요." />
      <br /> <button onClick={handleClick}>증가 및 입력창 포커스</button>
    </div>
  );
};

export const Timer = () => {
  const [seconds, setSeconds] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setSeconds((prev) => {
        return prev + 1;
      });
    }, 1000);
    return () => {
      clearInterval(timer);
      // (= 컴포넌트 사라지면 타이머도 제거한다.)
      // 컴포넌트가 사라져도 타이머는 계속 돌아간다.
      // 즉, 메모리낭비, 에러 발생 가능, 중복 타이머 생길 수 있음.
    };
  });

  return (
    <>
      <h2>현재 몇초 인지? {seconds}</h2>
    </>
  );
};

/**
 * 핵심: 같은 Counter를 2개 써도 state는 "각 컴포넌트 인스턴스"마다 따로 있다.
 * (서로 영향 없음)
 */
function CounterIndependent({ label }) {
  const [count, setCount] = useState(0);

  return (
    <div style={{ marginBottom: 12 }}>
      <span>{label} : </span>
      <button onClick={() => setCount((prev) => prev + 1)}>
        + (현재 {count})
      </button>
    </div>
  );
}

/**
 *  state를 "공유"하고 싶으면:
 * 1) 부모(App)로 state를 올리고(lifting)
 * 2) 자식에게 props로 내려준다
 */
function CounterShared({ label, count, onIncrease }) {
  return (
    <div style={{ marginBottom: 12 }}>
      <span>{label} : </span>
      <button onClick={onIncrease}>+ (현재 {count})</button>
    </div>
  );
}

export function App2() {
  //  이 state는 부모(App)에 있으니, 아래 자식들이 "공유" 가능
  const [sharedCount, setSharedCount] = useState(0);

  const handleIncreaseShared = () => {
    setSharedCount((prev) => prev + 1);
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>1. 독립 state 예시 (각자 따로 증가)</h2>
      <CounterIndependent label="독립 카운터 A" />
      <CounterIndependent label="독립 카운터 B" />

      <hr />

      <h2>2. 공유 state 예시 (둘 중 하나 눌러도 같이 증가)</h2>
      <CounterShared
        label="공유 카운터 A"
        count={sharedCount}
        onIncrease={handleIncreaseShared}
      />
      <CounterShared
        label="공유 카운터 B"
        count={sharedCount}
        onIncrease={handleIncreaseShared}
      />

      <p style={{ marginTop: 16 }}>
        {/* 위의 "공유 카운터"는 state가 App에 있어서 둘이 같은 값을 봅니다. */}
      </p>
    </div>
  );
}
