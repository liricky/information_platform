'use strict'
const utils = require('./utils')
const webpack = require('webpack')
const config = require('../config')
const merge = require('webpack-merge')
const path = require('path')
const baseWebpackConfig = require('./webpack.base.conf')
const CopyWebpackPlugin = require('copy-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const FriendlyErrorsPlugin = require('friendly-errors-webpack-plugin')
const portfinder = require('portfinder')

const HOST = process.env.HOST
const PORT = process.env.PORT && Number(process.env.PORT)


// mock code
const express = require('express')
const app = express()
const login = require('./../mock/login')
const revmsg = require('./../mock/receivemsg')
const sentmsg = require('./../mock/sentmsg')
const friendmsg = require('./../mock/friendmsg')
const msgdetail = require('./../mock/msgdetail')
const sendmsg = require('./../mock/sendmsg')
const recommendpost = require('./../mock/recommendpost')
const postdetail = require('./../mock/postdetail')
const hotreply = require('./../mock/hotreply')
const reply = require('./../mock/reply')
const likestatus = require('./../mock/likestatus')
const makecommentans = require('./../mock/makecommentans')
const changelikestatus = require('./../mock/changelikestatus')
const commentlikestatus = require('./../mock/commentlikestatus')
const changecommentlikestatus = require('./../mock/changecommentlikestatus')
const routes = express.Router()
app.use('/api',routes)
//mock code


const devWebpackConfig = merge(baseWebpackConfig, {
  module: {
    rules: utils.styleLoaders({ sourceMap: config.dev.cssSourceMap, usePostCSS: true })
  },
  // cheap-module-eval-source-map is faster for development
  devtool: config.dev.devtool,

  // these devServer options should be customized in /config/index.js
  devServer: {
    clientLogLevel: 'warning',
    historyApiFallback: {
      rewrites: [
        { from: /.*/, to: path.posix.join(config.dev.assetsPublicPath, 'index.html') },
      ],
    },
    hot: true,
    contentBase: false, // since we use CopyWebpackPlugin.
    compress: true,
    host: HOST || config.dev.host,
    port: PORT || config.dev.port,
    open: config.dev.autoOpenBrowser,
    overlay: config.dev.errorOverlay
      ? { warnings: false, errors: true }
      : false,
    publicPath: config.dev.assetsPublicPath,
    proxy: config.dev.proxyTable,
    quiet: true, // necessary for FriendlyErrorsPlugin
    watchOptions: {
      poll: config.dev.poll,
    },
    // mock code
    before(app){
      app.post('/login', (req, res) => {
        res.json(login)
      })
      app.get('/message/receive', (req, res) => {
        res.json(revmsg)
      })
      app.get('/message/sent', (req, res) => {
        res.json(sentmsg)
      })
      app.get('/user/getfriend', (req, res) => {
        res.json(friendmsg)
      })
      app.get('/message/detail', (req, res) => {
        res.json(msgdetail)
      })
      app.post('/message/send', (req, res) => {
        res.json(sendmsg)
      })
      app.get('/forum/recommend', (req, res) => {
        res.json(recommendpost)
      })
      app.get('/forum/detail', (req, res) => {
        res.json(postdetail)
      })
      app.get('/forum/gethotcomment', (req, res) => {
        res.json(hotreply)
      })
      app.get('/forum/getcomment', (req, res) => {
        res.json(reply)
      })
      app.get('/forum/getlike', (req, res) => {
        res.json(likestatus)
      })
      app.post('/forum/createcomment', (req, res) => {
        res.json(makecommentans)
      })
      app.post('/forum/changelike', (req, res) => {
        res.json(changelikestatus)
      })
      app.get('/forum/getcommentlike', (req, res) => {
        res.json(commentlikestatus)
      })
      app.post('/forum/changecommentlike', (req, res) => {
        res.json(changecommentlikestatus)
      })
    }
    // mock code
  },
  plugins: [
    new webpack.DefinePlugin({
      'process.env': require('../config/dev.env')
    }),
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NamedModulesPlugin(), // HMR shows correct file names in console on update.
    new webpack.NoEmitOnErrorsPlugin(),
    // https://github.com/ampedandwired/html-webpack-plugin
    new HtmlWebpackPlugin({
      filename: 'index.html',
      template: 'index.html',
      inject: true
    }),
    // copy custom static assets
    new CopyWebpackPlugin([
      {
        from: path.resolve(__dirname, '../static'),
        to: config.dev.assetsSubDirectory,
        ignore: ['.*']
      }
    ])
  ]
})

module.exports = new Promise((resolve, reject) => {
  portfinder.basePort = process.env.PORT || config.dev.port
  portfinder.getPort((err, port) => {
    if (err) {
      reject(err)
    } else {
      // publish the new Port, necessary for e2e tests
      process.env.PORT = port
      // add port to devServer config
      devWebpackConfig.devServer.port = port

      // Add FriendlyErrorsPlugin
      devWebpackConfig.plugins.push(new FriendlyErrorsPlugin({
        compilationSuccessInfo: {
          messages: [`Your application is running here: http://${devWebpackConfig.devServer.host}:${port}`],
        },
        onErrors: config.dev.notifyOnErrors
        ? utils.createNotifierCallback()
        : undefined
      }))

      resolve(devWebpackConfig)
    }
  })
})
