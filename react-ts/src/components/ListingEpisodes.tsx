import * as React from 'react';
import { Link } from 'react-router-dom';

import { Avatar, Chip, ExpansionPanel } from 'react-md';

import Episode from '../types/Episode';

export const ListingEpisodes = ({ episodes, match }: any) => {
    return (
        <ExpansionPanel 
            defaultExpanded={episodes[0].season < 2}
            className="no-margin"
            label={[
                `Season ${episodes[0].season}`,
                <div className="md-panel-secondary-label" key="secondary">{episodes.length} Episodes</div>
            ]} focused={false} columnWidths={[0,0]} footer={null}>
                {
                    episodes.map((episode: Episode, episodeIndex: number) => (
                        <Link to={{
                            pathname: `${match.url}/season/${episode.season}/episode/${episodeIndex + 1}`,
                            state: {
                                episode
                            }
                        }} key={episode.id}>
                            <Chip label={episode.name}
                                avatar={<Avatar>{episodeIndex + 1}</Avatar>}
                            />
                        </Link>
                    ))
                }
        </ExpansionPanel>
    )
}