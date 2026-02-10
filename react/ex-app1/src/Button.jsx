function Button(props) {
  const style = {
    width: "100px",
    height: "50px",
    color: props.color ? props.color : "white",
    fontWeight: "bold",
    fontSize: "18px",
    backgroundColor: props.bgcolor ? props.bgcolor : "black",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    border: "5px solid #555",
    //width : 테두리 + 패딩 + 콘텐츠 크기로 설정
    boxSizing: "border-box",
    borderRadius: "50px",
    cursor: "pointer",
    transition: "0.3s",
  };
  //버튼 클릭 핸들러(이벤트함수, 콜백)
  // function handleColor() {
  //   if (props.onClick) {
  //     props.onClick();
  //   }
  // }

  //클릭 이벤트를 받는 곳은 버튼이다.
  return (
    <div onClick={props.onClick} style={style}>
      {props.title}
    </div>
  );
}

export default Button;
