module.exports = {
    outputDir: './../src/main/resources/static',
    devServer: {
        port: 8070,
        proxy: {
            '/api': {
                target: 'http://localhost:8080/api',
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            },
            '/images': {
                target: 'http://localhost:8080/images',
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/images': ''
                }
            },
            '/ws': {
                target: 'http://localhost:8080/ws',
                ws: true,
                tcp: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/ws': ''
                }
            }
        }
    }
};