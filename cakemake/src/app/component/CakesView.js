import React from "react";
import ReactDom from "react-dom";

import {
    TabsContainer,
    Tabs, Tab,
    FontIcon
  } from 'react-md';

import CakeDisplay from "./CakeDisplay";

import API from '../lib/api';

export default class CakesView extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            cakes: [],
            favorites: []
        };
    }

    componentWillMount() {
        API.getAllCakes().then(({ cakes }) => {
            this.setState({ cakes: cakes || [] });
            this.setState({ favorites: API.getFavoriteCakes() });
        });
    }
    
    render() {
        const { state } = this

        return (
            <TabsContainer
              themed
              labelAndIcon
              panelClassName="md-grid">
              <Tabs mobile centered alignToKeyline inactiveTabClassName="md-text--secondary">
                <Tab icon={<FontIcon>cake</FontIcon>}>
                  {state && state.cakes && state.cakes.length && state.cakes.map((cake) => {
                    return <CakeDisplay cake={cake} favorite={API.isFavorite(cake)} />
                  }) || "No cake found :("} 
                </Tab>
                <Tab icon={<FontIcon forceSize>favorites</FontIcon>}>
                  {state && state.favorites && state.favorites.length && state.favorites.map((cake) => {
                    return <CakeDisplay cake={cake} favorite={true} />
                  }) || "No favorite cake found :("} 
                </Tab>
              </Tabs>
            </TabsContainer>
        )
    }
}