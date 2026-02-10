// Ex.jsx
// 1. 문제 1: 단일 Props 전달하기
//   목표: 단일 `props`를 활용하여 간단한 문구를 출력하는 컴포넌트를 작성하세요.
// 요구사항:
// - `Greeting`이라는 자식 컴포넌트를 만드세요.
// - `name`이라는 `props`를 받아 "환영합니다, [이름]님!"이라는 문구를 렌더링합니다.
// - 부모 컴포넌트에서 여러 사람의 이름을 넘겨 출력합니다.
// 출력예:
//    환영합니다, 홍길동님!
//    환영합니다, 김민수님!
//    환영합니다, 이영희님!
function Greeting(props) {
  return (
    <>
      <h2>환영합니다, {props.name}님!</h2>
    </>
  );
}
export function Props1() {
  return (
    <>
      <Greeting name="홍길동" />
      <Greeting name="김민수" />
      <Greeting name="이영희" />
    </>
  );
}
// 2. 문제 2: 여러 Props 전달하기
// 목표: 여러 개의 `props`를 활용하여 사용자의 정보를 출력하는 컴포넌트를 작성하세요.
// 요구사항:
// - `UserCard`라는 자식 컴포넌트를 작성하세요.
// - `name`, `age`, `job`을 `props`로 받아 사용자 정보를 표시합니다.
// - 부모 컴포넌트에서 두 명의 사용자 정보를 전달해 렌더링합니다.
function UserCard({ name, age, job }) {
  return (
    <>
      <h3>이름 : {name}</h3>
      <h3>나이 : {age}</h3>
      <h3>직업 : {job}</h3>
    </>
  );
}

export function Props2() {
  const user = {
    name: "홍길동",
    age: "28",
    job: "개발자",
  };
  return <UserCard {...user} />;
}

// 3. 문제 3: 배열 Props 전달하기
// 목표: 배열 데이터를 `props`로 전달하여 리스트 형태로 출력하는 컴포넌트를 작성하세요.
// 요구사항:
// - `ItemList`라는 자식 컴포넌트를 작성하세요.
// - `items`라는 배열을 `props`로 받아 `<li>` 태그로 각 항목을 렌더링합니다.
// - 부모 컴포넌트에서 두 개의 다른 배열을 전달해 두 개의 목록을 출력합니다.
// 출력예)
//  ItemList1
//  사과
//  바나나
//  딸기
//  ItemList2
//  축구
//  농구
//  야구
function ItemList(props) {
  return (
    <>
      <h3>{props.title}</h3>
      <ul>{props.children}</ul>
    </>
  );
}
export function Props3() {
  return (
    <>
      <ItemList title="과일목록">
        <li>사과</li>
        <li>바나나</li>
        <li>딸기</li>
      </ItemList>
      <ItemList title="운동종목">
        <li>축구</li>
        <li>농구</li>
        <li>야구</li>
      </ItemList>
    </>
  );
}

// 4. 문제 4: 이벤트 Props 전달하기
// 목표: 버튼을 클릭했을 때 이벤트를 처리하는 컴포넌트를 작성하세요.
// 요구사항:
// - `ClickButton`이라는 자식 컴포넌트를 작성하세요.
// - 부모 컴포넌트에서 클릭 시 경고창이 뜨도록 이벤트 핸들러를 전달하세요.
function ClickButton(props) {
  return <button onClick={props.onClick}>버튼을 클릭하세요.</button>;
}

export function Props4() {
  function handleClick() {
    //상태변경 함수로 치환할 수 있다.
    alert("버튼이 클릭되었습니다.");
  }
  return (
    <div>
      <h2>이벤트 핸들링</h2>
      {/* 화살표 함수로 감싼 경우 */}
      <ClickButton
        onClick={() => {
          handleClick();
        }}
      />

      {/* 함수 참조를 그대로 전달한 경우 */}
      <ClickButton onClick={handleClick} />
    </div>
  );
}

// 5. 문제 5: children을 이용한 컴포넌트 구성
// 목표: `children`을 활용하여 컴포넌트 내부에서 콘텐츠를 자유롭게 구성하는 연습을 합니다.
// 요구사항:
// - `InfoCard`라는 자식 컴포넌트를 작성하세요.
// - `title`이라는 `props`와 `children`을 사용하여
//     제목과 본문 콘텐츠를 렌더링합니다.
// - 부모 컴포넌트에서 두 개의 카드를 렌더링하세요.
function InfoCard(props) {
  return (
    <>
      <h2>{props.title}</h2>
      <p>{props.children}</p>
    </>
  );
}
export function Props5() {
  return (
    <div>
      <InfoCard title="첫번째 카드">
        <p>이곳은 첫번째 카드입니다.</p>
      </InfoCard>
      <InfoCard title="두번째 카드">
        <p>이곳은 두번째 카드입니다.</p>
      </InfoCard>
    </div>
  );
}
// export const List = () => {
//   const [text, setText] = useState("");
//   const [list, setList] = useState([]);

//   function addList() {
//     setList([...list, text]);
//     setText("");
//   }

//   return (
//     <div
//       style={{
//         width: "300px",
//         height: "230px",
//         padding: "20px",
//         border: "1px solid #d8d8d8",
//         boxSizing: "border-box",
//       }}
//     >
//       <input
//         type="text"
//         value={text}
//         placeholder="항목을 입력하세요"
//         onChange={(e) => {
//           setText(e.target.value);
//         }}
//       />
//       <button onClick={addList}>항목 추가</button>
//       <ul>
//         {list.map((item, index) => {
//           return <li key={index}>{item}</li>;
//         })}
//       </ul>
//     </div>
//   );
// };
