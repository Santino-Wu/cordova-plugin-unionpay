var exec = require('cordova/exec');
var unionpay = {};
unionpay.pay = pay;

module.exports = unionpay;

function pay(tn, successFn, failureFn) {
    exec(successFn, failureFn, 'Unionpay', 'pay', [ tn ]);
}
