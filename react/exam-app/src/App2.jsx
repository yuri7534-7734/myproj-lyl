import * as R from "./2-jsx/JSX";
import Library from "./2-jsx/library";
import * as E from "./2-jsx/Ex";
const price = 1000;
const hour = 16;
const items = [
  //객체
  { id: 1, name: "노트북", price: 8000 },
  { id: 2, name: "스마트폰", price: 4000 },
  { id: 3, name: "맥미니", price: 10000 },
];
const props = true;
function App() { //부모 컴포넌트
  return (
    <>
      {/* {R.E1} 
      {R.E2} */}
      {/* {R.E3}
      {R.E4} */}
      {/* {R.E5} */} {/* 리액트 엘리먼트는 바로 리턴 가능 */}
      {/* 단 JSX 태그 안에서는 { element } 형식으로 반환. */}
      {/* <Library /> 함수형 컴퍼넌트는 태그형식으로 감싸서 반환 */}
      <E.Hello name="홍길동" />
      <E.Sum num1={1} num2={4} />
      <E.DrinkMachine price={price} />
      <E.DrinkMachine1 price="1000" />
      <E.Greeting hour={hour} />
      <R.E7 items={items} />
      <R.E8 props={props} />
    </>
  );
}

export default App;
