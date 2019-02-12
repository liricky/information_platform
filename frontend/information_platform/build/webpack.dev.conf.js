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
const reportans = require('./../mock/reportans')
const createpostans = require('./../mock/createpostans')
const newreply = require('./../mock/newreply')
const newpublish = require('./../mock/newpublish')
const best = require('./../mock/best')
const all = require('./../mock/all')
const getuserinfo = require('./../mock/getuserinfo')
const addfriend = require('./../mock/addfriend')
const addblacklist = require('./../mock/addblacklist')
const showmyself = require('./../mock/showmyself')
const setmyself = require('./../mock/setmyself')
const sendappealans = require('./../mock/sendappealans')
const findfriend = require('./../mock/findfriend')
const getpost = require('./../mock/getpost')
const getcomment = require('./../mock/getcomment')
const deletepost = require('./../mock/deletepost')
const deletecomment = require('./../mock/deletecomment')
const helpall = require('./../mock/helpall')
const helpgetfinish = require('./../mock/helpgetfinish')
const helpsendfinish = require('./../mock/helpsendfinish')
const helpongoing = require('./../mock/helpongoing')
const helpsent = require('./../mock/helpsent')
const helpsend = require('./../mock/helpsend')
const helpsentfinish = require('./../mock/helpsentfinish')
const helpclaimfinish = require('./../mock/helpclaimfinish')
const helpclaim = require('./../mock/helpclaim')
const helpcancel = require('./../mock/helpcancel')
const appealget = require('./../mock/appealget')
const editpwd = require('./../mock/editpwd')
const appealgetdetail = require('./../mock/appealgetdetail')

const announcement = require('./../mock/announcement')
const announcement_new = require('./../mock/announcement_new')
const lostafound_board = require('./../mock/lostafound_board')
const lostafound_myboard = require('./../mock/lostafound_myboard')
const lostafound_delete = require('./../mock/lostafound_delete')
const lostafound_publish = require('./../mock/lostafound_publish')
const manage_user = require('./../mock/manage_user')
const manage_user_password = require('./../mock/manage_user_password')
const manage_user_forbid = require('./../mock/manage_user_forbid')
const manage_user_release = require('./../mock/manage_user_release')
const manage_forum = require('./../mock/manage_forum')
const manage_forum_delete = require('./../mock/manage_forum_delete')
const manage_inbox_report = require('./../mock/manage_inbox_report')
const manage_inbox_appeal = require('./../mock/manage_inbox_appeal')
const manage_announcement = require('./../mock/manage_announcement')
const manage_announcement_delete = require('./../mock/manage_announcement_delete')
const manage_announcement_publish = require('./../mock/manage_announcement_publish')
const manage_if = require('./../mock/manage_if')
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
      app.post('/report/create', (req, res) => {
        res.json(reportans)
      })
      app.post('/forum/createpost', (req, res) => {
        res.json(createpostans)
      })
      app.get('/forum/newreply', (req, res) => {
        res.json(newreply)
      })
      app.get('/forum/newpublish', (req, res) => {
        res.json(newpublish)
      })
      app.get('/forum/best', (req, res) => {
        res.json(best)
      })
      app.get('/forum/all', (req, res) => {
        res.json(all)
      })
      app.get('/user/getuserinfo', (req, res) => {
        res.json(getuserinfo)
      })
      app.post('/user/addfriend', (req, res) => {
        res.json(addfriend)
      })
      app.post('/user/addblacklist', (req, res) => {
        res.json(addblacklist)
      })
      app.get('/user/showmyself', (req, res) => {
        res.json(showmyself)
      })
      app.post('/user/setmyself', (req, res) => {
        res.json(setmyself)
      })
      app.post('/appeal/send', (req, res) => {
        res.json(sendappealans)
      })
      app.get('/user/findfriend', (req, res) => {
        res.json(findfriend)
      })
      app.get('/user/getpost', (req, res) => {
        res.json(getpost)
      })
      app.get('/user/getcomment', (req, res) => {
        res.json(getcomment)
      })
      app.post('/user/deletepost', (req, res) => {
        res.json(deletepost)
      })
      app.post('/user/deletecomment', (req, res) => {
        res.json(deletecomment)
      })
      app.get('/help/all', (req, res) => {
        res.json(helpall)
      })
      app.get('/help/ongoing', (req, res) => {
        res.json(helpongoing)
      })
      app.get('/help/sent', (req, res) => {
        res.json(helpsent)
      })
      app.get('/help/sendfinish', (req, res) => {
        res.json(helpsendfinish)
      })
      app.get('/help/getfinish', (req, res) => {
        res.json(helpgetfinish)
      })
      app.post('/help/send', (req, res) => {
        res.json(helpsend)
      })
      app.post('/help/claim', (req, res) => {
        res.json(helpclaim)
      })
      app.post('/help/cancel', (req, res) => {
        res.json(helpcancel)
      })
      app.post('/help/claimfinish', (req, res) => {
        res.json(helpclaimfinish)
      })
      app.post('/help/sentfinish', (req, res) => {
        res.json(helpsentfinish)
      })
      app.get('/appeal/get', (req, res) => {
        res.json(appealget)
      })
      app.post('/editpwd', (req, res) => {
        res.json(editpwd)
      })
      app.get('/appeal/getdetail', (req, res) => {
        res.json(appealgetdetail)
      });

      app.get('/announcement', (req, res) => {
        res.json(announcement)
      })
      app.get('/announcement/new', (req, res) => {
        res.json(announcement_new)
      })
      app.get('/lostafound/board', (req, res) => {
        res.json(lostafound_board)
      })
      app.get('/lostafound/myboard', (req, res) => {
        res.json(lostafound_myboard)
      })
      app.post('/lostafound/delete', (req, res) => {
        res.json(lostafound_delete)
      })
      app.post('/lostafound/publish', (req, res) => {
        res.json(lostafound_publish)
      })
      app.get('/manage/user', (req, res) => {
        res.json(manage_user)
      })
      app.post('/manage/user/password', (req, res) => {
        res.json(manage_user_password)
      })
      app.post('/manage/user/forbid', (req, res) => {
        res.json(manage_user_forbid)
      })
      app.post('/manage/user/release', (req, res) => {
        res.json(manage_user_release)
      })
      app.get('/manage/forum', (req, res) => {
        res.json(manage_forum)
      })
      app.post('/manage/forum/delete', (req, res) => {
        res.json(manage_forum_delete)
      })
      app.get('/manage/inbox/report', (req, res) => {
        res.json(manage_inbox_report)
      })
      app.get('/manage/inbox/appeal', (req, res) => {
        res.json(manage_inbox_appeal)
      })
      app.get('/manage/announcement', (req, res) => {
        res.json(manage_announcement)
      })
      app.post('/manage/announcement/delete', (req, res) => {
        res.json(manage_announcement_delete)
      })
      app.post('/manage/announcement/publish', (req, res) => {
        res.json(manage_announcement_publish)
      })
      app.post('/ifmanage', (req, res) => {
        res.json(manage_if)
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
