import UglifyJSPlugin from 'uglifyjs-webpack-plugin';

export const uglify = new UglifyJSPlugin({
  test: /\.js($|\?)/i,
  cache: false,
  parallel: true,
  sourceMap: false,
  uglifyOptions: { // https://github.com/mishoo/UglifyJS2/tree/harmony#parse-options
    parse: { // parse options

    },
    compress: { // https://github.com/mishoo/UglifyJS2#compress-options
      drop_console: true,
      warnings: true,
      keep_fnames: true,
    },
    mangle: { // https://github.com/mishoo/UglifyJS2#mangle-properties-options

    },
    output: { // https://github.com/mishoo/UglifyJS2#output-options

    },
    sourceMap: { // https://github.com/mishoo/UglifyJS2#source-map-options

    },
    nameCache: null, // or specify a name cache object
    ie8: false,
    toplevel: false,
    warnings: 'verbose',
  },
});
