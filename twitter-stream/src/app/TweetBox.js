import React from "react";
import {
    Avatar,
    Divider, FontIcon,
    IconSeparator,
    List, ListItem
} from 'react-md';

import "./TweetBox.css"

export const TweetBox = ({ tweets }) => {
    const user = tweets && tweets.length && tweets[0].user;
    return user && (
        <>
            <IconSeparator iconBefore className="md-cell md-cell--12"
                label={user.name + ' / @' + user.screenName + ' / ID: ' + user.id + ' / Created: ' + user.createdDate} >
                <Avatar src={user.profileImageUrl} role="presentation" />
            </IconSeparator>
            <List> {
                    tweets.map((tweet) => (
                        <ListItem 
                            key={tweet.idStr}
                            primaryText={tweet.text} 
                            secondaryText={'Tweet ID: ' + tweet.idStr + ' / Created: ' + tweet.createdAt}
                            leftIcon={<FontIcon key="chat">chat</FontIcon>}
                        />
                    ))
            } </List>
            <Divider />
        </>
    ) || 'Tweet not available..'
}
