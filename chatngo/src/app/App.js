import React from "react";
import ReactDom from "react-dom";
import SockJsClient from "react-stomp";
import somename from "sillyname";
import ChatBox from './ChatBox'

export default class App extends React.Component {

  wsSourceUrl = window.location.protocol + "//" + window.location.host + "/messages"

  constructor(props) {
    super(props);
    // randomUserId is used to emulate a unique user id for this demo usage
    this.userName = somename();
    this.state = {
      clientConnected: false,
      messages: []
    };
  }

  onMessageReceive = (msg, topic) => {
    this.setState(prevState => ({
      messages: [...prevState.messages, msg]
    }));
  }

  sendMessage = (message) => {
    try {
      this.wsClient.sendMessage("/topic/all", JSON.stringify({
        message,
        from: {
            name: this.userName
        }
      }));
      return true;
    } catch(e) {
      return false;
    }
  }

  componentWillMount() {
    fetch("/messages", {
      method: "GET"
    }).then((response) => {
      this.setState({ messages: response.body || [] });
    });
  }

  render() {
    return (
      <div>
        <ChatBox
            messages={this.state.messages}
            disabled={!this.state.clientConnected}
            onMessage={this.sendMessage}
            user={this.userName}>
        </ChatBox>

        <SockJsClient url={ this.wsSourceUrl } topics={["/topic/all"]}
          onMessage={ this.onMessageReceive } ref={ (client) => { this.wsClient = client }}
          onConnect={ () => { this.setState({ clientConnected: true }) } }
          onDisconnect={ () => { this.setState({ clientConnected: false }) } }
          debug={ true }/>
      </div>
    );
  }
}
