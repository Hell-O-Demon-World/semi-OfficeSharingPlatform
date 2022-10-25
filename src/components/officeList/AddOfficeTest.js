import React, { useRef, useState } from "react";

const AddOfficeTest = () => {
  const nameInputRef = useRef();
  const addressInputRef = useRef();
  const optionInputRef = useRef();
  const postcodeInputRef = useRef();

  const formSubmitHandler = (e) => {
    e.preventDefault();
    const sendRequest = async () => {
      try {
        const response = await fetch(
          "https://react-http-673e2-default-rtdb.firebaseio.com/office.json",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              name: nameInputRef.current.value,
              address: addressInputRef.current.value,
              option: optionInputRef.current.value,
              postcode: postcodeInputRef.current.value,
            }),
          }
        );
        const data = await response.json();
        console.log(data);
        if (!response.ok) {
          throw new Error("Someting went wrong");
        }
      } catch (err) {
        alert(err.msg);
      }
    };
    sendRequest();
  };
  return (
    <form onSubmit={formSubmitHandler}>
      <div>
        <label htmlFor="name">name</label>
        <input type="text" id="name" name="name" ref={nameInputRef} />
      </div>
      <div>
        <label htmlFor="address">address</label>
        <input type="text" id="address" name="address" ref={addressInputRef} />
      </div>
      <div>
        <label htmlFor="name">option</label>
        <input type="text" id="option" name="option" ref={optionInputRef} />
      </div>
      <div>
        <label htmlFor="name">postcode</label>
        <input
          type="text"
          id="postcode"
          name="postcode"
          ref={postcodeInputRef}
        />
      </div>
      <button>Add</button>
    </form>
  );
};

export default AddOfficeTest;
