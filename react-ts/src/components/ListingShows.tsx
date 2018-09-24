
import * as React from 'react';
import { ShowCardDisplay } from './ShowCardDisplay';

import API from '../lib/API'
import Show from '../types/Show';

export default class ListingShows extends React.Component<any, any> { 
    constructor(props: any) {
        super(props)

        this.state = {
            shows: []
        }
    }

    componentDidMount() {
        API.getShows().then((shows: Show[]) => {
            this.setState({
                shows
            })
        })
    }

    render() {
        const { shows } = this.state
        
        return (
            <div className="md-grid">
               { shows.map((show: Show) => (
                   <ShowCardDisplay show={show} key={show.id}/>
               )) }
            </div>
        )
    }
}