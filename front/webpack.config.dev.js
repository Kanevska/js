const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    entry: './src/main.js',
    devtool: 'source-map',
    watch:true,
    devServer:{
        host:'localhost',
        port:8080,
        watchOptions: {
            aggregateTimeout: 100,
        },
        proxy:{
            '/back/*': 'http://localhost:8082'
        }
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
        new ExtractTextPlugin('./dist/style.css')
    ]
};