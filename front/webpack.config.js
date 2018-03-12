const webpack = require('webpack');


module.exports = {
    entry: './src/main.js',
    output: {
        filename: 'bundle.js'
    },
    watch:true,
    devServer:{
        host:'localhost',
        port:8080,
        proxy:{
            '/departments/*':'http://localhost:8082',
            '/employee/*': 'http://localhost:8082'
        }
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: [ 'style-loader', 'css-loader' ]
            }
        ]
    },resolve: {
        alias: {
            'router': './router/',
            'src': '../../',
            'components': '../',
            'services':'../services/'

        }
    },
    plugins: [
        new webpack.ProvidePlugin({
            '$':'jquery',
            $: "jquery",
            jQuery: "jquery",
            'window.jQuery': 'jquery',
            'window.$': 'jquery'
        })
    ]
};