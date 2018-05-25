import React from "react";
import ReactDom from "react-dom";

export default class App extends React.Component {
  constructor(props) {
    super(props);
  }




  componentWillMount() {
    fetch("/messages", {
      method: "GET"
    }).then((response) => {
      console.log(response)
    });
  }


  render() {
    return (
      <div>
        sample chat
      </div>
    );
  }
}
