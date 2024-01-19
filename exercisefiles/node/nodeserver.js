const http = require('http');
const { format } = require('path');
const url = require('url');
// write a nodejs server that will expose a method call "get" that will return the value of the key passed in the query string
// example: http://localhost:3000/get?key=hello
// if the key is not passed, return "key not passed"
// if the key is passed, return "hello" + key
// if the url has other methods, return "method not supported"
// when server is listening, log "server is listening on port 3000"
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

        // add path DaysBetweenDates that will calculate days between two dates
        // example: http://localhost:3000/DaysBetweenDates?date1=2019-01-01&date2=2019-01-02 should return 1
        // if date1 or date2 is not passed, return "date1 or date2 not passed"
        // if date1 or date2 is not a valid date, return "date1 or date2 is not a valid date"
        // if date2 is less than date1, return "date2 is less than date1"
    } else if (pathname === '/DaysBetweenDates') {
        const date1 = query.date1;
        const date2 = query.date2;
        if (!date1 || !date2) {
            res.end('date1 or date2 not passed');
        } else if (isNaN(Date.parse(date1)) || isNaN(Date.parse(date2))) {
            res.end('date1 or date2 is not a valid date');
        } else if (new Date(date2) < new Date(date1)) {
            res.end('date2 is less than date1');
        } else {
            const daysBetweenDates = Math.round((new Date(date2) - new Date(date1)) / (1000 * 60 * 60 * 24));
            res.end(`${daysBetweenDates}`);
        }

    }
    else if (pathname === '/Validatephonenumber') {
        const phoneNumber = query.phoneNumber;
        const regex = /^\+[1-9]\d{1,14}$/;
        if (!phoneNumber) {
            res.end('phoneNumber not passed');
        } else if (regex.test(phoneNumber)) {
            res.end('valid');
        } else {
            res.end('invalid');
        }
    } else {
        res.end('method not supported');
    }
});

server.listen(3000, () => {
    console.log('server is listening on port 3000');
});

