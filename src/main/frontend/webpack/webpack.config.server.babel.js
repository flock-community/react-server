/* eslint one-var: ["error", "never"] */
import webpack from 'webpack';
import {join, resolve} from 'path';
import {files, js, serverCss} from './rules';
import {BundleAnalyzerPlugin} from 'webpack-bundle-analyzer';
import {NODE_ENV_ANALYZE, NODE_ENV_DEV, NODE_ENV_PROD} from './const';
import UglifyJSPlugin from 'uglifyjs-webpack-plugin';

const mode = [NODE_ENV_ANALYZE, NODE_ENV_PROD]
  .some(env => env === process.env.NODE_ENV) ? NODE_ENV_PROD : NODE_ENV_DEV;

const conf = {
  cache: false,
  // Limit the number of parallel processed modules. To enforce more reliable profiling results.
  parallelism: 1,
  target: 'web',

  mode,

  entry: {
    server: [resolve(__dirname, '../src/server.js')],
  },

  context: resolve(__dirname, '../src'),

  output: {
    filename: '[name].bundle.js?[hash]',
    path: resolve(__dirname, '../dist'),
    library: 'render',
    libraryTarget: 'commonjs2',
  },

  // uglify plugin is optional. But doesn't give much extra compression
  // and is prone to errors (learnings from the past).
  plugins: NODE_ENV_ANALYZE === process.env.NODE_ENV
    ? [new BundleAnalyzerPlugin({analyzerHost: '0.0.0.0'})]
    : [
      new UglifyJSPlugin({
        sourceMap: true
      }),
      new webpack.DefinePlugin({
        'process.env.NODE_ENV': JSON.stringify('production')
      })
    ],

  module: {
    rules: [
      js,
      serverCss,
      files,
    ],
  },

  resolve: {
    modules: ['node_modules'],
    extensions: ['.js', '.jsx'],
  },
};

export default conf;
