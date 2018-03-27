const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');

module.exports = {
    entry: './src/main.js',
    output: {
        filename: '[hash].bundle.js'
    },
    module: {
        rules: [
            {test: /\.css$/,
                use: ExtractTextPlugin.extract({
                    fallback: 'style-loader',
                    use: 'css-loader'
                })
            },
            {
                test: /\.js$/,
                exclude: /node_modules/,
                loader: 'eslint-loader',

            },
        ]
    },
    resolve: {
        alias: {
            'router': './router/',
            'src': '../../',
            'components': '../',
            'services':'../services/',
            'util':'../util/'

        }
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery',
            'window.jQuery': 'jquery',
            'window.$': 'jquery'
        }),
        new HtmlWebpackPlugin({
            template: './src/index.ejs',
        }),
        new UglifyJsPlugin(),
        new ExtractTextPlugin('./[hash]style.css')
    ]
};