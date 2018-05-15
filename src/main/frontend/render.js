import {React, Game, renderToString} from './dist/server.bundle.js';
import Html from './src/Html';

module.exports = function (server) {
    server.get('/', (req, res) => {
        res.send(
            Html(renderToString(<Game />))
        );
    });
};
