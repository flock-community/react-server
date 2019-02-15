/* eslint one-var: ["error", "never"] */
import {resolve} from 'path';
import {clientCss, files, js} from './rules';
import {NODE_ENV_ANALYZE, NODE_ENV_DEV, NODE_ENV_PROD} from './const';
import {html} from './plugins';
import {BundleAnalyzerPlugin} from 'webpack-bundle-analyzer';
import CleanWebpackPlugin from 'clean-webpack-plugin';
import MiniCssExtractPlugin from 'mini-css-extract-plugin';

const mode = [NODE_ENV_ANALYZE, NODE_ENV_PROD]
  .some(env => env === process.env.NODE_ENV) ? NODE_ENV_PROD : NODE_ENV_DEV;

module.exports = {
  mode,

  entry: {
    game: [resolve(__dirname, '../src/client.game.js')],
    app: [resolve(__dirname, '../src/client.app.js')],
  },

  context: resolve(__dirname, '../src'),

  output: {
    filename: '[name].bundle.js?[chunkhash]',
    path: resolve(__dirname, '../dist'),
    chunkFilename: '[name]-chunk-[chunkhash].js',
    publicPath: '/',
  },

  // uglify plugin is optional. But doesn't give much extra compression
  // and is prone to errors (learnings from the past).
  plugins: NODE_ENV_ANALYZE === process.env.NODE_ENV
    ? [new BundleAnalyzerPlugin({analyzerHost: '0.0.0.0'})]
    : [
      new CleanWebpackPlugin([resolve(__dirname, '../dist')], {
        root: resolve(__dirname, '../')
      }),
      html('game'),
      html('app'),
      new MiniCssExtractPlugin({
        filename: '[name].css',
        chunkFilename: '[id].css'
      })
    ],

  module: {
    rules: [
      js,
      clientCss,
      files,
    ],
  },

  resolve: {
    modules: [
      'node_modules',
    ],
    extensions: ['.js', '.jsx'],
  },
};
