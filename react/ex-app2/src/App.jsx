import { useState } from "react";
import "./App.css";

export const App = () => {
  const styleButton1 = {
    width: " 300px",
    padding: "10px",
    marginBottom: "20px",
    border: "1px solid #979797",
    backgroundColor: "#2623df",
    color: "white",
    cursor: "pointer",
    fontSize: "16px",
    fontWeight: "bold",
  };

  const styleButton2 = {
    width: " 300px",
    padding: "10px",
    marginBottom: "20px",
    backgroundColor: "#df2323",
    border: "1px solid #979797",
    color: "white",
    cursor: "pointer",
    fontSize: "16px",
    fontWeight: "bold",
  };

  const divStyle1 = {
    width: "400px",
    height: "800px",
    display: "flex",
    flexDirection: "column",
    justifyItems: "center",
    alignItems: "center",
    border: "1px solid gray",
    fontWeight: "bold",
  };

  const divStyle2 = {
    display: "flex",
    flexDirection: "column",
    justifyItems: "center",
    alignItems: "center",
    padding: "20px 0",
  };

  const inputStyle1 = {
    backgroundColor: "#dddddd",
    border: "1px solid #979797",
    height: "30px",
    textAlign: "center",
    fontSize: "16px",
    fontWeight: "bold",
  };

  const inputStyle2 = {
    backgroundColor: "#797979",
    border: "1px solid #979797",
    height: "30px",
    textAlign: "center",
    fontSize: "16px",
    fontWeight: "bold",
    color: "white",
  };

  const [num1, setNum1] = useState(0);
  const [num2, setNum2] = useState(0);
  const [result, setResult] = useState(0);

  const add = () => {
    setResult(Number(num1) + Number(num2));
  };

  const sub = () => {
    setResult(Number(num1) - Number(num2));
  };

  const mul = () => {
    setResult(Number(num1) * Number(num2));
  };

  const div = () => {
    setResult(Number(num1) / Number(num2));
  };

  const del = () => {
    setNum1(0);
    setNum2(0);
    setResult(0);
  };

  return (
    <div style={divStyle1}>
      <div style={divStyle2}>
        <h2>React App</h2>
        <h3 style={{ padding: "20px 0 20px 20px" }}>
          계산기 프로그램을 작성해보자
        </h3>
        <div style={{ padding: "0 5px 30px 0" }}>
          <label id="number1" style={{ margin: "30px" }}>
            숫자1
          </label>
          <input
            type="text"
            id="number1"
            value={num1}
            onChange={(e) => {
              setNum1(e.target.value);
            }}
            style={{
              backgroundColor: "#dddddd",
              border: "1px solid #979797",
              height: "30px",
              textAlign: "center",
              fontSize: "16px",
              fontWeight: "bold",
            }}
          />
        </div>
        <div style={{ padding: "0 5px 30px 0" }}>
          <label id="number2" style={{ margin: "30px" }}>
            숫자2
          </label>
          <input
            type="text"
            id="number2"
            value={num2}
            onChange={(e) => {
              setNum2(e.target.value);
            }}
            style={inputStyle1}
          />
        </div>
        <div style={{ padding: "0 5px 30px 0" }}>
          <label id="number3" style={{ margin: "20px" }}>
            연산결과
          </label>
          <input type="text" id="number3" value={result} style={inputStyle2} />
        </div>
      </div>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          marginBottom: "40px",
        }}
      >
        <button style={styleButton1} onClick={add}>
          덧셈
        </button>
        <button style={styleButton1} onClick={sub}>
          뺄셈
        </button>
        <button style={styleButton1} onClick={mul}>
          곱셈
        </button>
        <button style={styleButton1} onClick={div}>
          나눗셈
        </button>
        <button style={styleButton2} onClick={del}>
          지우기
        </button>
      </div>
    </div>
  );
};
