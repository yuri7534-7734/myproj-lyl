import { useState } from "react";
import "./App.css";
import Button from "./Button";
import React from "react";

// function Counter() {
//   const [count, setCount] = useState(0);
//   return (
//     <div>
//       <p>총 {count}번 클릭했습니다.</p>
//       <button onClick={() => setCount(count + 1)}>클릭</button>
//     </div>
//   );
// }

function App() {
  const [color, setColor] = useState("red");

  const style = {
    width: "200px",
    height: "350px",
    backgroundColor: color,
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    flexDirection: "column",
    gap: "40px",
  };
  
  return (
    // onClick 이벤트에 대응할 함수형변수(화살표함수, 코드)를 전달한다.
    // 화살표함수 내에서 실행문이 한줄 -> return {}; 생략
    // 하위컴퍼넌트로 상태변경함수를 전달하는 구조이다.
    <div style={style}>
      {/* <Counter /> */}
      <Button
        onClick={() => setColor("#eb1212")}
        title="빨강"
        bgcolor="#eb1212"
      />
      <Button
        onClick={() => setColor("#2e20ec")}
        title="파랑"
        bgcolor="#2e20ec"
      />
      <Button
        onClick={() => setColor("darkgray")}
        title="검정"
        color="white"
        bgcolor="darkgray"
      />
    </div>
  );
}

export default App;
