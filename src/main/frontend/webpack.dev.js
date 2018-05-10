const merge = require('webpack-merge');
const client = require('./webpack.client.js');

module.exports = merge(client, {
    mode: 'development',
    devtool: 'inline-source-map',
    devServer: {
        contentBase: './dist',
        watchOptions: {
            aggregateTimeout: 300,
            poll: 1000
        }
    }
});
