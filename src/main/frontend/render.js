import {React, Game, App, renderToString} from './dist/server.bundle.js';
import {game, app} from './src/Html';

module.exports = function (server) {
    server.get('/game', (req, res) => {
        res.send(
            game(renderToString(<Game />))
        );
    });

    server.get('/app', (req, res) => {
        res.send(
            app(renderToString(<App />))
        );
    });
};
