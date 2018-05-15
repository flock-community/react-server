import React from 'react';
import { renderToString } from 'react-dom/server';
import Game from './Game';

module.exports = {
    React: React,
    renderToString: renderToString,
    Game: Game
};
