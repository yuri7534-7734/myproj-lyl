import { StrictMode } from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import Library from "./chapter_03/Library.jsx";
import Clock from "./chapter_04/Clock.jsx";

const root = ReactDOM.createRoot(document.getElementById("root"));
setInterval(() => {
  root.render(
    <StrictMode>
      <Clock />
    </StrictMode>,
  );
}, 1000);
