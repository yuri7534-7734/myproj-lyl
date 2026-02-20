import * as C from "./9-conditional-render/ConditionalRender";
import * as E from "./9-conditional-render/Ex";

function App() {
  return (
    <>
      {/* <C.Conditional1 isLoggedIn="false" />
      <C.Conditional1 isLoggedIn="true" /> */}
      <E.Render1 />
      <E.Render2 />
      <E.Render3 />
    </>
  );
}
//Node.js에서 React F/W를 사용하는 중이다.
//ES모듈(표준) 방식 - 프론트에서 주로 사용.
export default App;
//CommonJS 방식 - 서버에서 주로 사용
