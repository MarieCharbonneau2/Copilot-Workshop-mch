//write npm command line to install mocha
//npm install --global mocha

//command to run this test file
//mocha test.js

const assert = require('assert');
const http = require('http');
const server = require('./nodeserver');

describe('Node Server', () => {
    it('should return "key not passed" if key is not passed', (done) => {
        http
        .get('http://localhost:3000/get' , (res) => {
            let data = '';
            res.on('data', (chunk) => {
                data += chunk;
            });
            res.on('end', () => {
                assert.equal(data, 'key not passed');
                done();
            });
        });
    });

    //add test to check get when key is equal to world
    it('should return "hello world" if key is equal to "world"', (done) => {
        http
        .get('http://localhost:3000/get?key=world' , (res) => {
            let data = '';
            res.on('data', (chunk) => {
                data += chunk;
            });
            res.on('end', () => {
                assert.equal(data, 'hello world');
                done();
            });
        });
    });

    //add test to check validatephoneNumber

    //write test to validate validateSpanishDNI
   

    //write test for returnColorCode red should return code #FF0000


   //write test for daysBetweenDates



});
