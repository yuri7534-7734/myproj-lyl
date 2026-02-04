//import { element2 } from "./1-element/Element";
import { element1, element2 } from "./1-element/Element";
//와일드카드 임포트 방식
import * as E from "./1-element/Element";
import * as R from "./1-element/Ex";
// import * as F from "./1-element/Yuri";
// import { Hello as H1 } from "./1-element/Element";
//export default인 경우
// import HelloH1 from "./1-element/Element";

function App() {
  //리액트 엘리먼트
  //return element2;
  // 리액트 엘리먼트는 => { element1 } 중괄호로 렌더링하고,
  // 리액트 컴퍼넌트(함수형,클래스형) => <Element1 /> 태그형식으로 렌더링
  // const frontData = ["HTML5", "CSS3", "jAVASCRIPT", "jQuery"];
  // const backData = ["Java", "Oracle", "JSP", "Spring Boot"];

  // const props = [
  //   { id : 1, name: "노트북", price : 8000},
  //   { id : 2, name: "스마트폰", price : 4000}
  // ]

  const products = [
    { id: 1, name: "노트북", price: 8000 },
    { id: 2, name: "스마트폰", price: 4000 },
    { id: 3, name: "맥미니", price: 10000 },
  ];

  return (
    <>
      {/* {E.element1}
      {element1}
      {element2}
      {E.element3}
      {E.element4}
      {E.element5}
      <E.Hello name="홍길동" />
      <E.ConfirmDialog /> */}
      <R.Namecard />
      <R.Greeting name="홍길동" age="25" />
      <R.ProductListProps products={products} />

      {/* <F.FrontComp propData1={frontData} frTitle="프론트엔드"></F.FrontComp>
      <F.BackComp propData2={backData} baTitle="백엔드"></F.BackComp> */}
    </>
  );
}
export default App;
