/* eslint one-var: ["error", "never"] */
import { resolve } from 'path';
import { js, clientCss } from './rules';
import { NODE_ENV_ANALYZE, NODE_ENV_DEV, NODE_ENV_PROD } from './const';
// import { uglify } from './plugins';
import { BundleAnalyzerPlugin } from 'webpack-bundle-analyzer';
import CleanWebpackPlugin from 'clean-webpack-plugin';
import HtmlWebpackPlugin from 'html-webpack-plugin';
import MiniCssExtractPlugin from 'mini-css-extract-plugin';

const mode = [NODE_ENV_ANALYZE, NODE_ENV_PROD]
  .some(env => env === process.env.NODE_ENV) ? NODE_ENV_PROD : NODE_ENV_DEV;

module.exports = {
  mode,

  entry: {
    client: [resolve(__dirname, '../src/client.js')],
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
    ? [new BundleAnalyzerPlugin({ analyzerHost: '0.0.0.0' })]
    : [
        new CleanWebpackPlugin([resolve(__dirname, '../dist')], {
          root: resolve(__dirname, '../')
        }),
        new HtmlWebpackPlugin({
          title: 'Server side Rendering the Tic Tac Toe tutorial',
          template: resolve(__dirname, '../src/index.html')
        }),
        new MiniCssExtractPlugin({
          filename: '[name].css',
          chunkFilename: '[id].css'
        })
      ],

  module: {
    rules: [
      js,
      clientCss,
    ],
  },

  resolve: {
    modules: [
      'node_modules',
    ],
    extensions: ['.js', '.jsx'],
  },
};
