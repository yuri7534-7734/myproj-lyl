// Event.jsx

//리액트 이벤트 개요
//리액트에서 이벤트는 일반적인 HTML DOM 이벤트와 유사하지만 약간의 차이점이 있습니다.
//리액트 이벤트는 기본적으로 synthetic event(합성 이벤트) 시스템을 사용합니다.

// 리액트 이벤트의 특징
// 1. 합성 이벤트(SyntheticEvent):
//    리액트는 이벤트 핸들러에 전달되는 이벤트 객체가 브라우저의
//    기본 이벤트 객체를 래핑한 SyntheticEvent라는 것을 의미합니다.
// 2. 브라우저 간 호환성 보장: 다양한 브라우저 간
//    이벤트 처리를 신경 쓰지 않아도 됩니다.
// 3. DOM 요소에만 사용 가능: 커스텀 컴포넌트에는 직접 이벤트를 전달할 수 없습니다.
//    반드시 DOM 요소에만 onClick, onChange와 같은 이벤트를 사용할 수 있습니다.
// 4. 기본 동작 취소: 이벤트 핸들러에서 preventDefault()
//    또는 stopPropagation()(이벤트 버블현상) 같은 메서드를 사용할 수 있습니다.
//    <input type="submit" />
//     e.preventDefault();

import React, { useState } from "react";

export const Event1 = () => {
  const [message, setMessage] = useState("버튼을 클릭하세요.");
  const handleClick = () => {
    setMessage("버튼이 클릭되었습니다.");
  };

  const style = {
    textAlign: "center",
    marginTop: "50px",
  };

  return (
    <div style={style}>
      <h3>{message}</h3>
      <button onClick={handleClick}>Click</button>
    </div>
  );
};

export const Event2 = () => {
  const [message, setMessage] = useState("버튼을 클릭하세요");
  const handleClick = (e, id) => {
    e.preventDefault(); //이벤트의 기본동작을 방지(submit방지)
    setMessage(`버튼 ${id}가 클릭됨.`);
  };

  return (
    <>
      <form action="http://myserver.com">
        <h3>{message}</h3>
        <button type="submit" onClick={(e) => handleClick(e, 1)}>
          첫번째 버튼
        </button>
        <button type="submit" onClick={(e) => handleClick(e, 2)}>
          두번째 버튼
        </button>
      </form>
    </>
  );
};

//이벤트 객체의 주요 속성:
// e.target: 이벤트가 발생한 요소를 가리킵니다.
// e.currentTarget: 이벤트가 바인딩된 요소를 가리킵니다. 이벤트 핸들러가 실행되는 요소.
// e.preventDefault(): 기본 동작 방지.
// e.stopPropagation(): 이벤트 전파 방지.
//  이벤트 버블링이란,
//   특정 요소에서 이벤트가 발생했을 때
//   그 이벤트가 부모 요소로 전파(버블링)되는 현상을 의미합니다.
//   이벤트 버블링(Event Bubbling)을 중단하는 데 사용됩니다.

//주요 리액트 이벤트들
// 이벤트 이름    설명                                    사용 예제
// onClick        클릭했을 때 발생하는 이벤트                버튼, 링크
// onChange        입력값이 변경될 때 발생하는 이벤트        <input>, <textarea> 등
// onSubmit        폼이 제출될 때 발생하는 이벤트            <form>
// onKeyPress    키보드 키가 눌렸을 때 발생하는 이벤트    <input>, <textarea>
// onMouseEnter    마우스가 요소 위로 올라갈 때 발생하는 이벤트    특정 박스나 이미지
// onFocus        입력 필드에 포커스가 들어갈 때 발생        <input>, <textarea>
// onBlur        포커스가 해제될 때 발생하는 이벤트        <input>, <textarea>

export const Event3 = () => {
  const [message, setMessage] = useState("클릭 전입니다.");

  const handleClick1 = () => {
    console.log("자식 버튼 클릭되었습니다!");
  };

  const handleClick2 = (e) => {
    console.log("자식 버튼 클릭되었습니다!(전파 차단)");
    e.stopPropagation(); //부모 클릭 차단
  };
  const handleParentClick = () => {
    console.log("부모 클릭됨.");
  };

  return (
    <div onClick={handleParentClick}>
      <p>부모 영역(클릭해보세요)</p>
      <hr />
      <p>상태 : {message}</p>
      <button onClick={handleClick1}>일반 버튼(버블링 발생)</button>
      <button onClick={handleClick2}>차단 버튼(stopPropagation)</button>
    </div>
  );
};
//Ex.jsx
//연습문제1: 마우스 오버와 마우스 아웃 이벤트
// 목표: onMouseEnter와 onMouseLeave 이벤트를 사용하여
//  마우스가 특정 영역에 들어오거나 나갈 때 상태를 변경하는 방법을 학습합니다.
// 요구사항: 다음 조건에 맞는 컴포넌트를 작성하세요.
// 마우스가 박스 위에 올라가면 배경색이 변경됩니다.
// 마우스가 박스를 벗어나면 원래 배경색으로 돌아옵니다.
// 상태로 관리되는 메시지를 화면에 출력하세요.
//    (예: "마우스가 들어왔습니다", "마우스가 나갔습니다")

//연습문제2: 폼 제출 이벤트 처리하기
// 목표: onSubmit 이벤트를 통해 폼 제출을 처리하고 기본 동작을 방지하는 방법
//요구사항 - 다음 조건에 맞는 컴포넌트를 작성하세요.
// 1.사용자 이름과 나이를 입력하는 폼을 만드세요.
// 2.폼이 제출되면 입력값을 콘솔에 출력하고 입력 필드를 비웁니다.
// 3.기본 폼 제출 동작을 방지하세요 (e.preventDefault() 사용).

// 연습문제 3: 입력 필드에서 글자 수 제한하기
// 목표: 입력 필드의 입력값을 상태로 관리하고 글자 수 제한하는 방법을 학습합니다.
// 요구사항: 다음 조건에 맞는 컴포넌트를 작성하세요.
// 1.사용자가 텍스트를 입력할 수 있는 입력 필드가 있습니다.
// 2.입력값은 최대 10자까지만 허용됩니다.
// 3.입력값의 길이에 따라 남은 글자 수를 화면에 표시하세요.
