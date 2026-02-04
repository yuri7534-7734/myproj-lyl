// 연습문제1: 동적 표현식 렌더링
// 목표: props로 받은 이름을 화면에 출력하는 간단한 컴포넌트 연습
// 요구사항
// 1. Namecard 함수형 컴포넌트를 만들고, user 객체의 정보(이름,나이)를 JSX에 넣어서
//   표현한다.
// 2. props는 사용안함.
const user = {
  name: "홍길동",
  age: 30,
};
export function Namecard() {
  return (
    <>
      <h1>이름 : {user.name}</h1>
      <h1>나이 : {user.age}</h1>
    </>
  );
}

//연습문제2: props를 이용해 이름과 나이 출력하기
// 목표: props로 전달받은 이름과 나이를 사용자 정의 컴퍼넌트에 출력한다.
// 요구사항
// 1. Greeting 함수형 컴퍼넌트를 만들고, props로 전달받은 name과 age를
//   출력한다.
// 2. 출력양식 : 안녕하세요, 홍길동님! 당신의 나이는 25살입니다.
export function Greeting(props) {
  return (
    <>
      <h2>안녕하세요, {props.name}님!</h2>
      <h2>당신의 나이는 {props.age}입니다.</h2>
      {/* JSX에서 백틱을 사용한 문자열 보간 */}
      <h3>{`백틱 사용해보는 ${props.name}님!`}</h3>
    </>
  );
}

//연습문제3: 다음의 객체배열의 데이터를 출력하시오.
//목표: props로 전달받은 객체 배열의 데이터를 조작할 수 있다.
//요구사항
// 1. 상품이름 - 가격(원)으로 출력한다.
// 출력 예) 1. 노트북 - 8000원
//          2. 스마트폰 - 4000원

//함수형 컴퍼넌트(화살표함수형태의 함수형 변수)
export const ProductList = () => {
  return (
    <>
      <ul>
        {products.map((item, index) => {
          return (
            <li key={index}>
              {index + 1}.{item.name} - {item.price}원
            </li>
          );
        })}
      </ul>
    </>
  );
};

export const ProductListProps = (props) => {
  return (
    <ul>
      {props.products.map((item) => {
        return (
          <li key={item.id}>
            {item.id}.{item.name} - {item.price}원
          </li>
        );
      })}
    </ul>
  );
};
