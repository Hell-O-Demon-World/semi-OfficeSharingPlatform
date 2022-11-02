import React, { useRef, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
const DatePick = () => {
  const calenderRef = useRef();
  const [startDate, setStartDate] = useState(new Date());
  const inputDate = () => {};
  return (
    <DatePicker
      selected={startDate}
      onChange={(date) => setStartDate(date)}
      onCalendarClose={inputDate}
      minDate={new Date()}
      dateFormat="yyyy-MM-dd"
      placeholderText="Select a date between today and 5 days in the future"
      ref={calenderRef}
    />
  );
};

export default DatePick;
