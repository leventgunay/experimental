import React from "react";
import ReactDom from "react-dom";
import somename from "sillyname";

import {
  Card,
  CardText,
  Toolbar,
  Avatar
} from 'react-md';

import CakeDisplay from "./component/CakeDisplay";
import CakesView from "./component/CakesView";

import { BrowserRouter as Router, Route } from "react-router-dom";

export default class App extends React.Component {

  constructor(props) {
    super(props);
    this.userName = somename();
  }

  render() {
    const { userName } = this
    return (
      <div>
        <Card className="md-block-centered">
          <Toolbar colored themed title="All 'bout Cakes!" nav={
            <Avatar src={"http://i.pravatar.cc/100?u=" + userName} role="presentation" />
          }/>
          <CardText>
            <Router>
            <div>
              <Route path="/" component={CakesView} />

              <Route
                path="/cake/:id"
                component={CakeDisplay}
              />  
            </div>
            </Router>
          </CardText>
        </Card>
      </div>
    );
  }
}
