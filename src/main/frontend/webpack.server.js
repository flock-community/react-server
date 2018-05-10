const merge = require('webpack-merge');
const common = require('./webpack.common.js');
const nodeExternals = require('webpack-node-externals');

module.exports = merge(common, {
    entry: {
        server: './src/Game.js'
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: 'css-loader/locals'
            }
        ]
    },
    output: {
        libraryTarget: 'commonjs2'
    },
    target: 'node',
    externals: [
        nodeExternals()
    ]
});
