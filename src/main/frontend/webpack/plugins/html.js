import HtmlWebpackPlugin from 'html-webpack-plugin';
import { resolve } from 'path';

export const html = chunk => new HtmlWebpackPlugin({
    title: 'Server side Rendering the Tic Tac Toe tutorial',
    template: resolve(__dirname, '../../src/index.html'),
    chunks: [chunk],
    filename: `index.${chunk}.html`
});
