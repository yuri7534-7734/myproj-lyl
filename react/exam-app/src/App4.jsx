import React from "react";
//React 8이후로 생략 가능. React함수 직접 사용 시에만 필요.
import "./App.css";
import * as F from "./4-props/props";
import * as E from "./4-props/Ex";

function App() {
  return (
    <>
      {/* <F.Props1 />
      <F.Props2 />
      <F.Props3 />
      <F.Props4 /> */}
      {/* <F.Props5 /> */}
      {/* <E.Props1 />
      <E.Props2 />  */}
      <E.Props4 />
      <E.Props5 />
    </>
  );
}
//Node.js에서 React F/W를 사용하는 중이다.
//ES모듈(표준) 방식 - 프론트에서 주로 사용.
export default App;
//CommonJS 방식 - 서버에서 주로 사용
