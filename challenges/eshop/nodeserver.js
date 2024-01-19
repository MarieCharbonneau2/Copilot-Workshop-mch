const http = require('http');
const { format } = require('path');
const url = require('url');

/**
 * Creates an HTTP server.
 *
 * @param {Function} requestListener - A function that will be called for each HTTP request made to the server.
 * @returns {http.Server} The created HTTP server.
 */
const server = http.createServer((req, res) => {
    const parsedUrl = url.parse(req.url, true);
    const { pathname, query } = parsedUrl;

    if (pathname === '/get') {
        const key = query.key;
        if (!key) {
            res.end('key not passed');
        } else {
            res.end(`hello ${key}`);
        }

    } else {
        res.end('method not supported');
    }
});

server.listen(3000, () => {
    console.log('server is listening on port 3000');
});

