import { useState } from "react";
// 연습문제 1: 버튼을 클릭할 때마다 색상 변경하기
// 설명: 버튼을 클릭할 때마다 배경 색상이 빨강, 초록, 파랑으로
//    순차적으로 변경되도록 만드세요.
const divstyle = {
  width: "300px",
  height: "150px",
  padding: "20px",
  border: "1px solid #d8d8d8",
  boxSizing: "border-box",
};

export const Button = () => {
  const [color, setColor] = useState("red");

  const style = {
    width: "300px",
    height: "150px",
    backgroundColor: color,
    padding: "20px",
    boxSizing: "border-box",
  };

  function handleButton() {
    if (color == "red") {
      setColor("blue");
    } else if (color == "blue") {
      setColor("green");
    } else {
      setColor("red");
    }
  }

  return (
    <div style={style}>
      <h2>현재 색상 : {color}</h2>
      <button onClick={handleButton}>색상변경</button>
    </div>
  );
};

// 연습문제 2: 체크박스 상태 관리하기
// 설명: 체크박스를 클릭하면 "ON" 또는 "OFF"라는 텍스트가
//   화면에 표시되도록 만드세요.
// 힌트: onChange, checked 속성을 이용
export const Check = () => {
  const [type, setType] = useState(false);

  function handleCheck(e) {
    setType(e.target.checked);
  }

  return (
    <div>
      <input type="checkbox" checked={type} onChange={handleCheck} />
      <p>{type ? "On" : "Off"}</p>
    </div>
  );
};

// export const Check = () => {
//   const [type, setType] = useState(false);
//   const style = { width: "20px", height: "20px" };

//   function toggleSet() {
//     setType(!type);
//   }
//   return (
//     <div style={divstyle}>
//       <input style={style} type="checkbox" onClick={toggleSet} />
//       <h3>{type ? "On" : "Off"}</h3>
//     </div>
//   );
// };

// 연습문제 3: 숫자 제한 걸기
// 설명: 숫자를 증가시키되, 숫자가 10 이상이면
//   더 이상 증가하지 않도록 제한하세요.
export const Count = () => {
  const [count, setCount] = useState(0);
  const [title, setTitle] = useState("");
  function handleCount() {
    if (count >= 10) return setTitle("더이상 증가 금지");
    setCount(count + 1);
  }
  return (
    <div
      style={{
        width: "300px",
        height: "200px",
        padding: "20px",
        border: "1px solid #d8d8d8",
        boxSizing: "border-box",
      }}
    >
      <h4>현재 숫자 : {count}</h4>
      <button onClick={handleCount}>증가</button>
      <h2
        style={{
          backgroundColor: "red",
          color: "white",
          display: "flex",
          justifyContent: "center",
        }}
      >
        {title}
      </h2>
    </div>
  );
};

// 연습문제 4: 버튼을 클릭할 때마다 리스트에 항목 추가하기
// 설명: 버튼을 클릭하면 입력 필드의 값을 리스트에 추가하고,
//   추가된 항목들을 화면에 표시하세요.
// 힌트: [], ["aaa", "bbb", "ccc"]
export const List = () => {
  const [text, setText] = useState("");
  const [list, setList] = useState([]);

  function addList() {
    setList([...list, text]);
    setText("");
  }
  return (
    <div
      style={{
        width: "300px",
        height: "200px",
        padding: "20px",
        border: "1px solid #d8d8d8",
        boxSizing: "border-box",
      }}
    >
      <input
        type="text"
        value={text}
        placeholder="항목을 입력하세요"
        onChange={(e) => {
          setText(e.target.value);
        }}
      />
      <button onClick={addList}>항목 추가</button>
      <ul>
        {list.map((item, index) => {
          return <li key={index}>{item}</li>;
        })}
      </ul>
    </div>
  );
};
