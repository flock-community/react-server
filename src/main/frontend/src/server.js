import React from 'react';
import { renderToString } from 'react-dom/server';
import { SheetsRegistry } from 'jss';
import JssProvider from 'react-jss/lib/JssProvider';
import { MuiThemeProvider, createGenerateClassName } from '@material-ui/core/styles';
import theme from './theme';
import Game from './Game';
import App from './App';

module.exports = {
    React,
    renderToString,
    SheetsRegistry,
    JssProvider,
    MuiThemeProvider,
    createGenerateClassName,
    theme,
    Game,
    App
};
