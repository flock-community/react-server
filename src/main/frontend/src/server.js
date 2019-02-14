import React from 'react';
import { renderToString } from 'react-dom/server';
import Game from './Game';
import App from './App';

module.exports = {
    React: React,
    renderToString: renderToString,
    Game: Game,
    App: App
};
