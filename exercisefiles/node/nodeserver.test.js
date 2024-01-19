const http = require('http');
const assert = require('assert');

describe('Node Server', () => {
    const server = require('./nodeserver');

    describe('GET /get', () => {
        it('should return "hello {key}" if key is passed', (done) => {
            http.get('http://localhost:3000/get?key=test', (res) => {
                let data = '';
                res.on('data', (chunk) => {
                    data += chunk;
                });
                res.on('end', () => {
                    assert.strictEqual(data, 'hello test');
                    done();
                });
            });
        });

        it('should return "key not passed" if key is not passed', (done) => {
            http.get('http://localhost:3000/get', (res) => {
                let data = '';
                res.on('data', (chunk) => {
                    data += chunk;
                });
                res.on('end', () => {
                    assert.strictEqual(data, 'key not passed');
                    done();
                });
            });
        });
    });

    describe('GET /DaysBetweenDates', () => {
        it('should return the correct number of days between two valid dates', (done) => {
            http.get('http://localhost:3000/DaysBetweenDates?date1=2019-01-01&date2=2019-01-02', (res) => {
                let data = '';
                res.on('data', (chunk) => {
                    data += chunk;
                });
                res.on('end', () => {
                    assert.strictEqual(data, '1');
                    done();
                });
            });
        });

        it('should return "date1 or date2 not passed" if date1 or date2 is not passed', (done) => {
            http.get('http://localhost:3000/DaysBetweenDates', (res) => {
                let data = '';
                res.on('data', (chunk) => {
                    data += chunk;
                });
                res.on('end', () => {
                    assert.strictEqual(data, 'date1 or date2 not passed');
                    done();
                });
            });
        });

        it('should return "date1 or date2 is not a valid date" if date1 or date2 is not a valid date', (done) => {
            http.get('http://localhost:3000/DaysBetweenDates?date1=invalid&date2=2019-01-02', (res) => {
                let data = '';
                res.on('data', (chunk) => {
                    data += chunk;
                });
                res.on('end', () => {
                    assert.strictEqual(data, 'date1 or date2 is not a valid date');
                    done();
                });
            });
        });

        it('should return "date2 is less than date1" if date2 is less than date1', (done) => {
            http.get('http://localhost:3000/DaysBetweenDates?date1=2019-01-02&date2=2019-01-01', (res) => {
                let data = '';
                res.on('data', (chunk) => {
                    data += chunk;
                });
                res.on('end', () => {
                    assert.strictEqual(data, 'date2 is less than date1');
                    done();
                });
            });
        });
    });

    describe('GET /unknown', () => {
        it('should return "method not supported"', (done) => {
            http.get('http://localhost:3000/unknown', (res) => {
                let data = '';
                res.on('data', (chunk) => {
                    data += chunk;
                });
                res.on('end', () => {
                    assert.strictEqual(data, 'method not supported');
                    done();
                });
            });
        });
    });
});