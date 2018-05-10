const fs = require('fs');

require.extensions['.html'] = function(module, filename) {
    module.exports = fs.readFileSync(filename, 'utf-8');
};

const html = require('../dist/index.html');

const regex = /[\s\S]+<div id="root">/;
const left = html.match(regex)[0];
const right = html.split(regex)[1];

module.exports = ( body ) => `${left}${body}${right}`;
