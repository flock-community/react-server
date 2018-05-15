require('babel-register')({
  presets: ['es2015', 'react']
});

const express = require('express');
const render = require('./render')

const port = 8080;
const server = express();

render(server);
server.use('/', express.static('./dist'));

server.listen(port);
console.log(`Serving at http://localhost:${port}`);
