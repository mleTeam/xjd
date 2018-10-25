module.exports = {
  /*
  ** Headers of the page
  */
  head: {
    title: '现金贷后台管理系统',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '现金贷后台管理系统' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },
  /*
  ** Customize the progress bar color
  */
  loading: { color: '#3B8070' },
  /*
  ** Build configuration
  */
  build: {
    /*
    ** Run ESLint on save
    */
    extend (config, { isDev, isClient }) {
      if (isDev && isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    },
    vendor: ['element-ui']
  },
  plugins: [
    { src: '~plugins/element-ui'},{ src:'~/plugins/axios'},{ src: '~/plugins/common' },{ src: '~/plugins/md5' }
  ],
  modules: [
    '@nuxtjs/axios',
    '@nuxtjs/proxy'
  ],
  proxy: [
    [
      '/manage',
      {
        target: 'http://cs.milibanking.com:9090', // api主机
        ws: false
      }
    ]
  ],
  axios: {
    baseURL: 'http://cs.milibanking.com:3000'
  }

  // proxy: {
  //   '/app/': {
  //     target: 'http://csapp.milibanking.com'
  //   }
  // }
}
