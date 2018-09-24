import * as React from 'react';

import { Media, Paper } from 'react-md';
import { RouteComponentProps } from 'react-router';
import ListingSeasons from './ListingSeasons';

export default ({ match, location, history }: RouteComponentProps) => {
    const { state } = location

    if (!state) {
        history.replace('/')
    }

    const { show } = state || {} as any
    return show && (
        <Paper className="md-grid">

            <Media forceAspect={false} style={{ width: 210, height: 295 }}>
                <img src={show.image.medium} style={{ width: 210, height: 295 }}/>
            </Media>
            <section className="md-cell md-cell--5-tablet md-cell--8-desktop">
                <p dangerouslySetInnerHTML={{ __html: show.summary }} />
            </section>
            
            <ListingSeasons show={show} match={match} />

        </Paper>
    ) || 'Not found.'

}