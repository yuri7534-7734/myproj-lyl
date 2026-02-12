import "./App.css";
import * as H from "./7-hooks/Hooks";

function App() {
  return (
    <>
      {/* <H.CounterMemo /> */}
      <H.CounterCallback2 />
    </>
  );
}
//Node.js에서 React F/W를 사용하는 중이다.
//ES모듈(표준) 방식 - 프론트에서 주로 사용.
export default App;
//CommonJS 방식 - 서버에서 주로 사용
