import * as React from 'react';
import { Button, MenuButton, Toolbar } from 'react-md'
import { BrowserRouter, Route } from 'react-router-dom'

import { EpisodeDisplay } from './components/EpisodeDisplay';
import ListingShows from './components/ListingShows'
import ShowDisplay from './components/ShowDisplay'

import './App.css';

class App extends React.Component {
  render() {
    return (
      <div>
        <BrowserRouter>
          <>
            <Toolbar themed title="Listing TV Shows"
              nav={<Button icon>menu</Button>}
              actions={
                <MenuButton icon id="menu"
                  menuItems={['Settings', 'Help', 'Feedback']}>
                  more_vert
                </MenuButton>
              }
            />

            {/* <CircularProgress centered id="loading"/> */}

            <Route path="/" component={ListingShows} exact />
            <Route path="/show/:showId" component={ShowDisplay} exact />
            <Route path="/show/:showId/season/:seasonNumber/episode/:episodeNumber" component={EpisodeDisplay}/>
            
          </>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;