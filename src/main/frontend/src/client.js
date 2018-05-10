import React from 'react';
import ReactDOM from 'react-dom';
import Game from './Game';

ReactDOM.hydrate(
    <Game/>,
    document.getElementById('root')
);
