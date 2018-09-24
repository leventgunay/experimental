
import * as React from 'react';

import { ExpansionList } from 'react-md';

import API from '../lib/API';
import Episode from '../types/Episode';
import { ListingEpisodes } from './ListingEpisodes';

export default class ListingSeasons extends React.Component<any, any> { 
    constructor(props: any) {
        super(props)

        this.state = {
            seasons: []
        }
    }

    componentDidMount() {
        const { show } = this.props

        API.getEpisodesByShow(show).then((episodes: Episode[]) => {
            const seasons: any[] = [];
            episodes.forEach((episode: Episode) => {
                seasons[episode.season] = seasons[episode.season] || []
                seasons[episode.season].push(episode)
            });
            this.setState({
                seasons
            })
        })
    }

    render() {
        const { match } = this.props
        const { seasons } = this.state
        let episodes: any[] = []

        if (seasons.length) {
            episodes = seasons.map((eps: Episode[], ind: number) => 
                <ListingEpisodes episodes={eps} match={match} key={ind} />
            )

            return (
                <ExpansionList
                    animateContent={false}
                    className="md-cell md-cell--12">
                    <> {episodes} </>
                </ExpansionList> 
            )
        } else {
            return 'No season aired.'
        }
    }
}