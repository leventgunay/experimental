import * as React from 'react';
import { RouteComponentProps } from 'react-router';

import { Avatar, Chip, FontIcon, Media, Paper } from 'react-md';

export const EpisodeDisplay = ({ location, history }: RouteComponentProps) => {
    const { state } = location

    if (!state) {
        history.replace('/')
    }

    const { episode } = location.state || {} as any

    return episode && (
        <Paper className="md-grid">
            <h3 className="md-cell md-cell--12">{ episode.name }</h3>
            <Media forceAspect={false} style={{ width: 250, height: 140, marginBottom: 10 }}>
                <img src={
                    episode.image && episode.image.medium ||
                    "http://static.tvmaze.com/images/no-img/no-img-landscape-text.png"
                } style={{ width: 250, height: 140 }}/>
            </Media>

            <section className="md-cell md-cell--4-tablet md-cell--8-desktop" style={{ marginTop: 0 }}>
                <p dangerouslySetInnerHTML={{ __html: episode.summary }} />
            </section>

            <section style={{marginTop: '10px'}}
                className="md-cell md-cell--12">
                <Chip label={episode.season} avatar={<Avatar>S</Avatar>} />
                <Chip label={episode.number} avatar={<Avatar>E</Avatar>} />
                <Chip label={episode.airdate} avatar={<Avatar icon={<FontIcon>today</FontIcon>} />} />
                {
                    episode.airtime && (
                        <Chip label={episode.airtime} avatar={<Avatar icon={<FontIcon>access_time</FontIcon>} />} />
                    )
                }
            </section>
        </Paper>
    ) || 'Not found.'
}