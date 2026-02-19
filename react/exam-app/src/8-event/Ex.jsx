//Ex.jsx
//연습문제1: 마우스 오버와 마우스 아웃 이벤트
// 목표: onMouseEnter와 onMouseLeave 이벤트를 사용하여
//  마우스가 특정 영역에 들어오거나 나갈 때 상태를 변경하는 방법을 학습합니다.
// 요구사항: 다음 조건에 맞는 컴포넌트를 작성하세요.
// 마우스가 박스 위에 올라가면 배경색이 변경됩니다.
// 마우스가 박스를 벗어나면 원래 배경색으로 돌아옵니다.
// 상태로 관리되는 메시지를 화면에 출력하세요.
//    (예: "마우스가 들어왔습니다", "마우스가 나갔습니다")

import React, { useState } from "react";

export const Event1 = () => {
  const [message, setMessage] = useState("박스에 마우스를 올려보세요.");
  const [color, setColor] = useState("white");

  const handleMouse = (id) => {
    setMessage("마우스가 들어왔습니다");
    setColor(id);
  };
  const handleLeave = (id) => {
    setMessage("마우스가 나갔습니다.");
    setColor(id);
  };

  return (
    <div
      style={{
        width: "200px",
        height: "200px",
        border: "1px solid gray",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        borderRadius: "10px",
        backgroundColor: color,
        margin: "30px 0 30px 0",
      }}
      onMouseEnter={() => {
        handleMouse("#1bfab7");
      }}
      onMouseLeave={() => {
        handleLeave("white");
      }}
    >
      {message}
    </div>
  );
};

//연습문제2: 폼 제출 이벤트 처리하기
// 목표: onSubmit 이벤트를 통해 폼 제출을 처리하고 기본 동작을 방지하는 방법
//요구사항 - 다음 조건에 맞는 컴포넌트를 작성하세요.
// 1.사용자 이름과 나이를 입력하는 폼을 만드세요.
// 2.폼이 제출되면 입력값을 콘솔에 출력하고 입력 필드를 비웁니다.
// 3.기본 폼 제출 동작을 방지하세요 (e.preventDefault() 사용).

export const Event2 = () => {
  const [message, setMessage] = useState("폼 제출 예제");
  const [value1, setValue1] = useState("");
  const [value2, setValue2] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault(); //기본동작 먼저 막기
    setMessage("폼 제출 완료!");
    console.log({ name: value1, age: value2 });
    alert(`이름은 ${value1}, 나이는 ${value2}`);
    setValue1("");
    setValue2("");
  };

  return (
    <div>
      <hr />
      <form
        style={{
          width: "200px",
          height: "200px",
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          alignItems: "center",
          marginBottom: "30px",
        }}
        onSubmit={handleSubmit}
      >
        <h3>{message}</h3>
        <input
          type="text"
          value={value1}
          onChange={(e) => {
            setValue1(e.target.value);
          }}
          placeholder="이름"
          style={{ marginBottom: "10px" }}
        />

        <input
          type="text"
          value={value2}
          onChange={(e) => {
            setValue2(e.target.value);
          }}
          placeholder="나이"
          style={{ marginBottom: "10px" }}
        />

        <button type="submit">전송</button>
      </form>
      <hr />
    </div>
  );
};

export function Event22() {
  const [formData, setFormData] = useState({ name: "", age: "" }); //상태2개 -> 10개

  const handleChange = (e) => {
    //Input태그의 name속성과 value속성의 값을 가져온다.
    const { name, value } = e.target;
    setFormData((prev) => {
      // 문자열을 KV객체의 키로 설정하려면, []대괄호 사용한다.
      return { ...prev, [name]: value };
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("이름:", formData.name, "나이:", formData.age);
    alert(`이름:${formData.name} 나이:${formData.age}`);
    setFormData({ name: "", age: "" });
  };

  return (
    <form
      onSubmit={handleSubmit}
      style={{ textAlign: "center", marginTop: "30px" }}
    >
      <h2>폼 제출 예제</h2>
      <input
        type="text"
        name="name"
        placeholder="이름"
        value={formData.name}
        onChange={handleChange}
        style={{ margin: "10px", padding: "5px" }}
      />
      <br />
      <input
        type="number"
        name="age"
        placeholder="나이"
        value={formData.age}
        onChange={handleChange}
        style={{ margin: "10px", padding: "5px" }}
      />
      <br />
      <button type="submit" style={{ padding: "10px 20px" }}>
        제출하기
      </button>
    </form>
  );
}

// 연습문제 3: 입력 필드에서 글자 수 제한하기
// 목표: 입력 필드의 입력값을 상태로 관리하고 글자 수 제한하는 방법을 학습합니다.
// 요구사항: 다음 조건에 맞는 컴포넌트를 작성하세요.
// 1.사용자가 텍스트를 입력할 수 있는 입력 필드가 있습니다.
// 2.입력값은 최대 10자까지만 허용됩니다.
// 3.입력값의 길이에 따라 남은 글자 수를 화면에 표시하세요.
export const Event3 = () => {
  const [value, setValue] = useState(10);
  const style = {
    width: "200px",
    height: "200px",
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    alignItems: "center",
    marginBottom: "30px",
  };

  const handleChange = (e) => {
    if (e.target.value.length <= 10) {
      setValue(e.target.value);
    }
  };

  return (
    <div style={style}>
      <h3>글자 수 제한하는 방법</h3>
      <input
        type="text"
        value={value}
        onChange={handleChange}
        placeholder="최대 10자 입력 제한"
      />
      <h4>남은 글자 수 : {10 - value.length}</h4>
    </div>
  );
};
