// function MyComponent(props) {
//   return (
//     <>
//       <div>{props.myData}</div>
//     </>
//   );
// }

export function FrontComp(props) {
  const liRows = [];
  for (let i = 0; i < props.propData1.length; i++) {
    liRows.push(<li key={i}>{props.propData1[i]}</li>); //중복되지 않는 key Drop 추가
  }
  return (
    <>
      <li>{props.frTitle}</li>
      <ul>{liRows}</ul>
    </>
  );
}
//props 객체를 구조분해하여 필요한 것만 추출
export const BackComp = ({ propData2, baTitle }) => {
  const liRows = [];
  let keyCnt = 0;
  for (let row of propData2) {
    liRows.push(<li key={keyCnt++}>{row}</li>);
  }

  return (
    <>
      <li>{baTitle}</li>
      <ul>{liRows}</ul>
    </>
  );
};
