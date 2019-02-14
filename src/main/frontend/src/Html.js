const fs = require('fs');

require.extensions['.html'] = function (module, filename) {
    module.exports = fs.readFileSync(filename, 'utf-8');
};

const gameIndex = require('../dist/index.game.html');
const appIndex = require('../dist/index.app.html');

const regex = /[\s\S]+<div id="root">/;

module.exports = {
    game: body => html(gameIndex, body),
    app: body => html(appIndex, body)
};

function html(index, body) {
    const left = index.match(regex)[0];
    const right = index.split(regex)[1];
    return `${left}${body}${right}`;
}
