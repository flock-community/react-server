import MiniCssExtractPlugin from 'mini-css-extract-plugin';

export const clientCss = {
    test: /\.css$/,
    use: [
        MiniCssExtractPlugin.loader,
        'css-loader'
    ]
};

export const serverCss = {
    test: /\.css$/,
    use: 'css-loader/locals'
};
