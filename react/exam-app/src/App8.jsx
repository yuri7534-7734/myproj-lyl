import * as E from "./8-event/Event";
import * as E2 from "./8-event/Ex";

function App() {
  return (
    <>
      <E2.Event1 />
      <E2.Event2 />
      <E2.Event22 />
      <E2.Event3 />
    </>
  );
}
//Node.js에서 React F/W를 사용하는 중이다.
//ES모듈(표준) 방식 - 프론트에서 주로 사용.
export default App;
//CommonJS 방식 - 서버에서 주로 사용
