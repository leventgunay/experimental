import * as React from 'react';

import { 
    Avatar, Button, 
    Card, CardText, CardTitle, 
    Chip, FontIcon, 
    Media, MediaOverlay 
} from 'react-md'

import { Link } from 'react-router-dom';

export const ShowCardDisplay = ({ show }: any) => (
    <Card className="md-cell md-cell--4">
        <Media aspectRatio="42-59">
            <Link to={{
                pathname: `/show/${show.id}`,
                state: {
                    show
                }
            }}>
                <img src={show.image.medium} />
                <MediaOverlay>
                    <CardTitle 
                        title={show.name} 
                        subtitle={show.genres.toString()}>
                        {
                            show.rating.average && (
                                <Button className="md-cell--right" icon>favorite
                                    <span>{show.rating.average.toFixed(1)}</span>
                                </Button>
                            )
                        }
                        
                    </CardTitle>
                </MediaOverlay>
            </Link>
        </Media>
        
        <CardText>
            
            <Chip
                label={show.language}
                avatar={<Avatar icon={<FontIcon>language</FontIcon>} suffix="blue-grey" />}
            />

            {
                show.network && (
                    <Chip style={{ float: 'right'}}
                        label={show.network.name}
                        avatar={<Avatar icon={<FontIcon>videocam</FontIcon>} suffix="blue-grey" />}
                    />
                )
            }
            
        </CardText>
    </Card>
)