import React from "react";
import ReactDom from "react-dom";
import {
    Avatar,
    Button,
    Card,
    CardText,
    TextField,
    Divider,
    Toolbar,
    FontIcon,
    IconSeparator
} from 'react-md';

import "./ChatBox.css"

export default class ChatBox extends React.Component {

    scrollBottom = (el) => {
        if (el) {
            const lastMessage = ReactDom.findDOMNode(el)
            if (lastMessage) {
                lastMessage.scrollIntoView()
            }
        }
    }

    keyPress = (e) => {
        if(e.keyCode == 13 && e.target && e.target.value && e.target.value.length){
            if(this.props.onMessage(e.target.value)) {
                e.target.value = ''
            }
        }
    }

    render() {
        const { messages, disabled, user } = this.props
        return (
        <Card className="md-block-centered">
            <Toolbar colored title="Chat 'N Go"
                nav={<Button icon>chat</Button>} />

            <CardText> {
                messages && messages.map && messages.map((msg, ind) => {
                    const me = user === msg.from.name
                    const float = me ? 'right' : 'left'
                    return <IconSeparator label={msg.message} iconBefore={!me} style={{ marginBottom: 10, textAlign: float }}
                            ref={ me && (messages.length === (ind + 1)) && this.scrollBottom }>
                        <Avatar src={"http://i.pravatar.cc/100?u=" + msg.from.name} role="presentation" style={{ float }} />
                    </IconSeparator>
                })
            } </CardText>

            <Divider />

            <TextField placeholder={disabled ? "Disconnected" : "Message"} block paddedBlock
            maxRows={2} disabled={disabled} onKeyUp={this.keyPress}
            rightIcon={<FontIcon>send</FontIcon>}/>
        </Card>
        )
    }
}
