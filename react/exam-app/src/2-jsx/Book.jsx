import React from "react";

function Book(props) { //함수 호출
  return (
    <>
      <h3>책의 이름은 {props.name}</h3>
      <h3>책의 가격은 {props.price}</h3>
    </>
  );
}

export default Book;
