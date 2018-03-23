const webpack = require('webpack');


module.exports = {
    entry: './src/main.js',
    output: {
        filename: 'bundle.js'
    },
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
            {
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
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
        })
    ]
};