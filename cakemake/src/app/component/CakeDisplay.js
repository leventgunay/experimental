import React from "react";
import ReactDom from "react-dom";
import { Link } from "react-router-dom";

import {
    Card,
    CardText,
    CardTitle,
    Media,
    MediaOverlay,
    Button
} from 'react-md';

import API from '../lib/api';


export default class CakeDisplay extends React.Component {

    toggleFavorite(cake, favorite = false) {
        if (favorite) {
            API.removeFavorite(cake);
        } else {
            API.addFavorite(cake);
        }
    }

    render() {
        const { cake, favorite, match } = this.props;

        if(match && match.params) {
            console.log(match)
        }

        return cake && (
            <Card className="cards__example md-cell md-cell--6 md-cell--8-tablet">
                <Link to={`/cake/${encodeURIComponent(cake.name)}`}>
                    <Media>
                    <img src={cake.image} alt={cake.name} />
                    <MediaOverlay>
                        <CardTitle title={cake.name}>
                            <Button onClick={() => this.toggleFavorite(cake, favorite)}
                                tooltipLabel={ favorite ? "Like" : "Remove"}
                                className="md-cell--right" icon primary={favorite}>favorite</Button>
                        </CardTitle>
                    </MediaOverlay>
                    </Media>
                </Link>
                <CardText>
                <p>
                    {cake.description}
                </p>
                </CardText>
            </Card>
        ) || "No such cake :(";
    }
}