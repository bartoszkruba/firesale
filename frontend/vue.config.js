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
            }
        }
    }
};