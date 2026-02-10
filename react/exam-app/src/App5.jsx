import "./App.css";
import * as S from "./5-state/State";
import * as E from "./5-state/Ex";

function App() {
  return (
    <>
      <E.Button />
      <E.Check />
      <E.Count />
      <E.List />
    </>
  );
}
//Node.js에서 React F/W를 사용하는 중이다.
//ES모듈(표준) 방식 - 프론트에서 주로 사용.
export default App;
//CommonJS 방식 - 서버에서 주로 사용
