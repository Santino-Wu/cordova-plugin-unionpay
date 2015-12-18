# cordova-plugin-unionpay
Cordova plugin for Unionpay

## Installation

```
# Cordova
cordova plugin add https://github.com/Santino-Wu/cordova-plugin-unionpay.git

# Ionic
ionic plugin add https://github.com/Santino-Wu/cordova-plugin-unionpay.git
```

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

## TODO

* [ ] To support iOS
