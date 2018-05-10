const webpack = require('webpack');
const merge = require('webpack-merge');
const client = require('./webpack.client.js');
const server = require('./webpack.server.js');
const UglifyJSPlugin = require('uglifyjs-webpack-plugin');

const production = {
    plugins: [
        new UglifyJSPlugin({
            sourceMap: true
        }),
        new webpack.DefinePlugin({
            'process.env.NODE_ENV': JSON.stringify('production')
        })
    ]
};

const hydrate = merge(client, production);
const ssr = merge(server, production);

module.exports = [hydrate, ssr];
