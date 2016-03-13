# cordova-plugin-unionpay

Cordova plugin for Unionpay, supports **iOS** and **Android**.

## Installation

```
# Official environment, Cordova
cordova plugin add https://github.com/Santino-Wu/cordova-plugin-unionpay.git --variable UNIONPAYMODE=00

# Develop environment, Cordova
cordova plugin add https://github.com/Santino-Wu/cordova-plugin-unionpay.git --variable UNIONPAYMODE=01

# Official environment, Ionic
ionic plugin add https://github.com/Santino-Wu/cordova-plugin-unionpay.git --variable UNIONPAYMODE=00

# Develop environment, Ionic
ionic plugin add https://github.com/Santino-Wu/cordova-plugin-unionpay.git --variable UNIONPAYMODE=01
```

> The above parameter `--variable UNIONPAYMODE=00` means invoking payment in official environment.
> If you don't have the permission for invoking payment in official environment, set variable `UNIONPAYMODE` as `01`(which is **develop** mode) to invoke payment.

## Usage

``` javascript
/**
 * @param {String} tn - The transaction number which fetched from Unionpay server
 * @param {Function} successFn - The callback function when payment was successful
 * @param {Function} failureFn - The callback function when payment was failed
 * @description
 * Requests payment with transaction number
 */
unionpay.pay(tn, successFn, failureFn);
```

## Example

``` javascript
// Fetches transaction number from your server
var tn = '...';

window.unionpay.pay(tn, function (msg) {
    // Payment was successful, do something
    // ...
}, function (error) {
    // Payment was failed, do something
    // ...
});
```
